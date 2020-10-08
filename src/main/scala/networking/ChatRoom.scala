package networking

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import akka.actor.Props

class ChatRoom extends Actor {
  import ChatRoom._
  def receive = {
    case NewUser(name, in, out) =>
      context.actorOf(Props(new User(name, in, out)))
    case CheckAllInput =>
      for (child <- context.children) child ! User.CheckInput
    case DistributeMessage(msg) =>
      for (child <- context.children) child ! User.PrintMessage(msg)
    case m => println("Unhandled message in ChatRoom: " + m)
  }
}

object ChatRoom {
  case class NewUser(name: String, in: BufferedReader, out: PrintStream)
  case object CheckAllInput
  case class DistributeMessage(message: String)
}