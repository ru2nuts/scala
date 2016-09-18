package org.ru2nuts.learn.hackerrank;

import java.util.*;


public class ProjectDeps {

    public static void main1(String[] args) {

        // read input
        Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        char c = 'a';

        //TODO: validate input here for n,m < 0, n,m == 0, etc.

        Node[] allNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            allNodes[i] = new Node();
            allNodes[i].index = i;
        }
        for (int i = 0; i < m; i++) {
            Node node = allNodes[sc.nextInt() - 1];
            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                node.parents.add(allNodes[sc.nextInt() - 1]);
            }
        }
        // done reading input

        ArrayList<Node> orderedTasks = new ProjectDeps().computeTasksOrder(allNodes);

        //print the tasks
        for (Node node : orderedTasks) {
            System.out.print(node.index + 1);
            System.out.print(' ');
        }
    }


    public static void main2(String[] args) {

        // read input
        Scanner sc = new java.util.Scanner(System.in);

        HashMap<Integer, Node> allNodesMap = new HashMap<Integer, Node>();
        while (sc.hasNextLine()) {
            int nodeIndex = sc.nextInt();
            int parentIndex = sc.nextInt();

            if (!allNodesMap.containsKey(nodeIndex)){
                allNodesMap.put(nodeIndex, new Node());
            }
            Node node = allNodesMap.get(nodeIndex);

            if (!allNodesMap.containsKey(parentIndex)) {
                allNodesMap.put(parentIndex, new Node());
            }
            Node parentNode = allNodesMap.get(parentIndex);

            if (!node.parents.contains(parentNode))
                node.parents.add(parentNode);
        }

        //TODO: validate input here for n,m < 0, n,m == 0, etc.

        int n = allNodesMap.size();
        Node[] allNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            allNodes[i] = allNodesMap.get(i);
            allNodes[i].index = i;
        }
        // done reading input

        ArrayList<Node> orderedTasks = new ProjectDeps().computeTasksOrder(allNodes);

        //print the tasks
        for (Node node : orderedTasks) {
            System.out.print(node.index + 1);
            System.out.print(' ');
        }
    }

    /**
     * Find all nodes with no children.
     *
     * @param allNodes
     * @return
     */
    private ArrayList<Node> getLeaves(Node[] allNodes) {
        ArrayList<Node> res = new ArrayList<>();
        for (Node n : allNodes) {
            boolean isLeaf = true;
            for (Node otherN : allNodes) {
                if (otherN.parents.contains(n))
                    isLeaf = false;
            }
            if (isLeaf)
                res.add(n);
        }
        return res;
    }

    /**
     * Return all parents for passed in nodes.
     *
     * @param nodes
     * @return
     */
    private ArrayList<Node> getParentLayer(ArrayList<Node> nodes) {
        ArrayList<Node> res = new ArrayList<>();
        for (Node n : nodes) {
            res.addAll(n.parents);
        }
        return res;
    }

    private ArrayList<Node> computeTasksOrder(Node[] allNodes) {

        ArrayList<Node> prelimOrder = new ArrayList<Node>();
        ArrayList<Node> layer = getLeaves(allNodes);

        // fill in the tasks from bottom up
        while (!layer.isEmpty()) {


            Collections.sort(layer, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return -Integer.compare(o1.index, o2.index);
                }
            });

            prelimOrder.addAll(layer);
            layer = getParentLayer(layer);
        }

        //reverse the order, keeping only early occurrences of transitive dependencies
        ArrayList<Node> finalOrder = new ArrayList<Node>();
        for (int i = prelimOrder.size() - 1; i >= 0; i--) {
            Node node = prelimOrder.get(i);
            if (!finalOrder.contains(node)) {
                finalOrder.add(node);
            }
        }

        return finalOrder;
    }

    private static class Node {
        int index = -1;
        ArrayList<Node> parents = new ArrayList<Node>();
    }
}


