package helpers.graph.model;

public class PriorityVertex implements Comparable<PriorityVertex> {
    private Vertex vertex;
    private int priority;

    public PriorityVertex(Vertex vertex, int priority) {
        this.vertex = vertex;
        this.priority = priority;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PriorityVertex pv) {
        return this.priority - pv.priority;
    }
}
