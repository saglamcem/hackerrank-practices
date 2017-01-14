/*
* Apparently this challenge can be found in two places:
* Cracking the Coding Interview - BFS Short Reach
* Graph Theory - BFS Short Reach
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Graph {

        private Node[] nodes;
        private static int EDGE_DISTANCE = 6;

        public Graph(int size) {
            nodes = new Node[size];
            for (int i=0; i<size; i++) {
                nodes[i] = new Node(i+1);
            }
        }

        private class Node {
            private int id;
            private Node(int id) {
                this.id = id;
            }
            public LinkedList<Integer> adjacent = new LinkedList<>();
        }

        public void addEdge(int first, int second) {
            // to create an edge, add both nodes as their neighbours
            nodes[first].adjacent.add(second);
            nodes[second].adjacent.add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(startId);

            int[] distances = new int[nodes.length];
            Arrays.fill(distances, -1); // initialize all distances at -1
            distances[startId] = 0; // the distance of a node from itself is 0
            // add starting node to the queue, set its distance to 0

            while(!queue.isEmpty()) {
                int nodeId = queue.remove();

                // check neighbors of "nodeId" to see if they've been visited
                for(int neighbour: nodes[nodeId].adjacent) {
                    // if neighbor wasn't visited before, set the distance and add it to the queue
                    if (distances[neighbour] == -1) {
                        distances[neighbour] = distances[nodeId] + EDGE_DISTANCE;
                        queue.add(neighbour);
                    }
                    // if it has already been visited, continue to avoid cycles
                }
            }

            return distances;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
