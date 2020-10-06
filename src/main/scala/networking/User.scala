package networking

import akka.actor.Actor

class User extends Actor {
  def receive = {
    case m => println("Unhandled message in User: " + m)
  }
}