package org.ru2nuts.learn.hackerrank

import java.util.Comparator

/**
  * https://www.hackerrank.com/challenges/find-the-running-median
  */
object FindRunningMedian {

  val leftDesc = com.google.common.collect.TreeMultiset.create[Int](new Comparator[Int] {
    override def compare(o1: Int, o2: Int) = {
      if (o2 < o1)
        -1
      else if (o2 == o1)
        0
      else
        1
    }
  })

  val rightAsc = com.google.common.collect.TreeMultiset.create[Int](new Comparator[Int] {
    override def compare(o1: Int, o2: Int) = {
      if (o1 < o2)
        -1
      else if (o1 == o2)
        0
      else
        1
    }
  })

  def add_item(item: Int) = {
    if (rightAsc.size() > 0 && item >= rightAsc.iterator().next()) {
      rightAsc.add(item)
    } else
      leftDesc.add(item)
    rebalance()
  }

  def remove_item(item: Int) = {
    if (leftDesc.remove(item)) {
      rebalance()
      true
    } else if (rightAsc.remove(item)) {
      rebalance()
      true
    } else {
      false
    }
  }

  def size() =
    leftDesc.size() + rightAsc.size()

  def rebalance() = {
    var leftSize = leftDesc.size()
    var rightSize = rightAsc.size()
    if (math.abs(leftSize - rightSize) > 1) {
      if (leftSize < rightSize)
        while (leftSize < rightSize) {
          val e = rightAsc.iterator().next()
          rightAsc.remove(e)
          leftDesc.add(e)
          rightSize -= 1
          leftSize += 1
        }
      else if (leftSize > rightSize)
        while (leftSize > rightSize) {
          val e = leftDesc.iterator().next()
          leftDesc.remove(e)
          rightAsc.add(e)
          leftSize -= 1
          rightSize += 1
        }
    }
  }

  def get_median(sb: StringBuilder) = {
    val leftSize = leftDesc.size()
    val rightSize = rightAsc.size()
    if (leftSize < rightSize) {
      sb.append(rightAsc.iterator().next()).append('.').append(0)
    } else if (leftSize > rightSize) {
      sb.append(leftDesc.iterator().next()).append('.').append(0)
    } else {
      val v1 = leftDesc.iterator().next()
      val v2 = rightAsc.iterator().next()
      val sum: Long = v1.toLong + v2.toLong
      if (sum == -1)
        sb.append("-0.5")
      else {
        sb.append(sum / 2)
        val sumMod = sum % 2
        if (sumMod == 1 || sumMod == -1) {
          sb.append('.').append(5)
        } else {
          sb.append('.').append(0)
        }
      }
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val sb = new StringBuilder
    val n = sc.nextInt
    sc.nextLine()

    var i = 0
    while (i < n) {
      i += 1
      val item = sc.nextInt
      add_item(item)
      get_median(sb)
      sb.append('\n')
    }
    println(sb)
  }
}
