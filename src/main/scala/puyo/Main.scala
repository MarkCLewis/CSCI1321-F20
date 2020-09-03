package puyo

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

object Main extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  stage = new PrimaryStage {
    title = "Puyo Puyo"
    scene = new Scene(1000, 800) {
      content = canvas
      renderer.draw()
    }
  }
}

// TODO list
//