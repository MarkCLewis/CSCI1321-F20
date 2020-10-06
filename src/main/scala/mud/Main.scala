package mud

import akka.actor.ActorSystem
import akka.actor.Props

object Main extends App {
  val system = ActorSystem("MyMUD")

  val roomManager = system.actorOf(Props(new RoomManager), "RoomManager")
}