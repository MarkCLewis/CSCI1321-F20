package drmario

class Virus(val x: Int, val y: Int, val color: MarioColor.Value) extends BoardElement with BoardCell {
  def cells: List[BoardCell] = List(this)
  def supported(grid: Array[Array[Option[(BoardCell, BoardElement)]]]): Boolean = true
  def removeCells(cs: Set[BoardCell]): Option[BoardElement] = if (cs.contains(this)) None else Some(this)
}