package adt2

trait MyMap[K, V] {
  def apply(key: K): V
  def add(key: K, value: V): Unit
  def remove(key: K): V
  def iterator: Iterator[(K, V)]
}