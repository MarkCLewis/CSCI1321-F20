package mud

import akka.actor.Actor
import adt.SortedLLPQ
import akka.actor.ActorRef

class ActivityManager extends Actor {
  import ActivityManager._
  
  private val pq = new SortedLLPQ[Event](_.when < _.when)
  private var time = 0

  def receive = {
    case ScheduleEvent(delay, who, msg) =>
      pq.enqueue(Event(time + delay, who, msg))
    case ProcessEvents =>
      time += 1
      ??? // Process *all* current events off the priority queue (while loop)
    case m => println("Unhandled message in ActivityManager: " + m)
  }
}

object ActivityManager {
  // Not a message type
  case class Event(when: Int, who: ActorRef, msg: Any)

  // Message types
  case class ScheduleEvent(delay: Int, who: ActorRef, msg: Any)
  case object ProcessEvents
}