package drmario

object Server extends App {
  val board = new Board

  var lastTime = -1L
  while (true) {
    val time = System.nanoTime()
    if (lastTime >= 0) {
      val delay = (time - lastTime)/1e9
      board.update(delay)
      val pBoard = board.makePassable()
      // Send info to client to draw
    }
    lastTime = time
  }
}