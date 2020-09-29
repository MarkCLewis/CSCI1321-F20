package actors2

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

object ThreeActorCountdown extends App {
  case class StartCounting(n: Int, next: ActorRef, nextNext: ActorRef)
  case class CountDown(n: Int, next: ActorRef)

  class CountingActor extends Actor {
    def receive = {
      case StartCounting(n, next, nextNext) =>
        println(n)
        next ! CountDown(n - 1, nextNext)
      case CountDown(n, next) =>
        if (n >= 0) {
          println(n)
          next ! CountDown(n - 1, sender)
        } else {
          system.terminate()
        }
      case m => println("Got message: " + m)
    }
  }

  val system = ActorSystem("ThreeCounting")

  val actor1 = system.actorOf(Props(new CountingActor), "Actor1")
  val actor2 = system.actorOf(Props(new CountingActor), "Actor2")
  val actor3 = system.actorOf(Props(new CountingActor), "Actor3")

  actor1 ! StartCounting(10, actor2, actor3)
}