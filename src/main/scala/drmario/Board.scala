package drmario

class Board {
  private var currentPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
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
      currentPill = currentPill.move(0, 1)
      dropDelay = 0.0
    }
    if (moveDelay >= moveInterval) {
      if (leftHeld) currentPill = currentPill.move(-1, 0)
      if (rightHeld) currentPill = currentPill.move(1, 0)
      if (downHeld) currentPill = currentPill.move(0, 1)
      if (upHeld) currentPill = currentPill.rotate()
      moveDelay = 0.0
    }
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