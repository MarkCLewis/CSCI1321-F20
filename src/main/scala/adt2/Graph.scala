package adt2

object Graph extends App {
  private val matrix = Array(
    Array(0, 1, 0, 1, 0),
    Array(0, 0, 1, 0, 1),
    Array(0, 0, 0, 0, 0),
    Array(1, 0, 0, 1, 1),
    Array(0, 1, 0, 0, 0)
  )

  def reachable(node1: Int, node2: Int, connect: Array[Array[Int]], visited: Set[Int] = Set.empty): Boolean = {
    if (node1 == node2) true else {
      val newVisited = visited + node1
      var i = 0
      var reached = false
      while(i < connect.length && !reached) {
        if (connect(node1)(i) > 0 && !visited(i)) reached ||= reachable(i, node2, connect, newVisited)
        i += 1
      }
      reached
    }
  }

  println(reachable(0, 4, matrix))
  println(reachable(2, 3, matrix))
  println(reachable(4, 3, matrix))

  def shortestPath(node1: Int, node2: Int, connect: Array[Array[Int]], visited: Set[Int] = Set.empty): Int = {
    if (node1 == node2) 0 else {
      val newVisited = visited + node1
      var i = 0
      var steps = 1000000000
      while(i < connect.length) {
        if (connect(node1)(i) > 0 && !visited(i)) 
          steps = steps min (connect(node1)(i) + shortestPath(i, node2, connect, newVisited))
        i += 1
      }
      steps
    }
  }

  println(shortestPath(0, 1, matrix))
  println(shortestPath(0, 2, matrix))
  println(shortestPath(3, 2, matrix))
  println(shortestPath(4, 3, matrix))
}
