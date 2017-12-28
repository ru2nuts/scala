package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-ransom-note/problem
  */
object HashTableRansomNote {

  def canBuild(subset: Array[String], superset: Array[String]): Boolean = {
    val wordsCounter = scala.collection.mutable.HashMap[String, Int]()

    subset.foreach { w =>
      wordsCounter.put(w, wordsCounter.getOrElse(w, 0) + 1)
    }

    superset.foreach { w =>
      val z = wordsCounter.getOrElse(w, 0)
      if (z > 0)
        wordsCounter.put(w, z - 1)
    }

    wordsCounter.values.reduce(_ + _) == 0
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var m = sc.nextInt();
    var n = sc.nextInt();
    var magazine = new Array[String](m);
    for (magazine_i <- 0 to m - 1) {
      magazine(magazine_i) = sc.next();
    }
    var ransom = new Array[String](n);
    for (ransom_i <- 0 to n - 1) {
      ransom(ransom_i) = sc.next();
    }
    if (canBuild(ransom, magazine))
      println("Yes")
    else
      println("No")

  }
}
