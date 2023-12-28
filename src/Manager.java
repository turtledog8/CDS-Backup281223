//import AVLTree.AVLTree;
//import dataStructures.graph.MyGraph;
//import dataStructures.List.arraylsit.MyArrayList;
//import dataStructures.List.doublelinkedlist.MyDoublyLinkedList;
//import dataStructures.List.linkedlist.MyLinkedList;
//import Model.Connection;
//import Model.Station;
//import dataStructures.Map.MyHashMap;
//
//import csvreader.CSVReader;
//import traversal.dijkstra.DijkstraAlgorithm;
//
//import java.util.*;
//
//
//public class Manager {
//
//    String stationsFilePath = "src/resources/stations.csv";
//    String connectionsFilePath = "src/resources/tracks.csv";
//    private final MyGraph<Station> graph;
//
//
//
//    private MyDoublyLinkedList<Station> stations;
//
//    private final  MyArrayList<Station> stationMyArrayList;
//    private final  MyArrayList<Connection> connectionMyArrayList;
//
//    private final MyDoublyLinkedList<Connection> connections;
//    private AVLTree<String> stationNameTree;
//
//    private MyHashMap<String, Station> stationCodeHashMap;
//
//    public Manager() {
//        CSVReader csvReader = new CSVReader();
//
//        stationMyArrayList = null;
//        connectionMyArrayList = null;
//
//
//
//
//        // Read stations from CSV
//        csvReader.readStationsCSV(stationsFilePath);
//        stations = csvReader.getStations();
//
//
//        // Read connections from CSV
//        csvReader.readConnectionsCSV(connectionsFilePath);
//        connections = csvReader.getConnections();
//
//
//        // Create a graph and add connections
//        graph = new MyGraph<Station>(false, true); // Set as unweighted and weighted
//        addConnectionsToGraph(connections);
//
//        stationNameTree = new AVLTree<String>();
//        buildStationNameTree();
//        graph.printGraph();
//        stationCodeHashMap = new MyHashMap<>();
//        buildStationCodeHashMap();
//    }
//    private void buildStationCodeHashMap() {
//        for (Station station : stations) {
//            stationCodeHashMap.put(station.getCode(), station);
//        }
//    }
//
//    private void buildStationNameTree() {
//        // Build the AVL tree with station names
//        for (Station station : stations) {
//            stationNameTree.insert(station.getNameMedium().toLowerCase());
//        }
//    }
//    private void addConnectionsToGraph(MyDoublyLinkedList<Connection> connections) {
//        for (Connection connection : connections) {
//            if (graph.isWeighted()) {
//                graph.addConnection(connection);
//            } else {
//                graph.connect(connection.getStationA(), connection.getStationB());
//            }
//        }
//    }
//
//
//    public List<String> findShortestPathDijkstra(Station start, Station end) {
//        DijkstraAlgorithm<Station> dijkstraAlgorithm = new DijkstraAlgorithm<>();
//        MyLinkedList<Station> path = dijkstraAlgorithm.findShortestPath(graph, start, end);
//
//        // Convert MyLinkedList to List
//        List<String> resultList = new ArrayList<>(path.size());
//        for (Station station : path) {
//            resultList.add(station.getNameMedium());
//        }
//
//        // Return the shortest path
//        return resultList;
//    }
//
//
//
//
//
//
//
//
//    public List<String> formatStations(List<String> stations) {
//        List<String> formattedStations = new ArrayList<>();
//
//        for (String station : stations) {
//            formattedStations.add(station.toString());
//        }
//
//        return formattedStations;
//    }
//
//
//
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    public List<String> linearSearchStationByName(String name) {
//        long startTime = System.nanoTime();
//
//        // Perform linear search
//        List<String> resultStations = new ArrayList<>();
//        for (Station station : stations) {
//            // Check if the name contains the search query (case-insensitive)
//            if (station.getNameMedium().toLowerCase().contains(name.toLowerCase())) {
//                // Station found
//                resultStations.add(station.getNameMedium() + " code : " + station.getCode());
//            }
//        }
//
//        long endTime = System.nanoTime();
//        long elapsedTime = endTime - startTime;
//        System.out.println("Linear Search Time: " + elapsedTime + " nanoseconds");
//
//        return resultStations;
//    }
//
//
//
//    public List<String> binarySearchStationByName(String name) {
//        long startTime = System.nanoTime();
//
//        // Search in the AVL tree
//        List<String> resultStations = new ArrayList<>();
//        List<String> treeResult = stationNameTree.inOrderTraversal();
//
//        for (String station : treeResult) {
//            // Check if the name contains the search query (case-insensitive)
//            if (station.toLowerCase().contains(name.toLowerCase())) {
//                resultStations.add(station);
//            }
//        }
//
//        long endTime = System.nanoTime();
//        long elapsedTime = endTime - startTime;
//        System.out.println("Binary Search Time: " + elapsedTime + " nanoseconds");
//
//        return resultStations;
//    }
//
//
//
//    private Station getStationByName(String name) {
//        // Retrieve the station by name
//        for (Station station : stations) {
//            if (station.getNameMedium().equalsIgnoreCase(name)) {
//                return station;
//            }
//        }
//        return null;
//    }
//
//    public Station getStationByCodeFromHashMap(String code) {
//        return stationCodeHashMap.get(code);
//    }
//
/////////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void shortestPathPublic(String stationCode1, String stationCode2) {
//        Station startStation = getStationByCode(stationCode1);
//        Station endStation = getStationByCode(stationCode2);
//
//
//        if (startStation != null && endStation != null) {
//            // Check if start and end stations are in the graph
//            if (getGraph().containsVertex(startStation) && getGraph().containsVertex(endStation)) {
//                // Find the shortest path using Dijkstra's algorithm
//                List<String> path = findShortestPathDijkstra(startStation, endStation);
//
//                // Print the path
//                System.out.println("Shortest path from " + startStation.getNameMedium() + " to " + endStation.getNameMedium() + ": ");
//                for (String station : path ){
//                    System.out.print(station + " -> ");
//                }
//            } else {
//                System.out.println("Start or end station not in the graph.");
//            }
//        } else {
//            System.out.println("Invalid station codes.");
//        }
//    }
//
//    protected Station getStationByCode(String code) {
//        for (Station station : stations) {
//            if (station.getCode().equals(code)) {
//                return station;
//            }
//        }
//        return null;
//    }
//
//
//    protected MyGraph<Station> getGraph() {
//        return graph;
//    }
//    public Station[] getStations() {
//        // Convert AVL tree to array
//        List<String> treeResult = stationNameTree.inOrderTraversal();
//        Station[] stationArray = new Station[treeResult.size()];
//
//        for (int i = 0; i < treeResult.size(); i++) {
//            stationArray[i] = getStationByName(treeResult.get(i));
//        }
//
//        return stationArray;
//    }
//    public List<String> binarySearchStationByNameFormatted(String name) {
//        List<String> resultStations = binarySearchStationByName(name);
//        return formatStations(resultStations);
//    }
//
//
//
//
//}
