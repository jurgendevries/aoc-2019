package year2022;

import base.Base;
import helpers.graph.Dijkstra;
import helpers.graph.model.Graph;
import helpers.graph.model.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 extends Base {
    private static final String INPUT = "2022/day12-input.txt";
    private static int width;
    private static int height;
    private static String[][] grid;
    private static int[][] directions;
    private Vertex start = null;
    private Vertex end = null;
    private Graph heightGraph = new Graph(true, true);

    public static void main(String[] args) throws IOException {
        Day12 main = new Day12();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        height = main.instructions.size();
        width = main.instructions.get(0).length();
        grid = new String[height][width];
        for (int i = 0; i < main.instructions.size(); i++) {
            grid[i] = main.instructions.get(i).split("");
        }
        directions = new int[][]{{-1, 0},{0, -1}, {1, 0}, {0, 1}};
        main.buildGraph();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void buildGraph() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String gridVal = grid[y][x].equals("S") ? "a" : grid[y][x].equals("E") ? "z" : grid[y][x];
                heightGraph.addVertex(y + "-" + x + "-" + gridVal);
                if (grid[y][x].equals("S")) {
                    start = heightGraph.getVertexByValue(y + "-" + x + "-" + gridVal);
                }
                if (grid[y][x].equals("E")) {
                    end = heightGraph.getVertexByValue(y + "-" + x + "-" + gridVal);
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String gridVal = grid[y][x].equals("S") ? "a" : grid[y][x].equals("E") ? "z" : grid[y][x];
                Vertex currentVertex = heightGraph.getVertexByValue(y + "-" + x + "-" + gridVal);
                char currentVal = gridVal.toCharArray()[0];
                for (int[] dir : directions) {
                    int newY = y + dir[0];
                    int newX = x + dir[1];
                    if (newY >= 0 && newY < height && newX >= 0 && newX < width) {
                        String newgridVal = grid[newY][newX].equals("S") ? "a" : grid[newY][newX].equals("E") ? "z" : grid[newY][newX];
                        char newVal = newgridVal.toCharArray()[0];
                        if (newVal - currentVal <= 1) {
                            Vertex connectedVertex = heightGraph.getVertexByValue(newY + "-" + newX + "-" + newgridVal);
                            heightGraph.addEdge(currentVertex, connectedVertex, 1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void part1() throws IOException {
        Dijkstra.shortestPathBetween(heightGraph, start, end);
    }

    @Override
    public void part2() throws IOException {
        List<Vertex> possibleStarts = new ArrayList<>();
        for (Vertex v : heightGraph.getVertices()) {
            String h = v.getData().split("-")[2];
            if (h.equals("a")) {
                possibleStarts.add(v);
            }
        }

        int shortestRoute = Integer.MAX_VALUE;
        Vertex resultStart = null;
        for (Vertex s : possibleStarts) {
            int result = Dijkstra.shortestPathBetween(heightGraph, s, end);
            if (result < shortestRoute) {
                shortestRoute = result;
                resultStart = s;
            }
        }

        System.out.println(resultStart.getData() + " - " + end.getData() + " - " + shortestRoute);
    }
}
