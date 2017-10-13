package org.ru2nuts.learn.hackerrank

import java.util.Comparator

import com.google.common.collect.TreeMultiset

/**
  * https://www.hackerrank.com/challenges/median
  */
object MedianUpdates {

  def advance_and_get(iter: java.util.Iterator[Int], steps: Int) = {
    (0 to steps - 1).foreach(i => iter.next())
    iter.next()
  }

  def calc_med(s: TreeMultiset[Int]): StringBuilder = {
    val n = s.size()
    val siter = s.iterator
    val sb = new StringBuilder()
    if (n % 2 == 0) {
      val v1 = advance_and_get(siter, n / 2 - 1)
      val v2 = siter.next()
      val sum: Long = 0L + v1 + v2
      if (sum == -1)
        sb.append("-0")
      else
        sb.append(sum / 2)
      val sumMod = sum % 2
      if (sumMod == 1 || sumMod == -1) {
        sb.append('.').append(5)
      }
    } else {
      sb.append(advance_and_get(siter, n / 2))
    }
    sb
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val sb = new StringBuilder
    val n = sc.nextInt
    sc.nextLine()

    val ss = com.google.common.collect.TreeMultiset.create[Int](new Comparator[Int] {
      override def compare(o1: Int, o2: Int) = {
        o2.compareTo(o1)
      }
    })

    (1 to n).foreach(i => {
      val op = sc.next
      val item = sc.nextInt

      if (op == "r") {
        var found = ss.remove(item)
        if (found && ss.size() > 0) {
          sb.append(calc_med(ss)).append('\n')
        } else {
          sb.append("Wrong!\n")
        }
      } else if (op == "a") {
        ss.add(item)
        sb.append(calc_med(ss)).append('\n')
      }
    })
    println(sb)
  }
}
