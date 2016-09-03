package org.ru2nuts.learn

import scala.collection.Seq
import scala.io.StdIn._

object HackerRankSolutions {

  def main1(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    //
    //
    //    val n = readLine().toInt
    //    val c: Array[Int] = readLine().split(" ").map(_.toInt)
    //
    //    val edges: Array[(Int, Int)] = new Array[(Int, Int)](n - 1)
    //    for (i <- 0 to (n - 2)) {
    //      edges(i) = readLine().split(" ").map(_.toInt).reduce((a, b) => (a, b))
    //    }
    //
    //    val q = readLine().toInt
    //    val newEdges: Array[(Int, Int)] = new Array[(Int, Int)](q)
    //    for (i <- 0 to q - 1) {
    //      newEdges(i) = readLine().split(" ").map(_.toInt).reduce((a, b) => (a, b))
    //    }
    //    0

  }

  def main2(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.nextInt();
    }

    println(arr.reduce((a, b) => a + b))
  }

  def main3(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var a0 = sc.nextInt();
    var a1 = sc.nextInt();
    var a2 = sc.nextInt();
    var b0 = sc.nextInt();
    var b1 = sc.nextInt();
    var b2 = sc.nextInt();

    def one(a: Int, b: Int) =
      if (a > b) 1 else 0

    val aa = one(a0, b0) + one(a1, b1) + one(a2, b2)
    val bb = one(b0, a0) + one(b1, a1) + one(b2, a2)

    println(s"${aa} ${bb}")

  }


  def main4(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var s = sc.next();

    println(s.count(_.isUpper) + 1)

  }

  def main5(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var k = sc.nextInt();
    var a = new Array[Int](n);
    for (a_i <- 0 to n - 1) {
      a(a_i) = sc.nextInt();
    }

    var res = (for (i <- 0 to n - 2) yield {
      (for (j <- i + 1 to n - 1) yield {
        val sum = a(i) + a(j)
        if (sum % k == 0) 1 else 0
      }).reduce(_ + _)
    }).reduce(_ + _)

    println(res)

  }

  def main6(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var S = sc.next();


    val pattern = "SOS" * (S.length / 3)
    val res = pattern.zip(S).map(z => (if (z._1 != z._2) 1 else 0)).reduce(_ + _)

    println(res)
  }

  def main7(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.nextInt();
    }

    var res = arr.map(_.toLong).reduce(_ + _)
    println(res)
  }


  def main8(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var a = Array.ofDim[Int](n, n);
    for (a_i <- 0 to n - 1) {
      for (a_j <- 0 to n - 1) {
        a(a_i)(a_j) = sc.nextInt();
      }
    }

    val s1 = (for (i <- 0 to n - 1) yield a(i)(i)).reduce(_ + _)
    val s2 = (for (i <- 0 to n - 1) yield a(i)(n - 1 - i)).reduce(_ + _)
    println(Math.abs(s1 - s2))
  }

  def main9(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.nextInt();
    }

    val pos = arr.count(_ > 0).toDouble
    val neg = arr.count(_ < 0).toDouble
    val zer = arr.count(_ == 0).toDouble

    println(pos / n)
    println(neg / n)
    println(zer / n)
  }

  def main10(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();

    for (i <- 1 to n) {
      print(" " * (n - i))
      println("#" * i)
    }
  }

  def main11(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var time = sc.next();

    val isPM = time.takeRight(2) == "PM"
    val origHour = time.take(2).toInt
    val min = time.substring(3, 5).toInt
    val sec = time.substring(6, 8).toInt
    val hour = if (origHour == 12) (if (isPM) origHour else 0) else origHour + (if (isPM) 12 else 0)
    println(f"$hour%02d:$min%02d:$sec%02d")
  }

  def main12(args: Array[String]) {
    val n = readLine().toInt
    val c: Array[Int] = readLine().split(" ").map(_.toInt)
    val p = c.head
    val arr = c.tail
    val left = arr.filter(_ < p).sorted
    val eq = arr.filter(_ == p).union(Seq(p))
    val right = arr.filter(_ > p)
    println(left.union(eq).union(right).mkString(" "))
  }

  def main13(args: Array[String]) {

    def sort(c: Array[Int]): Array[Int] = {
      if (c.isEmpty || c.length == 1) c
      else {
        val p = c.head
        val arr = c.tail
        val left = sort(arr.filter(_ < p))
        val eq = sort(arr.filter(_ == p).union(Seq(p)))
        val right = sort(arr.filter(_ > p))
        val res = left.union(eq).union(right)
        println(res.mkString(" "))
        res
      }
    }
    val n = readLine().toInt
    val c: Array[Int] = readLine().split(" ").map(_.toInt)
    sort(c)
  }

  def main14(args: Array[String]) {
    val t = readLine().toInt
    for (i <- 1 to t) {
      var n = readLine().toInt
      val c: Array[Int] = readLine().split(" ").map(_.toInt)

      var s1 = c(0)
      for (i <- 0 to n - 1) {
        for (j <- i + 1 to n) {
          val mc = c.slice(i, j).sum
          if (mc > s1) s1 = mc
        }
      }
      val allAr = c.filter(_ > 0)
      val s2 = if (allAr.isEmpty) c.max else allAr.sum
      print(s1)
      print(" ")
      println(s2)
    }
  }


  def main15(args: Array[String]) = {

    def fibonacciSeqMod(t1: BigInt, t2: BigInt, sizeLimit: BigInt): Seq[BigInt] = {
      def recur(current: List[BigInt], sizeLimit: BigInt): List[BigInt] = {
        if (current.size >= sizeLimit)
          current
        else {
          val z = current.takeRight(2)
          val (a1, a2) = (z(0), z(1))
          val a3: BigInt = a1 + a2 * a2

          val newCur: List[BigInt] = current ::: a3 :: Nil
          recur(newCur, sizeLimit)
        }
      }
      val cur = t1 :: t2 :: Nil
      recur(cur, sizeLimit)
    }

    val c: Array[Int] = readLine().split(" ").map(_.toInt)

    val t1 = c(0)
    val t2 = c(1)
    val n = c(2)

    val tn = fibonacciSeqMod(t1, t2, n).takeRight(1)(0)

    println(tn)
  }

  /* https://www.hackerrank.com/challenges/synchronous-shopping */
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var n = sc.nextInt()
    var m = sc.nextInt()
    var k = sc.nextInt()

    // shopping center type: number of fish types, array of fish types
    var shoppingCenters = new Array[(Int, Array[Int])](n)
    for (i <- 0 to n - 1) {
      val tn = sc.nextInt()
      val fishTypes = new Array[Int](tn)
      for (j <- 0 to tn - 1) {
        fishTypes(j) = sc.nextInt()
      }
      shoppingCenters(i) = (tn, fishTypes)
    }

    // road type: node 1, node 2, edge weight
    val road = new Array[(Int, Int, Int)](m)
    for (i <- 0 to n - 1) {
      road(i) = (sc.nextInt(), sc.nextInt(), sc.nextInt())
    }

    // get a set (S) of nodes with distinct fish types (so that we collect all types of fish)
    // add to that set nodes 1 and N
    // all combinations of 2 sub-paths form 1 to N, covering all nodes in S (min sub-path is 1->N)
    // traverse all combinations, choose min total weight one

    // all combinations of 2 sub-paths from 1->N (which collect all fish types)
    // choose min total weight combination
    // e.g.: nodes 1,2,3,N
    // 1->N, 1->2->N, 1->3-N, 1->3-N, 1->3->2->N

    // all paths from 1 -> N
    // all combinations of two paths (different or same)
    // choose only combinations, that collect all fish types
    // find combo with min total weight

    //type Node =

  }


}