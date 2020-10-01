package actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import scalafx.scene.paint.Color
import akka.actor.Props
import actors.Tank.CheckPosition

class Tank(img: WritableImage) extends Actor {
  val writer = img.pixelWriter
  val reader = img.pixelReader.get
  for (x <- 0 until img.width().toInt; y <- 0 until img.height().toInt) writer.setColor(x, y, Color.Black)
  for (x <- 0 until img.width().toInt) writer.setColor(x, img.height().toInt-1, Color.White)
  for (_ <- 1 to 8) {
    context.actorOf(Props(new Particle)) ! Particle.MoveTo(util.Random.nextInt(img.width().toInt), 0)
  }
  import Tank._
  def receive = {
    case CheckPosition(x, y, oldx, oldy) =>
      if (x < 0 || x >= img.width()) sender ! Particle.MoveTo(oldx, oldy)
      else if (reader.getColor(x, y) == Color.White) {
        writer.setColor(oldx, oldy, Color.White)
        sender ! Particle.MoveTo(util.Random.nextInt(img.width().toInt), 0)
      } else {
        sender ! Particle.MoveTo(x, y)
      }
    case m => println("Unhandled message in Tank: " + m)
  }
}

object Tank {
  case class CheckPosition(x: Int, y: Int, oldx: Int, oldy: Int)
}