package drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val cellSize = 30
  val border = 50
  def render(board: PassableBoard): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, 1000, 800)
    for (cell <- board.allCells) {
      val color = cell.color match {
        case MarioColor.Red => Color.Red
        case MarioColor.Yellow => Color.Yellow
        case MarioColor.Blue => Color.Blue
      }
      gc.fill = color
      cell.style match {
        case 0 =>
          gc.fillOval(border+cell.x*cellSize, border+cell.y*cellSize, cellSize, cellSize)
        case 1 =>
          gc.fillRect(border+cell.x*cellSize, border+cell.y*cellSize, cellSize, cellSize)
      }
    }
  }
}