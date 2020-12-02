List(1,2,3).mkString(", ")

val comm = "get sword".split(" +", 2)
// comm(0).toLowerCase match {

// }

List(1,2,3).patch(-1, Nil, 1)

Array("one" -> 1, "two" -> 2, "three" -> 3).toMap

val stateCodes = collection.mutable.Buffer("TX", "CO", "AZ")
val capitals = Map("TX" -> "Austin", "CO" -> "Denver", "AZ" -> "Phoenix")
val i = 2

capitals(stateCodes(i))

for (i <- (1 to 1000).par) yield i*i

"One" -> 1

case class TempData(high: Double)
val data = Array(TempData(90), TempData(80))
data.par.map(_.high).sum / data.length

<item name="sword">A long pointy thing.</item>

def makePersonXML(name: String, age: Int, desc: String): xml.Elem = {
  <person name={name} age={age.toString}>{desc}</person>
}

"Mark" == "  Mark    "

val xmlData = <entities> <entity type="player" x="3" y="2"/> <entity type="ghost" x="7" y="1"/> </entities>

(xmlData \ "entity").count(e => (e \ "@x").text.toInt < 5)
(xmlData \\ "@x").count(_.text.toInt < 5)


val lines = List("1. first","garbage","2. second", "19. a big number")
def numberedLines(lines: List[String]): Map[Int, String] = {
  val regex = """(\d+)\.(.*)""".r
  (for(regex(num, rest) <- lines) yield (num.toInt, rest)).toMap
}
numberedLines(lines)

"5.45e2".toDouble

val firstRE = """[A-Z][a-z]+"""
val lastRE = """(Mac )?[A-Z][a-z]+"""
val ageRE = """\d{1,3}"""
val fullRegex = s"($firstRE), ($lastRE): ($ageRE)".r

