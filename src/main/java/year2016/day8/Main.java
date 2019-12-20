package year2016.day8;

import java.io.*;

public class Main {
    private static final String INPUT = "day8-input.txt";
    private static final int WIDTH = 50;
    private static final int HEIGHT = 6;

    private static String[][] grid = new String[HEIGHT][WIDTH];

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\projects\\adventofcode\\src\\main\\resources\\"+ INPUT);
        fillGrid();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(" ");

            executeCommand(splitLine);

            printGrid();
            System.out.println("");
        }

        int litPositions = countLitPositions();

        System.out.println("count = [" + litPositions + "]");
    }

    private static int countLitPositions() {
        int counter = 0;
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                if (grid[y][x].equals("#")) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private static void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    private static void fillGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = ".";
            }
        }
    }

    private static void executeCommand(String[] splitLine) {
        if (splitLine[0].equals("rect")) {
            int width = Integer.parseInt(splitLine[1].split("x")[0]);
            int height = Integer.parseInt(splitLine[1].split("x")[1]);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    grid[y][x] = "#";
                }
            }
        } else {
            if (splitLine[1].equals("row")) {
                int row = Integer.parseInt(splitLine[2].split("=")[1]);
                int offset = Integer.parseInt(splitLine[4]);

                for (int y = row; y == row; y++) {
                    for (int i = 0; i < offset; i++) {
                        for (int x = WIDTH - 1; x > 0; x--) {
                            String tempVal = grid[y][x];
                            grid[y][x] = grid[y][x - 1];
                            grid[y][x - 1] = tempVal;
                        }
                    }
                }
            } else {
                int column = Integer.parseInt(splitLine[2].split("=")[1]);
                int offset = Integer.parseInt(splitLine[4]);

                for (int i = 0; i < offset; i++) {
                    for (int y = HEIGHT - 1; y > 0; y--) {
                        for (int x = column; x == column; x++) {
                            String tempVal = grid[y][x];
                            grid[y][x] = grid[y- 1][x];
                            grid[y - 1][x] = tempVal;
                        }
                    }
                }
            }
        }
    }
}
