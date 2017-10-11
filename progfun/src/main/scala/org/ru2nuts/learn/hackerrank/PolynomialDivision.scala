package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/polynomial-division
  */
object PolynomialDivision {

  /**
    * return pairs of (coefficient, exponent) from lower(0) to higher for the polynomial.
    *
    * @param l
    * @param r
    * @param c
    * @return
    */
  def build_px(l: Int, r: Int, c: Array[Long]): Array[(Double, Int)] = {
    var i = -1
    (for (k <- l to r) yield {
      i += 1
      (c(k).toDouble, i)
    }).toArray
  }

  def mult_pol_by_term(pol: Array[(Double, Int)], term: (Double, Int)): Array[(Double, Int)] =
    pol.map(e => (e._1 * term._1, e._2 + term._2))

  def subtract_pol(x: Array[(Double, Int)], y: Array[(Double, Int)]): Array[(Double, Int)] = {
    val n = math.max(x.last._2, y.last._2)
    val res = new Array[(Double, Int)](n + 1)

    var i = 0
    var j = 0
    var k = 0
    while (k <= n) {
      if (x(i)._2 == k && y(j)._2 == k) { //matching exponent
        res(k) = (x(i)._1 - y(j)._1, k)
        i += 1
        j += 1
      } else if (x(i)._2 == k && y(j)._2 > k) { // x exp matches
        res(k) = (x(i)._1, k)
        i += 1
      } else if (x(i)._2 > k && y(j)._2 == k) { // y exp matches
        res(k) = (-y(j)._1, k)
        j += 1
      } else {
        res(k) = (0, k)
      }
      k += 1
    }
    res
  }

  def get_highest_term(x: Array[(Double, Int)]): (Double, Int) =
    x.last

  def div_terms(numerator: (Double, Int), denominator: (Double, Int)): (Double, Int) =
    (numerator._1 / denominator._1, numerator._2 - denominator._2)

  def add_term(pol: Array[(Double, Int)], term: (Double, Int)): Array[(Double, Int)] = {
    if (pol.last._2 < term._2) {
      val res = new mutable.ArrayBuffer[(Double, Int)](pol.length + 1)
      res ++= pol
      res += term
      res.toArray
    } else if (pol.head._2 > term._2) {
      val res = new mutable.ArrayBuffer[(Double, Int)](pol.length + 1)
      res += term
      res ++= pol
      res.toArray
    } else {
      pol.map(t => {
        if (t._2 == term._2) {
          (t._1 + term._1, t._2)
        } else
          t
      })
    }
  }

  private val precision = 0.000001

  def compact_pol(pol: Array[(Double, Int)]): Array[(Double, Int)] =
    pol.filter(t => math.abs(t._1) > precision)

  def pol_div(numerator: Array[(Double, Int)], denominator: Array[(Double, Int)]): (Array[(Double, Int)], Array[(Double, Int)]) = {

    if (numerator.isEmpty || get_highest_term(numerator)._2 < get_highest_term(denominator)._2) {
      (Array[(Double, Int)]((0.0, 0)), numerator)
    } else {

      val res_term = div_terms(get_highest_term(numerator), get_highest_term(denominator))

      val subtracting_pol = mult_pol_by_term(denominator, res_term)

      val remainder = compact_pol(subtract_pol(numerator, subtracting_pol))

      val z = pol_div(remainder, denominator)

      val res = add_term(z._1, res_term)

      (res, z._2)
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var a = sc.nextLong();
    var b = sc.nextLong();
    var q = sc.nextInt();
    var c = new Array[Long](n);
    for (c_i <- 0 to n - 1) {
      c(c_i) = sc.nextLong();
    }
    var a0 = 0;
    while (a0 < q) {
      var queryType = sc.nextInt();
      var first = sc.nextInt();

      if (queryType == 1) {
        val i = first
        val x = sc.nextLong();
        c(i) = x
      }
      else if (queryType == 2) {
        val l = first
        val r = sc.nextInt();

        val px: Array[(Double, Int)] = build_px(l, r, c)
        val qx = Array[(Double, Int)]((b, 0), (a, 1))

        val res = pol_div(px, qx)

        if (res._2.isEmpty) {
          println("Yes")
        } else if (math.abs(res._2.head._1) > precision) {
          println("No")
        }
      }
      a0 += 1;
    }
  }

  def mainz(args: Array[String]): Unit = {
    {
      val numr = Array[(Double, Int)]((10.0, 3), (6.0, 2), (12, 1), (5, 0)).reverse
      val denom = Array[(Double, Int)]((2.0, 2), (10.0, 0)).reverse

      val z = pol_div(numr, denom)
      println(z)
    }
    {
      val numr = Array[(Double, Int)]((12, 1), (5, 0)).reverse
      val denom = Array[(Double, Int)]((2.0, 2), (10.0, 0)).reverse

      val z = pol_div(numr, denom)
      println(z)
    }
    {
      val numr = Array[(Double, Int)]((1, 3), (1, 2)).reverse
      val denom = Array[(Double, Int)]((1, 2), (1, 1), (-2, 0)).reverse

      val z = pol_div(numr, denom)
      println(z)
    }
  }
}
