package basics

import java.io.ObjectInputStream
import java.io.BufferedInputStream
import java.io.FileInputStream

object ReadData2 extends App {
  val ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("date.bin")))
  val date = ois.readObject() match {
    case d: Date => d
    case _ =>
      println("Got something that isn't a Date.")
      throw new RuntimeException("Not a Date.")
  }
  ois.close()
  println(date)
}