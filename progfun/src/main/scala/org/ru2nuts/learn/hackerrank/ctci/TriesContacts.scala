package org.ru2nuts.learn.hackerrank.ctci


/**
  * https://www.hackerrank.com/challenges/ctci-contacts/problem
  */
object TriesContacts {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var a0 = 0;
    while (a0 < n) {
      var op = sc.next();
      var contact = sc.next();
      a0 += 1;

      op match {
        case "add" => {
          addWord(trie, contact, 0)
        }
        case "find" => {
          println(findPartial(trie, contact, 0))
        }
        case _ => throw new IllegalStateException("I dunno");
      }
    }
  }

  val trie: Node = new Node(false, new scala.collection.mutable.HashMap[Char, Node](), 0)

  def findPartial(trie: Node, partialWord: String, startInd: Int): Int = {
    if (startInd >= partialWord.length) { //isEmpty
      return countWords(trie)
    }
    val c = partialWord(startInd)
    val possibleChild = trie.children.get(c)
    if (possibleChild.nonEmpty) {
      val n = possibleChild.get
      return (if (n.isWord && startInd == partialWord.length-1) 1 else 0) + findPartial(n, partialWord, startInd + 1)
    }
    return 0
  }

  def countWords(trie: Node): Int = {
    trie.wordCount
    //(if (trie.isWord) 1 else 0) + trie.children.map(e => countWords(e._2)).sum
  }

  def addWord(trie: Node, word: String, startInd: Int): Int = {
    if (startInd >= word.length) { //isEmpty
      trie.isWord = true
      return 1
    } else {
      val c = word(startInd)
      val possibleChild =
        if (trie.children.contains(c))
          trie.children(c)
        else {
          val newN = new Node(false, new scala.collection.mutable.HashMap[Char, Node](), 0)
          trie.children.put(c, newN)
          newN
        }
      trie.wordCount += 1
      addWord(possibleChild, word, startInd + 1)
      return trie.wordCount
    }
  }

  class Node(var isWord: Boolean, val children: scala.collection.mutable.HashMap[Char, Node], var wordCount: Int)
}
