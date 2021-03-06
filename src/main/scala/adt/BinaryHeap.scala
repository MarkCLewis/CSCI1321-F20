package adt

import scala.reflect.ClassTag

class BinaryHeap[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = {
    val ret = heap(1)
    var stone = 1
    back -= 1
    val last = heap(back)
    var flag = true
    while (flag && stone * 2 < back) {
      var higherChild = stone * 2
      if (higherChild + 1 < back && higherP(heap(higherChild + 1), heap(higherChild)))
        higherChild += 1
      if (higherP(heap(higherChild), last)) {
        heap(stone) = heap(higherChild)
        stone = higherChild
      } else flag = false
    }
    heap(stone) = last
    ret
  }

  def enqueue(a: A): Unit = {
    if (back >= heap.length) {
      heap = Array.tabulate(heap.length*2)(i => if (i < heap.length) heap(i) else null.asInstanceOf[A])
    }
    var bubble = back
    while (bubble > 1 && higherP(a, heap(bubble/2))) {
      heap(bubble) = heap(bubble/2)
      bubble /= 2
    }
    heap(bubble) = a
    back += 1
  }

  def isEmpty: Boolean = back == 1

  def peek: A = heap(1)
}