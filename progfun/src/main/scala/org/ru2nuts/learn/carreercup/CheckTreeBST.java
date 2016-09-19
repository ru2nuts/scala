package org.ru2nuts.learn.carreercup;

public class CheckTreeBST {

    public static void main(String[] args) {
        Node bstCandidate = new Node(10,
                new Node(7,
                        new Node(5,
                                new Node(4, null, null),
                                new Node(6, null, null)
                        ),
                        new Node(8, null, null)
                ),
                new Node(15,
                        new Node(13,
                                new Node(12, null, null),
                                new Node(14, null, null)
                        ),
                        new Node(17, null, null)
                )
        );
        boolean isBst = isBetweenAndValid(bstCandidate.left, Integer.MIN_VALUE, bstCandidate.val) &&
                isBetweenAndValid(bstCandidate.right, bstCandidate.val, Integer.MAX_VALUE);

        System.out.println(isBst);
    }

    public static boolean isBetweenAndValid(Node n, int min, int max) {
        return (n == null) || (n.val > min && n.val < max &&
                isBetweenAndValid(n.left, min, n.val) &&
                isBetweenAndValid(n.right, n.val, max));
    }

    static class Node {
        int val = 0;
        Node left = null;
        Node right = null;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
