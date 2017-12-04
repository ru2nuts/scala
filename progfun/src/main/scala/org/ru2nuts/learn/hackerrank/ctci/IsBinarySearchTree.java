package org.ru2nuts.learn.hackerrank.ctci;

/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem
 */
public class IsBinarySearchTree {

  class Node {
    int data;
    Node left;
    Node right;
  }

  boolean checkBST(Node root) {
    boolean result = true;

    if (root == null)
      return false;

    if (root.left != null)
      result = result && recurL(root.left, root.data) && checkBST(root.left);

    if (root.right != null)
      result = result && recurR(root.right, root.data) && checkBST(root.right);

    return result;
  }


  boolean recurL(Node n, int v) {
    return n == null || (n.data < v && recurL(n.left, v) && recurL(n.right, v));
  }

  boolean recurR(Node n, int v) {
    return n == null || (n.data > v && recurR(n.left, v) && recurR(n.right, v));
  }


}
