package basics

object ParametricPoly2 extends App {
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (List[A], Option[A]) = {
    def helper(lst2: List[A]): (List[A], Option[A]) = lst2 match {
      case Nil => (Nil, None)
      case h :: t =>
        if (pred(h)) {
          (t, Some(h))
        } else {
          val (rest, opt) = helper(t)
          (h :: rest, opt)
        }
    }
    helper(lst)
  }

  println(findAndRemove(List(1,7,3,9,4,13,8,26,2))(_ % 2 == 0))
  println(findAndRemove(List(1,7,3,9,4,13,8,26,2))(_ % 99 == 0))
}