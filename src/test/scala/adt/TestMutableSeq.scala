package adt

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import scala.collection.mutable

class TestMutableSeq {
  var seq: MutableSeq[Int] = null

  @Before def initSequence: Unit = {
    seq = new SinglyLL[Int]()
  }

  @Test def emptyOnCreate: Unit = {
    assertEquals(0, seq.length)
  }

  @Test def addGetTest: Unit = {
    seq.insert(0, 5)
    assertEquals(1, seq.length)
    assertEquals(5, seq(0))
    seq.insert(1, 4)
    assertEquals(2, seq.length)
    assertEquals(4, seq(1))
    seq.insert(0, 7)
    assertEquals(3, seq.length)
    assertEquals(7, seq(0))
    assertEquals(5, seq(1))
    assertEquals(4, seq(2))
  }

  @Test def addUpdateGetTest: Unit = {
    seq.insert(0, 0)
    seq.insert(1, 0)
    seq.insert(2, 0)
    seq.insert(3, 0)
    assertEquals(4, seq.length)
    seq(0) = 9
    seq(1) = 8
    seq(2) = 7
    seq(3) = 6
    assertEquals(9, seq(0))
    assertEquals(8, seq(1))
    assertEquals(7, seq(2))
    assertEquals(6, seq(3))
  }

  @Test def addRemoveTest: Unit = {
    for (i <- 1 to 10) seq.insert(i-1, i)
    assertEquals(10, seq.length)
    seq.remove(1)  // remove 2
    seq.remove(2)  // remove 4
    seq.remove(3)  // remove 6
    seq.remove(4)  // remove 8
    seq.remove(5)  // remove 10
    assertEquals(5, seq.length)
    assertEquals(1, seq(0))
    assertEquals(3, seq(1))
    assertEquals(5, seq(2))
    assertEquals(7, seq(3))
    assertEquals(9, seq(4))
  }

  @Test def bigTest: Unit = {
    val nums = mutable.ArrayBuffer.fill(1000)(util.Random.nextInt(10000))
    for (i <- nums.indices) seq.insert(i, nums(i))
    assertEquals(nums.length, seq.length)
    for (i <- nums.indices) assertEquals(nums(i), seq(i))
    for (i <- nums.indices.reverse.by(5)) {
      val n1 = nums.remove(i)
      val n2 = seq.remove(i)
      assertEquals(n1, n2)
    }
    assertEquals(nums.length, seq.length)
    for (i <- nums.indices) {
      assertEquals(nums(i), seq(i))
    }
  }
}