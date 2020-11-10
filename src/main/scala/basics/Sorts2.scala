package basics

object Sorts2 extends App {
  def bubbleSort(arr: Array[Int]): Unit = {
    for (i <- 0 until arr.length-1; j <- 0 until arr.length-1-i) {
      if (arr(j) > arr(j+1)) {
        val tmp = arr(j)
        arr(j) = arr(j+1)
        arr(j+1) = tmp
      }
    }
  }

  def bubbleSort2[A](arr: Array[A])(gt: (A, A) => Boolean): Unit = {
    for (i <- 0 until arr.length-1; j <- 0 until arr.length-1-i) {
      if (gt(arr(j), arr(j+1))) {
        val tmp = arr(j)
        arr(j) = arr(j+1)
        arr(j+1) = tmp
      }
    }
  }

  val nums = Array.fill(10)(util.Random.nextInt(100))
  println(nums.mkString(" "))
  bubbleSort2(nums)(_ < _)
  println(nums.mkString(" "))

  def quicksort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = lst match {
    case Nil | _ :: Nil => lst
    case pivot :: rest =>
      val (less, greater) = rest.partition(x => lt(x, pivot))
      quicksort(less)(lt) ::: (pivot :: quicksort(greater)(lt))
  }

  println(quicksort(List.fill(20)(util.Random.nextInt(100)))(_<_))

  def merge[A](lst1: List[A], lst2: List[A])(lt: (A, A) => Boolean): List[A] = (lst1, lst2) match {
    case (Nil, _) => lst2
    case (_, Nil) => lst1
    case (h1 :: t1, h2 :: t2) =>
      if (lt(h1, h2)) h1 :: merge(t1, lst2)(lt) else h2 :: merge(lst1, t2)(lt)
  }

  def mergesort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = lst match {
    case Nil | _ :: Nil => lst
    case _ =>
      val (left, right) = lst.splitAt(lst.length/2)
      merge(mergesort(left)(lt), mergesort(right)(lt))(lt)
  }

  println(mergesort(List.fill(20)(util.Random.nextInt(100)))(_<_))
}