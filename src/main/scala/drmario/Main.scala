package drmario

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
    title = "Dr. Mario"
    scene = new Scene(1000, 800) {
      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => board.leftPressed()
          case KeyCode.Right => board.rightPressed()
          case KeyCode.Up => board.upPressed()
          case KeyCode.Down => board.downPressed()
          case _ =>
        }
      }

      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => board.leftReleased()
          case KeyCode.Right => board.rightReleased()
          case KeyCode.Up => board.upReleased()
          case KeyCode.Down => board.downReleased()
          case _ =>
        }
      }

      var lastTime = -1L
      val timer = AnimationTimer(time => {
        if (lastTime >= 0) {
          val delay = (time - lastTime)/1e9
          board.update(delay)
          val pBoard = board.makePassable()
          renderer.render(pBoard)
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
// X - Make the pill move side to side
// X - Slow down side to side movement
// X - Make the pill rotate
// X - Respect side bounds and bottom
// X - Make the pill stop falling when it hits other stuff
// X - Make a new pill appear
// Boarder
// Scoreboard
// 