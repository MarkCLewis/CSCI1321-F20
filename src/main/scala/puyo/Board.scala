package puyo

class Board {
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
      // TODO: Check for groups of matching colors
      state = GameState.DroppingChain
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
    x >= 0 && x < 6 && y < 12 && _yos.forall(yo => yo.x != x || yo.y != y)
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