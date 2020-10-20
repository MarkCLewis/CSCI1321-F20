package adt

class SinglyLL[A] extends MutableSeq[A] {
  private class Node(var data: A, var next: Node)

  private var head: Node = null
  private var tail: Node = null
  private var numElems = 0

  def apply(index: Int): A = {
    assert(index >= 0 && index < numElems)
    var rover = head
    for (i <- 0 until index) {
      rover = rover.next
    }
    rover.data
  }

  def insert(index: Int,a: A): Unit = {
    assert(index >= 0 && index <= numElems)
    if (index == 0) {
      head = new Node(a, head)
    }
    var rover = head
    for (i <- 0 until index-1) {
      rover = rover.next
    }
    rover.next = new Node(a, rover.next)
    numElems += 1
  }

  def length: Int = numElems

  def remove(index: Int): A = ???

  def update(index: Int,a: A): Unit = {
    assert(index >= 0 && index < numElems)
    var rover = head
    for (i <- 0 until index) {
      rover = rover.next
    }
    rover.data = a
  }

}