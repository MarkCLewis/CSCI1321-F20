package puyo

import java.net.ServerSocket
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

case class ConnectedPlayer(board: Board, sock: Socket, in: ObjectInputStream, out: ObjectOutputStream)

object Server extends App {
  private val queue = new java.util.concurrent.LinkedBlockingQueue[ConnectedPlayer]()
  
  val ss = new ServerSocket(8080)
  Future {
    while (true) {
      val sock = ss.accept()
      val in = new ObjectInputStream(sock.getInputStream())
      val out = new ObjectOutputStream(sock.getOutputStream())
      queue.put(ConnectedPlayer(new Board, sock, in, out))
    }
  }
  
  private var connections = List[ConnectedPlayer]()
  var lastTime = -1L
  private var sendDelay = 0.0
  val sendInterval = 0.01
  while (true) {
    while (!queue.isEmpty()) {
      connections ::= queue.take()
    }
    val time = System.nanoTime()
    if (lastTime >= 0) {
      var sendBoards = false
      val delay = (time - lastTime) / 1e9
      sendDelay += delay
      if (sendDelay > sendInterval) {
        sendBoards = true
        sendDelay = 0.0
      }
      for (connection <- connections) {
        if (connection.in.available() > 0) {
          connection.in.readInt() match {
            case PressReleaseInfo.Pressed =>
              connection.in.readInt() match {
                case PressReleaseInfo.Left => connection.board.leftPressed()
                case PressReleaseInfo.Right => connection.board.rightPressed()
                case PressReleaseInfo.Up => connection.board.upPressed()
                case PressReleaseInfo.Down => connection.board.downPressed()
                case _ =>
              }
            case PressReleaseInfo.Released =>
              connection.in.readInt() match {
                case PressReleaseInfo.Left => connection.board.leftReleased()
                case PressReleaseInfo.Right => connection.board.rightReleased()
                case PressReleaseInfo.Up => connection.board.upReleased()
                case PressReleaseInfo.Down => connection.board.downReleased()
                case _ =>
              }
          }
        }
        connection.board.update(delay)
        if (sendBoards) {
          val pb = connection.board.makePassable()
          connection.out.writeObject(pb)
        }
      }
    }
    lastTime = time
  }
}
