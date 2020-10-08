package mud2

import akka.actor.Actor
import akka.actor.ActorRef
import scala.io.Source
import akka.actor.Props

class RoomManager extends Actor {
    val rooms = readRooms()
    println(rooms)
    for (child <- context.children) child ! Room.LinkExits(rooms)

    def readRooms(): Map[String, ActorRef] = {
        val xmlData = xml.XML.loadFile("map2.xml")
        val r = (xmlData \ "room").map(n => readRoom(n)).toMap
        r
    }

    def readRoom(node: xml.Node): (String, ActorRef) = {
        val key = (node \ "@keyword").text
        val name = (node \ "@name").text
        val desc = (node \ "description").text.trim
        val items = (node \ "item").map(n => Item((n \ "@name").text, n.text)).toList
        val exits = (node \ "exits").text.split(",")
        key -> context.actorOf(Props(new Room(name, desc, items, exits)), key)
    }

    import RoomManager._
    def receive = {
        case AddPlayerToRoom(player, keyword) =>
            player ! Player.SetLocation(rooms.get(keyword))
        case m => println("Unhandled message in RoomManager: " + m)
    }
}

object RoomManager {
    case class AddPlayerToRoom(player: ActorRef, keyword: String)
}