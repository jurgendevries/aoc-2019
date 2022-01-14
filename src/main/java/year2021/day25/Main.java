package year2021.day25;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day25-input.txt";
    private static List<String> instructions;
    private static final String EAST = ">";
    private static final String SOUTH = "v";
    private static int width;
    private static int height;
    private static String[][] grid;


    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        height = instructions.size();
        width = instructions.get(0).length();

        grid = new String[height][];

        for (int i = 0; i < instructions.size(); i++) {
            grid[i] = Arrays.stream(instructions.get(i).split("")).toArray(String[]::new);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        System.out.println("PART1:");
        main.part1();
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
//        Utils.printGrid(width, height, grid);
//        System.out.println("");
        boolean movementEast = true;
        boolean movementSouth = true;
        int steps = 0;

        while (movementEast || movementSouth) {
            movementEast = move(EAST);
            movementSouth = move(SOUTH);
            steps++;
//            System.out.println("step " + steps);
//            Utils.printGrid(width, height, grid);
//            System.out.println("");
        }

        System.out.println("DONE! " + steps + "steps.");
    }

    private boolean move(String direction) {
        boolean movement = false;
        String[][] newGrid = new String[height][width];
        List<String> newSouthPositions = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].equals(direction)) {
                    if (direction.equals(EAST)) {
                        if ((x + 1 < width && ".".equals(grid[y][x + 1]))) {
                            newGrid[y][x + 1] = EAST;
                            newGrid[y][x] = ".";
                            x++;
                            movement = true;
                        } else if ((x + 1 >= width && ".".equals(grid[y][0]))) {
                            newGrid[y][0] = EAST;
                            newGrid[y][x] = ".";
                            x++;
                            movement = true;
                        } else {
                            newGrid[y][x] = grid[y][x];
                        }
                    } else {
                        if ((y + 1 < height && ".".equals(grid[y + 1][x]))) {
                            newGrid[y + 1][x] = SOUTH;
                            newGrid[y][x] = ".";
                            newSouthPositions.add(x + "," + (y + 1));
                            movement = true;
                        } else if ((y + 1 >= height && ".".equals(grid[0][x]))) {
                            newGrid[0][x] = SOUTH;
                            newGrid[y][x] = ".";
                            movement = true;
                        } else {
                            newGrid[y][x] = grid[y][x];
                        }
                    }
                } else {
                    if (!newSouthPositions.contains((x + "," + y))) {
                        newGrid[y][x] = grid[y][x];
                    }
                }
            }
        }

        grid = Arrays.copyOf(newGrid, height);
        return movement;
    }

    @Override
    public void part2() throws IOException {

    }
}
