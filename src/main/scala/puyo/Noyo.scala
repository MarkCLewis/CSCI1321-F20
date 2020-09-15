package puyo

class Noyo(val x: Int, val y: Int) extends Yo {
  def move(dx: Int, dy: Int, isSafe: (Int, Int) => Boolean): Noyo = {
    if (isSafe(x+dx, y+dy)) new Noyo(x + dx, y + dy) else this
  }

}