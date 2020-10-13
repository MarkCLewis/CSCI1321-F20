package puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import java.net.Socket
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform
import scalafx.scene.control.TextInputDialog

object Client extends JFXApp {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)

  val dialog = new TextInputDialog("localhost")
  dialog.contentText = "Hostname or IP"
  dialog.headerText = "What machine is the server on?"
  dialog.title = "Server Host"
  val serverHost = dialog.showAndWait().getOrElse("localhost")
  val sock = new Socket(serverHost, 8080)
  val out = new ObjectOutputStream(sock.getOutputStream())
  val in = new ObjectInputStream(sock.getInputStream())
  Future {
    while (true) {
      in.readObject() match {
        case pb: PassableBoard =>
          Platform.runLater(renderer.draw(pb))
        case _ =>
          println("Oops! Got something other than a board to draw.")
      }
    }
  }

  stage = new PrimaryStage {
    title = "Puyo Puyo"
    scene = new Scene(1000, 800) {
      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => 
            out.writeInt(PressReleaseInfo.Pressed)
            out.writeInt(PressReleaseInfo.Left)
            out.flush()
          case KeyCode.Right => 
            out.writeInt(PressReleaseInfo.Pressed)
            out.writeInt(PressReleaseInfo.Right)
            out.flush()
          case KeyCode.Up => 
            out.writeInt(PressReleaseInfo.Pressed)
            out.writeInt(PressReleaseInfo.Up)
            out.flush()
          case KeyCode.Down => 
            out.writeInt(PressReleaseInfo.Pressed)
            out.writeInt(PressReleaseInfo.Down)
            out.flush()
          case _ =>
        }
      }

      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Left => 
            out.writeInt(PressReleaseInfo.Released)
            out.writeInt(PressReleaseInfo.Left)
            out.flush()
          case KeyCode.Right => 
            out.writeInt(PressReleaseInfo.Released)
            out.writeInt(PressReleaseInfo.Right)
            out.flush()
          case KeyCode.Up => 
            out.writeInt(PressReleaseInfo.Released)
            out.writeInt(PressReleaseInfo.Up)
            out.flush()
          case KeyCode.Down => 
            out.writeInt(PressReleaseInfo.Released)
            out.writeInt(PressReleaseInfo.Down)
            out.flush()
          case _ =>
        }
      }
    }
  }

  
}