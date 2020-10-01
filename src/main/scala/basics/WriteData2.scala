package basics

import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.ObjectOutputStream

case class Date(month: String, year: Int, day: Int)

object WriteData2 extends App {
  val date = Date("May", 1986, 3)
  val oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("date.bin")))
  oos.writeObject(date)
  oos.close()
}