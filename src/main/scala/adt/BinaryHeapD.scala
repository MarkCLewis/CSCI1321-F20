package adt

import scala.reflect.ClassTag

class BinaryHeapD[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = {
    val ret = heap(1)
    heap(1) = heap(back)
    var flag = true
    var i = 1
    back-=1
    while (flag && (i*2) < back) {
      var hpc: A = null.asInstanceOf[A]
      if ((i*2+1) < back) hpc = if(higherP(heap(i*2),heap(i*2+1))) heap(i*2) else heap(i*2+1)
      if(higherP(hpc, heap(i))) {
        val tmp = heap(i)
        heap(i) = hpc
        //gotta set the node at location of hpc to the value at heap(i)
        i*=2
      } else flag = false
    }
    ret
  }

  def enqueue(a: A): Unit = {
    heap(back) = a
    var i = back
    while (higherP(a, heap(i/2)) && i > 0) {
        val tmp = heap(i/2)
        heap(i/2) = a
        heap(i) = tmp
        i/=2
    }
    back += 1
  }

  def isEmpty: Boolean = back == 1

  def peek: A = heap(1)
}