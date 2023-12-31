package Model;

/**
 * The Station class represents a train station with various attributes.
 */
public class Station implements Comparable<Station> {
    private int id;
    private String code;
    private String uic;
    private String nameMedium;
    private double geo_lat;
    private double geo_lng;

    /**
     * Constructs a Station object with the specified attributes.
     *
     * @param id      the ID of the station
     * @param code    the code of the station
     * @param uic     the UIC code of the station
     * @param name    the medium-length name of the station
     * @param geo_lat the latitude of the station
     * @param geo_lng the longitude of the station
     */
    public Station(int id, String code, String uic, String name, Double geo_lat, Double geo_lng) {
        this.id = id;
        this.code = code;
        this.nameMedium = name;
        this.uic = uic;
        this.geo_lat = geo_lat;
        this.geo_lng = geo_lng;
    }

    /**
     * Gets the code of the station.
     *
     * @return the code of the station
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns a string representation of the Station object.
     *
     * @return a string representation of the Station object
     */
    @Override
    public String toString() {
        return String.format("Station{id=%d, code='%s', name='%s', uic='%s', latitude=%.6f, longitude=%.6f}%n",
                id, code, nameMedium, uic, geo_lat, geo_lng);
    }

    /**
     * Gets the medium-length name of the station.
     *
     * @return the medium-length name of the station
     */
    public String getNameMedium() {
        return nameMedium;
    }

    /**
     * Gets the longitude of the station.
     *
     * @return the longitude of the station
     */
    public Double getLongitude() {
        return geo_lng;
    }

    /**
     * Gets the latitude of the station.
     *
     * @return the latitude of the station
     */
    public Double getLatitude() {
        return geo_lat;
    }

    @Override
    public int compareTo(Station o) {
        if (o == null) {
            throw new NullPointerException("Station objects cannot be null.");
        }

        return nameMedium.compareTo(o.nameMedium);
    }

}
