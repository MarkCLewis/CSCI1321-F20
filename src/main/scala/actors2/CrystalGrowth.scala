package actors2

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import akka.actor.ActorSystem
import akka.actor.Props

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("Crystal")

  stage = new JFXApp.PrimaryStage {
    title = "Crystals"
    scene = new Scene(800, 800) {
      val img = new WritableImage(800, 800)
      content = new ImageView(img)
      system.actorOf(Props(new Jar(img)), "Jar")
    }
  }
}