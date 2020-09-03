package drmario

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

object Main extends JFXApp {
  stage = new PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(1000, 800) {
      val canvas = new Canvas(1000, 800)
      content = canvas
    }
  }
}