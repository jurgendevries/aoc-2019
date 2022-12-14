package year2022;

import base.Base;
import base.utils.Utils;

import java.io.IOException;

public class Day14 extends Base {
    private static final String INPUT = "2022/day14-input.txt";
    private static int width;
    private static int height;
    private static String[][] grid;
    private static int[][] directions;
    private static boolean endReached = false;
    private static int lowestY = 0;

    public static void main(String[] args) throws IOException {
        Day14 main = new Day14();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        height = 500;
        width = 1000;
        // down - leftdown - rightdown
        directions = new int[][]{{1, 0}, {1, -1}, {1, 1}};

        System.out.println("PART1:");

        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void buildCaves() {
        grid = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ".";
            }
        }
        for (String instruction : instructions) {
            String[] coords = instruction.split(" -> ");
            for (int i = 0; i < coords.length - 1; i++) {
                int x = Integer.parseInt(coords[i].split(",")[0]);
                int y = Integer.parseInt(coords[i].split(",")[1]);
                int xNext = Integer.parseInt(coords[i+1].split(",")[0]);
                int yNext = Integer.parseInt(coords[i+1].split(",")[1]);

                if (x == xNext) {
                    int yDraw = y;
                    do {
                        grid[yDraw][x] = "#";
                        yDraw = y < yNext ? yDraw + 1 : yDraw - 1;
                    } while (yDraw != (y < yNext ? yNext + 1 : yNext - 1));
                } else {
                    int xDraw = x;
                    do {
                        grid[y][xDraw] = "#";
                        xDraw = x < xNext ? xDraw + 1 : xDraw - 1;
                    } while (xDraw != (x < xNext ? xNext + 1 : xNext - 1));
                }
                if (y > lowestY || yNext > lowestY) {
                    lowestY = y > lowestY ? y : lowestY;
                    lowestY = yNext > lowestY ? yNext : lowestY;
                }
            }
        }
        // add floor
        for (int x = 0; x < width; x++) {
            grid[lowestY + 2][x] = "#";
        }
    }

    @Override
    public void part1() throws IOException {
        buildCaves();
        int sandCount = 0;
        while (!endReached) {
            dropSand(true);
            sandCount++;
        }
        System.out.println(sandCount-1);
    }

    @Override
    public void part2() throws IOException {
        buildCaves();
        endReached = false;
        int sandCount = 0;
        while (!endReached) {
            dropSand(false);
            sandCount++;
        }
        System.out.println(sandCount);
    }

    private void dropSand(boolean part1) {
        int curX = 500;
        int curY = 0;
        grid[curY][curX] = "+";
        boolean stopped = false;

        while (!stopped) {
            boolean moved = false;
            for (int[] dir : directions) {
                int testX = curX + dir[1];
                int testY = curY + dir[0];
                if (part1) {
                    if (testY > lowestY) {
                        endReached = true;
                        break;
                    }
                }
                if (".".equals(grid[testY][testX])) {
                    grid[curY][curX] = ".";
                    grid[testY][testX] = "+";
                    curX = testX;
                    curY = testY;
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                grid[curY][curX] = "o";
                stopped = true;
                if (!part1 && curY == 0 && curX == 500) {
                    endReached = true;
                    break;
                }
            }
        }
    }
}
