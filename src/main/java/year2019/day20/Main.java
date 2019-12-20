package year2019.day20;

import base.Base;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Main extends Base {
    private static int width = 0;
    private static int height = 0;
    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static final String INPUT = "2019/day20-test.txt";

    private static String[][] grid;// = new String[HEIGHT][WIDTH];
    private List<Coordinate> visitedLocations = new ArrayList<>();
    private List<Coordinate> wallLocations = new ArrayList<>();
    private Map<String, Portal> portals = new HashMap<>();
    private List<String> portalParts = new ArrayList<>();
    private Coordinate start;
    private Coordinate finish;


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

        main.part1();
    }

    @Override
    public void part1() throws IOException {
        prepare();
    }

    @Override
    public void part2() throws IOException {

    }

    public void prepare() throws IOException {
        String line;
        List<String[]> lineList = new ArrayList<>();
        int numberOfLines = 0;
        int longestLine = Integer.MIN_VALUE;
        while ((line = input.readLine()) != null) {
            String[] lineSplit = line.split("");
            longestLine = lineSplit.length > longestLine ? lineSplit.length : longestLine;
            lineList.add(lineSplit);
            numberOfLines++;
        }

        height = numberOfLines;
        width = longestLine;



        grid = new String[numberOfLines][longestLine];
        initGrid();
        for (int y = 0; y < height; y++) {
            String[] l = lineList.get(y);
            for (int x = 0; x < l.length; x++) {
                String s = l[x];
                grid[y][x] = s;
            }
        }

        findPortals();

        printGrid();
    }

    private void findPortals() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (StringUtils.isAlphanumeric(grid[y][x]) && !portalParts.contains(x + "," + y)) {
                    // check which portal
                    for (int d = 0; d < DIRECTIONS.length; d++) {
                        int[] dir = DIRECTIONS[d];
                        if (StringUtils.isAlphanumeric(grid[y + dir[1]][x + dir[0]])) {
                            portalParts.add((x + dir[0]) + "," + (y + dir[1]));
                            String name = grid[y][x] + grid[y + dir[1]][x + dir[0]];

                            if (name.equals("AA")) {
                                start = new Coordinate(x, y, null);
                            } else if (name.equals("ZZ")) {
                                finish = new Coordinate(x, y, null);
                            } else {
                                // d
                                // 0 = down
                                // 1 = right
                                // 2 = up
                                // 3 = left
                                Coordinate c = null;
                                if (d == 0 || d == 1) {
                                    c = new Coordinate(x + dir[0], y + dir[1], null);
                                } else {
                                    c = new Coordinate(x, y, null);
                                }

                                if (portals.containsKey(name)) {
                                    portals.get(name).setTo(c);
                                } else {
                                    portals.put(name, new Portal(name, c));
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void initGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = "";
            }
        }
    }

    private void printGrid() {
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
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

    private boolean isFinished(int x, int y) {
        return false;
    }

    private boolean isWallLocationVisited(int x, int y) {
        return wallLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isWall(int x, int y) {
        return grid[y][x].equals("#");
    }

    private boolean isAlreadyVisited(int x, int y) {
        return visitedLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
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

    public class Portal {
        private String name;
        private Coordinate from;
        private Coordinate to;

        public Portal(String name, Coordinate from) {
            this.name = name;
            this.from = from;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coordinate getFrom() {
            return from;
        }

        public void setFrom(Coordinate from) {
            this.from = from;
        }

        public Coordinate getTo() {
            return to;
        }

        public void setTo(Coordinate to) {
            this.to = to;
        }
    }
}
