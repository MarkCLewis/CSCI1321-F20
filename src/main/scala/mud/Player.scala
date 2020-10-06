package mud

class Player {

}

object Player {
  case class PrintMessage(msg: String)
  case class TakeItem(oitem: Option[Item])
}