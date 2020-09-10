package drmario

trait BoardCell {
  def x: Int
  def y: Int
  def color: MarioColor.Value
}