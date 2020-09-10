package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

object Main extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  val board = new Board
  stage = new PrimaryStage {
    title = "Puyo Puyo"
    scene = new Scene(1000, 800) {
      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => 
          case KeyCode.Right => 
          case KeyCode.Up => 
          case KeyCode.Down => 
          case _ =>
        }
      }

      var lastTime = -1L
      val timer = AnimationTimer(time => {
        if (lastTime >= 0) {
          val delay = (time-lastTime)/1e9
          board.update(delay)
          renderer.draw(board)
        }
        lastTime = time
      })
      timer.start()
    }
  }
}

// TODO:
// X - Draw a single Puyo
// X - Make the puyo a random color
// X - Draw a Noyo
// X - Draw PuyoChain
// X - Don't draw Yo's above the board
// X - Make the PuyoChain fall
// X - Make it fall slowly
// Move PuyoChain
// Rotate PuyoChain
// Make PuyoChain stop
// Make new PuyoChain appear
