package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/balanced-brackets/problem
  */
object BalancedBrackets {


  def isBalanced(s: String): String = {
    val parens = mutable.Stack[Char]()
    s.foreach(c => {
      if (c == '{')
        parens.push(c)
      else if (c == '}')
        if (parens.nonEmpty && parens.head == '{')
          parens.pop()
        else
          return "NO"
      else if (c == '(')
        parens.push(c)
      else if (c == ')')
        if (parens.nonEmpty && parens.head == '(')
          parens.pop()
        else
          return "NO"
      else if (c == '[')
        parens.push(c)
      else if (c == ']')
        if (parens.nonEmpty && parens.head == '[')
          parens.pop()
        else
          return "NO"
    })
    if (parens.isEmpty)
      "YES"
    else
      "NO"
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var t = sc.nextInt();
    var a0 = 0;
    while (a0 < t) {
      var s = sc.next();
      val result = isBalanced(s);
      println(result)
      a0 += 1;
    }
  }
}
