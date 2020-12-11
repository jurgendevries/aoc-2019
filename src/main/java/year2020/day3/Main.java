package year2020.day3;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day3-input.txt";

    private static String[][] grid;

    private static List<String> instructions;

    private static int numberOfLines;
    private static int gridWidth;
    private static int minNumberOfColumnsNeeded;
    private static int numberOfGridsNeeded;
    private static int numberOfColumnsNeeded;

    private void readLines() throws  IOException {
        numberOfLines = 0;
        gridWidth = 0;
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
            numberOfLines++;
            if (gridWidth == 0) {
                gridWidth = line.length();
            }
        }
    }

    private void prepare(int xStep) {
        minNumberOfColumnsNeeded = numberOfLines * xStep;
        numberOfGridsNeeded = (int) Math.ceil((double) minNumberOfColumnsNeeded / (double) gridWidth);
        numberOfColumnsNeeded = gridWidth * numberOfGridsNeeded;

        grid = Utils.fillGridWithInputHorizontalReps(numberOfColumnsNeeded, numberOfLines, instructions, numberOfGridsNeeded, gridWidth);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.readLines();
        //main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare(3);
        int numberOfTrees = traverseSlopes(3, 1);
        System.out.println(numberOfTrees);
    }

    @Override
    public void part2() throws IOException {
        int[][] values = new int[][] {{1,1}, {3,1}, {5,1}, {7,1}, {1,2}};

        int numberOfTrees = 0;
        long product = 1;

        for (int i = 0; i < values.length; i++) {
            prepare(values[i][0]);
            //Utils.printGrid(numberOfColumnsNeeded, numberOfLines, grid);
            int trees = traverseSlopes(values[i][0], values[i][1]);
            System.out.println("{" + values[i][0] + "," + values[i][1] + "}: " + trees);
            numberOfTrees += trees;
            product *= trees;
        }

        System.out.println(numberOfTrees);
        System.out.println(product);
    }

    private int traverseSlopes(int xStep, int yStep) {
        int currentX = 0;
        int currentY = 0;

        int numberOfTrees = 0;

        while (currentY < numberOfLines - 1) {
            currentY += yStep;
            currentX += xStep;

            if ("#".equals(grid[currentY][currentX])) {
                //grid[currentY][currentX] = "X";
                numberOfTrees++;
//            } else {
//                grid[currentY][currentX] = "O";
            }
        }
//        Utils.printGrid(numberOfColumnsNeeded, numberOfLines, grid);
        return numberOfTrees;

    }
}
