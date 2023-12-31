package test;

import model.Station;
import graph.MyGraph;
import org.junit.jupiter.api.Test;
import traversal.astar.AStarAlgorithm;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AStarAlgorithmTest {

    @Test
    void findShortestPath_should_Return_Shortest_Path() {
        //            Graph representation for our own sanity
        //   A------(1)-------B--------(1)------D
        //     \(100)                          /
        //      C--------------(1)-----------/
        ///
        // Create a sample graph
        MyGraph<Station> graph = new MyGraph<>(false, true);
        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        Station stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 0.0);
        Station stationC = new Station(3, "C", "UIC_C", "Station C", 1.0, 2.0);
        Station stationD = new Station(4, "D", "UIC_D", "Station D", 3.0, 0.0);

        graph.addEdge(stationA, stationB, 1.0);
        graph.addEdge(stationA, stationC, 100.0);

        graph.addEdge(stationC, stationD, 1.0);
        graph.addEdge(stationB, stationD, 1.0);

        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);

        List<Station> path = astar.findShortestPath(stationA, stationD);
        List<String> expectedPath = List.of("A", "B", "D");

        List<String> actualPathCodes = path.stream().map(Station::getCode).collect(Collectors.toList());

        System.out.println("Actual Path: " + actualPathCodes);
        System.out.println("Expected Path: " + expectedPath);

        assertEquals(expectedPath, actualPathCodes);
    }

    @Test
    void findShortestPath_given_a_complex_path() {
        //            Graph representation for our own sanity
        //   A------(100)------B--------(100)---------D----(100)----\
        //     \(1)          /(100)                   | (100)        \
        //      \----------C----(1)------- E--(1)-----H              I
        //                 \               \                        /
        //                  \ (100)        --------\(1)            /(1)
        ///                  \                      |             /
        ///                   F ------(100)----------G-----------/

        MyGraph<Station> graph = new MyGraph<>(false, true);

        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        Station stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 0.0);
        Station stationC = new Station(3, "C", "UIC_C", "Station C", 0.5, 1.0);
        Station stationD = new Station(4, "D", "UIC_D", "Station D", 3.0, 0.0);
        Station stationE = new Station(5, "E", "UIC_E", "Station E", 1.5, 1.0);
        Station stationF = new Station(6, "F", "UIC_F", "Station F", 1.0, 2.0);
        Station stationG = new Station(7, "G", "UIC_G", "Station G", 3.0, 2.0);
        Station stationH = new Station(8, "H", "UIC_H", "Station H", 3.0, 2.0);
        Station stationI = new Station(9, "I", "UIC_I", "Station I", 4.0, 1.0);

        graph.addEdge(stationA, stationB, 100.0);
        graph.addEdge(stationA, stationC, 1.0);
        graph.addEdge(stationB, stationC, 100.0);
        graph.addEdge(stationB, stationD, 100.0);
        graph.addEdge(stationD, stationH, 100.0);
        graph.addEdge(stationD, stationI, 100.0);
        graph.addEdge(stationC, stationE, 1.0);
        graph.addEdge(stationE, stationH, 100.0);
        graph.addEdge(stationE, stationG, 1.0);
        graph.addEdge(stationC, stationF, 100.0);
        graph.addEdge(stationF, stationG, 100.0);
        graph.addEdge(stationG, stationI, 1.0);

        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);
        List<Station> path = astar.findShortestPath(stationA, stationI);
        List<String> expectedPath = List.of("A", "C", "E", "G", "I");

        // Extract station codes from the actual path
        List<String> actualPathCodes = path.stream().map(Station::getCode).collect(Collectors.toList());

        System.out.println("Actual Path: " + actualPathCodes);
        System.out.println("Expected Path: " + expectedPath);

        assertEquals(expectedPath, actualPathCodes);
    }



        @Test
    void findShortestPath_noPathFound_shouldReturnEmptyList() {
        // sample graph with no connection between nodes
            MyGraph<Station> graph = new MyGraph<>(false, true);
        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        Station stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 1.0);
        graph.addVertex(stationA);
        graph.addVertex(stationB);

        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);

        List<Station> path = astar.findShortestPath(stationA, stationB);

        assertTrue(path.isEmpty());
    }


    @Test
    void getSteps_shouldReturnSteps() {
        MyGraph<Station> graph = new MyGraph<>(false, true);
        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);

        // Add stations and edges to the graph
        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        Station stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 0.0);
        Station stationC = new Station(3, "C", "UIC_C", "Station C", 1.0, 2.0);
        Station stationD = new Station(4, "D", "UIC_D", "Station D", 3.0, 0.0);

        graph.addEdge(stationA, stationB, 1.0);
        graph.addEdge(stationA, stationC, 100.0);
        graph.addEdge(stationC, stationD, 1.0);
        graph.addEdge(stationB, stationD, 1.0);

        List<Station> path = astar.findShortestPath(stationA, stationD);

        List<Station> steps = astar.getSteps();

        // Print the steps
        System.out.println("Steps: " + steps);

        // Add assertions for steps
        assertNotNull(steps);
        assertEquals(path, steps, "Steps should match the shortest path");
    }




    @Test
    void findShortestPath_startEqualsGoal_shouldReturnSingletonList() {
        // Create a sample graph with a single node
        MyGraph<Station> graph = new MyGraph<>();
        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        graph.addVertex(stationA);

        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);

        List<Station> path = astar.findShortestPath(stationA, stationA);

        assertEquals(List.of(stationA), path);
    }

    @Test
    void findShortestPath_unreachableGoal_shouldReturnEmptyList() {
        MyGraph<Station> graph = new MyGraph<>();
        Station stationA = new Station(1, "A", "UIC_A", "Station A", 0.0, 0.0);
        Station stationB = new Station(2, "B", "UIC_B", "Station B", 1.0, 1.0);
        graph.addVertex(stationA);
        graph.addVertex(stationB);

        AStarAlgorithm<Station> astar = new AStarAlgorithm<>(graph);

        // Find the shortest path to an unreachable goal
        List<Station> path = astar.findShortestPath(stationA, stationB);

        assertTrue(path.isEmpty());
    }



}
