package adt2

import org.junit._
import org.junit.Assert._

class TestQueue {
  var queue: adt.MyQueue[Int] = null

  @Before def initQueue(): Unit = {
    queue = new adt.LinkedQueue[Int]()
  }

  @Test def emptyOnCreate(): Unit = {
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveOne(): Unit = {
    queue.enqueue(5)
    assertFalse(queue.isEmpty)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveMany(): Unit = {
    val nums = Array.fill(100)(util.Random.nextInt)
    nums.foreach(queue.enqueue(_))
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveManyTwice(): Unit = {
    val nums = Array.fill(100)(util.Random.nextInt)
    nums.foreach(queue.enqueue(_))
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
    nums.foreach(queue.enqueue(_))
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }

}