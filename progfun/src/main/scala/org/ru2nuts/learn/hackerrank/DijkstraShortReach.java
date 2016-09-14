package org.ru2nuts.learn.hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 */
public class DijkstraShortReach {

    public static void main(String[] args) {

        // read input
        Scanner sc = new java.util.Scanner(System.in);

        int t = sc.nextInt();
        for (int ti = 0; ti < t; ti++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            HashSet<Integer> nodes = new HashSet<>();
            Hashtable<Edge, Integer> edges = new Hashtable<>();

            for (int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                nodes.add(x);
                nodes.add(y);

                int r = sc.nextInt();
                edges.put(new Edge(x, y), r);
            }

            assert nodes.size() == n;

            int s = sc.nextInt();
            // done reading input

            ArrayList<Integer> orderedTasks = new DijkstraShortReach().getDijkstraShortestPathsToAllOtherNodes(nodes, edges, s);

            //print the tasks
            for (Integer node : orderedTasks) {
                System.out.print(node);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> getDijkstraShortestPathsToAllOtherNodes(HashSet<Integer> graph, Hashtable<Edge, Integer> edges, int startN) {
        ArrayList<Integer> res = new ArrayList<>(edges.size() - 1);
        graph.remove(startN);
        for (Integer endNode : graph) {
            res.add(getDijkstraShortestDistance(graph, edges, startN, endNode));
        }
        return res;
    }

    int getDijkstraShortestDistance(HashSet<Integer> graph, Hashtable<Edge, Integer> edges, int node1, int node2) {


        return -1;
    }

    static class Edge {
        int node1 = -1;
        int node2 = -1;

        Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }
}
