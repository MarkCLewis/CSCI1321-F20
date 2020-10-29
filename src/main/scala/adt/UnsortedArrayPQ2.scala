package adt

import scala.reflect.ClassTag

class UnsortedArrayPQ2[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var numElems = 0
  private var data = new Array[A](10)
  def enqueue(a: A): Unit = {
    if (numElems >= data.length) {
        val tmp = Array.fill(data.length*2)(null.asInstanceOf[A])
        for (i <- 0 until data.length) tmp(i) = data(i)
        data = tmp
    }
    data(numElems) = a
    numElems += 1
  }
  def dequeue(): A = {
    val loc = findHighestPriority()
    val ret = data(loc)
    numElems -= 1
    data(loc) = data(numElems)
    data(numElems) = null.asInstanceOf[A]
    ret
  }
  def peek: A = data(findHighestPriority())

  def isEmpty: Boolean = numElems == 0 

  def findHighestPriority(): Int = {
    var ret = 0
    for (i <- 1 until numElems) {
      if (higherP(data(i), data(ret))) ret = i
    }
    ret
  }
}