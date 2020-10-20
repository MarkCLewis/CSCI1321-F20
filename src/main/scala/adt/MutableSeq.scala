package adt

trait MutableSeq[A] {
  def apply(index: Int): A
  def insert(index: Int, a: A): Unit
  def remove(index: Int): A
  def update(index: Int, a: A): Unit
  def length: Int
}