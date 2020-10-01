package actors2

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import akka.actor.Props
import scalafx.scene.paint.Color
import scala.concurrent.java8.FuturesConvertersImpl.P

class Jar(img: WritableImage) extends Actor {
  val writer = img.pixelWriter
  val reader = img.pixelReader.get

  for(x <- 0 until img.width().toInt; y <- 0 until img.height().toInt) writer.setColor(x, y, Color.White)
  for(x <- 0 until img.width().toInt) writer.setColor(x, img.height().toInt - 1, Color.Black)

  for (_ <- 1 to 8) {
    context.actorOf(Props(new Particle)) ! Particle.MoveTo(util.Random.nextInt(img.width().toInt), 0)
  }
  import Jar._
  def receive = {
    case CheckLocation(x, y, oldx, oldy) =>
      if (x < 0 || x >= img.width()) sender ! Particle.MoveTo(oldx, oldy)
      else if (reader.getColor(x, y) == Color.Black) {
        writer.setColor(oldx, oldy, Color.Black)
        sender ! Particle.MoveTo(util.Random.nextInt(img.width().toInt), 0)
      } else {
        sender ! Particle.MoveTo(x, y)
      }
    case m => println("Unhandled message in Jar: " + m)
  }
}

object Jar {
  case class CheckLocation(x: Int, y: Int, oldx: Int, oldy: Int)
}