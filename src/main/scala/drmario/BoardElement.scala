package drmario

trait BoardElement {
  def cells: List[BoardCell]
  def supported(grid: Array[Array[Option[(BoardCell, BoardElement)]]]): Boolean
  def removeCells(cs: Set[BoardCell]): Option[BoardElement]
}