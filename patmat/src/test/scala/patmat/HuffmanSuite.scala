package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("times") {
    new TestTrees {
      assert(times(List('a', 'b', 'c', 'a', 'b', 'a', 'a')) === List(('a', 4), ('b', 2), ('c', 1)))
    }
  }

  test("makeOrderedLeafList") {
    new TestTrees {
      assert(makeOrderedLeafList(List(('c', 1), ('a', 4), ('b', 2))) === List(Leaf('c', 1), Leaf('b', 2), Leaf('a', 4)))
    }
  }


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decoded secret") {
    val ss = decodedSecret
    assert(ss === string2Chars("huffmanestcool"))
  }


  test("decoded encoded secret") {
    val hi = encode(frenchCode)(string2Chars("huffmanestcool"))
    val ss = decodedSecret
    val zz = decode(frenchCode, hi)
    assert(ss === zz)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }


  test("decoded quick-encoded stuff") {
    val hi = encode(frenchCode)(string2Chars("huffmanestcool"))
    val ss = quickEncode(frenchCode)(string2Chars("huffmanestcool"))
    val zz1 = decode(frenchCode, hi)
    val zz2 = decode(frenchCode, ss)
    assert(zz1 === zz2)
  }

}
