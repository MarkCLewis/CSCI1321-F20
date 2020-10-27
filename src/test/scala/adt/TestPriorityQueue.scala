package adt

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class TestPriorityQueue {
  private var pq: MyPriorityQueue[Int] = null

  @Before def init = {
    pq = new UnsortedArrayPQ[Int](_ < _)
  }

  @Test def emptyOnInit = {
    assertTrue(pq.isEmpty)
  }

  @Test def addPeekRemoveAFew = {
    pq.enqueue(8)
    assertFalse(pq.isEmpty)
    assertEquals(8, pq.peek)
    pq.enqueue(3)
    assertEquals(3, pq.peek)
    pq.enqueue(13)
    assertEquals(3, pq.peek)
    assertEquals(3, pq.dequeue())
    assertEquals(8, pq.dequeue())
    assertEquals(13, pq.dequeue())
  }

  @Test def addRemoveMany = {
    val nums = Array.fill(100)(util.Random.nextInt(1000))
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      println(n, pq.peek)
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue())
    }
  }

  @Test def addRemoveManyTwice = {
    val nums = Array.fill(100)(util.Random.nextInt(1000))
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue())
    }
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue())
    }
  }
}