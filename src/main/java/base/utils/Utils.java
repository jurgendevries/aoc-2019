package base.utils;

import java.util.List;

public class Utils {
    public static void printGrid(int width, int height, String[][] grid) {
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    public static String[][] fillGrid(int width, int height) {
        String[][] grid = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ".";
            }
        }

        return grid;
    }

    public static String[][] fillGrid(int width, int height, List<String> instructions) {
        String[][] grid = new String[height][width];
        for (int y = 0; y < height; y++) {
            String instruction = instructions.get(y);
            for (int x = 0; x < width; x++) {
                grid[y][x] = String.valueOf(instruction.charAt(x));
            }
        }

        return grid;
    }

    public static String[][] fillGridWithInputHorizontalReps(int width, int height, List<String> input, int repetitions, int gridWidth) {
        String[][] grid = new String[height][width];

        for (int y = 0; y < height; y++) {
            for (int m = 0; m < repetitions; m++) {
                for (int x = 0; x < gridWidth; x++) {
                    int xVal = x + (m * gridWidth);
                    grid[y][xVal] = String.valueOf(input.get(y).charAt(x));
                }
            }
        }

        return grid;
    }
}
