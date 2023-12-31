package test;

import graph.MyGraph;
import list.linkedlist.MyLinkedList;
import org.junit.jupiter.api.Test;
import traversal.dijkstra.DijkstraAlgorithm;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    @Test
    void findShortestPath_basicScenario() {
         //            Graph representation for our own sanity
        //   A------(1)-------B--------(1)------D
        //     \(100)                          /
        //      C--------------(1)-----------/
      ///


        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 100.0);

        graph.addEdge("C", "D", 1.0);
        graph.addEdge("B", "D", 1.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "D");

        String actualPath = path.toString(); // Convert the MyLinkedList to a string
        String expectedPath = "[A, B, D]";

        System.out.println("Actual Path: " + actualPath);
        System.out.println("Expected Path: " + expectedPath);

        assertEquals(expectedPath, actualPath);
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


        MyGraph<String> graph = new MyGraph<String>(false, true);

        graph.addEdge("A", "B", 100.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "C", 100.0);
        graph.addEdge("B", "D", 100.0);
        graph.addEdge("D", "H", 100.0);
        graph.addEdge("D", "I", 100.0);
        graph.addEdge("C", "E", 1.0);
        graph.addEdge("E", "H", 100.0);
        graph.addEdge("E", "G", 1.0);
        graph.addEdge("C", "F", 100.0);
        graph.addEdge("F", "G", 100.0);
        graph.addEdge("G", "I", 1.0);



        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "I");

        String actualPath = path.toString(); // Convert the MyLinkedList to a string
        String expectedPath = "[A, C, E, G, I]";

        System.out.println("Actual Path: " + actualPath);
        System.out.println("Expected Path: " + expectedPath);

        assertEquals(expectedPath, actualPath);
    }




    @Test
    void findShortestPath_negativeWeights() {
        MyGraph<String> graph = new MyGraph<String>(false, true);

        graph.addEdge("A", "B", -1.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                dijkstra.findShortestPath(graph, "A", "B")
        );

        assertEquals("Negative weights are not allowed.", exception.getMessage());
    }

    @Test
    void findShortestPath_disconnectedGraph() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("C", "D", 2.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "D");

        assertTrue(path.isEmpty());
    }

    @Test
    void findShortestPath_unreachableDestination() {
        MyGraph<String> graph = new MyGraph<String>(false, true);

        // Creating two disconnected components
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("C", "D", 1.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "D");

        assertTrue(path.isEmpty(), "Path should be empty for unreachable destination");
    }

    @Test
    void findShortestPath_equalSourceAndDestination() {
        MyGraph<String> graph = new MyGraph<String>(false, true);

        graph.addEdge("A", "B", 1.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "A");

        assertEquals("[A]", path.toString(), "Path should contain only the source vertex");
    }

    @Test
    void findShortestPath_graphWithLoops() {
        MyGraph<String> graph = new MyGraph<String>(false, true);

        // Creating a graph with loops
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "A", 2.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "B");

        assertEquals("[A, B]", path.toString(), "Path should handle graphs with loops");
    }

    @Test
    void findShortestPath_graphWithDuplicateEdges() {
        MyGraph<String> graph = new MyGraph<String>(false, true);

        // Creating a graph with duplicate edges
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "B", 1.0);

        DijkstraAlgorithm<String> dijkstra = new DijkstraAlgorithm<>();
        MyLinkedList<String> path = dijkstra.findShortestPath(graph, "A", "B");

        assertEquals("[A, B]", path.toString(), "Path should consider the shortest edge");
    }

}
