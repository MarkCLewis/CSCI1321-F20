package drmario

class Pill(val cells: List[PillPiece]) extends BoardElement {
  def move(dx: Int, dy: Int): Pill = new Pill(cells.map(_.move(dx, dy)))

  def rotate(): Pill = ???
}