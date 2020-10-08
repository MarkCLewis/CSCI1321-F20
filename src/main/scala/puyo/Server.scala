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
  private var connections = List[ConnectedPlayer]()

  val ss = new ServerSocket(8080)
  Future {
    while (true) {
      val sock = ss.accept()
      val in = new ObjectInputStream(sock.getInputStream())
      val out = new ObjectOutputStream(sock.getOutputStream())
      queue.put(ConnectedPlayer(new Board, sock, in, out))
    }
  }

  var lastTime = -1L
  while (true) {
    while (!queue.isEmpty()) {
      connections ::= queue.take()
    }
    val time = System.nanoTime()
    if (lastTime >= 0) {
      val delay = (time - lastTime) / 1e9
      for (connection <- connections) {
        connection.board.update(delay)
      }
      // send passable board to the client
    }
    lastTime = time
  }
}
