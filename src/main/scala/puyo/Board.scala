package puyo

class Board {
  private var _yos = List.tabulate[Yo](6)(i => new Noyo(i, 11))
  private var currentChain = new PuyoChain(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))
  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false

  def yos = currentChain.p1 :: currentChain.p2 :: _yos

  private var dropDelay = 0.0
  val dropInterval = 1.5
  private var moveDelay = 0.0
  val moveInterval = 0.1

  def update(delay: Double): Unit = {
    dropDelay += delay
    moveDelay += delay
    if (dropDelay >= dropInterval) {
      currentChain = currentChain.move(0, 1)
      dropDelay = 0.0
    }
    if (moveDelay >= moveInterval) {
      if (leftHeld) currentChain = currentChain.move(-1, 0)
      if (rightHeld) currentChain = currentChain.move(1, 0)
      if (downHeld) currentChain = currentChain.move(0, 1)
      if (upHeld) {
        currentChain = currentChain.rotate()
        upHeld = false
      }
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