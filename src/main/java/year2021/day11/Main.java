package year2021.day11;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day11-input.txt";
    private static List<String> instructions;
    private static int width;
    private static int height;
    private static int[][] grid;

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
        int totalLuminated = 0;
        for (int day = 0; day < 300; day++) {
            List<String> luminatedPoints = new ArrayList<>();
            List<String> fullEnergyPoints = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    grid[y][x] += 1;
                    if (grid[y][x] > 9) {
                        fullEnergyPoints.add(x + "," + y);
                    }
                }
            }


            while (fullEnergyPoints.size() > 0) {
                String fullEnergyPoint = fullEnergyPoints.remove(0);
                luminatedPoints.add(fullEnergyPoint);
                int currentX = Integer.parseInt(fullEnergyPoint.split(",")[0]);
                int currentY = Integer.parseInt(fullEnergyPoint.split(",")[1]);

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (!(i == 0 && j == 0) &&
                                currentX + j >= 0 && currentX + j < width &&
                                currentY + i >= 0 && currentY + i < height
                        ) {
                            if (grid[currentY + i][currentX + j] <= 9) {
                                grid[currentY + i][currentX + j] += 1;
                                if (grid[currentY + i][currentX + j] > 9) {
                                    fullEnergyPoints.add((currentX + j) + "," + (currentY + i));
                                }
                            }

                        }
                    }
                }
            }

            // set all 9's to 0
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (grid[y][x] == 10) {
                        grid[y][x] = 0;
                    }
                }
            }

//            Utils.printGrid(width, height, grid);
//            System.out.println();
            totalLuminated += luminatedPoints.size();
            if (luminatedPoints.size() == 100) {
                System.out.println("answer part 2: " + (day + 1));
            }

        }

        System.out.println(totalLuminated);
    }

    @Override
    public void part2() throws IOException {

    }
}
