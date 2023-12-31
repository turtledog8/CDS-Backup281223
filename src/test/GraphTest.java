package test;

import graph.MyGraph;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

// i do not know which line I am missing from the test coverage my condolences.
public class GraphTest {
    private MyGraph<Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new MyGraph<Integer>();
    }

    @Test
    public void default_Constructor_Makes_Undirected_Unweighted_Graph() {
        assertFalse(graph.isDirected());
        assertFalse(graph.isWeighted());
    }

    @Test
    public void custom_Constructor_Returns_Inputted_Details() {
        MyGraph<Integer> directedGraph = new MyGraph<Integer>(true, false);
        assertTrue(directedGraph.isDirected());
        assertFalse(directedGraph.isWeighted());

        MyGraph<Integer> weightedGraph = new MyGraph<Integer>(false, true);
        assertFalse(weightedGraph.isDirected());
        assertTrue(weightedGraph.isWeighted());

        MyGraph<Integer> directedWeightedGraph = new MyGraph<Integer>(true, true);
        assertTrue(directedWeightedGraph.isDirected());
        assertTrue(directedWeightedGraph.isWeighted());
    }

    @Test
    public void isEmpty_Returns_True_On_Creation() {
        assertTrue(graph.isEmpty());
    }

    @Test
    public void isEmpty_Returns_False_On_Non_Empty_Graph() {
        graph.addVertex(1);
        assertFalse(graph.isEmpty());
    }

    @Test
    public void connect_Adds_Values() {
        assertFalse(graph.contains(1));
        assertFalse(graph.contains(2));
        graph.connect(1, 2);
        assertTrue(graph.contains(1));
        assertTrue(graph.contains(2));
    }

    @Test
    public void addVertex_Increases_Vertex_Count() {
        assertEquals(0, graph.getVertexCount());
        graph.addVertex(1);
        assertEquals(1, graph.getVertexCount());
    }

    @Test
    public void creatingVertex_In_Undirected_Graph_Without_Connecting_Are_Unconnected() {
        assertFalse(graph.areConnected(1, 2));
        assertFalse(graph.areConnected(2, 3));
        assertFalse(graph.areConnected(1, 3));

        graph.connect(1, 2);
        graph.connect(2, 3);

        assertTrue(graph.areConnected(1, 2));
        assertTrue(graph.areConnected(2, 3));
        assertFalse(graph.areConnected(1, 3));
    }

    @Test
    public void connectedVertexes_In_Directed_Graph_Go_One_Way() {
        graph = new MyGraph<Integer>(true, false);
        graph.connect(1, 2);

        assertTrue(graph.areConnected(1, 2));
        assertFalse(graph.areConnected(2, 1));
    }

    @Test
    public void getVertex_Count_Returns_Amount_Of_Vertexes() {
        assertEquals(0, graph.getVertexCount());

        graph.connect(1, 2);
        assertEquals(2, graph.getVertexCount());
    }

    @Test
    public void getWeight_Returns_Inputted_Weight() {
        assertThrows(UnsupportedOperationException.class, () -> graph.getWeight(1, 2));

        graph = new MyGraph<Integer>(false, true);
        graph.connect(1, 2, 0.5);
        assertEquals(0.5, graph.getWeight(1, 2), 0.0001);
    }

    @Test
    public void getNeighbours_Returns_Neighbours() {
        graph.connect(1, 2);
        Set<Integer> neighbors = new HashSet<>();
        for (Integer neighbor : graph.getNeighbours(1)) {
            neighbors.add(neighbor);
        }
        assertTrue(neighbors.contains(2));

        if (!graph.isDirected()) {
            neighbors.clear();
            for (Integer neighbor : graph.getNeighbours(2)) {
                neighbors.add(neighbor);
            }
            assertTrue(neighbors.contains(1));
        }
    }

    @Test
    public void getWeight_ThrowsException_InUnweightedGraph() {
        assertThrows(UnsupportedOperationException.class, () -> graph.getWeight(1, 2));
    }

    @Test
    public void areConnected_ReturnsFalse_ForUnconnectedVertices() {
        assertFalse(graph.areConnected(1, 2));
    }

    @Test
    public void bfs_ReturnsCorrectOrder_InUndirectedGraph() {
        graph.connect(1, 2);
        graph.connect(2, 3);
        graph.connect(1, 4);

        List<Integer> bfsOrder = graph.BFS(1);

        assertEquals(1, bfsOrder.get(0));
        assertEquals(2, bfsOrder.get(1));
        assertEquals(4, bfsOrder.get(2));
        assertEquals(3, bfsOrder.get(3));
    }


    @Test
    public void dfs_ReturnsCorrectOrder_InDirectedGraph() {
        graph = new MyGraph<Integer>(true, false);
        graph.connect(1, 2);
        graph.connect(2, 3);
        graph.connect(1, 4);

        Stack<Integer> dfsOrder = graph.DFS(1);

        Set<Integer> expectedOrder = new HashSet<>(List.of(1, 2, 3, 4));

        while (!dfsOrder.isEmpty()) {
            assertTrue(expectedOrder.contains(dfsOrder.pop()));
        }
    }
    @Test
    public void addVertex_DoesNotIncreaseCount_ForExistingVertex_throws_exception() {
        graph.addVertex(1);
        int initialCount = graph.getVertexCount();

        // Use assertThrows to check if the expected exception is thrown
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1));

        assertEquals("Vertex already exists.", thrownException.getMessage());
        assertEquals(initialCount, graph.getVertexCount());
    }


    @Test
    public void connect_ThrowsException_InWeightedGraph_WithoutWeight() {
        graph = new MyGraph<Integer>(false, true);
        graph.addVertex(1);
        graph.addVertex(2);
        assertThrows(UnsupportedOperationException.class, () -> graph.connect(1, 2));
    }

    @Test
    public void connect_ThrowsException_InUnweightedGraph_WithWeight() {
        graph = new MyGraph<Integer>(true, false);
        graph.addVertex(1);
        graph.addVertex(2);
        assertThrows(UnsupportedOperationException.class, () -> graph.connect(1, 2, 0.5));
    }

    @Test
    public void addConnection_AddsConnectionCorrectly() {
        MyGraph<Station> graph2 = new MyGraph<Station>(false, true);

        Station stationB = new Station(1, "code1", "uic1", "station1", 1.0, 1.0);
        Station stationA = new Station(2, "code1", "uic2", "station2", 1.0, 1.0);


        graph2.addVertex(stationA);
        graph2.addVertex(stationB);



        Connection connection = new Connection(stationA,  stationB, 4, 1 , 1 );
        graph2.addConnection(connection);

            assertTrue(graph2.areConnected(stationA, stationB));
        assertEquals(4, graph2.getWeight(stationA, stationB), 0.0001);
    }


////////////////////////////////////
@Test
public void getVertices_ReturnsCorrectSet() {
    graph.addVertex(1);
    graph.addVertex(2);
    Set<Integer> vertices = new HashSet<>(graph.getVertices());
    assertTrue(vertices.contains(1));
    assertTrue(vertices.contains(2));
}

    @Test
    public void addEdge_AddsEdgeCorrectly() {
        MyGraph<Station> graph2 = new MyGraph<Station>(false, true);

        Station stationB = new Station(1, "code1", "uic1", "station1", 1.0, 1.0);
        Station stationA = new Station(2, "code1", "uic2", "station2", 1.0, 1.0);


        graph2.addEdge(stationA, stationB, 4);

        assertTrue(graph2.areConnected(stationA, stationB));
    }

}
