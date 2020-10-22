package adt2

import collection.mutable

class SinglyLL[A] extends mutable.Buffer[A] {
  private class Node(var data: A, var next: Node)

  private var hd: Node = null
  private var tl: Node = null
  private var numElems = 0

  def +=(elem: A) = {
    if (tl == null) {
      tl = new Node(elem, null)
      hd = tl
    } else {
      tl.next = new Node(elem, null)
      tl = tl.next
    }
    numElems += 1
    this
  }

  def +=:(elem: A) = {
    if (hd == null) {
      hd = new Node(elem, null)
      tl = hd
    } else hd = new Node(elem, hd.next)
    numElems += 1
    this
  }

  def apply(n: Int): A = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = hd
    for (i <- 0 until n) rover = rover.next
    rover.data
  }

  def clear(): Unit = {
    hd = null
    tl = null
    numElems = 0
  }

  def insertAll(n: Int, elems: Traversable[A]): Unit = {
    if (n < 0 || n > numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    if (n == 0) {
      var rover: Node = null
      for (elem <- elems) {
        if (rover == null) hd = new Node(elem, hd)
        else rover.next = new Node(elem, rover.next)
        numElems += 1
        if (rover == null) rover = hd else rover = rover.next
      }
      if (tl == null) tl = rover
    } else {
      var rover = hd
      for (i <- 0 until n-1) rover = rover.next
      for (elem <- elems) {
        rover.next = new Node(elem, rover.next)
        numElems += 1
        rover = rover.next
      }
      if (n == numElems) tl = rover
    }
  }

  def length: Int = numElems

  def remove(n: Int): A = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    if (n == 0) {
      val ret = hd.data
      hd = hd.next
      if (hd == null) tl = null
      numElems -= 1
      ret
    } else {
      var rover = hd
      for (i <- 0 until n-1) rover = rover.next
      val ret = rover.next.data
      rover.next = rover.next.next
      if (n == numElems-1) tl = rover
      numElems -= 1
      ret
    }
  }

  def update(n: Int,newelem: A): Unit = {
    if (n < 0 || n >= numElems) throw new IndexOutOfBoundsException(s"index: $n for length: $numElems")
    var rover = hd
    for (i <- 0 until n) rover = rover.next
    rover.data = newelem
  }
  
  def iterator: Iterator[A] = new Iterator[A] {
    var rover = hd
    def hasNext: Boolean = rover != null
    def next(): A = {
      val tmp = rover.data
      rover = rover.next
      tmp
    }
  }
}