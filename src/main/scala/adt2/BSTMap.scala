package adt2

import collection.mutable

class BSTMap[K, V](lt: (K, K) => Boolean) extends MyMap[K, V] {
  private class Node(var kv: (K, V), var left: Node, var right: Node)
  private var root: Node = null

  def apply(key: K): V = {
    def helper(n: Node): V = {
      if (n.kv._1 == key) n.kv._2
      else if (lt(key, n.kv._1)) helper(n.left)
      else helper(n.right)
    }
    helper(root)
  }

  def add(key: K, value: V): Unit = {
    def helper(n: Node): Node = {
      if (n == null) new Node((key, value), null, null)
      else {
        if (n.kv._1 == key) n.kv = key -> value
        else if (lt(key, n.kv._1)) n.left = helper(n.left)
        else n.right = helper(n.right)
        n
      }
    }
    root = helper(root)
  }

  def remove(key: K): Option[V] = {
    def grabLargest(n: Node): (Node, Node) = {
      if (n.right != null) {
        val (link, largest) = grabLargest(n.right)
        n.right = link
        (n, largest)
      } else (n.left, n)
    }

    def findVictim(n: Node): (Node, Option[V]) = {
      if (n == null) (null, None)
      else if (key == n.kv._1) {
        if (n.left == null) (n.right, Some(n.kv._2))
        else if (n.right == null) (n.left, Some(n.kv._2))
        else {
          val (link, largestLeft) = grabLargest(n.left)
          largestLeft.left = link
          largestLeft.right = n.right
          (largestLeft, Some(n.kv._2))
        }
      } else {
        if (lt(key, n.kv._1)) {
          val (link, v) = findVictim(n.left)
          n.left = link
          (n, v)
        } else {
          val (link, v) = findVictim(n.right)
          n.right = link
          (n, v)
        }
      }
    }

    val (link, v) = findVictim(root)
    root = link
    v
  }

  def iterator = new Iterator[(K, V)] {
    val stack = new mutable.Stack[Node]()
    def pushAllLeft(n: Node): Unit = if (n != null) {
      stack.push(n)
      pushAllLeft(n.left)
    }
    pushAllLeft(root)
    def hasNext: Boolean = stack.nonEmpty
    def next(): (K, V) = {
      val top = stack.pop()
      pushAllLeft(top.right)
      top.kv
    }
  }

  def preorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = {
      if (n != null) {
        visit(n.kv._1, n.kv._2)
        helper(n.left)
        helper(n.right)
      }
    }
    helper(root)
  }

  def postorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = {
      if (n != null) {
        helper(n.left)
        helper(n.right)
        visit(n.kv._1, n.kv._2)
      }
    }
    helper(root)
  }

  def inorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = {
      if (n != null) {
        helper(n.left)
        visit(n.kv._1, n.kv._2)
        helper(n.right)
      }
    }
    helper(root)
  }
}