package org.ru2nuts.learn

import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkPlayground {

  def main(args: Array[String]) = {

    val conf = new SparkConf().setAppName("SparkPlayground").setMaster("local[2]")
    val sc = new SparkContext(conf)

    try {
      val rdd1 = sc.parallelize(Range(1, 100))
      val multiplesOfThree = rdd1.filter(_ % 3 == 0)

      multiplesOfThree.collect.foreach(println)

    } finally {
      sc.stop()
    }
  }
}
