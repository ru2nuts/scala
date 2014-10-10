package recfun


object Main {
  def main(args: Array[String]) {
    //    println("Pascal's Triangle")
    //    for (row <- 0 to 10) {
    //      for (col <- 0 to row)
    //        print(pascal(col, row) + " ")
    //      println()
    //    }


    //    println(balance("(if (zero? x) max (/ 1 x))".toList))
    //    println(balance("I told him (that it’s not (yet) done). (But he wasn’t listening)".toList))
    //    println(balance(":-)".toList))
    //    println(balance("())(".toList))


  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (r == 0 || c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def parenBalance(chars: List[Char], priorBalance: Int): Int = {
      if (chars.isEmpty)
        priorBalance
      else {
        val hc = chars.head
        if (hc == '(')
          parenBalance(chars.tail, priorBalance + 1)
        else if (hc == ')')
          if (priorBalance == 0)
            -1
          else
            parenBalance(chars.tail, priorBalance - 1)
        else
          parenBalance(chars.tail, priorBalance)
      }
    }

    parenBalance(chars, 0) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def recurse(money: Int, coins: List[Int], counter: Int): Int = {
      if (money == 0) {
        counter + 1
      }
      else if (money < 0 || coins.isEmpty) {
        counter
      }
      else {
        recurse(money - coins.head, coins, counter) + recurse(money, coins.tail, counter)
      }
    }

    if (money <= 0 || coins.isEmpty)
      0
    else
      recurse(money, coins, 0)
  }
}
