package adt

import scala.reflect.ClassTag

class SortedArrayPQ2[A: ClassTag](highestP: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var data = Array.fill(10)(null.asInstanceOf[A])
  private var numElems = 0

  def enqueue(a: A): Unit = {
    var found = false
    if(numElems+1 > data.length) expandArray()
    var i = numElems - 1
    while (i >= 0 && highestP(data(i), a)) {
      data(i + 1) = data(i)
      i -= 1
    }
    data(i + 1) = a
    numElems+=1
  }
  
  def dequeue(): A = {
    assert(numElems>0)
    val ret = data(numElems-1)
    data(numElems-1) = null.asInstanceOf[A]
    numElems-=1
    ret
  }
  
  def peek: A = {
    assert(numElems>0)
    data(numElems-1)
  }
  
  def isEmpty: Boolean = numElems == 0

  def expandArray(): Unit = {
    var newArray = Array.fill(data.size * 2)(null.asInstanceOf[A])
    for(i <- 0 until numElems) newArray(i) = data(i)
    data = newArray
  }
}