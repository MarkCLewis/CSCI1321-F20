package adt

object SpeedTestPQs extends App {
  val data = Array.fill(100000)(util.Random.nextInt())
  val reps = 4
  samples(5000, new BinaryHeap[Int](_ < _))
  samples(5000, new BinaryHeapZero[Int](_ < _))
  samples(5000, new BinaryHeapSwap[Int](_ < _))

  def avgAndStdev(xs: Seq[Double]): (Double, Double) = {
    val mean = xs.sum / xs.length
    val std = math.sqrt(xs.foldLeft(0.0)((sum, x) => sum + (x-mean)*(x-mean)) / xs.length)
    (mean, std)
  }

  def samples(samples: Int, pq: MyPriorityQueue[Int]): Unit = {
    val dropped = samples / 10
    val times = (for (_ <- 1 to samples+1) yield timePQ(pq)).sorted.drop(dropped).dropRight(dropped)
    println(pq.getClass().getName())
    //println(times.mkString(", "))
    println(avgAndStdev(times))
    println()
  }

  def timePQ(pq: MyPriorityQueue[Int]): Double = {
    val start = System.nanoTime()
    for (_ <- 1 to reps) {
      for (d <- data) pq.enqueue(d)
      while (!pq.isEmpty) {
        pq.peek
        pq.dequeue()
      }
    }
    (System.nanoTime() - start) * 1e-9
  }
}