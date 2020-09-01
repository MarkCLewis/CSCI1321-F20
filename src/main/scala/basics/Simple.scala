package basics

class Simple {
    private var _x: Int = 0

    def x = _x
    def x_=(newX: Int) = if (newX >= 0 ) _x = newX
}

object Simple2 {
    val s = new Simple
    s.x = 7
    s.x_=(7)
}