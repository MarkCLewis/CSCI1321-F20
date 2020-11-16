package adt

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import collection.mutable

class TestBSTMap {
  private var bst: BSTMap[String, Int] = null

  @Before def init = bst = new BSTMap[String, Int](_ < _)

  @Test def emptyOnStart = {
    //assertTrue(bst.isEmpty)
  }

  @Test def addGetOne = {
    bst += "one" -> 1
    assertEquals(Some(1), bst.get("one"))
    assertEquals(None, bst.get("two"))
  }

  @Test def addGetThree = {
    bst += "one" -> 1 += "two" -> 2 += "three" -> 3
    assertEquals(Some(1), bst.get("one"))
    assertEquals(Some(2), bst.get("two"))
    assertEquals(Some(3), bst.get("three"))
  }

  @Test def preOrderTest = {
    bst += "one" -> 1 += "two" -> 2 += "three" -> 3 += "four" -> 4 += "five" -> 5
    val buf = mutable.Buffer[String]()
    bst.preorder((k, v) => buf += k)
    assertEquals(mutable.Buffer("one", "four", "five", "two", "three"), buf)
  }

  @Test def postOrderTest = {
    bst += "one" -> 1 += "two" -> 2 += "three" -> 3 += "four" -> 4 += "five" -> 5
    val buf = mutable.Buffer[String]()
    bst.postorder((k, v) => buf += k)
    assertEquals(mutable.Buffer("five", "four", "three", "two", "one"), buf)
  }

  @Test def inOrderTest = {
    bst += "one" -> 1 += "two" -> 2 += "three" -> 3 += "four" -> 4 += "five" -> 5
    val buf = mutable.Buffer[String]()
    bst.inorder((k, v) => buf += k)
    assertEquals(mutable.Buffer("five", "four", "one", "three", "two"), buf)
  }
}