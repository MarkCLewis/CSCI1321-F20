package mud2

import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration._

object Main extends App {
  val system = ActorSystem("MyMUD2")

  val roomManager = system.actorOf(Props(new RoomManager), "RoomManager")

  // playerManager ! PlayerManager.CreatePlayer("Pat", "keyword", Console.in, Console.out)
  // system.scheduler.scheduleAtFixedRate(0.1.seconds, 0.1.second, playerManager, PlayerManager.CheckAllInput)
}