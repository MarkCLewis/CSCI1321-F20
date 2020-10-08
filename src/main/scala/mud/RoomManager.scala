package mud

import akka.actor.Actor
import scala.io.Source
import akka.actor.Props
import akka.actor.ActorRef

class RoomManager extends Actor {
  val rooms = readRooms()
  println(rooms)
  for (child <- context.children) child ! Room.LinkExits(rooms)

  def readRooms(): Map[String, ActorRef] = {
    val roomXML = xml.XML.loadFile("map.xml")
    val r = (roomXML \ "room").map(readRoom).toMap
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
      player ! Player.NextRoom(rooms.get(keyword))
    case m => println("Unhandled message in RoomManager: " + m)
  }

}

object RoomManager {
  case class AddPlayerToRoom(player: ActorRef, keyword: String)
}