package year2016.day13;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main extends Base {

    private static final int FAVOURITE_NUMBER = 1350;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int finX = 131;
    private static final int finY = 139;
    private static final int MAX_STEPS = 50;
    private static int uniqueLocationsWithin50Steps = 0;
//    private static final int FAVOURITE_NUMBER = 10;
//    private static final int WIDTH = 10;
//    private static final int HEIGHT = 7;
//    private static final int finX = 7;
//    private static final int finY = 4;

    private static String[][] grid = new String[HEIGHT][WIDTH];

    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    List<Coordinate> visitedLocations = new ArrayList<>();
    List<Coordinate> wallLocations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
//        main.part1(); // size 100x100 - location 31,39
        main.part2(); // size 50x50 - location 100,100 (not reachable)
    }

    @Override
    public void part1() throws IOException {
        fillGrid();
        printGrid();
        List<Coordinate> shortestPath = solveMaze(1,1);

        System.out.println("shortest path = " + shortestPath.size() + " steps long. Steps:");
        for (Coordinate c : shortestPath) {
            System.out.println("(" + c.getX() + ", " + c.getY() + ")");
        }

    }

    @Override
    public void part2() throws IOException {
        fillGrid();
        List<Coordinate> shortestPath = solveMaze(1,1);
        for(Coordinate coordinate : visitedLocations) {
            int count = 0;
            while (coordinate != null) {
                count++;
                coordinate = coordinate.getParent();
            }

            // laatste stap is altijd 1,1. Dit is eigenlijk geen stap
            if (count - 1 <= MAX_STEPS) {
                uniqueLocationsWithin50Steps++;
            }
        }

        System.out.println("All visited locations in 50x50 grid: " + visitedLocations.size());
        System.out.println("All visited locations in 50x50 grid, within 50 steps: " + uniqueLocationsWithin50Steps);

    }

    private List<Coordinate> solveMaze(int x, int y) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        nextToVisit.add(new Coordinate(x, y, null));
        int steps = 0;
        while (!nextToVisit.isEmpty()) {
            Coordinate current = nextToVisit.remove();

            if (!isValidLocation(current.getX(), current.getY()) ||
                isAlreadyVisited(current.getX(), current.getY()) ||
                isWallLocationVisited(current.getX(), current.getY())) {
                continue;
            }

            if (isWall(current.getX(), current.getY())) {
                if (!isWallLocationVisited(current.getX(), current.getY())) {
                    wallLocations.add(current);
                }
                continue;
            }

            if (isFinished(current.getX(), current.getY())) {
                return backTrackPath(current);
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(current.getX() + direction[0], current.getY() + direction[1], current);
                nextToVisit.add(coordinate);
                if (!isAlreadyVisited(current.getX(), current.getY())) {
                    visitedLocations.add(current);
                }
            }
        }

        return Collections.emptyList();
    }

    private boolean isWallLocationVisited(int x, int y) {
        return wallLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isFinished(int x, int y) {
        return x == finX && y == finY;
    }

    private boolean isWall(int x, int y) {
        return grid[y][x].equals("#");
    }

    private boolean isAlreadyVisited(int x, int y) {
        return visitedLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private List<Coordinate> backTrackPath(Coordinate current) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = current;

        while (iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }

        return path;
    }

    private static void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    private static void fillGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean bitsAreEven = areBitsEven(x,y);
                grid[y][x] = bitsAreEven ? "." : "#";
            }
        }
    }

    private static boolean areBitsEven(int x, int y) {
        int value = x*x + 3*x + 2*x*y + y + y*y + FAVOURITE_NUMBER;
        String binaryValue = Integer.toString(value, 2);

        int counter = 0;

        for (char valuePos : binaryValue.toCharArray()) {
            if (valuePos == '1') {
                counter++;
            }
        }

        return counter % 2 == 0;
    }

    public class Coordinate {
        private int x;
        private int y;
        private Coordinate parent;

        public Coordinate(int x, int y, Coordinate parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Coordinate getParent() {
            return parent;
        }

        public void setParent(Coordinate parent) {
            this.parent = parent;
        }
    }
}
