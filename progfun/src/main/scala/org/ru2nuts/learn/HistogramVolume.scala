package org.ru2nuts.learn

/**
  * Created by ru2nuts on 9/9/16.
  */
class HistogramVolume {

  def findIndexOfMax(hist: Array[Int], startIndex: Int, endIndex: Int): Int = {
    hist.zipWithIndex.maxBy(hz => {
      if (hz._2 < startIndex || hz._2 > endIndex) -1 else hz._1
    })._2
  }

  def volumeBetween(hist: Array[Int], startIndex: Int, endIndex: Int): Int = {
    if (startIndex >= endIndex)
      0
    val topLevel: Int = Math.min(hist(startIndex), hist(endIndex))
    val volumes = for (i <- startIndex + 1 to endIndex - 1) yield {
      topLevel - hist(i)
    }
    if (volumes.isEmpty) 0 else volumes.reduce(_ + _)
  }

  def histVolume(hist: Array[Int], startIndex: Int, endIndex: Int, isLeft: Boolean): Int = {
    if (startIndex >= endIndex)
      0
    else if (isLeft) {
      val maxBar = findIndexOfMax(hist, startIndex, endIndex - 1)
      volumeBetween(hist, maxBar, endIndex) + histVolume(hist, startIndex, maxBar, isLeft)
    }
    else {
      val maxBar = findIndexOfMax(hist, startIndex + 1, endIndex)
      volumeBetween(hist, startIndex, maxBar) + histVolume(hist, maxBar, endIndex, isLeft)
    }
  }

  def computeHistogramVolume(hist: Array[Int]): Int = {
    val maxBar = findIndexOfMax(hist, 0, hist.length - 1)

    histVolume(hist, 0, maxBar, true) +
      histVolume(hist, maxBar, hist.length - 1, false)
  }

}

object HistogramVolume {
  def main(args: Array[String]) = {
    print("26->")
    println(new HistogramVolume().computeHistogramVolume(Array(0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0)))

    print("46->")
    println(new HistogramVolume().computeHistogramVolume(Array(0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 8, 0, 2, 0, 5, 2, 0, 3, 0, 0)))
  }
}
