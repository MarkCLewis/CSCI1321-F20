package networking2

import akka.actor.Actor

class Chatter extends Actor {
  def receive = {
    case m => println("Unhandled message in Chatter: " + m)
  }
}

object Chatter {
  case object CheckInput
}