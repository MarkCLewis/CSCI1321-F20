package actors2

import akka.actor.Actor

class Particle extends Actor {
  import Particle._
  def receive = {
    case MoveTo(x, y) =>
      var nx = x + util.Random.nextInt(3) - 1
      var ny = y + util.Random.nextInt(2)
      while (nx == x && ny == y) {
        nx = x + util.Random.nextInt(3) - 1
        ny = y + util.Random.nextInt(2)
      }
      sender ! Jar.CheckLocation(nx, ny, x, y)
    case m => println("Unhandled message in Particle: " + m)
  }
}

object Particle {
  case class MoveTo(x: Int, y: Int)
}