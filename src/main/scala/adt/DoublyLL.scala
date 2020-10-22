package adt

class DoublyLL[A] extends MutableSeq[A] {
  private class Node(var data: A, var prev: Node, var next: Node)

  private val end: Node = new Node(null.asInstanceOf[A], null, null)
  end.next = end
  end.prev = end
  private var numElems = 0

  def apply(index: Int): A = {
    assert(index >= 0 && index < numElems)
    var rover = end.next
    for (i <- 0 until index) {
      rover = rover.next
    }
    rover.data
  }

  def insert(index: Int,a: A): Unit = {
    assert(index >= 0 && index <= numElems)
    var rover = end
    for (i <- 0 until index) {
      rover = rover.next
    }
    val n = new Node(a, rover, rover.next)
    rover.next = n
    n.next.prev = n
    numElems += 1
  }

  def length: Int = numElems

  def remove(index: Int): A = {
    assert(index >= 0 && index < numElems)
    numElems -= 1
    var rover = end.next
    for (i <- 0 until index) {
      rover = rover.next
    }
    val ret = rover.data
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    ret
  }

  def update(index: Int,a: A): Unit = {
    assert(index >= 0 && index < numElems)
    var rover = end.next
    for (i <- 0 until index) {
      rover = rover.next
    }
    rover.data = a
  }

}