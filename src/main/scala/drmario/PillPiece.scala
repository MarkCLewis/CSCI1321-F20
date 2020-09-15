package drmario

class PillPiece(val x: Int, val y: Int, val color: MarioColor.Value) extends BoardCell {
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): PillPiece = {
    if (isClear(x+dx, y+dy)) new PillPiece(x + dx, y + dy, color) else this
  }

  def canMove(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Boolean = isClear(x+dx, y+dy)
}