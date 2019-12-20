package year2019.day19;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day19-input.txt";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1200;

    private Map<Long, String> instructionMap;
    private static String inputString = "";
    private String[] instructions;

    private static String[][] grid = new String[HEIGHT][WIDTH];

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
//        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                IntcodeComputer ic = new IntcodeComputer(new HashMap<>(instructionMap));
                ic.setInputs(new ArrayList<>(Arrays.asList(Long.valueOf(x), Long.valueOf(y))));
                List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();
                if (output.get(0).getOutputValue() == 1) {
                    grid[y][x] = "#";
                }
            }
        }
        printGrid();
        System.out.println(countPullingTiles());
    }

    private int countPullingTiles() {
        int count = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (grid[y][x] == "#") {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void part2() throws IOException {
        prepare();
        boolean fits = false;
        String c = "";

        // every 11 squares = 2 wider
//        int offsetY = 900;
//        int offsetX = 900;
        for (int y = 900; y < HEIGHT; y++) {
            for (int x = 900; x < WIDTH; x++) {
                if (isPulling(x, y)) {
                    if (isPulling(x + 99, y)) {
                        if (isPulling(x, y + 99)) {
                            fits = true;
                            c = x + "," + y;
                            break;
                        }
                    } else {
                        // skip the rest of this line
                        x = WIDTH;
                    }
                }
            }

            if (fits) {
                break;
            }
        }

        int[] cc = Arrays.asList(c.split(",")).stream().mapToInt(Integer::parseInt).toArray();
        long answer = cc[0] * 10000 + cc[1];
        System.out.println(c);
        System.out.println(answer);
    }

    private boolean isPulling(int x, int y) {
        IntcodeComputer ic = new IntcodeComputer(new HashMap<>(instructionMap));
        ic.setInputs(new ArrayList<>(Arrays.asList(Long.valueOf(x), Long.valueOf(y))));
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        return output.get(0).getOutputValue() == 1;
    }

    public void prepare() throws IOException {
        inputString = input.readLine();
        instructions = inputString.split(",");
        instructionMap = new HashMap<>();
        for (long i = 0; i < instructions.length; i++) {
            instructionMap.put(i, instructions[Math.toIntExact(i)]);
        }
        fillGrid();
    }

    private void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    private void fillGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = ".";
            }
        }
    }
}
