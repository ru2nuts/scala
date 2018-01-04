package org.ru2nuts.learn.hackerrank

import scala.collection.mutable.{HashSet, ListBuffer, Queue}

/**
  * https://www.hackerrank.com/challenges/saveprincess
  */
object BotSavesPrincess {

  def main(args: Array[String]) = {
    val m = scala.io.StdIn.readLine.toInt
    val grid = new Array[String](m)
    for (i <- 0 until m) {
      grid.update(i, scala.io.StdIn.readLine)
    }
    displayPathtoPrincess(m, grid)
  }

  /* Refer to Output format section for more details */
  def displayPathtoPrincess(n: Int, grid: Array[String]) = {
    val path = getPath(grid, n / 2, n / 2)
    path.foreach(_.foreach(println))
  }

  def getAdjacent(grid: Array[String], row: Int, col: Int, visited: HashSet[(Int, Int)]): List[(Int, Int, String)] = {
    visited.add((row, col))
    val paths = List(
      if (row - 1 >= 0 && !visited.contains(row - 1, col))
        Some(row - 1, col, "UP")
      else
        None,
      if (col - 1 >= 0 && !visited.contains(row, col - 1))
        Some(row, col - 1, "LEFT")
      else
        None,
      if (row + 1 < grid.length && !visited.contains(row + 1, col))
        Some(row + 1, col, "DOWN")
      else
        None,
      if (col + 1 < grid.length && !visited.contains(row, col + 1))
        Some(row, col + 1, "RIGHT")
      else
        None)

    paths.filter(_.nonEmpty).map(_.get)
  }

  def getPath(grid: Array[String], row: Int, col: Int): Option[List[String]] = {

    val allPaths = new ListBuffer[List[String]]()

    val visited = new HashSet[(Int, Int)]()

    val queue = new Queue[(Int, Int, ListBuffer[String])]()
    queue.enqueue((row, col, new ListBuffer[String]()))
    while (queue.nonEmpty) {
      val (r, c, currentPath) = queue.dequeue()
      if (grid(r).charAt(c) == 'p') {
        allPaths.append(currentPath.toList)
      }
      val adj = getAdjacent(grid, r, c, visited)

      adj.foreach(a => {
        val newCurrentPath = currentPath.clone()
        newCurrentPath.append(a._3)
        queue.enqueue((a._1, a._2, newCurrentPath))
      })
    }
    if (allPaths.nonEmpty)
      return Some(allPaths.minBy(_.length))
    else
      return None
  }
}
