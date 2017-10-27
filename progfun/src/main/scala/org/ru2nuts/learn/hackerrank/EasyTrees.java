package org.ru2nuts.learn.hackerrank;

import java.util.ArrayList;

public class EasyTrees {

  class Node {
    int data;
    Node left;
    Node right;
  }


  void preOrder(Node root) {
    if (root != null) {
      System.out.print(root.data + " ");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  void postOrder(Node root) {
    if (root != null) {
      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.data + " ");
    }
  }

  void inOrder(Node root) {
    if (root != null) {
      inOrder(root.left);
      System.out.print(root.data + " ");
      inOrder(root.right);
    }
  }

  static int height(Node root) {
    // Write your code here.
    if (root != null && (root.left != null || root.right != null))
      return 1 + Math.max(height(root.left), height(root.right));
    return 0;
  }

  void topView(Node root) {
    topViewHelper(true, root.left);
    System.out.print(root.data + " ");
    topViewHelper(false, root.right);
  }

  void topViewHelper(boolean isLeft, Node n) {
    if (n != null) {
      if (isLeft) {
        topViewHelper(true, n.left);
        System.out.print(n.data + " ");
      } else {
        System.out.print(n.data + " ");
        topViewHelper(false, n.right);
      }
    }
  }

  void levelOrder(Node root) {
    ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
    levelOrderHelper(root, 0, nodes);
    for (int i = 0; i < nodes.size(); i++) {
      ArrayList<Node> l = nodes.get(i);
      for (int j = 0; j < l.size(); j++) {
        System.out.print(l.get(j).data + " ");
      }
    }
  }

  void levelOrderHelper(Node n, int level, java.util.ArrayList<java.util.ArrayList<Node>> nodes) {
    if (n != null) {
      java.util.ArrayList<Node> levelList;
      if (nodes.size() > level) {
        levelList = nodes.get(level);
      } else {
        levelList = new java.util.ArrayList<Node>();
        nodes.add(levelList);
      }
      levelList.add(n);
      levelOrderHelper(n.left, level + 1, nodes);
      levelOrderHelper(n.right, level + 1, nodes);
    }
  }


  Node Insert(Node root, int value) {
    if (root != null) {
      if (value >= root.data) {
        if (root.right == null) {
          root.right = new Node();
          root.right.data = value;
        } else {
          Insert(root.right, value);
        }
      } else {
        if (root.left == null) {
          root.left = new Node();
          root.left.data = value;
        } else {
          Insert(root.left, value);
        }

      }
    } else {
      root = new Node();
      root.data = value;
    }
    return root;
  }

  public static void main(String[] args) {

  }

}
