package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/median
  */
object MedianUpdates {

  def calc_med(ss: Seq[Int]): (Int, Int) = {
    val s = ss
    val n = s.length
    val isEven = (n % 2 == 0)
    if (isEven) {
      val v1 = s(s.length / 2 - 1)
      val v2 = s(s.length / 2)
      val sum = v1 + v2
      (sum / 2, if (sum % 2 == 1) 5 else 0)
    } else {
      (s(s.length / 2), 0)
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt
    sc.nextLine()

    var ss = scala.collection.mutable.PriorityQueue[Int]()

    (1 to n).foreach(i => {
      val op = sc.next
      val item = sc.nextInt

      if (op == "r") {
        var found = false
        ss = ss.filter(e => {
          if (!found && e == item) {
            found = true
            false
          } else
            true
        })
        if (found && ss.length > 0) {
          val med: (Int, Int) = calc_med(ss.dequeueAll)
          println(med._1 + (if (med._2 > 0) "." + med._2 else ""))
        } else {
          println("Wrong!")
        }
      } else if (op == "a") {
        ss += item
        val med: (Int, Int) = calc_med(ss.dequeueAll)
        println(med._1 + (if (med._2 > 0) "." + med._2 else ""))
      }
    })
    val q = sc.nextInt
  }


}
