package graph;

import java.util.Objects;

/**
 * Edge between two vertices.
 *
 * @param <T> the type of vertices in the edge
 */
class Edge<T> {
    private T source;
    private T destination;

    /**
     * Constructs an edge between two vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     */
    public Edge(T source, T destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * Computes the hash code for the edge.
     *
     * @return the hash code of the edge
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    /**
     * Checks if the edge is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge<?> edge = (Edge<?>) obj;
        return Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    /**
     * Gets the first vertex of the edge.
     *
     * @return the first vertex of the edge
     */
    public T getVertexA() {
        return source;
    }

    /**
     * Gets the second vertex of the edge.
     *
     * @return the second vertex of the edge
     */
    public T getVertexB() {
        return destination;
    }
}
