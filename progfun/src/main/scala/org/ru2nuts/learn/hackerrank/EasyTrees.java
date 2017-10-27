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


  static Node lca(Node root, int v1, int v2) {
    if (root == null)
      return null;

    if (root.data == v1 || root.data == v2)
      return root;

    if (lcaHelperContains(root.left, v1) && lcaHelperContains(root.right, v2) ||
        lcaHelperContains(root.left, v2) && lcaHelperContains(root.right, v1))
      return root;

    Node nl = lca(root.left, v1, v2);
    if (nl != null)
      return nl;

    return lca(root.right, v1, v2);
  }

  static boolean lcaHelperContains(Node n, int v) {
    if (n == null)
      return false;
    return n.data == v || lcaHelperContains(n.left, v) || lcaHelperContains(n.right, v);
  }




  public static void main(String[] args) {

  }

}
