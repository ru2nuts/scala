package org.ru2nuts.learn.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class TopologicalSort {

    public static void main(String[] args) {

        // read input
        Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        //TODO: validate input here for n,m < 0, n,m == 0, etc.

        Node[] allNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            allNodes[i] = new Node(i);
        }
        for (int i = 0; i < m; i++) {
            Node node = allNodes[sc.nextInt() - 1];
            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                node.parents.add(allNodes[sc.nextInt() - 1]);
            }
        }
        // done reading input

        ArrayList<Node> orderedTasks = new TopologicalSort().kahnSort(allNodes);

        //print the tasks
        for (Node node : orderedTasks) {
            System.out.print(node.index + 1);
            System.out.print(' ');
        }
    }

    public static void main2(String[] args) {

        // read input
        Scanner sc = new java.util.Scanner(System.in);

        int nodeCount = sc.nextInt();
        int edgeCount = sc.nextInt();

        HashMap<Integer, Node> allNodesMap = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            allNodesMap.put(i, new Node(i));
        }

        for (int i = 0; i < edgeCount; i++) {

            Node parentNode = allNodesMap.get(sc.nextInt());
            Node childNode = allNodesMap.get(sc.nextInt());
            if (!childNode.parents.contains(parentNode))
                childNode.parents.add(parentNode);
            //allNodesMap.get(node1)
        }

        //TODO: validate input here for n,m < 0, n,m == 0, etc.

        int n = allNodesMap.size();
        Node[] allNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            allNodes[i] = allNodesMap.get(i);
            allNodes[i].index = i;
        }
        // done reading input

        ArrayList<Node> orderedTasks = new TopologicalSort().kahnSort(allNodes);

        //print the tasks
        for (Node node : orderedTasks) {
            System.out.print(node.index);
            System.out.print(' ');
        }
    }

    public ArrayList<Node> kahnSort(Node[] graph) {

        ArrayList<Node> res = new ArrayList<>();
        Queue<Node> noParentNodes = getNoParentNodes(graph);

        while (!noParentNodes.isEmpty()) {
            Node topNode = noParentNodes.poll();
            res.add(topNode);

            for (Node childNode : getChildren(topNode, graph)) {
                childNode.parents.remove(topNode);
                if (childNode.parents.isEmpty()) {
                    noParentNodes.add(childNode);
                }
            }
        }
        return res;
    }

    private ArrayList<Node> getChildren(Node parent, Node[] graph) {
        ArrayList<Node> res = new ArrayList<>();
        for (Node n : graph) {
            if (n.parents.contains(parent))
                res.add(n);
        }
        return res;
    }

    private Queue<Node> getNoParentNodes(Node[] graph) {
        Queue<Node> res = new LinkedBlockingQueue<>();
        for (Node n : graph) {
            if (n.parents.isEmpty())
                res.add(n);
        }
        return res;
    }

    static class Node {
        int index = -1;
        ArrayList<Node> parents = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }
    }
}
