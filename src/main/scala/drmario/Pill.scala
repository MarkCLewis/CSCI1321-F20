package drmario

class Pill(val cells: List[PillPiece]) extends BoardElement {
  def drop(): Pill = new Pill(cells.map(_.drop()))
}