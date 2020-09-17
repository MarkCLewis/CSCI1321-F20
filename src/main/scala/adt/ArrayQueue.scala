package adt

import scala.reflect.ClassTag

class ArrayQueue[A : ClassTag] extends MyQueue[A] {
  private var front = 0
  private var back = 0
  private var data = Array.fill(10)(null.asInstanceOf[A])

  def dequeue(): A = {
    val ret = data(front)
    front = (front + 1) % data.length
    ret
  }

  def enqueue(a: A): Unit = {
    // TODO: Grow the array if full
    data(back)
    back = (back + 1) % data.length
  }

  def isEmpty: Boolean = front == back

  def peek: A = data(front)
}