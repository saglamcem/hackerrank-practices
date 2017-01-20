package com.cemdev;

import java.util.*;

/*
KRUSKAL(G):
1 A = ∅
2 foreach v ∈ G.V:
3    MAKE-SET(v)
4 foreach (u, v) in G.E ordered by weight(u, v), increasing:
5    if FIND-SET(u) ≠ FIND-SET(v):
6       A = A ∪ {(u, v)}
7       UNION(u, v)
8 return
*/

public class Kruskal {

    public static class Edge implements Comparable<Edge> {
        private int node1;
        private int node2;
        private int weight;

        public Edge(int node1, int node2, int weight) {
            // -1 to work properly with Node indices and provided input
            this.node1 = node1 - 1;
            this.node2 = node2 - 1;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node1: " + this.node1 + ", " + "Node2: " + this.node2 + ", " + "Weight: " + this.weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) {
                return -1;
            } else if (this.weight > o.weight) {
                return 1;
            } else {
                return Integer.compare(this.node1 + this.weight + this.node2, o.node1 + o.weight + o.node2);
            }
        }
    }

    public static class Graph {
        private List<Edge> edges;
        private int numOfNodes;
        private HashSet<Integer> vertices;
        public Graph(int numOfNodes) {
            edges = new ArrayList<>();
            vertices = new HashSet<>();
            this.numOfNodes = numOfNodes;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        public void sortEdges(List<Edge> edges) {
            Collections.sort(edges);
        }

        public void makeSet(HashSet<Integer> vertices) {
            for (Edge edge: edges) {
                vertices.add(edge.node1);
                vertices.add(edge.node2);
            }
            // vertices made into a set
        }

        public void createForest(HashMap forest) {
            for (Integer vertex: vertices) {
                Set<Integer> tree = new HashSet<>();
                tree.add(vertex);
                forest.put(vertex, tree);
            }
        }

        public int getTotalWeight() {
            sortEdges(edges);
            makeSet(vertices);

            HashMap<Integer, Set<Integer>> forest = new HashMap<>();
            createForest(forest);

            ArrayList<Edge> mst = new ArrayList<>();
            for (Edge edge: edges) {
                Set<Integer> reach1 = forest.get(edge.node1);
                Set<Integer> reach2 = forest.get(edge.node2);
                // check var
                if (reach1.equals(reach2)) {
                    continue;
                }

                mst.add(edge);
                reach1.addAll(reach2);
                for (Integer i: reach1) {
                    forest.put(i, reach1);
                }

                if (reach1.size() == vertices.size()) {
                    break;
                }
            }

            int totalWeight = 0;
            for (Edge edge: mst) {
                totalWeight += edge.weight;
            }

            return totalWeight;

        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbersOfNE = stringToIntArray(sc.nextLine()); // get # of nodes and edges
        int numOfNodes = numbersOfNE[0];
        int numOfEdges = numbersOfNE[1];

        Graph graph = new Graph(numOfNodes);

        for (int i=0; i<numOfEdges; i++) {
            int[] edgeInfo = stringToIntArray(sc.nextLine()); // get edge info
            Edge edge = new Edge(edgeInfo[0], edgeInfo[1], edgeInfo[2]);
            graph.addEdge(edge);
        }

        System.out.println(graph.getTotalWeight());
    }

    public static int[] stringToIntArray(String stringArray) {

        String[] numbersStr = stringArray.split(" "); // get numbers in string array
        int[] numbers = new int[numbersStr.length];

        for (int i=0; i<numbersStr.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i]); // convert and populate
        }
        return numbers;
    }
}

/*
Testcase #2
4 5
1 2 1
3 2 150
4 3 99
1 4 100
3 1 200
**
200

4 6
1 2 5
1 3 3
4 1 6
2 4 7
3 2 4
3 4 5
*/
