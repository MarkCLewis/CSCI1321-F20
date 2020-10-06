package basics

import java.io.ObjectInputStream
import java.io.BufferedInputStream
import java.io.FileInputStream

object ReadData extends App {
  val ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("student.bin")))
  val student = ois.readObject() match {
    case s: Student => s
    case _ =>
      println("Reading a non-student!!!")
      throw new RuntimeException("Not a student.")
  }
  println(student)
  ois.close()
}