package drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def render(): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, 1000, 800)
  }
}