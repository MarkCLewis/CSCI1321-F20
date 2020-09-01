package mud

import scala.io.Source

class Room(name: String, desc: String, private var items: List[Item], exits: Array[Int]) {
    def description(): String = ???

    def getExit(dir: Int): Option[Room] = {
        if (exits(dir) == -1) None else Some(Room.rooms(exits(dir)))
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
    val rooms = readRooms()

    def readRooms(): Array[Room] = {
        val source = Source.fromFile("map.txt")
        val lines = source.getLines()
        val r = Array.fill(lines.next().toInt)(readRoom(lines))
        source.close()
        r
    }

    def readRoom(lines: Iterator[String]): Room = {
        val name = lines.next()
        val desc = lines.next()
        val items = List.fill(lines.next().toInt)(Item(lines.next(), lines.next()))
        val exits = lines.next().split(",").map(_.toInt)
        new Room(name, desc, items, exits)
    }
}