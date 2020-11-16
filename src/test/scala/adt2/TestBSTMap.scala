package adt2

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import collection.mutable

class TestBSTMap {
  var bst: BSTMap[String, Int] = null

  @Before def init = bst = new BSTMap[String, Int](_ < _)

  @Test def addGetOne = {
    bst.add("a", 1)
    assertEquals(1, bst("a"))
  }

  @Test def addGetFour = {
    bst.add("b", 2)
    bst.add("a", 1)
    bst.add("d", 4)
    bst.add("c", 3)
    assertEquals(1, bst("a"))
    assertEquals(2, bst("b"))
    assertEquals(3, bst("c"))
    assertEquals(4, bst("d"))
  }

  @Test def testpreOrder = {
    bst.add("c", 3)
    bst.add("b", 2)
    bst.add("a", 1)
    bst.add("e", 5)
    bst.add("d", 4)
    val buf = mutable.Buffer[String]()
    bst.postorder((k, v) => buf += k)
    assertEquals(mutable.Buffer("a", "b", "d", "e", "c"), buf)
  }

  @Test def testinOrder = {
    bst.add("c", 3)
    bst.add("b", 2)
    bst.add("a", 1)
    bst.add("e", 5)
    bst.add("d", 4)
    val buf = mutable.Buffer[String]()
    bst.inorder((k, v) => buf += k)
    assertEquals(mutable.Buffer("a", "b", "c", "d", "e"), buf)
  }

  @Test def addGetMany = {
    val data = Array.fill(1000)(util.Random.nextString(10), util.Random.nextInt)
    for (kv <- data) bst.add(kv._1, kv._2)
    for ((key, value) <- data) assertEquals(value, bst(key))
  }

  @Test def addTraverseMany = {
    val data = Array.fill(1000)(util.Random.nextString(10), util.Random.nextInt)
    for (kv <- data) bst.add(kv._1, kv._2)
    for (((key, value), kv) <- data.sorted.zip(bst.iterator.toSeq)) assertEquals(value, kv._2)
  }

  @Test def addRemoveFew = {
    bst.add("one", 1)
    bst.add("two", 2)
    bst.add("three", 3)
    bst.add("four", 4)
    bst.add("five", 5)
    assertEquals(Some(1), bst.remove("one"))
    assertEquals(Some(3), bst.remove("three"))
    assertEquals(2, bst("two"))
    assertEquals(4, bst("four"))
    assertEquals(5, bst("five"))
  }

  @Test def addRemoveGetMany = {
    val data = Array.fill(1000)(util.Random.nextString(10), Option(util.Random.nextInt))
    for (kv <- data) bst.add(kv._1, kv._2.get)
    for (_ <- 1 to 50) {
      val index = util.Random.nextInt(data.length)
      assertEquals(data(index)._2, bst.remove(data(index)._1))
      data(index) = (data(index)._1, None)
    }
    for ((key, value) <- data) {
      if(value.nonEmpty) assertEquals(value.get, bst(key))
    }
  }

}