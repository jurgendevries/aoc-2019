package year2021.day20;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day20-input.txt";
    private static List<String> instructions;
    private static int width;
    private static int height;
    private static String[][] grid;
    private static int[][] directions;
    String algorithm;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        height = instructions.size() - 2;
        width = instructions.get(0).length();

        grid = new String[height][];

        algorithm = instructions.get(0);

        for (int i = 2; i < instructions.size(); i++) {
            grid[i - 2] = Arrays.stream(instructions.get(i).split("")).toArray(String[]::new);
        }

        directions = new int[][]{{-1,-1},{0,-1},{1,-1},{-1, 0}, {0, 0}, {1, 0},{-1, 1}, {0, 1}, {1, 1}};
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        System.out.println("PART1:");
        main.part1();
    }

    @Override
    public void part1() throws IOException {
        int numberOfRounds = 50;

        String[][] inputGrid = expandGrid(grid, numberOfRounds);
        for (int i = 0; i < numberOfRounds; i++) {
            inputGrid = iterate(inputGrid, i);
            if (i == 1) {
                System.out.println("PART 1: " + count(inputGrid));
            }
        }

        System.out.println("PART 2: " + count(inputGrid));

    }

    private String[][] iterate(String[][] inputGrid, int iteration) {
        String[][] outputGrid = new String[inputGrid.length][inputGrid[0].length];
        for (int y = 0; y < outputGrid.length; y++) {
            for (int x = 0; x < outputGrid[0].length; x++) {
                StringBuilder valueString = new StringBuilder();
                for (int[] d : directions) {
                    if (x + d[0] < 0 || x + d[0] >= outputGrid[0].length || y + d[1] < 0 || y + d[1] >= outputGrid.length) {
                        valueString.append(iteration % 2 == 0 ? "." : "#");
                    } else {
                        valueString.append(inputGrid[y + d[1]][x + d[0]]);
                    }
                }
                outputGrid[y][x] = String.valueOf(algorithm.charAt(Integer.parseInt(valueString.toString().replace('.', '0').replace('#', '1'), 2)));
            }
        }

        return outputGrid;
    }

    private int count(String[][] inputGrid) {
        int total = 0;
        for (int y = 0; y < inputGrid.length; y++) {
            for (int x = 0; x < inputGrid.length; x++) {
                if (inputGrid[y][x].equals("#")) {
                    total++;
                }
            }
        }
        return total;
    }

    private String[][] expandGrid(String[][] inputGrid, int numberOfRounds) {
        String[][] newGrid = new String[inputGrid.length + 4 * numberOfRounds][inputGrid[0].length + 4 * numberOfRounds];
        Arrays.stream(newGrid).forEach(g -> Arrays.fill(g, "."));
        for (int y = 0; y < inputGrid.length; y++) {
            for (int x = 0; x < inputGrid[0].length; x++) {
                newGrid[y + 2 * numberOfRounds][x + 2 * numberOfRounds] = inputGrid[y][x];
            }
        }
        return newGrid;
    }

    @Override
    public void part2() throws IOException {

    }
}
