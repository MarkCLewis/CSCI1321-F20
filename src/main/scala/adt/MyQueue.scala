package adt

trait MyQueue[A] {
  def enqueue(a: A): Unit

  /**
    * Removes the element that has been in the longest.
    *
    * @return
    */
  def dequeue(): A
  def peek: A
  def isEmpty: Boolean
  
}