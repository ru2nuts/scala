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

  def calc_med(s: TreeMultiset[Int]): (Int, Int) = {
    val n = s.size()
    val isEven = (n % 2 == 0)

    val siter = s.iterator
    if (isEven) {
      val v1 = advance_and_get(siter, n / 2 - 1)
      val v2 = siter.next()
      val sum = v1 + v2
      (sum / 2, if (sum % 2 == 1) 5 else 0)
    } else {
      (advance_and_get(siter, s.size / 2), 0)
    }
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
          val med: (Int, Int) = calc_med(ss)
          sb.append(med._1).append(if (med._2 > 0) "." + med._2 else "").append("\n")
        } else {
          sb.append("Wrong!\n")
        }
      } else if (op == "a") {
        ss.add(item)
        val med: (Int, Int) = calc_med(ss)
        sb.append(med._1).append(if (med._2 > 0) "." + med._2 else "").append("\n")
      }
    })
    println(sb)
    val q = sc.nextInt
  }
}
