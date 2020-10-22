package adt

class LinkedQueue[A] extends MyQueue[A] {
  class Node(val data: A, var next: Node)

  private var front: Node = null
  private var back: Node = null

  def dequeue(): A = {
    val ret = front.data
    front = front.next
    if (front == null) back = null
    ret
  }

  def enqueue(a: A): Unit = {
    val n = new Node(a, null)
    if (isEmpty) front = n else back.next = n
    back = n
  } 

  def isEmpty: Boolean = back == null

  def peek: A = front.data
}