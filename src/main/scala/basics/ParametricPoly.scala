package basics

object ParametricPoly extends App {
  /**
    * Finds the first occurrancethat satisfies a predicate and gives back the value and the
    * list with it removed.
    */
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (List[A], Option[A])  = {
    val index = lst.indexWhere(pred)
    if (index >= 0) {
      (lst.patch(index, Nil, 1), Some(lst(index)))
    } else {
      (lst, None)
    }
  }

  println(findAndRemove(List(1,7,3,9,2,13,17,6))(_ % 2 == 0))
  println(findAndRemove(List(1,7,3,9,2,13,17,6))(_ % 99 == 0))
}