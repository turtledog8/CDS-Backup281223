package graph;

import java.util.Collection;

/**
 * Interface for graphs with basic functionality.
 *
 * @param <T> Data type to be stored.
 */
public interface Graph<T> {
    /**
     * Checks if two vertices are connected in the graph.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @return true if the vertices are connected, false otherwise
     */
    boolean areConnected(T value1, T value2);

    /**
     * Checks if the graph contains a given vertex.
     *
     * @param value the vertex to check for existence
     * @return true if the vertex exists in the graph, false otherwise
     */
    boolean contains(T value);

    /**
     * Gets the count of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    int getVertexCount();

    /**
     * Checks if the graph is empty.
     *
     * @return true if the graph is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Adds a vertex to the graph.
     *
     * @param value the value of the vertex to be added
     */
    void addVertex(T value);

    /**
     * Connects two vertices in the graph.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     */
    void connect(T value1, T value2);

    /**
     * Connects two vertices in the graph with a specified weight.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @param weight the weight of the connection
     */
    void connect(T value1, T value2, double weight);

    /**
     * Gets the weight of the connection between two vertices.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @return the weight of the connection
     */
    double getWeight(T value1, T value2);

    /**
     * Gets the neighbors of a vertex in the graph.
     *
     * @param value the vertex
     * @return an iterable of neighbors of the vertex
     */
    Iterable<T> getNeighbours(T value);

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise
     */
    boolean isDirected();

    /**
     * Checks if the graph is weighted.
     *
     * @return true if the graph is weighted, false otherwise
     */
    boolean isWeighted();

    /**
     * Gets a collection of all vertices in the graph.
     *
     * @return a collection of vertices
     */
    Collection<T> getVertices();

    /**
     * Checks if a vertex contains any neighbors in the graph.
     *
     * @param vertex the vertex to check
     * @return true if the vertex has neighbors, false otherwise
     */
    boolean containsVertex(Object vertex);

}
