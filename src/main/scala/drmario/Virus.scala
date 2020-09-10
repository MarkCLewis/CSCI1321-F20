package drmario

class Virus(val x: Int, val y: Int, val color: MarioColor.Value) extends BoardElement with BoardCell {
  def cells: List[BoardCell] = List(this)
}