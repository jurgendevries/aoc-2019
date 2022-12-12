package helpers.graph;

import helpers.graph.model.Edge;
import helpers.graph.model.Graph;
import helpers.graph.model.PriorityVertex;
import helpers.graph.model.Vertex;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    public static Map<Vertex, Integer> dijkstra(Graph g, Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        List<Vertex> visitedVertices = new ArrayList<>();

        PriorityQueue<PriorityVertex> queue = new PriorityQueue<>();
        queue.add(new PriorityVertex(start, 0));

        // fill the start vertex with zero distance
        distances.put(start, 0);

        // fill the rest of the distances with maxValues
        for (Vertex vertex : g.getVertices()) {
            if (vertex != start) {
                distances.put(vertex, Integer.MAX_VALUE);
            }
        }

        while (!queue.isEmpty()) {
            Vertex current = queue.poll().getVertex();
            if (!visitedVertices.contains(current)) {
                visitedVertices.add(current);
                for (Edge edge : current.getEdges()) {
                    Integer currentWeight = distances.get(current) + edge.getWeight();
                    Vertex neighbour = edge.getEnd();

                    if (currentWeight < distances.get(neighbour)) {
                        distances.put(neighbour, currentWeight);
                        queue.add(new PriorityVertex(edge.getEnd(), distances.get(neighbour)));
                    }
                }
            }
        }
        return distances;
    }

    public static int shortestPathBetween(Graph g, Vertex start, Vertex target) {
        int result = dijkstra(g, start).get(target);
        System.out.println("Shortest path between " + start.getData() + " - " + target.getData() + " = " + result);
        return result;
    }

    public static int shortestPathBetween(Graph g, Vertex start, String targetData) {
        Map<Vertex, Integer> results = dijkstra(g, start);
        Map.Entry<Vertex, Integer> res = results.entrySet()
                .stream()
                .filter(x -> x.getKey().getData().contains(targetData))
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(1)
                .collect(Collectors.toList()).get(0);

        System.out.println("Shortest path between " + start.getData() + " - " + res.getKey().getData() + " = " + res.getValue());
        return res.getValue();
    }

    public static void dijkstraResultPrinter(Map<Vertex, Integer> distances) {
        System.out.println("Shortest routes:");
        for (Map.Entry<Vertex, Integer> entry : distances.entrySet()) {
            System.out.println(entry.getKey().getData() + " : " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(true, true);
        Vertex a = testGraph.addVertex("A");
        Vertex b = testGraph.addVertex("B");
        Vertex c = testGraph.addVertex("C");
        Vertex d = testGraph.addVertex("D");
        Vertex e = testGraph.addVertex("E");
        Vertex f = testGraph.addVertex("F");
        Vertex g = testGraph.addVertex("G");

        testGraph.addEdge(a, c, 100);
        testGraph.addEdge(a, b, 3);
        testGraph.addEdge(a, d, 4);
        testGraph.addEdge(d, c, 3);
        testGraph.addEdge(d, e, 8);
        testGraph.addEdge(e, b, -2);
        testGraph.addEdge(e, f, 10);
        testGraph.addEdge(b, g, 9);
        testGraph.addEdge(e, g, -50);



        Graph testGraph1 = new Graph(true, true);
        Vertex a1 = testGraph1.addVertex("A1");
        Vertex a2 = testGraph1.addVertex("A2");
        Vertex a3 = testGraph1.addVertex("A3");
        Vertex a4 = testGraph1.addVertex("A4");
        Vertex a5 = testGraph1.addVertex("A5");
        Vertex b1 = testGraph1.addVertex("B1");
        Vertex b2 = testGraph1.addVertex("B2");
        Vertex b3 = testGraph1.addVertex("B3");
        Vertex b4 = testGraph1.addVertex("B4");
        Vertex b5 = testGraph1.addVertex("B5");
        Vertex c1 = testGraph1.addVertex("C1");
        Vertex c2 = testGraph1.addVertex("C2");
        Vertex c3 = testGraph1.addVertex("C3");
        Vertex c4 = testGraph1.addVertex("C4");
        Vertex c5 = testGraph1.addVertex("C5");
        Vertex d1 = testGraph1.addVertex("D1");
        Vertex d2 = testGraph1.addVertex("D2");
        Vertex d3 = testGraph1.addVertex("D3");
        Vertex d4 = testGraph1.addVertex("D4");
        Vertex d5 = testGraph1.addVertex("D5");

        testGraph1.addEdge(a1, a2, 50);
        testGraph1.addEdge(a1, b1, 1);
        testGraph1.addEdge(a2, a3, 50);
        testGraph1.addEdge(a2, b2, 50);
        testGraph1.addEdge(a2, a1, 1);
        testGraph1.addEdge(a3, a4, 1);
        testGraph1.addEdge(a3, b3, 1);
        testGraph1.addEdge(a3, a2, 50);
        testGraph1.addEdge(a4, a5, 1);
        testGraph1.addEdge(a4, b4, 1);
        testGraph1.addEdge(a4, a3, 50);
        testGraph1.addEdge(a5, b5, 1);
        testGraph1.addEdge(a5, a4, 1);

        testGraph1.addEdge(b1, b2, 50);
        testGraph1.addEdge(b1, c1, 1);
        testGraph1.addEdge(b1, a1, 1);
        testGraph1.addEdge(b2, b1, 1);
        testGraph1.addEdge(b2, b3, 1);
        testGraph1.addEdge(b2, a2, 50);
        testGraph1.addEdge(b2, c2, 50);
        testGraph1.addEdge(b3, c3, 1);
        testGraph1.addEdge(b3, a3, 50);
        testGraph1.addEdge(b3, b2, 50);
        testGraph1.addEdge(b3, b4, 1);
        testGraph1.addEdge(b4, b3, 1);
        testGraph1.addEdge(b4, b5, 1);
        testGraph1.addEdge(b4, a4, 1);
        testGraph1.addEdge(b4, c4, 50);
        testGraph1.addEdge(b5, b4, 1);
        testGraph1.addEdge(b5, a5, 1);
        testGraph1.addEdge(b5, c5, 1);

        testGraph1.addEdge(c1, c2, 50);
        testGraph1.addEdge(c1, b1, 1);
        testGraph1.addEdge(c1, d1, 1);
        testGraph1.addEdge(c2, c1, 1);
        testGraph1.addEdge(c2, b2, 50);
        testGraph1.addEdge(c2, d2, 1);
        testGraph1.addEdge(c2, c3, 1);
        testGraph1.addEdge(c3, c2, 50);
        testGraph1.addEdge(c3, c4, 50);
        testGraph1.addEdge(c3, b3, 1);
        testGraph1.addEdge(c3, d3, 1);
        testGraph1.addEdge(c4, c3, 1);
        testGraph1.addEdge(c4, c5, 1);
        testGraph1.addEdge(c4, b4, 1);
        testGraph1.addEdge(c4, d4, 50);
        testGraph1.addEdge(c5, c4, 50);
        testGraph1.addEdge(c5, b5, 1);
        testGraph1.addEdge(c5, d5, 1);

        testGraph1.addEdge(d1, d2, 1);
        testGraph1.addEdge(d1, c1, 1);
        testGraph1.addEdge(d2, d1, 1);
        testGraph1.addEdge(d2, d3, 1);
        testGraph1.addEdge(d2, c2, 50);
        testGraph1.addEdge(d3, d2, 1);
        testGraph1.addEdge(d3, d4, 50);
        testGraph1.addEdge(d3, c3, 1);
        testGraph1.addEdge(d4, d3, 1);
        testGraph1.addEdge(d4, d5, 1);
        testGraph1.addEdge(d4, c4, 50);
        testGraph1.addEdge(d5, d4, 50);
        testGraph1.addEdge(d5, c5, 1);


/**
 *
 *       A  B  C   D
 *  1:  |1 |1 |1 |1 |
 *  2:  |50|50|50|1 |
 *  3:  |50|1 |1 |1 |
 *  4:  |1 |1 |50|50|
 *  5:  |1 |1 |1 |1 |
 *
 */

//        dijkstraResultPrinter(dijkstra(testGraph, a));
//        shortestPathBetween(testGraph, a, g);
//
//        dijkstraResultPrinter(dijkstra(testGraph1, a));
        shortestPathBetween(testGraph1, a1, d5);
        shortestPathBetween(testGraph1, a1, b3);
        shortestPathBetween(testGraph1, a1, a3);
    }
}

