package helpers.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // connected/disconnected: unlinked verteces
    // directed/undirected: single direction/ bidirectional connections
    // weighted: edges can be weighted
    // stores all verteces

    private List<Vertex> vertices;
    private boolean weighted;
    private boolean directed;

    public Graph() {
        this(false);
    }

    public Graph(boolean weighted) {
        this(weighted, false);
    }
    public Graph(boolean weighted, boolean directed) {
        this.weighted = weighted;
        this.directed = directed;
        this.vertices = new ArrayList<>();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public boolean isDirected() {
        return directed;
    }

    public Vertex getVertexByValue(String data) {
        return vertices.stream().filter(v -> data.equals(v.getData())).findFirst().get();
    }

    public Vertex addVertex(String data) {
        Vertex vertex = new Vertex(data);
        vertices.add(vertex);
        return vertex;
    }

    public void removeVertext(Vertex vertex) {
        vertices.remove(vertex);
    }

    public void addEdge(Vertex startVertex, Vertex endVertex, Integer weight) {
        if(!weighted) {
            weight = null;
        }
        startVertex.addEdge(endVertex, weight);
        if (!directed) {
            endVertex.addEdge(startVertex, weight);
        }
    }

    public void removeEdge(Vertex startVertex, Vertex endVertex) {
        startVertex.removeEdge(endVertex);
        if (!directed) {
            endVertex.removeEdge(startVertex);
        }
    }

    public void print() {
        for (Vertex v : vertices) {
            v.print(weighted);
        }
    }

    public static void main(String[] args) {
        Graph busNetwork = new Graph(true, true);
        Vertex cliftonStation = busNetwork.addVertex("Clifton");
        Vertex capeMayStation = busNetwork.addVertex("Cape May");

        busNetwork.addEdge(cliftonStation, capeMayStation, 1000);
        busNetwork.print();
    }
}

