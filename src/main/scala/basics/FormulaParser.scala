package basics

object FormulaParser {
  val nonVals = "+-*/(".toSet
  def parse(formula: String, vars: Map[String, Double]): Double = {
    val f = formula.trim()
    var i = f.length - 1
    var opLoc = -1
    var parensCnt = 0
    while (i > 0) {
      if (f(i) == ')') parensCnt += 1
      else if (f(i) == '(') parensCnt -= 1
      else if (parensCnt == 0 && (f(i) == '+' || f(i) == '-') && !nonVals(f(i-1))) {
        opLoc = i
        i = -1
      } else if (parensCnt == 0 && opLoc < 0 && (f(i) == '*' || f(i) == '/')) {
        opLoc = i
      }
      i -= 1
    }
    if (opLoc < 0) {
      if (f(0) == '(') parse(f.substring(1, f.length-1), vars) else try {
        f.toDouble
      } catch {
        case ex: NumberFormatException => vars(f)
      }
    } else {
      f(opLoc) match {
        case '+' => parse(f.substring(0, opLoc), vars) + parse(f.substring(opLoc+1), vars)
        case '-' => parse(f.substring(0, opLoc), vars) - parse(f.substring(opLoc+1), vars)
        case '*' => parse(f.substring(0, opLoc), vars) * parse(f.substring(opLoc+1), vars)
        case '/' => parse(f.substring(0, opLoc), vars) / parse(f.substring(opLoc+1), vars)
      }
    }
  }
}