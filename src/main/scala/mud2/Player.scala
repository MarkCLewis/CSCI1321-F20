package mud2

class Player {

}

object Player {
  case class PrintMessage(message: String)
  case class TakeItem(oitem: Option[Item])
}