package org.ru2nuts.learn.carreercup

import org.scalatest.FunSuite

class ProbabilityUtilsTest extends FunSuite {

  test("testNChooseKF") {
    val n: Int = 26
    val k: Int = 3
    val pr: ProbabilityUtils = new ProbabilityUtils
    val res: Double = pr.nChooseKF(n, k)
    assertResult(2600)(res)
    println(res)
  }

  test("testNChooseKI") {
    val n: Int = 26
    val k: Int = 3
    val pr: ProbabilityUtils = new ProbabilityUtils
    val res: Double = pr.nChooseKI(n, k)
    assertResult(2600)(res)
    println(res)
  }

  test("testFactorialI") {
    val n: Int = 26
    val pr: ProbabilityUtils = new ProbabilityUtils
    val res: Double = pr.factorialI(n)
    assertResult(4.0329146112660565E26)(res)
    println(res)
  }

  test("testFactorialR") {
    val n: Int = 26
    val pr: ProbabilityUtils = new ProbabilityUtils
    val res: Double = pr.factorialR(n)
    assertResult(4.0329146112660565E26)(res)
  }

  test("testCatalanF") {
    val utils: ProbabilityUtils = new ProbabilityUtils()
    var i: Int = 0
    while (i < 20) {
      {
        println("==================")
        println(utils.catalanF(i))
      }
      {
        i += 1;
        i - 1
      }
    }

  }

  test("testCatalanI") {
    val utils: ProbabilityUtils = new ProbabilityUtils()
    var i: Int = 0
    while (i < 20) {
      {
        println(utils.catalanI(i))
      }
      {
        i += 1;
        i - 1
      }
    }

  }

}
