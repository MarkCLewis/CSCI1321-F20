package puyo

object PuyoColor extends Enumeration {
  val Red, Green, Blue, Purple, Yellow = Value

  def random: Value = util.Random.nextInt(5) match {
    case 0 => Red
    case 1 => Green
    case 2 => Blue
    case 3 => Purple
    case 4 => Yellow
  }
}