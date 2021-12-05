package year2020.day11;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day11-test.txt";

    private static String[][] grid;

    private static List<String> instructions;

    private static int numberOfLines;
    private static int gridWidth;
    private static int numberOfSeatsChanged;
    private static int finalOccupiedSeats;

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

    private void prepare() {
        grid = Utils.fillGrid(gridWidth, numberOfLines, instructions);
        Utils.printGrid(gridWidth, numberOfLines, grid);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.readLines();
        main.prepare();
        System.out.println();
        main.part1();
        //main.part2();
    }

    @Override
    public void part1() throws IOException {
        do {
            numberOfSeatsChanged = 0;
            cycle(false);
            Utils.printGrid(gridWidth, numberOfLines, grid);
            System.out.println();
        } while (numberOfSeatsChanged > 0);

        countTotalOccupiedSeats();

        System.out.println("Total occupied: " + finalOccupiedSeats);
    }

    private void cycle(boolean vision) {
        String[][] copyGrid = Utils.copyGrid(grid, numberOfLines, gridWidth);
        for (int y = 0; y < numberOfLines; y++) {
            for (int x = 0; x < gridWidth; x++) {
                copyGrid[y][x] = determineNewState(x, y, vision);
            }
        }
        grid = Arrays.copyOf(copyGrid, numberOfLines);
    }



    private void countTotalOccupiedSeats() {
        for (int y = 0; y < numberOfLines; y++) {
            for (int x = 0; x < gridWidth; x++) {
                if ("#".equals(grid[y][x])) {
                    finalOccupiedSeats++;
                }
            }
        }
    }

    private String determineNewState(int x, int y, boolean vision) {
        int occupiedSeatLimit = vision ? 5 : 4;
        String currentValue = grid[y][x];
        String newValue = ".";
        if (".".equals(currentValue)) {
            return newValue;
        } else {
            int occupiedSeatsAdjacent = countOccupiedSeatAdjacent(x, y, vision);
            if ("L".equals(currentValue)) {
                if (occupiedSeatsAdjacent == 0) {
                    newValue = "#";
                    numberOfSeatsChanged++;
                } else {
                    newValue = "L";
                }
            } else {
                if (occupiedSeatsAdjacent >= occupiedSeatLimit) {
                    newValue = "L";
                    numberOfSeatsChanged++;
                } else {
                    newValue = "#";
                }
            }
        }
        return newValue;
    }

    private int countOccupiedSeatAdjacent(int x, int y, boolean vision) {
        int occupiedSeats = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (vision) {
                    int currentY = y;
                    int currentX = x;
                    boolean seatFound = false;
                    while (!seatFound &&
                            currentY + i >= 0 && currentY + i < numberOfLines &&
                        currentX + j >= 0 && currentX + j < gridWidth) {
                        currentY += i;
                        currentX += j;
                        if (!(i == 0 && j == 0)) {
                            String val = grid[currentY][currentX];
                            if ("#".equals(val)) {
                                occupiedSeats++;
                                seatFound = true;
                            } else if ("L".equals(val)) {
                                seatFound = true;
                            }
                        } else {
                            // own seat found
                            seatFound = true;
                        }
                    }
                } else {
                    if (y + i >= 0 && y + i < numberOfLines && x + j >= 0 && x + j < gridWidth &&
                            !(i == 0 && j == 0) &&
                            "#".equals(grid[y + i][x + j])) {
                        occupiedSeats++;
                    }
                }
            }
        }

        return occupiedSeats;
    }

    @Override
    public void part2() throws IOException {
        do {
            numberOfSeatsChanged = 0;
            cycle(true);
            Utils.printGrid(gridWidth, numberOfLines, grid);
            System.out.println();
        } while (numberOfSeatsChanged > 0);

        countTotalOccupiedSeats();

        System.out.println("Total occupied: " + finalOccupiedSeats);
    }
}
