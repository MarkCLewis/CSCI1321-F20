package puyo

class PuyoChain(val p1: Puyo, val p2: Puyo) {
  def move(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): PuyoChain = {
    if (p1.canMove(dx, dy, isSafe) && p2.canMove(dx, dy, isSafe))
      new PuyoChain(p1.move(dx, dy, isSafe), p2.move(dx, dy, isSafe))
    else this
  }

  def rotate(isSafe: (Int, Int) => Boolean): PuyoChain = {
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
    if (isSafe(p1.x + nx, p1.y + ny)) new PuyoChain(p1, new Puyo(p1.x + nx, p1.y + ny, p2.color))
    else this
  }
}