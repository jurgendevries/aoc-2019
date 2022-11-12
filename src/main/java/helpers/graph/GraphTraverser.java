package helpers.graph;

import helpers.graph.model.Edge;
import helpers.graph.model.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphTraverser {

    /**
     * traverse the graph Vertex by Vertex. 1 connects to 2 and 3 -> first travers Vertex 2 until exhausted
     * next go back and traverse 3 until exhausted etc. etc.
     * @param start
     * @param visitedVertices
     */
    public static void depthFirstTraversal(Vertex start, List<Vertex> visitedVertices) {
        System.out.println(start.getData());
        for (Edge edge : start.getEdges()) {
            Vertex neighbour = edge.getEnd();

            if (!visitedVertices.contains(neighbour)) {
                visitedVertices.add(neighbour);
                depthFirstTraversal(neighbour, visitedVertices);
            }
        }
    }

    /**
     * Travers the graph layer by layer. 1 connects to 2 and 3 -> add 2 and 3 to the que and look for their neighbours.
     * Add those to the que and look for their neighbours etc. etc.
     * @param start
     * @param visitedVertices
     */
    public static void breadthFirstTraversal(Vertex start, List<Vertex> visitedVertices) {
        LinkedList<Vertex> visitQueue = new LinkedList<>();
        visitQueue.addLast(start);

        while (!visitQueue.isEmpty()) {
            Vertex current = visitQueue.removeFirst();
            System.out.println(current.getData());

            for (Edge edge : current.getEdges()) {
                Vertex neighbour = edge.getEnd();

                if (!visitedVertices.contains(neighbour)) {
                    visitedVertices.add(neighbour);
                    visitQueue.addLast(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        TestGraph test = new TestGraph();
        Vertex startV = test.getStartVertex();
        List<Vertex> visitedVerticesDfs = new ArrayList<>();
        List<Vertex> visitedVerticesBfs = new ArrayList<>();
        visitedVerticesDfs.add(startV);
        visitedVerticesBfs.add(startV);

        System.out.println("Depth first traversal: ");
        depthFirstTraversal(startV, visitedVerticesDfs);
        System.out.println("Breadth first traversal:");
        breadthFirstTraversal(startV, visitedVerticesBfs);
    }
}
