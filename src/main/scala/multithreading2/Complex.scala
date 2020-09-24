package multithreading2

case class Complex(r: Double, i: Double) {
  def +(that: Complex) = Complex(r+that.r, i+that.i)
  def *(that: Complex) = Complex(r*that.r - i*that.i, i*that.r + r*that.i)
}

// (a+bi) * (c+di)