package org.ru2nuts.learn.hackerrank

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
    val path = step(None, grid, n / 2, n / 2, new scala.collection.mutable.HashSet[(Int, Int)]())
    path.foreach(_.foreach(println))
  }


  def step(from: Option[String], grid: Array[String], row: Int, col: Int,
           visited: scala.collection.mutable.HashSet[(Int, Int)]): Option[List[String]] = {
    if (grid(row).charAt(col) == 'p') {
      from.map(_ :: Nil)
    } else if (visited.contains(row, col)) {
      None
    } else {
      visited.add((row, col))
      val paths = List(
        if (row - 1 >= 0)
          step(Some("UP"), grid, row - 1, col, visited)
        else
          None,
        if (col - 1 >= 0)
          step(Some("LEFT"), grid, row, col - 1, visited)
        else
          None,
        if (row + 1 < grid.length)
          step(Some("DOWN"), grid, row + 1, col, visited)
        else
          None,
        if (col + 1 > grid.length)
          step(Some("RIGHT"), grid, row, col + 1, visited)
        else
          None)
        .filter(_.nonEmpty).map(_.get)

      if (paths.nonEmpty) {
        val minPath = paths.minBy(_.length)
        if (from.nonEmpty)
          Some(from.get :: minPath)
        else
          Some(minPath)
      } else
        None
    }
  }
}
