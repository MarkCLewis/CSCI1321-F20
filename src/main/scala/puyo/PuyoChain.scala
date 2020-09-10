package puyo

class PuyoChain(val p1: Puyo, val p2: Puyo) {
  def move(dx: Int, dy: Int): PuyoChain = {
    new PuyoChain(p1.move(dx, dy), p2.move(dx, dy))
  }

  def rotate(): PuyoChain = {
    // 0, -1 -> 1, 0 -> 0, 1 -> -1, 0
    val ox = p2.x - p1.x
    val oy = p2.y - p1.y
    val (nx, ny) = (ox, oy) match {
      case (0, -1) => (1, 0)
      case (1, 0) => (0, 1)
      case (0, 1) => (-1, 0)
      case (-1, 0) => (0, -1)
      case _ => (0, 0)
    }
    new PuyoChain(p1, new Puyo(p1.x + nx, p1.y + ny, p2.color))
  }
}