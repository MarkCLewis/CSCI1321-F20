package drmario

class Pill(val cells: List[PillPiece]) extends BoardElement {
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Pill = {
    if (cells.forall(_.canMove(dx, dy, isClear))) new Pill(cells.map(_.move(dx, dy, isClear)))
    else this
  }

  def rotate(isClear: (Int, Int) => Boolean): Pill = {
    require(cells.length == 2)
    if (cells(0).y == cells(1).y) {
      val sc = cells.sortBy(_.x)
      if (sc(1).canMove(-1, -1, isClear)) new Pill(List(sc(0), sc(1).move(-1, -1, isClear))) else this
    } else {
      val sc = cells.sortBy(_.y)
      if (sc(0).canMove(0, 1, isClear) && sc(1).canMove(1, 0, isClear))
        new Pill(List(sc(0).move(0, 1, isClear), sc(1).move(1, 0, isClear)))
      else this
    }
  }
}