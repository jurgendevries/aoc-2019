package year2021.day15;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day15-input.txt";
    private static List<String> instructions;
    private static int width;
    private static int height;
    private static int[][] grid;
    private static int[][] directions;
    Integer[][] scores;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        height = instructions.size();
        width = instructions.get(0).length();

        grid = new int[height][];

        for (int i = 0; i < instructions.size(); i++) {
            grid[i] = Arrays.stream(instructions.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }

        directions = new int[][]{{-1, 0},{0, -1}, {1, 0}, {0, 1}};
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }


    @Override
    public void part1() throws IOException {
        System.out.println("PART ONE: " + traverse(false));
    }

    @Override
    public void part2() throws IOException {
        System.out.println("PART ONE: " + traverse(true));
    }

    public int traverse(boolean part2) {
        int gridHeight = part2 ? height * 5 : height;
        int gridWidth = part2 ? width * 5 : width;
        scores = new Integer[gridHeight][gridWidth];
        scores[0][0] = 0;

        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                scores[y][x] = x == 0 && y == 0 ? 0 : Integer.MAX_VALUE - 10000;
            }
        }

        boolean hasChanges;
        do {
            hasChanges = false;
            for (int y = 0; y < gridHeight; y++) {
                for (int x = 0; x < gridWidth; x++) {
                    int currentRisk = scores[y][x];
                    for (int[] d : directions) {
                        int nextY = y + d[0];
                        int nextX = x + d[1];
                        if (nextY >= 0 && nextY < gridHeight && nextX >= 0 && nextX < gridWidth) {
                            int nextRisk = currentRisk + calculateRisk(nextY, nextX, grid);
                            if (scores[nextY][nextX] > nextRisk) {
                                hasChanges = true;
                                scores[nextY][nextX] = nextRisk;
                            }
                        }
                    }
                }
            }
        } while (hasChanges);
        return scores[gridHeight - 1][gridWidth - 1];
    }

    public int calculateRisk(int y, int x, int[][] grid) {
        if (y < height && x < width) {
            return grid[y][x];
        }

        int tileRisk = 0;
        if (x >= width) {
            tileRisk = calculateRisk(y, x - width, grid) + 1;
        }
        if (y >= height) {
            tileRisk = calculateRisk(y - height, x, grid) + 1;
        }

        return tileRisk % 10 == 0 ? 1 : tileRisk % 10;
    }

}
