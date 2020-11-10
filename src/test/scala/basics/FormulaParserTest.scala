package basics

import org.junit.Test
import org.junit.Assert._

class FormulaParserTest {
  @Test def simple: Unit = assertEquals(7, FormulaParser.parse("4+3", Map.empty), 1e-8)
  
  @Test def opOrder: Unit = assertEquals(7, FormulaParser.parse("3+2*2", Map.empty), 1e-8)

  @Test def parens: Unit = assertEquals(10, FormulaParser.parse("(3+2)*2", Map.empty), 1e-8)

  @Test def big: Unit = assertEquals(0, FormulaParser.parse("(3+2)*4/(15-5)-2", Map.empty), 1e-8)

  @Test def withVars: Unit = assertEquals(0, FormulaParser.parse("(x+2)*4/(y-5)-2", Map("x" -> 3.0, "y" -> 15.0)), 1e-8)
}