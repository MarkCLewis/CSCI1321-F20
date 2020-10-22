package adt2

import collection.mutable

class DoublyLL[A] extends mutable.Buffer[A] {
  private class Node(var data: A, var prev: Node, var next: Node)

  private val end = new Node(null.asInstanceOf[A], null, null)
  end.next = end
  end.prev = end
  private var numElems = 0

  def +=(elem: A) = {
    val n = new Node(elem, end.prev, end)
    end.prev.next = n
    end.prev = n
    numElems += 1
    this
  }

  def +=:(elem: A) = {
    val n = new Node(elem, end, end.next)
    end.next.prev = n
    end.next = n
    numElems += 1
    this
  }

  def apply(n: Int): A = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = end.next
    for (i <- 0 until n) rover = rover.next
    rover.data
  }

  def clear(): Unit = {
    end.next = end
    end.prev = end
    numElems = 0
  }

  def insertAll(n: Int, elems: Traversable[A]): Unit = {
    if (n < 0 || n > numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = end
    for (i <- 0 until n) rover = rover.next
    for (elem <- elems) {
      val n = new Node(elem, rover, rover.next)
      rover.next.prev = n
      rover.next = n
      numElems += 1
      rover = rover.next
    }
  }

  def length: Int = numElems

  def remove(n: Int): A = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = end.next
    for (i <- 0 until n) rover = rover.next
    val ret = rover.data
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    numElems -= 1
    ret
  }

  def update(n: Int,newelem: A): Unit = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = end.next
    for (i <- 0 until n) rover = rover.next
    rover.data = newelem
  }
  
  def iterator: Iterator[A] = new Iterator[A] {
    var rover = end.next
    def hasNext: Boolean = rover != null
    def next(): A = {
      val tmp = rover.data
      rover = rover.next
      tmp
    }
  }
}