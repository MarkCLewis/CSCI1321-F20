package mud

import akka.actor.ActorSystem
import akka.actor.Props

object Main extends App {
  val system = ActorSystem("MyMUD")

  val roomManager = system.actorOf(Props(new RoomManager), "RoomManager")

  // system.scheduler.scheduleAtFixedRate(0.1.seconds, 0.1.second, playerManager, PlayerManager.CheckAllInput)
  
  // playerManager ! PlayerManager.NewPlayer(name, keyword, Console.in, Console.out)
}
