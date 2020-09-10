package drmario

class PillPiece(val x: Int, val y: Int, val color: MarioColor.Value) extends BoardCell {
  def move(dx: Int, dy: Int): PillPiece = new PillPiece(x + dx, y + dy, color)
}