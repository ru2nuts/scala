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

  def findPartial(trie: Node, partialWord: String, strartInd: Int): Int = {
    if (strartInd >= partialWord.length) { //isEmpty
      return countWords(trie)
    }
    val c = partialWord(strartInd)
    val possibleChild = trie.children.get(c)
    if (possibleChild.nonEmpty) {
      return findPartial(possibleChild.get, partialWord, strartInd + 1)
    }
    return 0
  }

  def countWords(trie: Node): Int = {
    trie.wordCount
    //(if (trie.isWord) 1 else 0) + trie.children.map(e => countWords(e._2)).sum
  }

  def addWord(trie: Node, word: String, strartInd: Int): Int = {
    if (strartInd >= word.length) { //isEmpty
      trie.isWord = true
      return 1
    }
    val c = word(strartInd)
    val possibleChild = trie.children.get(c)
    if (possibleChild.nonEmpty) {
      trie.wordCount += addWord(possibleChild.get, word, strartInd + 1)
      return trie.wordCount
    } else {
      val newN = new Node(false, new scala.collection.mutable.HashMap[Char, Node](), 0)
      trie.children.put(c, newN)
      trie.wordCount += addWord(newN, word, strartInd + 1);
      return trie.wordCount;
    }
  }

  class Node(var isWord: Boolean, val children: scala.collection.mutable.HashMap[Char, Node], var wordCount: Int)

}
