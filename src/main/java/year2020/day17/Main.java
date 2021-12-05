package year2020.day17;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day17-input.txt";
    //private static final int WIDTH = 10;
    private static String[][][][] grid;

    private static List<String> instructions;

    private void prepare() throws IOException {
        String line;
        int width = 0;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
            width = line.length();
        }

        grid = Utils.fillGrid(instructions.size(), width, 1, 1, instructions);
        Utils.printGrid(instructions.size(), width, 1, 1, grid);
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
        for (int i = 0; i < 6; i++) {
            System.out.println("CYCLE " + i);
            cycle();
        }

        long total = 0;

        for (int t = 0; t < grid.length; t++) {
            for (int z = 0; z < grid[0].length; z++) {
                for (int y = 0; y < grid[0][0].length; y++) {
                    for (int x = 0; x < grid[0][0][0].length; x++) {
                        if ("#".equals(grid[t][z][y][x])) {
                            total++;
                        }
                    }
                }
            }
        }

        System.out.println(total);

    }

    private void cycle() {
        String[][][][] copyGrid = Utils.copyGrid(grid, grid.length + 2, grid[0].length + 2, grid[0][0].length + 2, grid[0][0][0].length + 2, 1);
        grid = Utils.copyGrid(copyGrid, copyGrid.length, copyGrid[0].length, copyGrid[0][0].length, copyGrid[0][0][0].length, 0);


        for (int t = 0; t < grid.length; t++) {
            for (int z = 0; z < grid[0].length; z++) {
                for (int y = 0; y < grid[0][0].length; y++) {
                    for (int x = 0; x < grid[0][0][0].length; x++) {
                        copyGrid[t][z][y][x] = determineNewState(x, y, z, t, grid);
                    }
                }
            }
        }

        grid = Utils.copyGrid(copyGrid, copyGrid.length, copyGrid[0].length, copyGrid[0][0].length, copyGrid[0][0][0].length,0);
        Utils.printGrid(grid[0][0][0].length, grid[0][0].length, grid[0].length, grid.length, grid);
    }

    private String determineNewState(int x, int y, int z, int t, String[][][][] oldGrid) {
        int activeAround = countActiveAround(x, y, z, t, oldGrid);
        String newState = "";
        try {
            newState = oldGrid[t][z][y][x];
        } catch (IndexOutOfBoundsException e) {
            // does not exist in old grid, so inactive
            newState = ".";
        }



        if ("#".equals(newState)) {
            if (activeAround < 2 || activeAround > 3) {
                newState = ".";
            }
        } else {
            if (activeAround == 3) {
                newState = "#";
            }
        }

        return newState;
    }

    private int countActiveAround(int x, int y, int z, int t, String[][][][] oldGrid) {
        int active = 0;
        // count active coords next to current
        for (int diffT = -1; diffT < 2; diffT++) { // depth (Z)
            for (int diffZ = -1; diffZ < 2; diffZ++) { // depth (Z)
                for (int diffY = -1; diffY < 2; diffY++) { // height (Y)
                    for (int diffX = -1; diffX < 2; diffX++) { // width (X)
                        try {
                            if (!(diffT == 0 && diffZ == 0 && diffY == 0 && diffX == 0) &&
                                    "#".equals(oldGrid[t + diffT][z + diffZ][y + diffY][x + diffX])) {
                                active++;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            // if coord outside grid... then it's inactive, so no count
                        }
                    }
                }
            }
        }

        return active;
    }



    @Override
    public void part2() throws IOException {

    }
}
