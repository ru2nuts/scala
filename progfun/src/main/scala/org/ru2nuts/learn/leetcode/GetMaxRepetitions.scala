package org.ru2nuts.learn.leetcode

object GetMaxRepetitions {

  def multS(s: String, n: Int) = {
    val ss = new StringBuilder(s.length * n)
    (1 to n).foreach(_ => ss.append(s))
    ss.toString()
  }

  def canBeObtained(s1: String, s2: String): Boolean = {
    var curS2Ind = 0
    s1.foreach(c1 => {
      if (curS2Ind < s2.length && c1 == s2.charAt(curS2Ind)) {
        curS2Ind = curS2Ind + 1
      }
    })
    curS2Ind > 0 && curS2Ind == s2.length
  }

  def getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int = {
    val in_s1 = multS(s1, n1)
    val in_s2 = multS(s2, n2)

    var m = 0
    (1 to in_s1.length/in_s2.length).foreach(i => {
      if (canBeObtained(in_s1, multS(in_s2, i))) {
        m = i
      }
    })
    m
  }


  def main(args: Array[String]): Unit = {
    println(multS("zaq", 3))
    println("truths")
    println(canBeObtained("zsdgrgcxvaerg", "zs"))
    println(canBeObtained("zsdgrgcxvaerg", "zd"))
    println(canBeObtained("zsdgrgcxvaerg", "sd"))
    println(canBeObtained("zsdgrgcxvaerg", "dx"))
    println(canBeObtained("zsdgrgcxvaerg", "rg"))
    println(canBeObtained("zsdgrgcxvaerg", "ag"))
    println(canBeObtained("zsdgrgcxvaerg", "zg"))
    println(canBeObtained("zsdrcxvaerg", "zg"))
    println("falses")
    println(canBeObtained("zsdgrgcxvaerg", "zz"))
    println(canBeObtained("zsdrcxvaerg", "gg"))
    println(canBeObtained("zsdgrgcxvaerg", "11"))
    println(canBeObtained("zsdgrgcxvaerg", "1"))
    println(canBeObtained("zsdgrgcxvaerg", ""))

    println(getMaxRepetitions("abc", 4, "ab", 2))

  }


}
