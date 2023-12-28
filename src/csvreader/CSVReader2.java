package csvreader;

import Model.Connection;
import Model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CSVReader class for reading station and connection data from CSV files.
 */
public class CSVReader2 {

    private final List<Station> stations;
    private final List<Connection> connections;
    private final Map<String, Station> nameStationMap;

    /**
     * Constructs a CSVReader with empty lists for stations and connections.
     */
    public CSVReader2() {
        stations = new ArrayList<>();
        connections = new ArrayList<>();
        nameStationMap = new HashMap<>();
    }

    /**
     * Validates station data using regular expressions.
     *
     * @param data the array containing station data
     * @throws IllegalArgumentException if data is invalid
     */
    private void validateStationData(String[] data) {
        if (data.length < 11) {
            throw new IllegalArgumentException("Invalid station data: insufficient fields");
        }

        validateField(data[0], "\\d+", "Invalid station ID");
        validateField(data[1], "[a-zA-Z0-9]+", "Invalid station code");
        validateField(data[2], ".+", "Invalid station UIC");
        validateField(data[3], ".+", "Invalid station name");
        validateField(data[9], "-?\\d+(\\.\\d+)?", "Invalid latitude");
        validateField(data[10], "-?\\d+(\\.\\d+)?", "Invalid longitude");
    }

    /**
     * Validates a field using a regular expression.
     *
     * @param fieldValue the value of the field to validate
     * @param regex      the regular expression pattern
     * @param errorMsg   the error message for invalid data
     * @throws IllegalArgumentException if the field is invalid
     */
    private void validateField(String fieldValue, String regex, String errorMsg) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(errorMsg + ": " + fieldValue);
        }
    }

    /**
     * Reads station data from a CSV file and populates the stations list.
     *
     * @param filePath the path of the station CSV file
     * @return the list of stations
     */
    public ArrayList<Station> readStationsCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                validateStationData(data);

                int id = Integer.parseInt(data[0]);
                String code = data[1].toLowerCase();
                String uic = data[2];
                String name = data[3];
                double latitude = Double.parseDouble(data[9]);
                double longitude = Double.parseDouble(data[10]);

                Station station = new Station(id, code, uic, name, latitude, longitude);
                stations.add(station);
                nameStationMap.put(code, station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(stations);
    }
    /**
     * Reads connection data from a CSV file, matches station codes to stations,
     * and populates the connections list.
     *
     * @param filePath the path of the connection CSV file
     * @return the list of connections
     */
    public ArrayList<Connection> readConnectionsCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String codeA = data[0].toLowerCase();
                String codeB = data[1].toLowerCase();
                int distance = Integer.parseInt(data[2]);
                matchCodesToStations(codeA, codeB, distance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(connections);
    }

    /**
     * Gets the list of stations as an ArrayList.
     *
     * @return the list of stations
     */
    public List<Station> getStationsList() {
        return new ArrayList<>(stations);
    }

    /**
     * Gets the list of connections as an ArrayList.
     *
     * @return the list of connections
     */
    public List<Connection> getConnectionsList() {
        return new ArrayList<>(connections);
    }

    /**
     * Gets the name-station map.
     *
     * @return the name-station map
     */
    public Map<String, Station> getNameStationMap() {
        return new HashMap<>(nameStationMap);
    }

    /**
     * Matches station codes to stations and creates a connection,
     * which is added to the connections list.
     *
     * @param codeA    the code of the first station
     * @param codeB    the code of the second station
     * @param distance the distance between the stations
     */
    private void matchCodesToStations(String codeA, String codeB, int distance) {
        Station stationA = nameStationMap.get(codeA);
        Station stationB = nameStationMap.get(codeB);
        Connection connection = new Connection(stationA, stationB, distance, 0, 0);
        connections.add(connection);
    }
}
