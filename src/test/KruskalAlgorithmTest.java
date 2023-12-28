package test;

import graph.MyGraph;
import org.junit.Test;
import traversal.kruskal.KruskalAlgorithm;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KruskalAlgorithmTest {

    @Test
    public void testMinimumSpanningTreeOnBasicGraph() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("B", "D", 7.0);
        graph.addEdge("C", "D", 3.0);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Assert minimumSpanningTree contains the expected edges
        assertEquals(3, minimumSpanningTree.size());
        System.out.println(minimumSpanningTree);

        // Create a string representation of the expected minimumSpanningTree
        String expectedString = "[Edge{source=B, destination=C, weight=1.0}," +
                " Edge{source=A, destination=B, weight=2.0}," +
                " Edge{source=C, destination=D, weight=3.0}]";

        // Convert the minimumSpanningTree to string and compare
        assertEquals(3, minimumSpanningTree.size());

        assertEquals(expectedString, minimumSpanningTree.toString());
    }


    @Test
    public void testMinimumSpanningTreeOnDisconnectedGraph() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("C", "D", 3.0);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Assert that the minimumSpanningTree contains the expected edges
        assertEquals(2, minimumSpanningTree.size());



        System.out.println(minimumSpanningTree);
        String expectedString = "[Edge{source=A, destination=B, weight=2.0}," +
                " Edge{source=C, destination=D, weight=3.0}]";

        // Convert the minimumSpanningTree to string and compare
        assertEquals(expectedString, minimumSpanningTree.toString());
    }



    @Test
    public void testMinimumSpanningTreeOnGraphWithCycles() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("C", "A", 4.0);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        System.out.println(minimumSpanningTree);
        String expectedString = "[Edge{source=B, destination=C, weight=1.0}," +
                " Edge{source=A, destination=B, weight=2.0}]";


        assertEquals(2, minimumSpanningTree.size());

        assertEquals(expectedString, minimumSpanningTree.toString());
    }

    @Test
    public void testMinimumSpanningTreeOnGraphWithDuplicateEdges() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "B", 3.0);
        graph.addEdge("B", "C", 1.0);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);


        System.out.println(minimumSpanningTree);
        String expectedString = "[Edge{source=B, destination=C, weight=1.0}," +
                " Edge{source=A, destination=B, weight=3.0}]";
        assertEquals(2, minimumSpanningTree.size());
        assertEquals(expectedString, minimumSpanningTree.toString());
    }



    @Test
    public void testMinimumSpanningTreeOnGraphWithNegativeWeights() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", -2.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "C", -1.0);
        graph.addEdge("B", "D", 7.0);
        graph.addEdge("C", "D", 3.0);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            kruskalAlgorithm.findMinimumSpanningTree(graph);
        });
    }


    @Test
    public void testMinimumSpanningTreeOnLargeGraph() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addEdge("A", "B", 4.0);
        graph.addEdge("A", "H", 8.0);
        graph.addEdge("B", "H", 11.0);
        graph.addEdge("B", "C", 8.0);
        graph.addEdge("H", "I", 7.0);
        graph.addEdge("H", "G", 1.0);
        graph.addEdge("I", "G", 6.0);
        graph.addEdge("I", "C", 2.0);
        graph.addEdge("D", "C", 7.0);
        graph.addEdge("G", "F", 2.0);
        graph.addEdge("D", "F", 14.0);
        graph.addEdge("D", "E", 9.0);
        graph.addEdge("F", "E", 10.0);



        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);
        System.out.println(minimumSpanningTree);
        String expectedString = "[Edge{source=H, destination=G, weight=1.0}," +
                " Edge{source=G, destination=F, weight=2.0}," +
                " Edge{source=I, destination=C, weight=2.0}," +
                " Edge{source=A, destination=B, weight=4.0}," +
                " Edge{source=I, destination=G, weight=6.0}," +
                " Edge{source=D, destination=C, weight=7.0}," +
                " Edge{source=A, destination=H, weight=8.0}," +
                " Edge{source=D, destination=E, weight=9.0}]";
        assertEquals(8, minimumSpanningTree.size());
        assertEquals(expectedString, minimumSpanningTree.toString());
    }

    @Test
    public void testMinimumSpanningTreeOnEmptyGraph() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        System.out.println(minimumSpanningTree);
        assertEquals(0, minimumSpanningTree.size());
    }

    @Test
    public void testMinimumSpanningTreeOnGraphWithSingleVertex() {
        MyGraph<String> graph = new MyGraph<String>(false, true);
        graph.addVertex("A");
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();

        List<KruskalAlgorithm.Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        System.out.println(minimumSpanningTree);
        assertEquals(0, minimumSpanningTree.size());
    }

}
