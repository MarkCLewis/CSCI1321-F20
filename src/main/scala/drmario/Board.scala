package drmario

class Board {
  import Board._
  private var currentPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
    new PillPiece(4,0,MarioColor.random)))
  private var nextPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
    new PillPiece(4,0,MarioColor.random)))
  private var _elements = List.fill[BoardElement](5)(new Virus(util.Random.nextInt(8), 
      2+util.Random.nextInt(14), MarioColor.random))

  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false
  
  def elements: List[BoardElement] = if (mode == MarioMode.Normal) currentPill :: _elements else _elements

  private var dropDelay = 0.0
  val dropInterval = 0.5
  private var moveDelay = 0.0
  val moveInterval = 0.1

  private var mode = MarioMode.Normal

  def makePassable(): PassableBoard = {
    PassableBoard(for (elem <- elements; cell <- elem.cells) yield cell.makePassable())
  }

  def update(delay: Double): Unit = {
    mode match {
      case MarioMode.Normal =>
        dropDelay += delay
        moveDelay += delay
        if (dropDelay >= dropInterval) {
          dropPiece()
          dropDelay = 0.0
        }
        if (moveDelay >= moveInterval) {
          if (leftHeld) currentPill = currentPill.move(-1, 0, isClear)
          if (rightHeld) currentPill = currentPill.move(1, 0, isClear)
          if (downHeld) dropPiece()
          if (upHeld) currentPill = currentPill.rotate(isClear)
          moveDelay = 0.0
        }
      case MarioMode.CheckSupport =>
        val grid = makeGrid()
        if (!checkSupport(grid)) {
          if (!checkRemove(grid)) {
            mode = MarioMode.Normal
          }
        }
    }
  }

  def dropPiece(): Unit = {
    val newPill = currentPill.move(0, 1, isClear)
    if (newPill == currentPill) { // Didn't actually fall
      _elements ::= currentPill
      currentPill = nextPill
      nextPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
        new PillPiece(4,0,MarioColor.random)))
      mode = MarioMode.CheckSupport
    } else {
      currentPill = newPill
    }
  }

  def isClear(x: Int, y: Int): Boolean = {
    x >= 0 && x < width && y < height && _elements.forall(_.cells.forall(c => c.x != x || c.y != y))
  }

  def makeGrid(): Array[Array[Option[(BoardCell, BoardElement)]]] = {
    val ret = Array.fill(width, height)(None: Option[(BoardCell, BoardElement)])
    for (elem <- _elements; cell <- elem.cells) ret(cell.x)(cell.y) = Some(cell -> elem)
    ret
  }

  def checkRemove(grid: Array[Array[Option[(BoardCell, BoardElement)]]]): Boolean = {
    var victims = List[BoardCell]()
    // Vertical
    for (x <- 0 until width) {
      var matching = List[BoardCell]()
      for (y <- 0 until height) {
        if (grid(x)(y).map(t => matching.isEmpty || t._1.color == matching.head.color).getOrElse(false)) matching ::= grid(x)(y).get._1
        else {
          if (matching.length >= 4) victims :::= matching
          matching = grid(x)(y).map(_._1 :: Nil).getOrElse(Nil)
        }
      }
      if (matching.length >= 4) victims :::= matching
    }

    // Horizontal
    for (y <- 0 until height) {
      var matching = List[BoardCell]()
      for (x <- 0 until width) {
        if (grid(x)(y).map(t => matching.isEmpty || t._1.color == matching.head.color).getOrElse(false)) matching ::= grid(x)(y).get._1
        else {
          if (matching.length >= 4) victims :::= matching
          matching = grid(x)(y).map(_._1 :: Nil).getOrElse(Nil)
        }
      }
      if (matching.length >= 4) victims :::= matching
    }

    val vset = victims.toSet
    _elements = _elements.flatMap(_.removeCells(vset))
    victims.nonEmpty
  }

  def checkSupport(grid: Array[Array[Option[(BoardCell, BoardElement)]]]): Boolean = {
    var ret = false
    for (y <- height-2 to 0 by -1; x <- 0 until width) {
      grid(x)(y).foreach { 
        case (_, elem: Pill) =>
          if (!elem.supported(grid)) {
            for (cell <- elem.cells) grid(cell.x)(cell.y) = None
            val newElem = elem.move(0, 1, (x, y) => true)
            for (cell <- newElem.cells) grid(cell.x)(cell.y) = Some(cell, newElem)
            _elements = newElem :: _elements.filter(_ != elem)
            ret = true
          }
        case _ =>
      }
    }
    ret
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
  val width = 8
  val height = 16
}