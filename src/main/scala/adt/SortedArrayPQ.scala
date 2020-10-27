package adt

import scala.reflect.ClassTag

class SortedArrayPQ[A: ClassTag](highestP: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var default = null.asInstanceOf[A]
  private var data = Array.fill(10)(default)
  private var numElems = 0

  def enqueue(a: A): Unit = {
    var found = false
    if(numElems+1 > data.length) expandArray()
    for(i <- 0 until numElems) {
      if(highestP(a, data(i)) & !found){
        for(j <- numElems-1 to i by -1) data(j+1) = data(j)
        data(i) = a
        found = true
      } 
    }
    if(!found) data(numElems) = a
    numElems+=1
  }
  
  def dequeue(): A = {
    assert(numElems>0)
    val ret = data(0)
    for(i <- 1 until numElems) data(i-1) = data(i)
    numElems-=1
    ret
  }
  
  def peek: A = {
    assert(numElems>0)
    data(0)
  }
  
  def isEmpty: Boolean = numElems == 0

  def expandArray(): Unit = {
    var newArray = Array.fill(data.size * 2)(default)
    for(i <- 0 until numElems) newArray(i) = data(i)
    data = newArray
  }

}