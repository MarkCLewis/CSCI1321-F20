package drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val cellSize = 30
  val border = 50
  def render(board: Board): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, 1000, 800)
    for (element <- board.elements; cell <- element.cells) {
      val color = cell.color match {
        case MarioColor.Red => Color.Red
        case MarioColor.Yellow => Color.Yellow
        case MarioColor.Blue => Color.Blue
      }
      gc.fill = color
      cell match {
        case v:Virus =>
          gc.fillOval(border+v.x*cellSize, border+v.y*cellSize, cellSize, cellSize)
        case pp:PillPiece =>
          gc.fillRect(border+pp.x*cellSize, border+pp.y*cellSize, cellSize, cellSize)
      }
    }
  }
}