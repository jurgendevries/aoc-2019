package year2022;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;

public class Day22 extends Base {
    private static final String INPUT = "2022/day22-input.txt";

    private static int width;
    private static int height;
    private static String[][] grid;
    private static int[][] directions;
    private String passPhrase;
    private Coord current;

    private int currentDirection;

    public static void main(String[] args) throws IOException {
        Day22 main = new Day22();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));

    }

    private void readInput() {
        int widestLine = Integer.MIN_VALUE;
        height = instructions.size() - 2;
        for (int i = 0; i < instructions.size() - 1; i++) {
            if (instructions.get(i).length() > widestLine) {
                widestLine = instructions.get(i).length();
            }
        }
        width = widestLine;
        grid = new String[height][width];
        for (int y = 0; y < instructions.size() - 2; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = " ";
            }
        }

        int startX = -1;
        for (int y = 0; y < instructions.size() - 2; y++) {
            for (int x = 0; x < instructions.get(y).split("").length; x++) {
                grid[y][x] = String.valueOf(instructions.get(y).charAt(x));
            }
            if (startX < 0) {
                for (int x = 0; x < instructions.get(y).length(); x++) {
                    if (".".equals(grid[y][x])) {
                        startX = x;
                        break;
                    }
                }
                current = new Coord(startX, y);
            }
        }
        passPhrase = instructions.get(instructions.size() - 1);
        // 0       1      2      3
        // right - down - left - up
        directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        Utils.printGrid(width, height, grid);
        currentDirection = 0;
    }

    @Override
    public void part1() throws IOException {
        readInput();
        int[] distances = Arrays.stream(passPhrase.split("\\D")).mapToInt(x -> Integer.parseInt(x)).toArray();
        String[] directionChanges = passPhrase.split("\\d+");
        currentDirection = 0;
        for (int i = 0; i < distances.length; i++) {
            int dist = distances[i];
            // move
            move(dist, current, null, false);
            currentDirection = changeDir(i, directionChanges, currentDirection);
        }

//        Utils.printGrid(width, height, grid);
        System.out.println("Done: [" + (current.x + 1) + "," + (current.y + 1) + "]");
        int resultX = current.x + 1;
        int resultY = current.y + 1;
        int result = 1000 * resultY + 4 * resultX + currentDirection;
        System.out.println(result);

    }

    private void move(int dist, Coord c, Cube cube, boolean part2) {
        boolean canMove = true;
        int moves = 0;
        while (canMove && moves < dist) {
            Coord newC = part2 ? getNewCoordB(c, cube) : getNewCoord(c);
            if (c.x != newC.x || c.y != newC.y) {
                grid[c.y][c.x] = currentDirection == 0 ? ">" : currentDirection == 1 ? "v" : currentDirection == 2 ? "<" : "^";
                c.x = newC.x;
                c.y = newC.y;
                moves++;
            } else {
                canMove = false;
            }
        }
    }

    private Coord getNewCoord(Coord c) {
        Coord newC = new Coord(c.x + directions[currentDirection][1], c.y + directions[currentDirection][0]);
        if (newC.x < 0 || newC.x >= width || newC.y < 0 || newC.y >= height || " ".equals(grid[newC.y][newC.x])) {
            // outside grid or empty space -> wrap around get first space in opposite direction if possible
            // dir = 0 (right) -> get first option from the left of the current row
            // dir = 1 (down) -> get first option from the top of the current col
            // dir = 2 (left) -> get first option from the right of the current row
            // dir = 0 (up) -> get first option from the bottom of the current col

            switch (currentDirection) {
                case 0:
                    for (int x = 0; x < width; x++) {
                        if ("#".equals(grid[newC.y][x])) {
                            newC = c;
                            break;
                        } else if (".".equals(grid[newC.y][x]) || ">".equals(grid[newC.y][x]) || "v".equals(grid[newC.y][x]) || "<".equals(grid[newC.y][x]) || "^".equals(grid[newC.y][x])) {
                            newC.x = x;
                            break;
                        }
                    }
                    break;
                case 1:
                    for (int y = 0; y < height; y++) {
                        if ("#".equals(grid[y][newC.x])) {
                            newC = c;
                            break;
                        } else if (".".equals(grid[y][newC.x])  || ">".equals(grid[y][newC.x]) || "v".equals(grid[y][newC.x]) || "<".equals(grid[y][newC.x]) || "^".equals(grid[y][newC.x])) {
                            newC.y = y;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int x = width - 1; x >= 0; x--) {
                        if ("#".equals(grid[newC.y][x])) {
                            newC = c;
                            break;
                        } else if (".".equals(grid[newC.y][x])  || ">".equals(grid[newC.y][x]) || "v".equals(grid[newC.y][x]) || "<".equals(grid[newC.y][x]) || "^".equals(grid[newC.y][x])) {
                            newC.x = x;
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int y = height - 1; y >= 0; y--) {
                        if ("#".equals(grid[y][newC.x])) {
                            newC = c;
                            break;
                        } else if (".".equals(grid[y][newC.x])  || ">".equals(grid[y][newC.x]) || "v".equals(grid[y][newC.x]) || "<".equals(grid[y][newC.x]) || "^".equals(grid[y][newC.x])) {
                            newC.y = y;
                            break;
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("unknown direction!");
            }
            return newC;
        } else if ("#".equals(grid[newC.y][newC.x])) {
            // hits wall -> return current coordinate (no move)
            return c;
        } else if (".".equals(grid[newC.y][newC.x])  || ">".equals(grid[newC.y][newC.x]) || "v".equals(grid[newC.y][newC.x]) || "<".equals(grid[newC.y][newC.x]) || "^".equals(grid[newC.y][newC.x])) {
            // '.' -> just move to next open space (move)
            return newC;
        }
        System.out.println("Can we get here???");
        return null; //?
    }

    @Override
    public void part2() throws IOException {
        readInput();
        int[] distances = Arrays.stream(passPhrase.split("\\D")).mapToInt(x -> Integer.parseInt(x)).toArray();
        String[] directionChanges = passPhrase.split("\\d+");
        currentDirection = 0;

        Cube cube = createCube(false);
//        Cube cube = createCube(true);

        for (int i = 0; i < distances.length; i++) {
            int dist = distances[i];
            // move
            move(dist, current, cube, true);
            currentDirection = changeDir(i, directionChanges, currentDirection);
        }
        System.out.println();
//        Utils.printGrid(width, height, grid);
        System.out.println("Done: [" + (current.x + 1) + "," + (current.y + 1) + "]");
        int resultX = current.x + 1;
        int resultY = current.y + 1;
        int result = 1000 * resultY + 4 * resultX + currentDirection;
        System.out.println(result);
    }

    private Coord getNewCoordB(Coord c, Cube cube) {
        Coord newC = new Coord(c.x + directions[currentDirection][1], c.y + directions[currentDirection][0]);
        if (newC.x < 0 || newC.x >= width || newC.y < 0 || newC.y >= height || " ".equals(grid[newC.y][newC.x])) {
            // outside grid or empty space -> wrap around get first space in next cube face

            // determine current cube face
            Face currentFace = c.x >= cube.faces.get(0).xMin && c.x <= cube.faces.get(0).xMax && c.y >= cube.faces.get(0).yMin && c.y <= cube.faces.get(0).yMax ? cube.faces.get(0) :
                    c.x >= cube.faces.get(1).xMin && c.x <= cube.faces.get(1).xMax && c.y >= cube.faces.get(1).yMin && c.y <= cube.faces.get(1).yMax ? cube.faces.get(1) :
                    c.x >= cube.faces.get(2).xMin && c.x <= cube.faces.get(2).xMax && c.y >= cube.faces.get(2).yMin && c.y <= cube.faces.get(2).yMax ? cube.faces.get(2) :
                    c.x >= cube.faces.get(3).xMin && c.x <= cube.faces.get(3).xMax && c.y >= cube.faces.get(3).yMin && c.y <= cube.faces.get(3).yMax ? cube.faces.get(3) :
                    c.x >= cube.faces.get(4).xMin && c.x <= cube.faces.get(4).xMax && c.y >= cube.faces.get(4).yMin && c.y <= cube.faces.get(4).yMax ? cube.faces.get(4) : cube.faces.get(5);

            if (currentFace.connections.get(currentDirection) != null) {
                // complex move to connecting face
                String connection = currentFace.connections.get(currentDirection);
                int oldDir = currentDirection;
                currentDirection = Integer.parseInt(connection.split("")[1]);
                switch (oldDir) {
                    case 0:
                        newC.x = currentFace.detX0.apply(c.x, c.y);
                        newC.y = currentFace.detY0.apply(c.x, c.y);
                        break;
                    case 1:
                        newC.x = currentFace.detX1.apply(c.x, c.y);
                        newC.y = currentFace.detY1.apply(c.x, c.y);
                        break;
                    case 2:
                        newC.x = currentFace.detX2.apply(c.x, c.y);
                        newC.y = currentFace.detY2.apply(c.x, c.y);
                        break;
                    default:
                        newC.x = currentFace.detX3.apply(c.x, c.y);
                        newC.y = currentFace.detY3.apply(c.x, c.y);
                }
                // check location on newFace is not "#"
                if ("#".equals(grid[newC.y][newC.x])) {
                    currentDirection = oldDir;
                    return c;
                }
            } else {
                // impossible move!
                System.out.println("ERROR! impossible move!!");
            }
            return newC;
        } else if ("#".equals(grid[newC.y][newC.x])) {
            // hits wall -> return current coordinate (no move)
            return c;
        } else if (".".equals(grid[newC.y][newC.x])  || ">".equals(grid[newC.y][newC.x]) || "v".equals(grid[newC.y][newC.x]) || "<".equals(grid[newC.y][newC.x]) || "^".equals(grid[newC.y][newC.x])) {
            // '.' -> just move to next open space (move)
            return newC;
        }
        System.out.println("Can we get here???");

        return null;
    }

    private int changeDir(int index, String[] directionChanges, int currentDirection) {
        if (index + 1 < directionChanges.length) {
            switch (directionChanges[index + 1]) {
                case "R":
                    currentDirection = (currentDirection + 1) % 4;
                    break;
                case "L":
                    currentDirection = currentDirection - 1 >= 0 ? currentDirection - 1 : 3;
                    break;
                default:
                    throw new IllegalArgumentException("cannot change to that direction");
            }
        }
        return currentDirection;
    }

    private Cube createCube(boolean test) {
        if (test) {
            // create cube
            Map<Integer, String> connectionsA = new HashMap<>();
            connectionsA.put(0, "F2");
            connectionsA.put(3, "B1");
            connectionsA.put(2, "C1");
            Face a = new Face("A", 8, 11, 0, 3, connectionsA, (Integer x, Integer y) -> 15, (Integer x, Integer y) -> 11 - y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> y + 4, (Integer x, Integer y) -> 4,
                    (Integer x, Integer y) -> 11 - x, (Integer x, Integer y) -> 4
            );

            Map<Integer, String> connectionsB = new HashMap<>();
            connectionsB.put(1, "E3");
            connectionsB.put(2, "F3");
            connectionsB.put(3, "A1");
            Face b = new Face("B", 0, 3, 4, 7, connectionsB, (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 11 - x, (Integer x, Integer y) -> 11,
                    (Integer x, Integer y) -> 19 - y, (Integer x, Integer y) -> 11,
                    (Integer x, Integer y) -> 11 - x, (Integer x, Integer y) -> 0
            );

            Map<Integer, String> connectionsC = new HashMap<>();
            connectionsC.put(1, "E0");
            connectionsC.put(3, "A0");
            Face c = new Face("C", 4, 7, 4, 7, connectionsC, (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 8, (Integer x, Integer y) -> 15 - x,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 8, (Integer x, Integer y) -> x - 4
            );

            Map<Integer, String> connectionsD = new HashMap<>();
            connectionsD.put(0, "F1");
            Face d = new Face("D", 8, 11, 4, 7, connectionsD, (Integer x, Integer y) -> 19 - y, (Integer x, Integer y) -> 8,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y
            );

            Map<Integer, String> connectionsE = new HashMap<>();
            connectionsE.put(1, "B3");
            connectionsE.put(2, "C3");
            Face e = new Face("E", 8, 11, 8, 11, connectionsE, (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 11 - x, (Integer x, Integer y) -> 7,
                    (Integer x, Integer y) -> 15 - y, (Integer x, Integer y) -> 7,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y
            );

            Map<Integer, String> connectionsF = new HashMap<>();
            connectionsF.put(0, "A2");
            connectionsF.put(1, "B0");
            connectionsF.put(3, "D2");
            Face f = new Face("F", 12, 15, 8, 11, connectionsF, (Integer x, Integer y) -> 11, (Integer x, Integer y) -> 11 - y,
                    (Integer x, Integer y) -> 0, (Integer x, Integer y) -> 19 - x,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 11, (Integer x, Integer y) -> 19 - x
            );
            List<Face> faces = new ArrayList<>();
            faces.add(a);
            faces.add(b);
            faces.add(c);
            faces.add(d);
            faces.add(e);
            faces.add(f);
            return new Cube(faces);
        } else {

            // create cube
            Map<Integer, String> connectionsA = new HashMap<>();
            connectionsA.put(2, "E0");
            connectionsA.put(3, "F0");
            Face a = new Face("A", 50, 99, 0, 49, connectionsA, (Integer x, Integer y) -> x, (Integer x, Integer y) ->  y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 0, (Integer x, Integer y) -> 149 - y,
                    (Integer x, Integer y) -> 0, (Integer x, Integer y) -> 100 + x
            );

            Map<Integer, String> connectionsB = new HashMap<>();
            connectionsB.put(0, "D2");
            connectionsB.put(1, "C2");
            connectionsB.put(3, "F3");

            Face b = new Face("B", 100, 149, 0, 49, connectionsB, (Integer x, Integer y) -> 99, (Integer x, Integer y) -> 149 - y,
                    (Integer x, Integer y) -> 99, (Integer x, Integer y) -> x - 50,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> x - 100, (Integer x, Integer y) -> 199
            );

            Map<Integer, String> connectionsC = new HashMap<>();
            connectionsC.put(0, "B3");
            connectionsC.put(2, "E1");
            Face c = new Face("C", 50, 99, 50, 99, connectionsC, (Integer x, Integer y) -> 50 + y, (Integer x, Integer y) -> 49,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> y - 50, (Integer x, Integer y) -> 100,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y
            );

            Map<Integer, String> connectionsD = new HashMap<>();
            connectionsD.put(0, "B2");
            connectionsD.put(1, "F2");

            Face d = new Face("D", 50, 99, 100, 149, connectionsD, (Integer x, Integer y) -> 149, (Integer x, Integer y) -> 149 - y,
                    (Integer x, Integer y) -> 49, (Integer x, Integer y) -> 100 + x,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y
            );

            Map<Integer, String> connectionsE = new HashMap<>();
            connectionsE.put(2, "A0");
            connectionsE.put(3, "C0");
            Face e = new Face("E", 0, 49, 100, 149, connectionsE, (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y,
                    (Integer x, Integer y) -> 50, (Integer x, Integer y) -> 149 - y,
                    (Integer x, Integer y) -> 50, (Integer x, Integer y) -> 50 + x
            );

            Map<Integer, String> connectionsF = new HashMap<>();
            connectionsF.put(0, "D3");
            connectionsF.put(1, "B1");
            connectionsF.put(2, "A1");
            Face f = new Face("F", 0, 49, 150, 199, connectionsF, (Integer x, Integer y) -> y - 100, (Integer x, Integer y) -> 149,
                    (Integer x, Integer y) -> 100 + x, (Integer x, Integer y) -> 0,
                    (Integer x, Integer y) -> y - 100, (Integer x, Integer y) -> 0,
                    (Integer x, Integer y) -> x, (Integer x, Integer y) -> y
            );
            List<Face> faces = new ArrayList<>();
            faces.add(a);
            faces.add(b);
            faces.add(c);
            faces.add(d);
            faces.add(e);
            faces.add(f);
            return new Cube(faces);
        }
    }

    class Cube {
        private List<Face> faces;

        public Cube(List<Face> faces) {
            this.faces = faces;
        }
    }

    class Face {
        private String id;
        private int xMin;
        private int xMax;
        private int yMin;
        private int yMax;
        private String[][] faceGrid;
        private Map<Integer, String> connections;
        private BiFunction<Integer, Integer, Integer> detX0;
        private BiFunction<Integer, Integer, Integer> detY0;
        private BiFunction<Integer, Integer, Integer> detX1;
        private BiFunction<Integer, Integer, Integer> detY1;
        private BiFunction<Integer, Integer, Integer> detX2;
        private BiFunction<Integer, Integer, Integer> detY2;
        private BiFunction<Integer, Integer, Integer> detX3;
        private BiFunction<Integer, Integer, Integer> detY3;

        public Face(String id, int xMin, int xMax, int yMin, int yMax, Map<Integer, String> connections, BiFunction<Integer, Integer, Integer> detX0, BiFunction<Integer, Integer, Integer> detY0, BiFunction<Integer, Integer, Integer> detX1, BiFunction<Integer, Integer, Integer> detY1, BiFunction<Integer, Integer, Integer> detX2, BiFunction<Integer, Integer, Integer> detY2, BiFunction<Integer, Integer, Integer> detX3, BiFunction<Integer, Integer, Integer> detY3) {
            this.id = id;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
            this.connections = connections;
            this.detX0 = detX0;
            this.detY0 = detY0;
            this.detX1 = detX1;
            this.detY1 = detY1;
            this.detX2 = detX2;
            this.detY2 = detY2;
            this.detX3 = detX3;
            this.detY3 = detY3;
        }

        public Face(String id, int xMin, int xMax, int yMin, int yMax, Map<Integer, String> connections) {
            this.id = id;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
            this.connections = connections;

            this.faceGrid = new String[yMax - yMin][xMax - xMin];
            for (int y = yMin; y <= yMax; y++) {
                for (int x = xMin; x <= xMax; x++) {
                    this.faceGrid[y][x] = grid[y][x];
                }
            }
        }
    }

    class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
