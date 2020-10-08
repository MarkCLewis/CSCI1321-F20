package puyo

import collection.mutable

class Board {
  import Board._
  private var _yos = List.tabulate[Yo](6)(i => new Noyo(i, 11))
  private var currentChain = new PuyoChain(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))
  private var nextChain = new PuyoChain(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))
  private var state = GameState.DroppingChain
  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false

  def yos = currentChain.p1 :: currentChain.p2 :: _yos

  private var dropDelay = 0.0
  val dropInterval = 1.5
  private var moveDelay = 0.0
  val moveInterval = 0.1

  val offsets = List((-1, 0), (1, 0), (0, -1), (0, 1))

  def makePassable(): PassableBoard = {
    PassableBoard(yos.map(_.makePassable()))
  }

  def update(delay: Double): Unit = {
    state match {
    case GameState.DroppingChain =>
      dropDelay += delay
      moveDelay += delay
      if (dropDelay >= dropInterval) {
        dropChain()
        dropDelay = 0.0
      }
      if (moveDelay >= moveInterval) {
        if (leftHeld) currentChain = currentChain.move(-1, 0, isSafe)
        if (rightHeld) currentChain = currentChain.move(1, 0, isSafe)
        if (downHeld) dropChain()
        if (upHeld) {
          currentChain = currentChain.rotate(isSafe)
          upHeld = false
        }
        moveDelay = 0.0
      }
    case GameState.CheckSupport =>
      // Run through yos and move unsupported down from bottom up
      var somethingFell = false
      _yos = _yos.sortBy(-_.y).map { yo =>
        val newYo = yo.move(0, 1, isSafe)
        if (newYo != yo) somethingFell = true
        newYo
      }
      if (!somethingFell) state = GameState.CheckMatching
    case GameState.CheckMatching =>
      val grid = makeGrid()
      state = if (removeMatches(grid)) GameState.CheckSupport else GameState.DroppingChain
    }
  }

  def dropChain(): Unit = {
    val newChain = currentChain.move(0, 1, isSafe)
    if (newChain == currentChain) {
      state = GameState.CheckSupport
      _yos = currentChain.p1 :: currentChain.p2 :: _yos
      currentChain = nextChain
      nextChain = new PuyoChain(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))
    } else {
      currentChain = newChain
    }
  }

  def isSafe(x: Int, y: Int): Boolean = {
    x >= 0 && x < width && y < height && _yos.forall(yo => yo.x != x || yo.y != y)
  }

  def makeGrid(): Array[Array[Option[Yo]]] = {
    val grid = Array.fill(width, height)(None: Option[Yo])
    for (yo <- _yos) {
      grid(yo.x)(yo.y) = Some(yo)
    }
    grid
  }

  def removeMatches(grid: Array[Array[Option[Yo]]]): Boolean = {
    def recur(x: Int, y: Int, color: PuyoColor.Value, visited: mutable.Set[Yo]): Unit = {
      grid(x)(y).map{ yo => 
        if (yo.color != color || visited(yo)) Set() else {
          visited += yo
          for ((dx, dy) <- offsets; nx = x+dx; ny = y+dy; if nx >= 0 && nx < width && ny >= 0 && ny < height) recur(nx, ny, color, visited)
        }
      }.getOrElse(Set.empty)
    }
    var victims = mutable.Set[Yo]()
    for (yo <- _yos; if !victims(yo) && yo.color != PuyoColor.Gray) {
      val visited = mutable.Set[Yo]()
      recur(yo.x, yo.y, yo.color, visited)
      if (visited.size >= 4) victims ++= visited
    }
    _yos = _yos.filter(yo => !victims(yo))
    victims.nonEmpty
  }

  def upPressed(): Unit = upHeld = true
  def downPressed(): Unit = downHeld = true
  def leftPressed(): Unit = leftHeld = true
  def rightPressed(): Unit = rightHeld = true
  def upReleased(): Unit = upHeld = false
  def downReleased(): Unit = downHeld = false
  def leftReleased(): Unit = leftHeld = false
  def rightReleased(): Unit = rightHeld = false
}

object Board {
  val width = 6
  val height = 12
}