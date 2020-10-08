package puyo

trait Yo {
  def x: Int
  def y: Int
  def color: PuyoColor.Value

  def move(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): Yo
  def makePassable(): PassableYo
}