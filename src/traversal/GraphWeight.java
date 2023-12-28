package traversal;

import List.linkedlist.MyLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * The GraphWeight class represents a weighted graph with vertices and edges.
 *
 * @param <T> the type of data stored in the graph
 */
public class GraphWeight<T> {

    private Map<T, MyLinkedList<Node<T>>> adjacencyList;

    /**
     * Constructs an empty weighted graph.
     */
    public GraphWeight() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to be added
     */
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new MyLinkedList<>());
        }
    }

    /**
     * Adds a weighted edge between two vertices in the graph.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void addEdge(T source, T destination, int weight) {
        Node<T> sourceNode = new Node<>(destination, weight);
        Node<T> destinationNode = new Node<>(source, weight);

        adjacencyList.get(source).add(sourceNode);
        adjacencyList.get(destination).add(destinationNode);
    }

    /**
     * Gets the connections (edges) of a specific vertex in the graph.
     *
     * @param vertex the vertex to get connections for
     * @return a linked list of connections for the vertex
     */
    public MyLinkedList<Node<T>> getConnections(T vertex) {
        return adjacencyList.get(vertex);
    }

    /**
     * Gets the connection (edges) of a specific vertex in the graph.
     *
     * @param vertex the vertex to get connections for
     * @return a linked list of connections for the vertex
     */
    public MyLinkedList<Node<T>> getConnection(T vertex) {
        return adjacencyList.get(vertex);
    }

    /**
     * Gets the neighbors (adjacent vertices) of a specific vertex in the graph.
     *
     * @param current the vertex to get neighbors for
     * @return a linked list of neighbors for the vertex
     */
    public MyLinkedList<T> getNeighbors(T current) {
        MyLinkedList<T> neighbors = new MyLinkedList<>();
        for (Node<T> node : adjacencyList.get(current)) {
            neighbors.add(node.getData());
        }
        return neighbors;
    }

    /**
     * Checks if the graph is weighted.
     *
     * @return true if the graph is weighted, false otherwise
     */
    public boolean isWeighted() {
        // Check if the graph is weighted by looking at the weight of the first edge
        return !adjacencyList.isEmpty() && !adjacencyList.values().iterator().next().isEmpty()
                && adjacencyList.values().iterator().next().get(0).getWeight() != 0;
    }

    /**
     * Gets the weight of the edge between two vertices in the graph.
     *
     * @param current the source vertex
     * @param neighbor the destination vertex
     * @return the weight of the edge, or Double.POSITIVE_INFINITY if there is no direct connection
     */
    public double getWeight(T current, T neighbor) {
        MyLinkedList<Node<T>> connections = adjacencyList.get(current);
        for (Node<T> node : connections) {
            if (node.getData().equals(neighbor)) {
                return node.getWeight();
            }
        }
        return Double.POSITIVE_INFINITY; // Return positive infinity if no direct connection
    }

    /**
     * The Node class represents a node in the weighted graph with data and weight.
     *
     * @param <T> the type of data stored in the node
     */
    public static class Node<T> {
        private T data;
        private int weight;

        /**
         * Constructs a node with the specified data and weight.
         *
         * @param data   the data of the node
         * @param weight the weight of the edge
         */
        public Node(T data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        /**
         * Gets the data of the node.
         *
         * @return the data of the node
         */
        public T getData() {
            return data;
        }

        /**
         * Gets the weight of the edge.
         *
         * @return the weight of the edge
         */
        public int getWeight() {
            return weight;
        }
    }
}
