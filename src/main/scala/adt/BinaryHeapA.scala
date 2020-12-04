package adt

import scala.reflect.ClassTag

class BinaryHeapA[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = {
    val ret = heap(1)
    def recur(idx: Int): Unit = {
      var child = idx*2
      if(child+1 <= back){
        if(higherP(heap(child), heap(child+1))) {
          heap(idx) = heap(child)
          heap(child) = null.asInstanceOf[A]
          recur(child)
        }
        else{
          heap(idx) = heap(child+1)
          heap(child+1) = null.asInstanceOf[A]
          recur(child+1)
        }
      }else if(child <= back){
        heap(idx) = heap(child)
        heap(child) = null.asInstanceOf[A]
        recur(child)
      }else{
        heap(idx) = null.asInstanceOf[A]
      }
    }
    recur(1)
    back -= 1
    ret
  }

  def enqueue(a: A): Unit = {
    back += 1
    if(back > heap.size){
      var newHeap = Array.fill(heap.size*2)(null.asInstanceOf[A])
      for(i <- 1 until back){
        newHeap(i) = heap(i)
      }
      heap = newHeap
    }
    var bubble = back
    while(bubble >= 2 && higherP(a, heap(bubble/2))){
      heap(bubble) = heap(bubble/2)
      bubble /= 2
    }
    if(bubble == 1) heap(1) = a
  }

  def isEmpty: Boolean = back == 1

  def peek: A = heap(1)

}