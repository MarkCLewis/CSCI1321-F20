package adt

import scala.reflect.ClassTag

class BinaryHeap[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = ???

  def enqueue(a: A): Unit = ???

  def isEmpty: Boolean = back == 1

  def peek: A = ???
}