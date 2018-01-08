package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/battery/problem
  */
object LaptopBatteryLife {

  def trainModel(fileName: String): ((Double, Double), Double) = {
    val fs = scala.io.Source.fromFile(fileName)

    val allVals = fs.getLines().map(l => {
      val a = l.split(',')
      (a(0).toDouble, a(1).toDouble)
    }).toSeq


    val maxY = allVals.maxBy(d => d._2)._2

    val maxYValsSorted = allVals.filter(d => d._2 == maxY).sortBy(d => d._1)

    val maxX = maxYValsSorted(0)._1

    val cleanVals = allVals.filter(d => d._1 < maxX)

    fs.close()

    (getLR(cleanVals), maxY)
  }

  private def getLR(cleanVals: Seq[(Double, Double)]) = {
    val xs = cleanVals.map(_._1)
    val ys = cleanVals.map(_._2)
    val n = cleanVals.size.toDouble

    val sum_x = xs.sum
    val sum_y = ys.sum
    val sum_xx = xs.map(x => x * x).sum
    val sum_xy = xs.zip(ys).map(p => p._1 * p._2).sum
    val sum_yy = ys.map(y => y * y).sum

    val beta = (n * sum_xy - sum_x * sum_y) / (n * sum_xx - sum_x * sum_x)
    val alpha = (1 / n) * sum_y - beta * (1 / n) * sum_x

    (alpha, beta)
  }

  private def getSimpleLR(cleanVals: Seq[(Double, Double)]) = {
    val xs = cleanVals.map(_._1)
    val ys = cleanVals.map(_._2)

    val avgX = xs.sum / xs.size
    val avgY = ys.sum / ys.size

    val x_minus_avgXs = xs.map(xi => xi - avgX)
    val y_minus_avgYs = ys.map(yi => yi - avgY)

    val numer = x_minus_avgXs.zip(y_minus_avgYs).map(p => p._1 * p._2).sum
    val denom = x_minus_avgXs.map(xi => xi * xi).sum

    val beta = numer / denom
    val alpha = avgY - beta * avgX

    (alpha, beta)
  }

  def main(args: Array[String]) {

    val ((alpha, beta), maxY) = trainModel("/Users/oarutyunyants/projects/ru2nuts/scala/progfun/src/main/scala/org/ru2nuts/learn/hackerrank/LaptopBatteryLife.data/trainingdata.txt")

    val maxX = (maxY - alpha) / beta


    val sc = new java.util.Scanner(System.in);
    var x = sc.nextDouble();

    println(if (x >= maxX) maxY else alpha + beta * x)
  }

}
