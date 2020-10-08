package networking2

import akka.actor.Actor
import java.io.BufferedReader
import java.io.PrintStream
import akka.actor.Props
import java.net.Socket

class ChatRoom extends Actor {
  import ChatRoom._
  def receive = {
    case CheckAllInput =>
      for (child <- context.children) child ! Chatter.CheckInput
    case NewChatter(name, in , out, sock) =>
      context.actorOf(Props(new Chatter(name, in, out, sock)))
    case SendToAll(msg) =>
      for (child <- context.children) child ! Chatter.PrintMessage(msg)
    case m => println("Unhandled message in ChatRoom: " + m)
  }
}

object ChatRoom {
  case object CheckAllInput
  case class NewChatter(name: String, in: BufferedReader, out: PrintStream, sock: Socket)
  case class SendToAll(msg: String)
}