package mud

import scala.io.Source
import akka.actor.Actor
import akka.actor.ActorRef

class Room(name: String, desc: String, private var items: List[Item], exitKeys: Array[String]) extends Actor {
    private var exits: Array[Option[ActorRef]] = null

    import Room._
    def receive = {
        case LinkExits(rooms) => exits = exitKeys.map(key => rooms.get(key))
        case GetDescription => sender ! Player.PrintMessage(description())
        case GetItem(itemName) => sender ! Player.TakeItem(getItem(itemName))
        case m => println("Unhandled message in Room: " + m)
    }

    def description(): String = ???

    def getExit(dir: Int): Option[ActorRef] = {
        exits(dir)
    }
    
    def getItem(itemName: String): Option[Item] = {
        items.find(_.name == itemName) match {
            case Some(item) =>
                items = ???
                Some(item)
            case None => None
        }
    }
    
    def dropItem(item: Item): Unit = ???

}

object Room {
    case class LinkExits(rooms: Map[String, ActorRef])
    case object GetDescription
    case class GetItem(itemName: String)
}