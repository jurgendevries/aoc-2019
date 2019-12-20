package year2019.day13;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static int HEIGHT;
    private static int WIDTH;
    private static long score;
    private static int blockCounter = 333;
    private static int ballX;
    private static int paddleX;
    private Tile[][] grid;
    private Map<Long, String> instructionMap;
    private static String inputString = "";
    private String[] instructions;

    private static final String INPUT = "2019\\day13-input.txt";

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);


        //main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        WIDTH = Math.toIntExact(output.get(output.size() - 3).getOutputValue()) + 1;
        HEIGHT = Math.toIntExact(output.get(output.size() - 2).getOutputValue()) + 1;

        grid = new Tile[HEIGHT][WIDTH];

        for (int i = 0; i < output.size(); i += 3) {
            int x = Math.toIntExact(output.get(i).getOutputValue());
            int y = Math.toIntExact(output.get(i + 1).getOutputValue());
            Tile tile = Tile.valueOf(output.get(i + 2).getOutputValue());

            grid[y][x] = tile;
        }

        drawGrid();

        // get number of tiles painted at least once
        System.out.println("finished");

    }

    @Override
    public void part2() throws IOException {
        prepare();
        instructionMap.put(0L, "2");
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        ic.setInputs(new ArrayList<Long>(Arrays.asList(0L)));
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        WIDTH = 45;
        HEIGHT = 21;

        grid = new Tile[HEIGHT][WIDTH];

        while (blockCounter > 0) {
            for (int i = 0; i < output.size(); i += 3) {
                int x = Math.toIntExact(output.get(i).getOutputValue());
                int y = Math.toIntExact(output.get(i + 1).getOutputValue());

                if (x < 0) {
                    score = output.get(i + 2).getOutputValue();
                } else {
                    Tile tile = Tile.valueOf(output.get(i + 2).getOutputValue());
                    grid[y][x] = tile;
                }
            }

            drawGrid();

            if (ballX < paddleX) {
                ic.setInputs(new ArrayList<Long>(Arrays.asList(-1L)));
            } else if (ballX == paddleX) {
                ic.setInputs(new ArrayList<Long>(Arrays.asList(0L)));
            } else {
                ic.setInputs(new ArrayList<Long>(Arrays.asList(1L)));
            }

            output = ic.executeInstructions();
        }

        drawGrid();
        // get number of tiles painted at least once
        System.out.println("finished, score: " + score);
    }

    private void prepare() throws IOException {
        inputString = input.readLine();
        instructions = inputString.split(",");
        instructionMap = new HashMap<>();
        for (long i = 0; i < instructions.length; i++) {
            instructionMap.put(i, instructions[Math.toIntExact(i)]);
        }
    }

    private void drawGrid() {
        blockCounter = 0;
        System.out.println(score);
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                if (grid[y][x] != null) {
                    switch (grid[y][x]) {
                        case EMPTY:
                            sb.append(" ");
                            break;
                        case WALL:
                            sb.append("|");
                            break;
                        case BLOCK:
                            blockCounter++;
                            sb.append("#");
                            break;
                        case PADDLE:
                            sb.append("_");
                            paddleX = x;
                            break;
                        case BALL:
                            sb.append("o");
                            ballX = x;
                            break;
                    }
                }
            }
            System.out.println(sb);
        }
        //System.out.println(blockCounter);
    }

    public enum Tile {
        EMPTY (0),
        WALL (1),
        BLOCK (2),
        PADDLE (3),
        BALL (4);

        private final long tileId;
        private static Map map = new HashMap<>();

        static {
            for (Tile tile : Tile.values()) {
                map.put(tile.tileId, tile);
            }
        }

        Tile(int tileId) {
            this.tileId = tileId;
        }

        public static Tile valueOf(long tileId) {
            return (Tile) map.get(tileId);
        }

        public long getTileId() {
            return tileId;
        }
    }
}
