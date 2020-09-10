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

}