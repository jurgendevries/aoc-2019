package base.utils;

import java.util.List;

public class Utils {
    public static String[][] copyGrid(String[][] old, int numberOfLines, int gridWidth) {
        String[][] current = new String[numberOfLines][gridWidth];
        for(int i=0; i < old.length; i++) {
            for (int j = 0; j < old[i].length; j++) {
                current[i][j] = old[i][j];
            }
        }
        return current;
    }

    public static String[][][] copyGrid(String[][][] old, int depth, int height, int width, int ofset) {
        String[][][] current = new String[depth][height][width];
        for (int h = 0; h < depth - ofset; h++) {
            for (int i = 0; i < height - ofset; i++) {
                for (int j = 0; j < width - ofset; j++) {
                    try {
                        current[h + ofset][i + ofset][j + ofset] = old[h][i][j];
                    } catch (IndexOutOfBoundsException e) {
                        current[h + ofset][i + ofset][j + ofset] = ".";
                    }
                }
            }
        }
        for (int h = 0; h < depth; h++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (current[h][i][j] == null) {
                        current[h][i][j] = ".";
                    }
                }
            }
        }
        return current;
    }

    public static String[][][][] copyGrid(String[][][][] old, int time, int depth, int height, int width, int ofset) {
        String[][][][] current = new String[time][depth][height][width];
        for (int g = 0; g < time - ofset; g++) {
            for (int h = 0; h < depth - ofset; h++) {
                for (int i = 0; i < height - ofset; i++) {
                    for (int j = 0; j < width - ofset; j++) {
                        try {
                            current[g + ofset][h + ofset][i + ofset][j + ofset] = old[g][h][i][j];
                        } catch (IndexOutOfBoundsException e) {
                            current[g + ofset][h + ofset][i + ofset][j + ofset] = ".";
                        }
                    }
                }
            }
        }
        for (int g = 0; g < time - ofset; g++) {
            for (int h = 0; h < depth; h++) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (current[g][h][i][j] == null) {
                            current[g][h][i][j] = ".";
                        }
                    }
                }
            }
        }
        return current;
    }

    public static void printGrid(int width, int height, int depth, int time, String[][][][] grid) {

        for (int z = 0; z < time; z++) {
            System.out.println("LEVEL: " + z);
            printGrid(width, height, depth, grid[z]);
        }
    }


    public static void printGrid(int width, int height, int depth, String[][][] grid) {

        for (int z = 0; z < depth; z++) {
            System.out.println("LEVEL: " + z);
            printGrid(width, height, grid[z]);
        }
    }

    public static void printGrid(int width, int height, String[][] grid) {
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    public static void printGrid(int width, int height, int[][] grid) {
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

    public static String[][][] fillGrid(int width, int height, int depth, List<String> instructions) {
        String[][][] grid = new String[depth][height][width];
        for (int z = 0; z < depth; z++) {
            for (int y = 0; y < height; y++) {
                String instruction = y < instructions.size() ? instructions.get(y) : ".";
                for (int x = 0; x < width; x++) {
                    if (z == depth/2 && y < instructions.size() && x < instruction.length()) {
                        grid[z][y][x] = String.valueOf(instruction.charAt(x));
                    } else {
                        grid[z][y][x] = ".";
                    }
                }
            }
        }

        return grid;
    }

    public static String[][][][] fillGrid(int width, int height, int depth, int time, List<String> instructions) {
        String[][][][] grid = new String[time][depth][height][width];
        for (int t = 0; t < time; t++) {
            for (int z = 0; z < depth; z++) {
                for (int y = 0; y < height; y++) {
                    String instruction = y < instructions.size() ? instructions.get(y) : ".";
                    for (int x = 0; x < width; x++) {
                        if (z == depth / 2 && y < instructions.size() && x < instruction.length()) {
                            grid[t][z][y][x] = String.valueOf(instruction.charAt(x));
                        } else {
                            grid[t][z][y][x] = ".";
                        }
                    }
                }
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
