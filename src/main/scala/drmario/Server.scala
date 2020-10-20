package drmario

import java.net.ServerSocket
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.concurrent.LinkedBlockingQueue
import scalafx.scene.input.KeyCode

case class ConnectedPlayer(board: Board, sock: Socket, in: ObjectInputStream, out: ObjectOutputStream)

object Server extends App {
  private val playerQueue = new LinkedBlockingQueue[ConnectedPlayer]
  
  val ss = new ServerSocket(8080)
  Future {
    while (true) {
      val sock = ss.accept()
      val in = new ObjectInputStream(sock.getInputStream())
      val out = new ObjectOutputStream(sock.getOutputStream())
      val cp = ConnectedPlayer(new Board, sock, in, out)
      playerQueue.put(cp)
    }
  }
  
  private var players = List[ConnectedPlayer]()
  var lastTime = -1L
  private var sendDelay = 0.0
  val sendInterval = 0.01
  while (true) {
    while (!playerQueue.isEmpty()) {
      val cp = playerQueue.take()
      players ::= cp
    }
    val time = System.nanoTime()
    if (lastTime >= 0) {
      var sendBoards = false
      val delay = (time - lastTime)/1e9
      sendDelay += delay
      if (sendDelay > sendInterval) {
        sendBoards = true
        sendDelay = 0.0
      }
      for (p <- players) {
        //println(p.in.available())
        if (p.in.available() > 0) {
          p.in.readInt() match {
            case PressReleaseInfo.Pressed => 
              println("Got pressed")
              p.in.readInt() match {
                case PressReleaseInfo.Left => p.board.leftPressed()
                case PressReleaseInfo.Right => p.board.rightPressed()
                case PressReleaseInfo.Up => p.board.upPressed()
                case PressReleaseInfo.Down => p.board.downPressed()
                case _ =>
              }
            case PressReleaseInfo.Released =>
              println("Got released")
              p.in.readInt() match {
                case PressReleaseInfo.Left => p.board.leftReleased()
                case PressReleaseInfo.Right => p.board.rightReleased()
                case PressReleaseInfo.Up => p.board.upReleased()
                case PressReleaseInfo.Down => p.board.downReleased()
                case _ =>
              }
            }
        }
        p.board.update(delay)
        if (sendBoards) {
          val pBoard = p.board.makePassable()
          p.out.writeObject(pBoard)
        }
      }
    }
    lastTime = time
  }
}