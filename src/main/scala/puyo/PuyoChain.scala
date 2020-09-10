package puyo

class PuyoChain(val p1: Puyo, val p2: Puyo) {
  def drop(): PuyoChain = {
    new PuyoChain(p1.drop(), p2.drop())
  }
}