package org.ru2nuts.learn.hackerrank

import java.util.Comparator

import com.google.common.collect.TreeMultiset

/**
  * https://www.hackerrank.com/challenges/median
  */
object MedianUpdates {

  def advance_and_get(iter: java.util.Iterator[Int], steps: Int) = {
    var i = 0
    while (i < steps) {
      i += 1
      iter.next()
    }
    iter.next()
  }

  def calc_med(s: TreeMultiset[Int], sb: StringBuilder) = {
    val n = s.size()
    val siter = s.iterator
    if (n % 2 == 0) {
      val v1 = advance_and_get(siter, n / 2 - 1)
      val v2 = siter.next()
      val sum: Long = v1.toLong + v2.toLong
      if (sum == -1)
        sb.append("-0.5")
      else {
        sb.append(sum / 2)
        val sumMod = sum % 2
        if (sumMod == 1 || sumMod == -1) {
          sb.append('.').append(5)
        }
      }
    } else {
      sb.append(advance_and_get(siter, n / 2))
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val sb = new StringBuilder
    val n = sc.nextInt
    sc.nextLine()

    val ss = com.google.common.collect.TreeMultiset.create[Int](new Comparator[Int] {
      override def compare(o1: Int, o2: Int) = {
        if (o2 < o1)
          -1
        else if (o2 == o1)
          0
        else
          1
      }
    })

    var i = 0
    while (i < n) {
      i += 1
      val op = sc.next
      val item = sc.nextInt
      if (op == "r") {
        var found = ss.remove(item)
        if (found && ss.size() > 0) {
          calc_med(ss, sb)
          sb.append('\n')
        } else {
          sb.append("Wrong!\n")
        }
      } else if (op == "a") {
        ss.add(item)
        calc_med(ss, sb)
        sb.append('\n')
      }
    }
    println(sb)
  }
}
