package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/swap-nodes-algo/problem
  */
object SwapNodesAlgo {

  class Node(val index: Int) {
    var left: Node = null
    var right: Node = null

    override def toString: String = "" + index + "(" + left.toString + " -- " + right.toString + ")"
  }

  def swapBranches(level: Int, tree: Node, currentLevel: Int): Any = {
    if (tree == null)
      return;
    if (level == currentLevel) {
      val tmp = tree.right
      tree.right = tree.left
      tree.left = tmp
    } else if (currentLevel < level) {
      swapBranches(level, tree.left, currentLevel + 1)
      swapBranches(level, tree.right, currentLevel + 1)
    }
  }

  def printInOrder(tree: Node): Any = {
    if (tree != null) {
      printInOrder(tree.left)
      print(tree.index)
      print(' ')
      printInOrder(tree.right)
    }
  }

  def getDebth(n: Node, currentDepth: Int): Int = {
    if (n == null)
      currentDepth
    else {
      return Math.max(getDebth(n.left, currentDepth + 1), getDebth(n.right, currentDepth + 1))
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt

    val nodes = Array.ofDim[Node](n)
    (1 to n).foreach(index => {
      val a = sc.nextInt
      val b = sc.nextInt

      val anode: Node =
        if (a != -1) {
          nodes(a - 1) = new Node(a)
          nodes(a - 1)
        } else
          null

      val bnode: Node =
        if (b != -1) {
          nodes(b - 1) = new Node(b)
          nodes(b - 1)
        } else
          null

      var currentNode = nodes(index - 1)

      if (currentNode == null) {
        currentNode = new Node(index)
      }

      currentNode.left = anode
      currentNode.right = bnode
      nodes(index - 1) = currentNode
    })

    val tree = nodes(0)

    val depth: Int = getDebth(tree, 0)

    val t = sc.nextInt
    (1 to t).foreach(index => {
      val k = sc.nextInt
      (1 to (depth / k)).foreach(kx => {
        swapBranches(k * kx - 1, tree, 0)
      })
      printInOrder(tree)
      println()
    })
  }
}