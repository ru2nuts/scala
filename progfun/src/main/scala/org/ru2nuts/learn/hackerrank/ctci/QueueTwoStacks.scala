package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
  */
object QueueTwoStacks {

  class Queue[T] {

    val in = new scala.collection.mutable.Stack[T]
    val out = new scala.collection.mutable.Stack[T]

    def reload() = {
      if (in.nonEmpty && out.isEmpty) {
        in.foreach(e => {
          out.push(e)
        })
        in.clear()
      }
    }

    def ennqueue(v: T) = {
      in.push(v)
    }

    def dequeue(): T = {
      if (out.isEmpty && in.nonEmpty) {
        reload
      }
      if (out.nonEmpty)
        out.pop()
      else
        throw new Exception("empty")
    }

    def peek(): T = {
      if (out.isEmpty && in.nonEmpty) {
        reload
      }
      if (out.nonEmpty) {
        val r = out.pop()
        out.push(r)
        r
      }
      else
        throw new Exception("empty")
    }
  }

  def main(args: Array[String]) {
    val queue = new Queue[Int]
    val sc = new java.util.Scanner(System.in)
    val q = sc.nextInt()
    for (i <- 0 to q - 1) {
      val qt = sc.nextInt
      qt match {
        case 1 => {
          val v = sc.nextInt()
          queue.ennqueue(v)
        }
        case 2 => {
          queue.dequeue()
        }
        case 3 => {
          println(queue.peek())
        }
        case _ => {
          throw new Exception("wrong")
        }
      }
    }
  }
}
