package drmario

class Board {
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
  
  def elements: List[BoardElement] = currentPill :: _elements 

  private var dropDelay = 0.0
  val dropInterval = 0.5
  private var moveDelay = 0.0
  val moveInterval = 0.1

  def update(delay: Double): Unit = {
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
  }

  def dropPiece(): Unit = {
    val newPill = currentPill.move(0, 1, isClear)
    if (newPill == currentPill) { // Didn't actually fall
      _elements ::= currentPill
      currentPill = nextPill
      nextPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
        new PillPiece(4,0,MarioColor.random)))
    } else {
      currentPill = newPill
    }
  }

  def isClear(x: Int, y: Int): Boolean = {
    x >= 0 && x < 8 && y < 16 && _elements.forall(_.cells.forall(c => c.x != x || c.y != y))
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