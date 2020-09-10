package puyo

class Board {
  private var _yos = List.tabulate[Yo](6)(i => new Noyo(i, 11))
  private var currentChain = new PuyoChain(new Puyo(2, 0, PuyoColor.random), new Puyo(2, -1, PuyoColor.random))

  def yos = currentChain.p1 :: currentChain.p2 :: _yos

  private var dropDelay = 0.0
  val dropInterval = 1.5

  def update(delay: Double): Unit = {
    dropDelay += delay
    if (dropDelay >= dropInterval) {
      currentChain = currentChain.drop()
      dropDelay = 0.0
    }
  }
}