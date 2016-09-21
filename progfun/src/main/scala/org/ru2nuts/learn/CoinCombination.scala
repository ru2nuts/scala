package org.ru2nuts.learn

object CoinCombination {

  def main(args: Array[String]): Unit = {


    val denoms = Array[Int](100, 25, 10, 5, 1)
    while (true) {
      println("Amount? :")
      val amt = scala.io.StdIn.readInt()

      val tree = recur(denoms, amt)

      tree.foreach(n => {
        println("===================")
        n.foreach(c => {
          println(c)
        })
        println("===================")
      })

      println("++++++++++++++++++++++++++++++++++++++")
      tree.map(e => (e.map(_._2).sum, e)).minBy(_._1)._2.foreach(println)
      println("++++++++++++++++++++++++++++++++++++++")
    }
  }

  def recur(denoms: Array[Int], amt: Int): Array[Array[(Int, Int)]] = {
    // branch off into: without the first element and with (1, 2,..,N) occurences of the first element
    // N * demon(0) < amt
    if (denoms.isEmpty || amt == 0)
      Array[Array[(Int, Int)]]()
    else if (denoms.length == 1) {
      val firstCoin = denoms(0)
      val mult = amt / firstCoin
      if (mult == 0) {
        // if cannot combine smallest coins - exit
        Array[Array[(Int, Int)]]()
      } else {
        // otherwise - there should only be one way to combine smallest coins
        Array[Array[(Int, Int)]](Array[(Int, Int)]((firstCoin, mult)))
      }
    } else {

      if (amt < 0)
        throw new Exception("what?")

      val firstCoin = denoms(0)
      val restOfCoins = denoms.tail

      //with first coin

      val withFirst = (for (i <- 1 to amt / firstCoin reverse) yield {

        val restOfAmnts = recur(restOfCoins, amt - firstCoin * i)

        val firstCointAmt: (Int, Int) = (firstCoin, i)

        if (restOfAmnts.isEmpty)
          Array(Array[(Int, Int)](firstCointAmt))
        else {
          restOfAmnts.map(subArray => {
            Array[(Int, Int)](firstCointAmt) ++ subArray
          })
        }
      }).flatten

      //without first coin
      val withoutFirst = recur(restOfCoins, amt)

      withoutFirst ++ withFirst
    }
  }
}

