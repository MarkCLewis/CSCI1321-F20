package basics

object Recursion2 extends App {
  def recursiveFactorial(n: Int): Int = {
      if (n <= 1) 1
      else n * recursiveFactorial(n-1)
  }

  // 0 1 1 2 3 5 8 13 ...
  def recursiveFib(n: Int): Int = {
      if (n == 0) 0
      else if (n == 1) 1
      else recursiveFib(n-1) + recursiveFib(n-2)
  }
  println(recursiveFactorial(3))
  println(recursiveFactorial(1))

  println(recursiveFib(0))
  println(recursiveFib(1))
  println(recursiveFib(3))
  println(recursiveFib(5))

  def packBins(objs: List[Double], bins: Array[Double]): Boolean = objs match {
    case Nil => true
    case o1 :: rest =>
      bins.indices.exists(i => if (o1 <= bins(i)) {
        bins(i) -= o1
        val ret = packBins(rest, bins)
        bins(i) += o1
        ret
      } else false)
  }

  def knapsack01(items: List[(Double, Double)], size: Double): Double = items match {
    case Nil => 0.0
    case (value, weight) :: rest =>
      (if (weight <= size) knapsack01(rest, size-weight)+value else 0.0) max knapsack01(rest, size)
  }
}