package traversal.kruskal;


import graph.MyGraph;

import java.util.*;

public class KruskalAlgorithm<T> {

    // Package-private Edge class
    public static class Edge<U> {
        private final U source;
        private final U destination;
        private final double weight;

        public Edge(U source, U destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public U getSource() {
            return source;
        }

        public U getDestination() {
            return destination;
        }

        public double getWeight() {
            return weight;
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "source=" + source +
                    ", destination=" + destination +
                    ", weight=" + weight +
                    '}';
        }
    }

    public List<Edge<T>> findMinimumSpanningTree(MyGraph<T> graph) {
        // Check for negative weights
        if (containsNegativeWeights(graph)) {
            throw new IllegalArgumentException("Graph contains negative weights or cycles. Cannot find minimum spanning tree.");
        }

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();

        // Priority queue to store edges sorted by weight
        PriorityQueue<Edge<T>> edgeQueue = new PriorityQueue<>(Comparator.comparingDouble(edge -> graph.getWeight(edge.getSource(), edge.getDestination())));

        // Initialize Disjoint Set to track connected components
        DisjointSet<T> disjointSet = new DisjointSet<>(graph.getVertices());

        // Add all edges to the priority queue
        for (T vertex : graph.getVertices()) {
            for (T neighbor : graph.getNeighbours(vertex)) {
                double weight = graph.getWeight(vertex, neighbor);
                Edge<T> edge = new Edge<>(vertex, neighbor, weight);
                edgeQueue.add(edge);
            }
        }

        // Process edges until the minimum spanning tree is complete
        while (!edgeQueue.isEmpty() && minimumSpanningTree.size() < graph.getVertexCount() - 1) {
            Edge<T> edge = edgeQueue.poll();

            T parentA = disjointSet.find(edge.getSource());
            T parentB = disjointSet.find(edge.getDestination());

            // Check if adding the edge creates a cycle
            if (!parentA.equals(parentB)) {
                minimumSpanningTree.add(edge);
                disjointSet.union(parentA, parentB);
            }
        }

        return minimumSpanningTree;
    }

    private boolean containsNegativeWeights(MyGraph<T> graph) {
        for (T vertex : graph.getVertices()) {
            for (T neighbor : graph.getNeighbours(vertex)) {
                double weight = graph.getWeight(vertex, neighbor);
                if (weight < 0) {
                    return true;
                }
            }
        }
        return false;
    }



    public static class DisjointSet<U> {
        private final Map<U, U> parent;

        public DisjointSet(Collection<U> elements) {
            parent = new HashMap<>();
            for (U element : elements) {
                parent.put(element, element);
            }
        }

        public U find(U element) {
            if (!parent.containsKey(element)) {
                throw new IllegalArgumentException("Element not present in the disjoint set.");
            }

            if (!element.equals(parent.get(element))) {
                // Path compression: set the parent to the root
                parent.put(element, find(parent.get(element)));
            }

            return parent.get(element);
        }

        public void union(U element1, U element2) {
            U root1 = find(element1);
            U root2 = find(element2);

            if (!root1.equals(root2)) {
                // Union by rank: attach the shorter tree to the root of the taller tree
                parent.put(root1, root2);
            }
        }
    }
}
