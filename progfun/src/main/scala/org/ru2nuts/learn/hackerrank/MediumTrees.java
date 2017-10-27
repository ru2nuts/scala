package org.ru2nuts.learn.hackerrank;

public class MediumTrees {
  class Node {
    public int frequency; // the frequency of this tree
    public char data;
    public Node left, right;
  }

  void decode(String S, Node root) {
    StringBuilder res = new StringBuilder();
    decodeHelper(S, 0, root, root, res);
    System.out.println(res.toString());
  }

  private void decodeHelper(String inputStr, int pos, Node currentNode, Node root, StringBuilder res) {
    if (currentNode.left == null && currentNode.right == null) { // at leaf
      res.append(currentNode.data);
      if (pos == inputStr.length()) // at leaf and done - finish
        return;
      else {
        decodeHelper(inputStr, pos, root, root, res); // at leaf and not done - restart
      }
    } else if (inputStr.charAt(pos) == '0' && currentNode.left != null) {
      decodeHelper(inputStr, pos + 1, currentNode.left, root, res); // go left
    } else if (inputStr.charAt(pos) == '1' && currentNode.right != null) {
      decodeHelper(inputStr, pos + 1, currentNode.right, root, res); // go right
    }
  }
}
