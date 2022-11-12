package helpers.graph;

import helpers.graph.model.Graph;
import helpers.graph.model.Vertex;

public class TestGraph {
    private Graph testGraph;
    public TestGraph() {
        testGraph = new Graph(false, true);
        Vertex startNode = testGraph.addVertex("v0.0.0");
        Vertex v1 = testGraph.addVertex("v1.0.0");
        Vertex v2 = testGraph.addVertex("v2.0.0");

        Vertex v11 = testGraph.addVertex("v1.1.0");
        Vertex v12 = testGraph.addVertex("v1.2.0");
        Vertex v21 = testGraph.addVertex("v2.1.0");

        Vertex v111 = testGraph.addVertex("v1.1.1");
        Vertex v112 = testGraph.addVertex("v1.1.2");
        Vertex v121 = testGraph.addVertex("v1.2.1");
        Vertex v211 = testGraph.addVertex("v2.1.1");

        testGraph.addEdge(startNode, v1, null);
        testGraph.addEdge(startNode, v2, null);

        testGraph.addEdge(v1, v11, null);
        testGraph.addEdge(v1, v12, null);
        testGraph.addEdge(v2, v21, null);

        testGraph.addEdge(v11, v111, null);
        testGraph.addEdge(v11, v112, null);
        testGraph.addEdge(v12, v121, null);
        testGraph.addEdge(v21, v211, null);

        // create a cycle
        testGraph.addEdge(v211, v2, null);
    }

    public Vertex getStartVertex() {
        return testGraph.getVertices().get(0);
    }
}
