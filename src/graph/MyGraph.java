package graph;

import list.linkedlist.MyLinkedList;
import model.Connection;
import model.Station;
import traversal.GraphWeight;

import java.util.*;
import java.util.function.Function;

/**
 * MyGraph class implements the Graph interface to represent a graph data structure.
 *
 * @param <T> the type of vertices in the graph
 */

public class MyGraph<T> implements Graph<T> {
    private final Map<T, Set<T>> adjacencyList;
    private final Map<Edge<T>, Double> weights;
    private final boolean isDirected;
    private final boolean isWeighted;
    private final Map<T, T> previous = new HashMap<>();

    /**
     * Constructs a new MyGraph with default properties.
     */
    public MyGraph() {
        this(false, false);
    }

    /**
     * Constructs a new MyGraph with specified properties.
     *
     * @param isDirected true if the graph is directed, false otherwise
     * @param isWeighted true if the graph is weighted, false otherwise
     */
    public MyGraph(boolean isDirected, boolean isWeighted) {
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        adjacencyList = new HashMap<>();
        weights = new HashMap<>();
    }

    /**
     * Checks if two vertices are connected in the graph.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @return true if the vertices are connected, false otherwise
     */
    @Override
    public boolean areConnected(T value1, T value2) {
        if (!contains(value1) || !contains(value2)) {
            return false;
        }

        Set<T> neighbors = adjacencyList.get(value1);
        return neighbors != null && neighbors.contains(value2);
    }

    /**
     * Checks if the graph contains a given vertex.
     *
     * @param value the vertex to check for existence
     * @return true if the vertex exists in the graph, false otherwise
     */
    @Override
    public boolean contains(T value) {
        return adjacencyList.containsKey(value);
    }

    /**
     * Gets the count of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    @Override
    public int getVertexCount() {
        return adjacencyList.size();
    }

    /**
     * Checks if the graph is empty.
     *
     * @return true if the graph is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param value the value of the vertex to be added
     */
    @Override
    public void addVertex(T value) {
        if (contains(value)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }

        adjacencyList.put(value, new HashSet<>());
    }

    /**
     * Adds a vertex to the graph if it does not already exist.
     *
     * @param value the value of the vertex to be added
     */
    private void addVertexIfNotExist(T value) {
        if (!contains(value)) {
            adjacencyList.put(value, new HashSet<>());
        }
    }

    /**
     * Connects two vertices in the graph.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     */
    @Override
    public void connect(T value1, T value2) {
        if (isWeighted) {
            throw new UnsupportedOperationException("Cannot add unweighted connection to a weighted graph");
        }

        addVertexIfNotExist(value1);
        addVertexIfNotExist(value2);

        Set<T> neighbors1 = adjacencyList.get(value1);
        Set<T> neighbors2 = adjacencyList.get(value2);

        // Check if the connection already exists
        if (!neighbors1.contains(value2)) {
            neighbors1.add(value2);

            if (!isDirected) {
                // If the graph is undirected, add an edge from value2 to value1 as well
                neighbors2.add(value1);
            }
        }
    }


    /**
     * Connects two vertices in the graph with a specified weight.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @param weight the weight of the connection
     */
    @Override
    public void connect(T value1, T value2, double weight) {
        if (!isWeighted) {
            throw new UnsupportedOperationException("Cannot add a weighted connection to an unweighted graph");
        }

        addVertexIfNotExist(value1);
        addVertexIfNotExist(value2);

        adjacencyList.get(value1).add(value2);

        if (!isDirected) {
            adjacencyList.get(value2).add(value1);
        }

        Edge<T> edge = new Edge<>(value1, value2);
        weights.put(edge, weight);
    }

    /**
     * Gets the weight of the connection between two vertices.
     *
     * @param value1 the first vertex
     * @param value2 the second vertex
     * @return the weight of the connection
     */
    @Override
    public double getWeight(T value1, T value2) {
        if (!isWeighted) {
            throw new UnsupportedOperationException("Graph is not weighted");
        }

        Edge<T> edge = new Edge<>(value1, value2);

        Double returnValue = weights.get(edge);
        return (returnValue == null) ? Double.POSITIVE_INFINITY : returnValue;
    }

    /**
     * Gets the neighbors of a vertex in the graph.
     *
     * @param value the vertex
     * @return an iterable of neighbors of the vertex
     */
    @Override
    public Iterable<T> getNeighbours(T value) {
        Set<T> neighbors = adjacencyList.get(value);
        System.out.println(value);
        return (neighbors != null) ? neighbors : Collections.emptySet();
    }

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise
     */
    @Override
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Checks if the graph is weighted.
     *
     * @return true if the graph is weighted, false otherwise
     */
    @Override
    public boolean isWeighted() {
        return isWeighted;
    }

    /**
     * Gets a collection of all vertices in the graph.
     *
     * @return a collection of vertices
     */
    @Override
    public Collection<T> getVertices() {
        return adjacencyList.keySet();
    }


    /**
     * Adds a connection to the graph using a Connection object.
     *
     * @param connection the Connection object representing the connection to be added
     */
    public void addConnection(Connection connection) {
        if (isWeighted) {
            connect(connection.getStationA(), connection.getStationB(), connection.getDistance());
        } else {
            connect(connection.getStationA(), connection.getStationB());
        }
    }

    /**
     * Prints the adjacency list representation of the graph.
     */
    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (Map.Entry<T, Set<T>> entry : adjacencyList.entrySet()) {
            T vertex = entry.getKey();
            Set<T> neighbors = entry.getValue();

            System.out.print(vertex + " -> ");
            for (T neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if a vertex contains any neighbors in the graph.
     *
     * @param vertex the vertex to check
     * @return true if the vertex has neighbors, false otherwise
     */
    @Override
    public boolean containsVertex(Object vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }

        Set<T> neighbors = adjacencyList.get(vertex);
        return !neighbors.isEmpty();
    }



    /**
     * Gets the connections of a vertex in the graph.
     *
     * @param current the vertex
     * @return a linked list of connections from the vertex
     */
    public MyLinkedList<GraphWeight.Node<T>> getConnection(T current) {
        Set<T> neighbors = adjacencyList.get(current);
        MyLinkedList<GraphWeight.Node<T>> connectionList = new MyLinkedList<>();

        if (neighbors != null) {
            for (T neighbor : neighbors) {
                double weight = isWeighted ? getWeight(current, neighbor) : 1.0;
                connectionList.add(new GraphWeight.Node<>(neighbor, (int) weight));
            }
        }

        return connectionList;
    }

    /**
     * Adds a weighted edge to the graph.
     *
     * @param a      the first vertex of the edge
     * @param b      the second vertex of the edge
     * @param weight the weight of the edge
     */



    public void addEdge(T a, T b, double weight) {
        if (!isWeighted) {
            throw new UnsupportedOperationException("Cannot add a weighted edge to an unweighted graph");
        }

        addVertexIfNotExist(a);
        addVertexIfNotExist(b);

        adjacencyList.get(a).add(b);

        if (!isDirected) {
            adjacencyList.get(b).add(a);
        }

        Edge<T> edge = new Edge<>(a, b);
        weights.put(edge, weight);
    }

    // Assuming T has a method getHeuristicCost(T destination)
    public double getHeuristicCostToDestination(T start, T goal, Function<T, Double> heuristicFunction) {
        return heuristicFunction.apply(start);
    }


    // Assuming T has a method getConnectionWeight(T neighbor)
    public Double getCostBetweenNodes(T current, T neighbor) {
        if (!isWeighted) {
            return 1.0; // Default weight for unweighted graph
        }

        Edge<T> edge = new Edge<>(current, neighbor);
        return weights.getOrDefault(edge, Double.POSITIVE_INFINITY);
    }
    /**
     * Performs a breadth-first search starting from a given vertex.
     *
     * @param startVertex the starting vertex for the search
     * @return a queue containing the vertices visited during the search
     */
    public List<T> BFS(T startVertex) {
        Map<T, Boolean> visited = new HashMap<>();
        for (T vertex : adjacencyList.keySet()) {
            visited.put(vertex, false);
        }

        List<T> bfsOrder = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
        visited.put(startVertex, true);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            bfsOrder.add(currentVertex);

            for (T neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    queue.add(neighbor);
                }
            }
        }

        return bfsOrder;
    }


    /**
     * Performs a depth-first search starting from a given vertex.
     *
     * @param startVertex the starting vertex for the search
     * @return a stack containing the vertices visited during the search
     */
    public Stack<T> DFS(T startVertex) {
        Map<T, Boolean> visited = new HashMap<>();
        for (T vertex : adjacencyList.keySet()) {
            visited.put(vertex, false);
        }

        Stack<T> stack = new Stack<>();
        visited.put(startVertex, true);

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T currentVertex = stack.pop();

            for (T neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    stack.push(neighbor);
                }
            }
        }

        return stack;
    }

    /**
     * Performs a breadth-first search to find stations within a rectangle defined by GPS coordinates.
     *
     * @param startLatitude   The starting latitude of the rectangle.
     * @param startLongitude  The starting longitude of the rectangle.
     * @param endLatitude     The ending latitude of the rectangle.
     * @param endLongitude    The ending longitude of the rectangle.
     * @return A list of stations within the specified rectangle.
     */
    public List<Station> findStationsInRectangle(double startLatitude, double startLongitude,
                                                 double endLatitude, double endLongitude) {
        List<Station> resultStations = new ArrayList<>();
        Map<T, Boolean> visited = new HashMap<>();

        for (T vertex : adjacencyList.keySet()) {
            visited.put(vertex, false);
        }

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.get(vertex)) {
                List<T> stationsInComponent = bfsForRectangle(vertex, startLatitude, startLongitude,
                        endLatitude, endLongitude, visited);

                for (T station : stationsInComponent) {
                    resultStations.add((Station) station);
                }
            }
        }

        return resultStations;
    }

    private List<T> bfsForRectangle(T startVertex, double startLatitude, double startLongitude,
                                    double endLatitude, double endLongitude, Map<T, Boolean> visited) {
        List<T> stationsInRectangle = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();

        visited.put(startVertex, true);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            stationsInRectangle.add(currentVertex);

            for (T neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.get(neighbor) && isStationInRectangle((Station) neighbor, startLatitude, startLongitude,
                        endLatitude, endLongitude)) {
                    visited.put(neighbor, true);
                    queue.add(neighbor);
                }
            }
        }

        return stationsInRectangle;
    }

    private boolean isStationInRectangle(Station station, double startLatitude, double startLongitude,
                                         double endLatitude, double endLongitude) {
        // Check if station's coordinates are within the specified rectangle
        double latitude = station.getLatitude();
        double longitude = station.getLongitude();

        return latitude >= startLatitude && latitude <= endLatitude &&
                longitude >= startLongitude && longitude <= endLongitude;
    }
}