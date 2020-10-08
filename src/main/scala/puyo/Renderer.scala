package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val border = 50
  val cellSize = 40

  def draw(board: PassableBoard): Unit = {
    gc.fill = Color.Beige
    gc.fillRect(0, 0, 1000, 800)
    for (yo <- board.yos; if yo.y >= 0) {
      yo.style match {
        case 0 =>
          gc.fill = yo.color match {
            case PuyoColor.Red => Color.Red
            case PuyoColor.Green => Color.Green
            case PuyoColor.Blue => Color.Blue
            case PuyoColor.Purple => Color.Purple
            case PuyoColor.Yellow => Color.Yellow
          }
        case 1 =>
          gc.fill = Color.Gray
      }
      gc.fillOval(yo.x*cellSize + border, yo.y*cellSize + border, cellSize, cellSize)
    }
  }
}