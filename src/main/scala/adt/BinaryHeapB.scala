package adt

import scala.reflect.ClassTag

class BinaryHeapB[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = {
    val ret = heap(1)
    var curr = 1
    var left = heap(1)
    var right = heap(1)
    while(curr*2 < back) {

      left = heap(curr*2)
      if(curr*2+1 < back) right = heap((curr*2)+1)
      else right = left

      if(higherP(left, right)) {
        heap(curr) = left
        curr = curr*2
      }
      
      else if(higherP(right, left)) {
        heap(curr) = right
        curr = (curr*2) +1
      }
      
    }

    heap(curr) = heap(back)
    back -= 1
    ret
  }

  def enqueue(a: A): Unit = {
    var location = back - 1
    while(higherP(a, heap(back)) && location > 1){
      heap(location + 1) = heap(location)
      location /= 2
    }
  
    back+=1
  }

  def isEmpty: Boolean = back == 1

  def peek: A = heap(1)
}