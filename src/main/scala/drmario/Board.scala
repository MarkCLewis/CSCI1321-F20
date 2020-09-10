package drmario

class Board {
  private var currentPill = new Pill(List(new PillPiece(3,0,MarioColor.random), 
    new PillPiece(4,0,MarioColor.random)))
  private var _elements = List.fill[BoardElement](5)(new Virus(util.Random.nextInt(8), 
      2+util.Random.nextInt(14), MarioColor.random))
  
  def elements: List[BoardElement] = currentPill :: _elements 

  private var dropDelay = 0.0
  val dropInterval = 0.5

  def update(delay: Double): Unit = {
    dropDelay += delay
    if (dropDelay >= dropInterval) {
      currentPill = currentPill.drop()
      dropDelay = 0.0
    }
  }
}