package drmario

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer

object Main extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  val board = new Board

  stage = new PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(1000, 800) {
      content = canvas

      var lastTime = -1L
      val timer = AnimationTimer(time => {
        if (lastTime >= 0) {
          val delay = (time - lastTime)/1e9
          board.update(delay)
          renderer.render(board)
        }
        lastTime = time
      })
      timer.start()
    }
  }
}

// TODO:
// X - Draw a virus
// X - Draw multiple viruses of the same color
// X - Make them different colors
// X - Draw a pill
// X - Make the pill fall
// X - Make pill not fall so fast
// Make the pill stop falling
// Make the pill move side to side
// Make the pill rotate
// Make a new pill appear
// Boarder
// Scoreboard
// 