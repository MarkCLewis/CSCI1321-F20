package mud

import akka.actor.ActorRef
import java.io.BufferedReader
import java.io.PrintStream

class Player(name: String, in: BufferedReader, out: PrintStream) {
  private var location: ActorRef = null
}

object Player {
  case class PrintMessage(msg: String)
  case class TakeItem(oitem: Option[Item])
  case class NextRoom(oroom: Option[ActorRef])
}