package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent

object Client extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)

  stage = new PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(1000, 800) {
      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => //board.leftPressed()
          case KeyCode.Right => //board.rightPressed()
          case KeyCode.Up => //board.upPressed()
          case KeyCode.Down => //board.downPressed()
          case _ =>
        }
      }

      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => //board.leftReleased()
          case KeyCode.Right => //board.rightReleased()
          case KeyCode.Up => //board.upReleased()
          case KeyCode.Down => //board.downReleased()
          case _ =>
        }
      }
    }
  }

  //renderer.render(pBoard)
}