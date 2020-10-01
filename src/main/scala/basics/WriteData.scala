package basics

import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.ObjectOutputStream

case class Student(name: String, grade: Int, id: String)

object WriteData extends App {
  val student = Student("Pat", 56, "0056374")
  val oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("student.bin")))
  oos.writeObject(student)
  oos.close()
}