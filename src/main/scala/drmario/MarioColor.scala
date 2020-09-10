package drmario

object MarioColor extends Enumeration {
  val Red, Blue, Yellow = Value

  def random: Value = util.Random.nextInt(3) match {
    case 0 => Red
    case 1 => Yellow
    case 2 => Blue
  }
}