package adt

import scala.reflect.ClassTag

class BinaryHeapC[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var back = 1

  def dequeue(): A = {
    var ret = heap(1)
    var flag = true
    var stone = 1
    heap(1) = heap(back)
    back -= 1
    while (flag && stone*2 < back) {
      var left = stone*2
      var right = stone*2+1
      var hp = 0
      if (higherP(heap(stone), heap(left))) {
        hp = left
      }
      else if (higherP(heap(stone), heap(right))) {
        hp = right
      }
      if (hp != 0) {
        var temp = heap(stone)
        heap(stone) = heap(hp)
        heap(hp) = temp
        stone = hp 
      }
      else {
        flag = false
      }
    }
    ret
  }

  def enqueue(a: A): Unit = {
    var bubble = back
    heap(bubble) = a
    while (higherP(a, heap(bubble/2)) && bubble < 1) {
      heap(bubble) = heap(bubble/2)
      heap(bubble/2) = a
      bubble = bubble/2
    }
    back += 1
  }

  def isEmpty: Boolean = back == 1

  def peek: A = heap(1)
}