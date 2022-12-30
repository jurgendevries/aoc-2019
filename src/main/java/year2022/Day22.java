package year2022;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.Arrays;

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
        //main.part1();
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
        // right - down - left - right
        directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Utils.printGrid(width, height, grid);
//        System.out.println(passPhrase);
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
            move(dist, current, false);
            currentDirection = changeDir(i, directionChanges, currentDirection);
        }

        Utils.printGrid(width, height, grid);
        System.out.println("Done: [" + (current.x + 1) + "," + (current.y + 1) + "]");
        int resultX = current.x + 1;
        int resultY = current.y + 1;
        int result = 1000 * resultY + 4 * resultX + currentDirection;
        System.out.println(result);

    }

    private void move(int dist, Coord c, boolean part2) {
        boolean canMove = true;
        int moves = 0;
        while (canMove && moves < dist) {
            Coord newC = part2 ? getNewCoord2(c) : getNewCoord(c);
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

    private Coord getNewCoord2(Coord c) {
        Coord newC = new Coord(c.x + directions[currentDirection][1], c.y + directions[currentDirection][0]);
        if (newC.x < 0 || newC.x >= width || newC.y < 0 || newC.y >= height || " ".equals(grid[newC.y][newC.x])) {
            // outside grid or empty space -> wrap around get first space in next cube face

            // determine current cube face
            String currentFace;
            if (c.x >= 50 && c.x <= 99 && c.y >= 0 && c.y <= 49) {
                currentFace = "A";
            } else if (c.x >= 100 && c.x <= 149 && c.y >= 0 && c.y <= 49) {
                currentFace = "B";
            } else if (c.x >= 50 && c.x <= 99 && c.y >= 50 && c.y <= 99) {
                currentFace = "C";
            } else if (c.x >= 50 && c.x <= 99 && c.y >= 100 && c.y <= 149) {
                currentFace = "D";
            } else if (c.x >= 0 && c.x <= 49 && c.y >= 100 && c.y <= 149) {
                currentFace = "E";
            } else {
                currentFace = "F";
            }
            String move = currentFace + currentDirection;
            // use dir to determine next cube face and dir
            switch (move) {
                case "A2":
                    currentDirection = 0;
                    newC.x = 0;
                    newC.y = 149 - c.y;
                    // dir 2 -> left goes to E (move is to the right) X = 0++, Y = 149 - current Y
                    break;
                case "A3":
                    currentDirection = 0;
                    newC.x = 0;
                    newC.y = 100 + c.x;
                    // dir 3 -> up goes to F (move is to the right) X = 0++, Y = 150 + current X
                    break;
                case "B0":
                    currentDirection = 2;
                    newC.x = 99;
                    newC.y = 149 - c.y;
                    // dir 0 -> right goes to D (move is to left) X = 99, Y = 149 - current Y
                    break;
                case "B1":
                    currentDirection = 2;
                    newC.x = 99;
                    newC.y = c.x - 50;
                    // dir 1 -> down goes to C (move is to left) X = 99, Y = current X - 50
                    break;
                case "B3":
                    currentDirection = 3;
                    newC.x = c.x - 100;
                    newC.y = 199;
                    // dir 3 -> up goes to F ( move is up) X = current X - 100, Y = 199
                    break;
                case "C0":
                    currentDirection = 3;
                    newC.x = 50 + c.y;
                    newC.y = 49;
                    // dir 0 -> right goes to B (move is up) X = current Y + 50, Y = 49
                    break;
                case "C2":
                    currentDirection = 1;
                    newC.x = c.y - 50;
                    newC.y = 100;
                    // dir 2 -> left goes to E (move is down) X = current Y - 50, Y = 100
                    break;
                case "D0":
                    currentDirection = 2;
                    newC.x = 149;
                    newC.y = 149 - c.y;
                    // dir 0 -> right goes to B (move is left) X = 149, Y = 149 - current Y
                    break;
                case "D1":
                    currentDirection = 2;
                    newC.x = 49;
                    newC.y = 100 + c.x;
                    // dir 1 -> down goes to F (move is lef) X = 49, Y = 100 + current X
                    break;
                case "E2":
                    currentDirection = 0;
                    newC.x = 50;
                    newC.y = 149 - c.y;
                    // dir 2 -> left goes to A (move is right) X = 50, Y = 149 - current Y
                    break;
                case "E3":
                    currentDirection = 0;
                    newC.x = 50;
                    newC.y = 50 + c.x;
                    // dir 3 -> up goes to C (move is right) X = 50, Y = 50 + current X
                    break;
                case "F0":
                    currentDirection = 3;
                    newC.x = c.y - 100;
                    newC.y = 149;
                    // dir 0 -> right goes to D (move is up) X = current Y - 100, Y = 149
                    break;
                case "F1":
                    currentDirection = 1;
                    newC.x = 100 + c.x;
                    newC.y = 0;
                    // dir 1 -> down goes to B (move is down) X = current X + 100, Y = 0
                    break;
                case "F2":
                    currentDirection = 1;
                    newC.x = c.y - 100;
                    newC.y = 0;
                    // dir 2 -> left goes to A (move is down) X = current Y - 100 , Y = 0
                    break;
                default:
                    throw new IllegalArgumentException("Impossible move!");
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
        for (int i = 0; i < distances.length; i++) {
            int dist = distances[i];
            // move
            move(dist, current, true);
            currentDirection = changeDir(i, directionChanges, currentDirection);
        }
        Utils.printGrid(width, height, grid);
        System.out.println("Done: [" + (current.x + 1) + "," + (current.y + 1) + "]");
        int resultX = current.x + 1;
        int resultY = current.y + 1;
        int result = 1000 * resultY + 4 * resultX + currentDirection;
        System.out.println(result);
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

    class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
