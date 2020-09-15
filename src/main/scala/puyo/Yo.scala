package puyo

trait Yo {
  def x: Int
  def y: Int

  def move(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): Yo
}