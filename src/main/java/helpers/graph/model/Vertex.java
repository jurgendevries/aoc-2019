package helpers.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    // vertex can store any kind of data
    // contains a list of connections (edges)

    private String data;
    private List<Edge> edges;

    public Vertex(String data) {
        this.data = data;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Vertex endVertex, Integer weight) {
        this.edges.add(new Edge(this, endVertex, weight));
    }

    public void removeEdge(Vertex endVertex) {
        this.edges.removeIf(e -> e.getEnd().equals(endVertex));
    }

    public String getData() {
        return data;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void print(boolean showWeight) {
        String message = "";
        if (edges.isEmpty()) {
            System.out.println(this.data + " --> ");
            return;
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge currentEdge = edges.get(i);
            if (i == 0) {
                message += currentEdge.getStart().data + " --> ";
            }
            message += currentEdge.getEnd().data;
            if (showWeight) {
                message += " (" + currentEdge.getWeight() + ")";
            }
            if (i != edges.size() - 1) {
                message += ", ";
            }
        }
        System.out.println(message);
    }
}

