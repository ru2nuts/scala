package org.ru2nuts.learn.hackerrank

object FizzBuzz {

  def main(args: Array[String]): Unit = {
    (1 to 99).foreach(i => {
      if (i % 15 == 0) {
        println("FizzBuzz")
      } else if(i % 5 == 0) {
        println("Buzz")
      } else if(i % 3 == 0) {
        println("Fizz")
      } else
        println(i)
    })
  }
}
