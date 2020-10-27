package adt

import scala.reflect.ClassTag

class UnsortedArrayPQ[A: ClassTag](comp: (A, A) => Int) extends MyPriorityQueue[A] {
  private var front = 0
  private var back = 0
  private var data = new Array[A](10)
  def enqueue(a: A): Unit = {
    if ((back + 1) % data.length == front) {
        val tmp = Array.fill(data.length*2)(null.asInstanceOf[A])
        for (i <- 0 until data.length-1) tmp(i) = data((front + i) % data.length)
        front = 0
        back = data.length - 1
        data = tmp
    }
    data(back) = a
    back = (back + 1) % data.length
    }
  def dequeue(): A = {
    var temp = data(front)
    for(i <- 0 until data.length) {
      // if(comp(temp, data(i))) {
      //   temp = data(i)
      // }
    }
    for(i <- data.indexOf(temp) until data.length-1) {
      data(i) = data(i+1)
    }
    temp
  }
  def peek: A = data(front)
  def isEmpty: Boolean = front == back

}