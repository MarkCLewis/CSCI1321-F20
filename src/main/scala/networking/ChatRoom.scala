package networking

import akka.actor.Actor

class ChatRoom extends Actor {
  def receive = {
    case m => println("Unhandled message in ChatRoom: " + m)
  }
}