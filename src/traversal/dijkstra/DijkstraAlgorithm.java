package traversal.dijkstra;

import list.linkedlist.MyLinkedList;
import map.MyHashMap;
import graph.MyGraph;
import traversal.GraphWeight;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The DijkstraAlgorithm class provides a method to find the shortest path in a weighted graph using Dijkstra's algorithm.
 *
 * @param <T> the type of data stored in the graph
 */
public class DijkstraAlgorithm<T> {
    private MyHashMap<T, Double> distances; // New field to store distances

    /**
     * Finds the shortest path in a weighted graph using Dijkstra's algorithm.
     *
     * @param graph       the weighted graph
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return a linked list representing the shortest path from the source to the destination
     */
    public MyLinkedList<T> findShortestPath(MyGraph<T> graph, T source, T destination) {
        distances = new MyHashMap<>();  // Initialize distances map
        MyHashMap<T, T> previousVertices = new MyHashMap<>();
        Set<T> settled = new HashSet<>();
        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(n -> distances.get(n.vertex)));

        // Check if source and destination are the same
        if (source.equals(destination)) {
            MyLinkedList<T> singleVertexPath = new MyLinkedList<>();
            singleVertexPath.add(source);
            return singleVertexPath;
        }

        distances.put(source, 0.0);
        priorityQueue.add(new Node<>(source, 0));

        while (!priorityQueue.isEmpty()) {
            Node<T> node = priorityQueue.poll();
            T current = node.vertex;
            if (settled.contains(current)) {
                continue;
            }
            settled.add(current);
            relaxNeighbours(graph, current, distances, previousVertices, priorityQueue, settled);
        }

        return constructPath(source, destination, previousVertices).reverse();
    }

    private void relaxNeighbours(MyGraph<T> graph, T current, MyHashMap<T, Double> distances, MyHashMap<T, T> previousVertices, PriorityQueue<Node<T>> priorityQueue, Set<T> settled) {
        MyLinkedList<GraphWeight.Node<T>> neighbours = graph.getConnection(current);
        if (neighbours != null) {
            for (GraphWeight.Node<T> neighbor : neighbours) {
                T adjacentVertex = neighbor.getData();
                if (adjacentVertex == null) {
                    continue;  // Skip null vertices
                }

                double edgeWeight = neighbor.getWeight();
                double currentDistance = distances.getOrDefault(current, Double.POSITIVE_INFINITY);
                double tentativeDistance = currentDistance + edgeWeight;

                // Check for negative weights
                if (edgeWeight < 0) {
                    throw new IllegalArgumentException("Negative weights are not allowed.");
                }

                if (!settled.contains(adjacentVertex) && tentativeDistance + 1e-9 < distances.getOrDefault(adjacentVertex, Double.POSITIVE_INFINITY)) {
                    distances.put(adjacentVertex, tentativeDistance);
                    previousVertices.put(adjacentVertex, current);

                    priorityQueue.add(new Node<>(adjacentVertex, tentativeDistance));
                }
            }
        }
    }


    private MyLinkedList<T> constructPath(T source, T destination, MyHashMap<T, T> previousVertices) {
        MyLinkedList<T> path = new MyLinkedList<>();
        T current = destination;

        // Check if the destination is reachable
        if (!previousVertices.containsKey(destination)) {
            return path;  // Return an empty path
        }

        while (current != null && !current.equals(source)) {
            path.add(current);
            current = previousVertices.get(current);
        }

        // Add the source vertex to the path
        if (current != null && current.equals(source)) {
            path.add(source);
        }

        return path;
    }


    public MyHashMap<T, Double> getDistances() {
        return distances;
    }

    public String getDistancesToString() {
        StringBuilder result = new StringBuilder();
        for (T vertex : distances.keySet()) {
            result.append(vertex).append(": ").append(distances.get(vertex)).append(", ");
        }
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());  // Remove the trailing ", "
        }
        return result.toString();
    }

    /**
     * The Node class represents a node in the priority queue with a vertex and distance.
     *
     * @param <K> the type of data stored in the node
     */
    static class Node<K> {
        K vertex;
        double distance;

        /**
         * Constructs a node with the specified vertex and distance.
         *
         * @param vertex   the vertex of the node
         * @param distance the distance of the node
         */
        public Node(K vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

}
