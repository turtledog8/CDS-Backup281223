import List.linkedlist.MyLinkedList;
import Map.MyHashMap;
import Model.Connection;
import Model.Station;
import Tree.binarytree.BinarySearchTree;
import csvreader.CSVReader2;
import graph.MyGraph;
import search.BinarySearch;
import sorting.mergesort.MergeSort;
import sorting.selectionsort.SelectionSort;
import traversal.GraphWeight;
import traversal.astar.AStarAlgorithm;
import traversal.dijkstra.DijkstraAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager2 {
    Scanner scanner = new Scanner(System.in);

    private String stationsFilePath = "src/resources/stations.csv";
    private String connectionsFilePath = "src/resources/tracks.csv";
    private CSVReader2 csvReader = new CSVReader2();

    private final ArrayList<Station> stationArrayList = csvReader.readStationsCSV(stationsFilePath);
    private final ArrayList<Connection> connectionArrayList = csvReader.readConnectionsCSV(connectionsFilePath);

    MyLinkedList<Station> stationsLinkedList;
    Connection[] connectionsArray;


    SelectionSort<Connection> selectionSort;
    MergeSort<Station> mergeSortStation;
    MergeSort<Connection> mergeSort;

    BinarySearch<Station> binarySearch;            /////////////////CHECK THIS SHIT OUT
    BinarySearchTree<Station> binaryTree;
    MyHashMap<String, Station> hashMap;
    Station[] allStations;
    GraphWeight<String> graphWeight;

     MyGraph<Station> myGraph;

    public void setMenuChoice() {
        while (true) {
            int menuChoice;

            stationsLinkedList = new MyLinkedList<>();

            allStations = new Station[stationArrayList.size()];
            mergeSortStation = new MergeSort<>();

            selectionSort = new SelectionSort<>();
            mergeSort = new MergeSort<>();

            binarySearch = new BinarySearch<>();

            hashMap = new MyHashMap<>();

            connectionsArray = connectionArrayList.toArray(new Connection[0]);
            binaryTree = new BinarySearchTree<>();

            graphWeight = new GraphWeight<>();

            myGraph = new MyGraph<Station>();
            addConnectionsToGraph(connectionArrayList);



            int counterStation = 0;
            for (Station station : stationArrayList) {
                stationsLinkedList.add(station);
                allStations[counterStation] = station;
                binaryTree.insert(station);
                hashMap.put(station.getCode(), station);
                counterStation++;
            }

            int counterConnection = 0;
            for (Connection connection : connectionArrayList) {
                connectionsArray[counterConnection] = connection;
                counterConnection++;
            }
            myGraph = new MyGraph<Station>();

// Add connections to the graph
            for (Connection connection : connectionArrayList) {
                myGraph.connect(connection.getStationA(), connection.getStationB());
            }


            // Add connections to the graph
            for (Connection connection : connectionArrayList) {
                myGraph.connect(connection.getStationA(), connection.getStationB());
            }



            // NOTE TO TEACHER: I am not really sure what was meant by length i assumed it was the connection length which would mean the distance in this case.
            System.out.println("""

                    CDS Menu:
                    1- Linear Search Station By Name
                    2- Binary Search Station By Name
                    3- Merge Sort Connections By Distance       
                    4- Selection Sort Connections By Distance
                    5- Binary search tree with stations / Search using binary Search tree
                    6- HashMap with station code as key / Search station using code
                    7- Determine which stations fall within "the rectangle" using the BFS algorithm
                    8- Find shortest Path using the dijkstra algorithm
                    9- Find shortest path using the Astar algorithm

                    """);
            System.out.print("Choice: ");
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1 -> {
                    SearchStationByNameLinear();
                }
                case 2 -> {
                    SearchStationByNameBinary();
                }
                case 3 -> {
                    MergeSortConnectionsLength();
                }
                case 4 -> {
                    SelectionSortConnectionsLength();
                }
                case 5 -> {
                    SearchStationUsingBinarySearchTree();
                }
                case 6 -> {
                    SearchStationUsingHashMap();
                }
                case 7 -> {
                    findStationsWithinRectangle();
                }
                case 8 -> {
                    findShortestPathDijkstra();
                }
                case 9 -> {
                    findShortestPathAStar();
                }
                case 0 -> {
                    System.out.println("Bye Bye!");
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }



    ////////////////////********************-- MENU METHODS --********************////////////////////
    private void SearchStationByNameLinear() {
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter the name of the station to search: ");
        String searchName = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Station station : stationArrayList) {
            if (station.getNameMedium().toLowerCase().contains(searchName)) {
                System.out.println("Station found: " + station);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No station found with the provided name.");
        }
    }


    private void SearchStationByNameBinary() {
        System.out.print("Enter the name of the station to search: ");
        String searchName = scanner.next().toLowerCase();

        mergeSortStation.sort(allStations);

        // Use the binarySearch object for searching
        int index = binarySearch(allStations, searchName);

        if (index != -1) {
            System.out.println("Station found: " + allStations[index]);
        } else {
            System.out.println("No station found with the provided name.");
        }
    }



    private void MergeSortConnectionsLength() {
        mergeSort.sort(connectionsArray);
        System.out.println(Arrays.toString(connectionsArray));
        System.out.println();
        System.out.println("↑↑↑ Tracks after merge sort ↑↑↑");
    }


    private void SelectionSortConnectionsLength() {
        selectionSort.sort(connectionsArray);
        System.out.println(Arrays.toString(connectionsArray));
        System.out.println();
        System.out.println("↑↑↑ Tracks after merge sort ↑↑↑");
    }
    private void SearchStationUsingBinarySearchTree() {
        System.out.print("Enter part of the station name to search: ");
        String searchSubstring = scanner.next().toLowerCase();

        // Perform search using binary search tree
        MyLinkedList<Station> matchingStations = binaryTree.searchBySubstring(searchSubstring);

        // Print the result
        if (matchingStations.isEmpty()) {
            System.out.println("No stations found matching the search: '" + searchSubstring + "'.");
        } else {
            System.out.println("Stations matching the search '" + searchSubstring + "':");
            for (Station station : matchingStations) {
                System.out.println(station);
            }
        }
    }

    private void SearchStationUsingHashMap() {
        System.out.print("Enter part of the station name to search: ");
        String searchSubstring = scanner.next().toLowerCase();

        // Perform search using hash map
        MyLinkedList<Station> matchingStations = hashMap.searchBySubstring(searchSubstring);

        // Print the result
        if (matchingStations.isEmpty()) {
            System.out.println("No stations found matching the search: '" + searchSubstring + "'.");
        } else {
            System.out.println("Stations matching the search '" + searchSubstring + "':");
            for (Station station : matchingStations) {
                System.out.println(station);
            }
        }
    }

    private void findStationsWithinRectangle() {
        System.out.print("Enter the station code for the first station: ");
        String stationCode1 = scanner.next().toUpperCase();

        System.out.print("Enter the station code for the second station: ");
        String stationCode2 = scanner.next().toUpperCase();

        // Find the stations corresponding to the given station codes
        Station station1 = findStationByCode(stationCode1);
        Station station2 = findStationByCode(stationCode2);

        if (station1 == null || station2 == null) {
            System.out.println("Invalid station code. Please make sure both station codes are valid.");
            return;
        }

        // Specify the rectangle using station coordinates
        double startLatitude = Math.min(station1.getLatitude(), station2.getLatitude());
        double startLongitude = Math.min(station1.getLongitude(), station2.getLongitude());
        double endLatitude = Math.max(station1.getLatitude(), station2.getLatitude());
        double endLongitude = Math.max(station1.getLongitude(), station2.getLongitude());

        // Find stations within the specified rectangle
        List<Station> stationsInRectangle = myGraph.findStationsInRectangle(startLatitude, startLongitude, endLatitude, endLongitude);

        // Print or process the result as needed
        System.out.println("Stations within the specified rectangle:");
        for (Station station : stationsInRectangle) {
            System.out.println(station);
        }
        System.out.println("Stations within the specified rectangle found successfully");

    }



    public void findShortestPathDijkstra() {
        System.out.println("Enter The Starting Station Code Of Your Journey: ");
        String stationCode1 = scanner.next();
        System.out.println("Enter The Final Station Code Of Your Journey: ");
        String stationCode2 = scanner.next();

        Station startStation = findStationByCode(stationCode1);
        Station endStation = findStationByCode(stationCode2);

        if (startStation != null && endStation != null) {
            // Check if start and end stations are in the graph
            if (getGraph().containsVertex(startStation) && getGraph().containsVertex(endStation)) {
                // Record start time
                long startTime = System.currentTimeMillis();

                // Find the shortest path using Dijkstra's algorithm
                List<String> path = findShortestPathDijkstra(startStation, endStation);

                // Record end time
                long endTime = System.currentTimeMillis();

                // Calculate and print the total distance
                int totalDistance = calculateTotalDistance(path);
                System.out.println("Total distance: " + totalDistance + " units");

                // Print the path
                System.out.println("Shortest path from " + startStation.getNameMedium() + " to " + endStation.getNameMedium() + ": ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println(); // Add a new line for better formatting

                // Print the time taken
                System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
            } else {
                System.out.println("Start or end station not in the graph.");
            }
        } else {
            System.out.println("Invalid station codes.");
        }
    }

    public void findShortestPathAStar() {
        System.out.println("Enter The Starting Station Code Of Your Journey: ");
        String stationCode1 = scanner.next();
        System.out.println("Enter The Final Station Code Of Your Journey: ");
        String stationCode2 = scanner.next();

        Station startStation = findStationByCode(stationCode1);
        Station endStation = findStationByCode(stationCode2);

        if (startStation != null && endStation != null) {
            // Check if start and end stations are in the graph
            if (getGraph().containsVertex(startStation) && getGraph().containsVertex(endStation)) {
                // Record start time
                long startTime = System.currentTimeMillis();

                // Find the shortest path using A* algorithm
                List<String> path = findShortestPathAStar(startStation, endStation);

                // Record end time
                long endTime = System.currentTimeMillis();

                // Calculate and print the total distance
                int totalDistance = calculateTotalDistance(path);
                System.out.println("Total distance: " + totalDistance + " units");

                // Print the path
                System.out.println("Shortest path from " + startStation.getNameMedium() + " to " + endStation.getNameMedium() + ": ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println(); // Add a new line for better formatting

                // Print the time taken
                System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
            } else {
                System.out.println("Start or end station not in the graph.");
            }
        } else {
            System.out.println("Invalid station codes.");
        }
    }

    public List<String> findShortestPathAStar(Station start, Station end) {
        AStarAlgorithm<Station> aStarAlgorithm = new AStarAlgorithm<>(myGraph);
        List<Station> path = aStarAlgorithm.findShortestPath(start, end);

        List<String> resultList = new ArrayList<>(path.size());
        for (Station station : path) {
            resultList.add(station.getNameMedium());
        }

        // Return the shortest path
        return resultList;
    }



////////////////////********************-- HELPER METHODS --********************////////////////////

    // Updated binarySearch method to handle searching for a substring
    private int binarySearch(Station[] list, String searchSubstring) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            String currentName = list[middle].getNameMedium().toLowerCase();

            if (currentName.contains(searchSubstring)) {
                return middle;
            }

            if (currentName.compareTo(searchSubstring) < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    private void addConnectionsToGraph(ArrayList<Connection> connections) {
        for (Connection connection : connections) {
            if (myGraph.isWeighted()) {
                myGraph.addConnection(connection);
            } else {
                myGraph.connect(connection.getStationA(), connection.getStationB());
            }
        }
    }

    private Station findStationByCode(String stationCode) {
        for (Station station : stationArrayList) {
            if (station.getCode().equalsIgnoreCase(stationCode)) {
                return station;
            }
        }
        return null; // Station not found
    }





    public List<String> findShortestPathDijkstra(Station start, Station end) {
        DijkstraAlgorithm<Station> dijkstraAlgorithm = new DijkstraAlgorithm<>();
        MyLinkedList<Station> path = dijkstraAlgorithm.findShortestPath(myGraph, start, end);

        List<String> resultList = new ArrayList<>(path.size());
        for (Station station : path) {
            resultList.add(station.getNameMedium());
        }

        // Return the shortest path
        return resultList;
    }
    protected MyGraph<Station> getGraph() {
        return myGraph;
    }

    private int calculateTotalDistance(List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Station stationA = findStationByName(path.get(i));
            Station stationB = findStationByName(path.get(i + 1));
            Connection connection = findConnection(stationA, stationB);
            totalDistance += connection.getDistance();
        }
        return totalDistance;
    }

    private Station findStationByName(String name) {
        for (Station station : stationArrayList) {
            if (station.getNameMedium().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    private Connection findConnection(Station stationA, Station stationB) {
        for (Connection connection : connectionArrayList) {
            Station connectionStationA = connection.getStationA();
            Station connectionStationB = connection.getStationB();

            // Check if both stations in the connection are not null
            if (connectionStationA != null && connectionStationB != null) {
                // Check if the connection matches either way
                if ((connectionStationA.equals(stationA) && connectionStationB.equals(stationB)) ||
                        (connectionStationA.equals(stationB) && connectionStationB.equals(stationA))) {
                    return connection;
                }
            }
        }
        return null;
    }

}
