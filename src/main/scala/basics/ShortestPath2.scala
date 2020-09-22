package basics

object ShortestPath2 extends App {
  val maze = Array(
    Array(0,1,0,1,0,0,0,0,0,0),
    Array(0,1,0,1,0,1,1,0,1,0),
    Array(0,1,0,0,0,0,1,0,1,0),
    Array(0,1,1,1,0,1,1,1,1,0),
    Array(0,0,0,0,0,0,0,0,1,0),
    Array(0,1,1,1,1,0,1,1,1,0),
    Array(0,1,0,0,0,0,0,0,1,0),
    Array(0,1,0,1,0,1,0,0,1,0),
    Array(0,1,0,1,0,1,1,1,1,0),
    Array(0,0,0,1,0,0,0,0,0,0)
  )

  val offsets = Array((1, 0), (-1, 0), (0, -1), (0, 1))
  
  println(breadthFirstShortestPath(maze, 0, 0,  9, 9))

  def breadthFirstShortestPath(maze: Array[Array[Int]], sx: Int, sy: Int, ex: Int, ey: Int): Int = {
    val queue = new adt2.ArrayQueue[(Int, Int, Int)]
    queue.enqueue((sx, sy, 0))
    val visited = collection.mutable.Set((sx, sy))
    while (!queue.isEmpty) {
      val (x, y, steps) = queue.dequeue()
      for ((offx, offy) <- offsets) {
        val newx = x + offx
        val newy = y + offy
        if (newx == ex && newy == ey) return steps + 1
        if (newx >= 0 && newx < maze.length && newy >= 0 && newy < maze(newx).length &&
            maze(newx)(newy) == 0 && !visited(newx -> newy)) {
          queue.enqueue((newx, newy, steps+1))
          visited += newx -> newy
        }
      }
    }
    1000000000
  }
}