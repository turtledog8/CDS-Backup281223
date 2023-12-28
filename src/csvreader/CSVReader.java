//package csvreader;
//
//import dataStructures.List.doublelinkedlist.MyDoublyLinkedList;
//import dataStructures.Map.MyHashMap;
//import Model.Connection;
//import Model.Station;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
///**
// * CSVReader class for reading station and connection data from CSV files.
// */
//public class CSVReader {
//
//    private final MyDoublyLinkedList<Station> stations;
//    private final MyDoublyLinkedList<Connection> connections;
//    private final MyHashMap<String, Station> nameStationMap;
//
//    /**
//     * Constructs a CSVReader with empty lists for stations and connections.
//     */
//    public CSVReader() {
//        stations = new MyDoublyLinkedList<>();
//        connections = new MyDoublyLinkedList<>();
//        nameStationMap = new MyHashMap<>();
//    }
//
//    /**
//     * Reads station data from a CSV file and populates the stations list.
//     *
//     * @param fileName the name of the station CSV file
//     */
//    public void readStationsCSV(String fileName) {
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            br.readLine(); // Skip the header line
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                int id = Integer.parseInt(data[0]);
//                String code = data[1].toLowerCase();
//                String uic = data[2];
//                String name = data[3];
//                double latitude = Double.parseDouble(data[9]);
//                double longitude = Double.parseDouble(data[10]);
//                Station station = new Station(id, code, uic, name, latitude, longitude);
//                stations.add(station);
//                nameStationMap.put(code, station);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Reads connection data from a CSV file, matches station codes to stations,
//     * and populates the connections list.
//     *
//     * @param fileName the name of the connection CSV file
//     */
//    public void readConnectionsCSV(String fileName) {
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            br.readLine(); // Skip the header line
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                String codeA = data[0].toLowerCase();
//                String codeB = data[1].toLowerCase();
//                int distance = Integer.parseInt(data[2]);
//                matchCodesToStations(codeA, codeB, distance);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Matches station codes to stations and creates a connection,
//     * which is added to the connections list.
//     *
//     * @param codeA    the code of the first station
//     * @param codeB    the code of the second station
//     * @param distance the distance between the stations
//     */
//    private void matchCodesToStations(String codeA, String codeB, int distance) {
//        Station stationA = nameStationMap.get(codeA);
//        Station stationB = nameStationMap.get(codeB);
//        Connection connection = new Connection(stationA, stationB, distance, 0, 0);
//        connections.add(connection);
//    }
//
//    /**
//     * Gets the list of stations.
//     *
//     * @return the list of stations
//     */
//    public MyDoublyLinkedList<Station> getStations() {
//        return stations;
//    }
//
//    /**
//     * Gets the list of connections.
//     *
//     * @return the list of connections
//     */
//    public MyDoublyLinkedList<Connection> getConnections() {
//        return connections;
//    }
//}
