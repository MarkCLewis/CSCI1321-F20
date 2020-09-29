package mud

import scala.io.Source
import akka.actor.Actor

class Room(name: String, desc: String, private var items: List[Item], exits: Array[Int]) extends Actor {
    def receive = {
        case m => println("Unhandled message in Room: " + m)
    }

    def description(): String = ???

    def getExit(dir: Int): Option[Room] = { ???
        //if (exits(dir) == -1) None else Some(Room.rooms(exits(dir)))
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
    
}