package actors

import akka.actor.ActorSystem
import akka.actor.Props
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("Crystals")

  stage = new JFXApp.PrimaryStage {
    title = "Crystals"
    scene = new Scene(800, 800) {
      val img = new WritableImage(800, 800)
      content = new ImageView(img)
      val tank = system.actorOf(Props(new Tank(img)), "Tank")
    }
  }

}