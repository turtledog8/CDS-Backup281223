package traversal.astar;

import Map.MyHashMap;
import Model.Station;
import graph.MyGraph;
import traversal.GraphWeight;

import java.util.*;

public class AStarAlgorithm<T> {
    private MyHashMap<T, Double> gScores; // Cost from start to this node
    private MyHashMap<T, Double> fScores; // gScore + heuristic cost to destination

    public static class Node<K> {
        K vertex;
        double gScore;
        double fScore;

        public Node(K vertex, double gScore, double fScore) {
            this.vertex = vertex;
            this.gScore = gScore;
            this.fScore = fScore;
        }
    }

    private MyGraph<T> graph;
    private List<T> steps; // Store steps during the algorithm execution

    public AStarAlgorithm(MyGraph<T> graph) {
        this.graph = graph;
        this.steps = new ArrayList<>();
    }

    public List<T> findShortestPath(T start, T goal) {
        PriorityQueue<Node<T>> openSet = new PriorityQueue<>(Comparator.comparingDouble(node -> node.fScore));
        MyHashMap<T, T> cameFrom = new MyHashMap<>();
        gScores = new MyHashMap<>();
        fScores = new MyHashMap<>();

        gScores.put(start, 0.0);
        fScores.put(start, getHeuristicCostToDestination(start, goal));
        openSet.add(new Node<>(start, 0, fScores.get(start)));

        while (!openSet.isEmpty()) {
            T current = openSet.poll().vertex;
            steps.add(current); // Store the current step

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (GraphWeight.Node<T> connection : graph.getConnection(current)) {
                T neighbor = connection.getData();
                double tentativeGScore = gScores.getOrDefault(current, Double.POSITIVE_INFINITY) + connection.getWeight();

                if (tentativeGScore < gScores.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScores.put(neighbor, tentativeGScore);
                    fScores.put(neighbor, tentativeGScore + getHeuristicCostToDestination(neighbor, goal));
                    openSet.add(new Node<>(neighbor, tentativeGScore, fScores.get(neighbor)));
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private List<T> reconstructPath(MyHashMap<T, T> cameFrom, T current) {
        List<T> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(current); // Add the start node
        Collections.reverse(path);
        return path;
    }

    private double getHeuristicCostToDestination(T start, T goal) {
        double startLat = ((Station) start).getLatitude();
        double startLng = ((Station) start).getLongitude();
        double goalLat = ((Station) goal).getLatitude();
        double goalLng = ((Station) goal).getLongitude();

        // Haversine formula for great-circle distance
        double R = 6371.0; // Earth radius in kilometers
        double dLat = Math.toRadians(goalLat - startLat);
        double dLng = Math.toRadians(goalLng - startLng);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(goalLat)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }


    // Getter method to retrieve the steps
    public List<T> getSteps() {
        return steps;
    }
}
