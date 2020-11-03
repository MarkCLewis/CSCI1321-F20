package basics

object RecursionG1 extends App {
    def factorial(num: Long): Long = {
        if(num <= 1){
            1L
        }
        else{
            num * factorial(num - 1)
        }
    }

    // 1, 1, 2, 3, 5, 8, 13, ...
    def fibonaci(step: Int): Int = {
        if(step <= 1) 1
        else{
            fibonaci(step - 1) + fibonaci(step - 2)
        }
    }

    println("factorial 5 " + factorial(5))

    println("fibonaci 5 " + fibonaci(5))


  def binPack(objs: List[Int], bins: Array[Int]): Boolean = objs match {
    case Nil => true
    case o1 :: rest =>
      bins.indices.exists { i => if (o1 <= bins(i)) {
        bins(i) -= o1
        val ret = binPack(rest, bins)
        bins(i) += o1
        ret
      } else {
        false
      }
    }
  }

  def knapsack01(items: List[(Double, Double)], limit: Double): Double = items match {
    case Nil => 0.0
    case (value, weight) :: rest =>
      (if (weight <= limit) value + knapsack01(rest, limit-weight) else 0.0) max
        knapsack01(rest, limit)
  }
}

