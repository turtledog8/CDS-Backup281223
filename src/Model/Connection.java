package Model;

/**
 * The Connection class represents a connection between two stations, including the distance between them.
 * It is used for storing connections from a CSV file.
 */
public class Connection implements Comparable<Connection> {
    private final Station stationA;
    private final Station stationB;
    private final int distance;
    private final int dont;
    private final int dont2;

    /**
     * Constructs a Connection object with the specified stations and distance.
     *
     * @param stationA the starting station of the connection
     * @param stationB the destination station of the connection
     * @param distance the distance between the two stations
     * @param dont     this remains unused I don't know what it is
     * @param dont2    this also remains unused I still don't know what it is
     */
    public Connection(Station stationA, Station stationB, int distance, int dont, int dont2) {
        this.stationA = stationA;
        this.stationB = stationB;
        this.distance = distance;
        this.dont = dont;
        this.dont2 = dont2;
    }

    /**
     * Returns a string representation of the Connection object.
     *
     * @return a string representation of the Connection object
     */
    @Override
    public String toString() {
        return "Connection{" +
                "stationA=" + stationA +
                ", stationB=" + stationB +
                ", distance=" + distance +
                ", dont=" + dont +
                ", dont2=" + dont2 +
                '}';
    }

    /**
     * Gets the starting station of the connection.
     *
     * @param <T> the type of the station
     * @return the starting station of the connection
     */
    public <T> T getStationA() {
        return (T) stationA;
    }

    /**
     * Gets the destination station of the connection.
     *
     * @param <T> the type of the station
     * @return the destination station of the connection
     */
    public <T> T getStationB() {
        return (T) stationB;
    }

    /**
     * Gets the distance between the two stations in the connection.
     *
     * @return the distance between the two stations
     */
    public double getDistance() {
        return distance;
    }



    @Override
    public int compareTo(Connection other) {
        // Compare connections based on their distance (length)
        return Integer.compare((int) this.getDistance(), (int) other.getDistance());
    }

}
