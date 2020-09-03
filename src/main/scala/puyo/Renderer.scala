package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def draw(): Unit = {
    gc.fill = Color.Magenta
    gc.fillRect(0, 0, 1000, 800)
  }
}