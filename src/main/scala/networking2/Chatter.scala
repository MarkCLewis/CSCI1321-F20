package networking2

import akka.actor.Actor
import java.io.BufferedReader
import java.io.PrintStream
import java.net.Socket

class Chatter(name: String, in: BufferedReader, out: PrintStream, sock: Socket) extends Actor {
  import Chatter._
  def receive = {
    case CheckInput =>
      if (in.ready()) {
        val input = in.readLine()
        if (input == "exit") {
          out.println("Goodbye.")
          sock.close()
          context.stop(self)
        } else sender ! ChatRoom.SendToAll(s"$name: $input")
      }
    case PrintMessage(msg) =>
      out.println(msg)
    case m => println("Unhandled message in Chatter: " + m)
  }
}

object Chatter {
  case object CheckInput
  case class PrintMessage(msg: String)
}