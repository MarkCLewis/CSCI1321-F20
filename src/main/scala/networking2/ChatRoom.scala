package networking2

import akka.actor.Actor

class ChatRoom extends Actor {
  import ChatRoom._
  def receive = {
    case CheckAllInput =>
      for (child <- context.children) child ! Chatter.CheckInput
    case m => println("Unhandled message in ChatRoom: " + m)
  }
}

object ChatRoom {
  case object CheckAllInput
}