package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/maximum-element/problem
  */
object StackMaxElem {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt
    val stack = scala.collection.mutable.Stack[Long]()
    val bag = com.google.common.collect.TreeMultiset.create[Long](new java.util.Comparator[Long]() {
      override def compare(o1: Long, o2: Long) = {
        o2.compare(o1)
      }
    })

    (1 to n).foreach(index => {
      val opType = sc.nextInt()
      if (opType == 1) {
        val v = sc.nextLong()
        stack.push(v)
        bag.add(v, 1)
      } else if (opType == 2) {
        bag.remove(stack.pop(),1)
      } else if (opType == 3) {
        val m = bag.iterator().next()
        println(m)
      }
    })
  }
}
