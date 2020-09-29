package mud

import akka.actor.Actor
import scala.io.Source
import akka.actor.Props
import akka.actor.ActorRef

class RoomManager extends Actor {
  val rooms = readRooms()

  def readRooms(): Map[String, ActorRef] = {
    val source = Source.fromFile("map.txt")
    val lines = source.getLines()
    val r = Array.fill(lines.next().toInt)(readRoom(lines)).toMap
    source.close()
    r
  }

  def readRoom(lines: Iterator[String]): (String, ActorRef) = {
    val key = lines.next()
    val name = lines.next()
    val desc = lines.next()
    val items = List.fill(lines.next().toInt)(Item(lines.next(), lines.next()))
    val exits = lines.next().split(",").map(_.toInt)
    key -> context.actorOf(Props(new Room(name, desc, items, exits)), key)
  }

  def receive = {
    case m => println("Unhandled message in Room: " + m)
  }

}