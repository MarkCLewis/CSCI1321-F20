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
}