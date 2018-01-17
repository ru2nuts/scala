package org.ru2nuts.spark.example


object ScalaMain {
  def main(args: Array[String]) = {
    def sm = new ScalaMain
    sm.mnc(null)
    (new ReservoirSampling).sample(3, 100, Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15).iterator).foreach(println(_))
  }

  def pr(s: String) =
    println(s)

}

class ScalaMain {
  def mnc(args: Array[String]) =
    ScalaMain.pr("Hello world")

}
