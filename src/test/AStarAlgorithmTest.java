package test;

import graph.MyGraph;
import Model.Connection;
import Model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import traversal.astar.AStarAlgorithm;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AStarAlgorithmTest {

    private AStarAlgorithm<Station> aStar;
    private Station stationA;
    private Station stationB;
    private Station stationC;
    private Station stationD;
    private Station stationE;
    private Station stationF;
    private Station stationG;

    private MyGraph graph;
    @BeforeEach
    void setUp() {
        stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 1.0);
        stationC = new Station(3, "C", "UIC_C", "Station C", 0.0, 0.0);
        stationD = new Station(4, "D", "UIC_D", "Station D", 1.0, 1.0);
        stationE = new Station(5, "E", "UIC_E", "Station E", 0.0, 0.0);
        stationF = new Station(6, "F", "UIC_F", "Station F", 1.0, 1.0);
        stationG = new Station(7, "G", "UIC_G", "Station G", 1.0, 1.0);

        // Create connections between stations
        Connection connectionAB = new Connection(stationA, stationB, 1, 0, 0);
        Connection connectionAC = new Connection(stationA, stationC, 4, 0, 0);
        Connection connectionBC = new Connection(stationB, stationC, 3, 0, 0);
        Connection connectionBD = new Connection(stationB, stationD, 1, 0, 0);
        Connection connectionBE = new Connection(stationB, stationE, 10, 0, 0);
        Connection connectionCD = new Connection(stationC, stationD, 6, 0, 0);
        Connection connectionCG = new Connection(stationC, stationG, 3, 0, 0);
        Connection connectionED = new Connection(stationE, stationD, 5, 0, 0);
        Connection connectionEF = new Connection(stationE, stationF, 7, 0, 0);
        Connection connectionGD = new Connection(stationG, stationD, 1, 0, 0);
        Connection connectionGE = new Connection(stationG, stationE, 2, 0, 0);
        Connection connectionGF = new Connection(stationG, stationF, 5, 0, 0);

        // Create the graph
        Map<Station, List<Connection>> graph = new HashMap<>();
        graph.put(stationA, Arrays.asList(connectionAB, connectionAC));
        graph.put(stationB, Arrays.asList(connectionAB, connectionAC, connectionBC, connectionBD, connectionBE));
        graph.put(stationC, Arrays.asList(connectionAC, connectionBC, connectionCD, connectionCG));
        graph.put(stationD, Arrays.asList(connectionBD, connectionCD, connectionED, connectionGD));
        graph.put(stationE, Arrays.asList(connectionBE, connectionED, connectionEF, connectionGE));
        graph.put(stationF, Arrays.asList(connectionEF, connectionGF));
        graph.put(stationG, Arrays.asList(connectionCG, connectionGD, connectionGE, connectionGF));

        aStar = new AStarAlgorithm<>((MyGraph<Station>) graph);
    }
    @Test
    void testShortestPathAD() {
        List<Station> shortestPathAD = aStar.findShortestPath(stationA, stationD);
        printSteps("Shortest Path from A to D:", aStar.getSteps());
        assertEquals(Arrays.asList(stationA, stationB, stationD), shortestPathAD);
    }



    @Test
    void testShortestPathAEF() {
        List<Station> shortestPathAEF = aStar.findShortestPath(stationA, stationF);
        printSteps("Shortest Path from A to F:", aStar.getSteps());
        assertEquals(Arrays.asList(stationA, stationC, stationG, stationF), shortestPathAEF);
    }


    private void printSteps(String message, List<Station> steps) {
        System.out.println(message);
        for (Station step : steps) {
            System.out.println(step.getNameMedium());
        }
    }
    //////////////////////////////////////////////////////////////////////////////


    @Test
    void testInvalidInput() {
        List<Station> invalidPath = aStar.findShortestPath(null, stationD);
        assertEquals(Collections.emptyList(), invalidPath);
    }

    @Test
    void testUnreachableDestination() {
        Station unreachableStation = new Station(8, "H", "UIC_H", "Station H", 2.0, 2.0);
        List<Station> unreachablePath = aStar.findShortestPath(stationA, unreachableStation);
        assertEquals(Collections.emptyList(), unreachablePath);
    }


}