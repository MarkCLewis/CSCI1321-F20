package puyo

class Puyo(val x: Int, val y: Int, val color: PuyoColor.Value) extends Yo {
  def move(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): Puyo = {
    if (isSafe(x+dx, y+dy)) new Puyo(x + dx, y + dy, color) else this
  }

  def canMove(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): Boolean = isSafe(x+dx, y+dy)
  def makePassable(): PassableYo = PassableYo(x, y, color, 0)
}