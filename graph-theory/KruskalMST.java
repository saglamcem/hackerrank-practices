import java.util.*;

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

    public static class Tree {
        private LinkedList<Integer> nodesInTree;
        private int totalWeight = 0;
        public Tree() {
            nodesInTree = new LinkedList<>();
        }
        public void addEdge(Edge edge) {
            if (!nodesInTree.contains(edge.node1)) {
                nodesInTree.add(edge.node1);
            }
            if (!nodesInTree.contains(edge.node2)) {
                nodesInTree.add(edge.node2);
            }
        }
        public boolean contains(Edge edge) {
            return (!(nodesInTree.contains(edge.node1) && nodesInTree.contains(edge.node2)));
        }
        public void increaseTotalWeight(int weight) {
            this.totalWeight += weight;

        }
    }

    public static class Graph {
        private List<Edge> edges;
        private int numOfNodes;
        public Graph(int numOfNodes) {
            edges = new ArrayList<>();
            this.numOfNodes = numOfNodes;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        public void sortEdges() {
            Collections.sort(edges);
        }

        public int getTotalWeight() {
            sortEdges();
            // trees will be filled
            LinkedList<Tree> forest = new LinkedList<>();

            for (Edge edge: edges) {

                if (forest.isEmpty()) {
                    Tree tree = new Tree();
                    tree.addEdge(edge);
                    tree.increaseTotalWeight(edge.weight);
                    forest.add(tree);
                } else {
                    int notSeenCounter = 0;
                    for (Tree tree: forest) {

                        if (!tree.contains(edge)) {
                            notSeenCounter++;
                        } else {
                            // means the tree already contained part of the edge
                            tree.addEdge(edge);
                            tree.increaseTotalWeight(edge.weight);

                            if (tree.nodesInTree.size() == numOfNodes) {
                                return tree.totalWeight;
                            }
                        }
                    }
                    if (notSeenCounter == forest.size()) {
                        Tree newTree = new Tree();
                        newTree.addEdge(edge);
                        newTree.increaseTotalWeight(edge.weight);
                        forest.add(newTree);
                    }
                }
            }

            return -1;
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
4 6
1 2 5
1 3 3
4 1 6
2 4 7
3 2 4
3 4 5
*/
