package drmario

class PillPiece(val x: Int, val y: Int, val color: MarioColor.Value) extends BoardCell {
  def drop(): PillPiece = new PillPiece(x, y+1, color)
}