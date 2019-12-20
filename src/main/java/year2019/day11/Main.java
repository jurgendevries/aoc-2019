package year2019.day11;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;
    private static Position pos;

    private static final String INPUT = "2019\\day11.txt";
    private static String inputString = "";
    private String[] instructions;
    private Map<Long, String> instructionMap;
    private String[][][] grid;
    private int dir = 0; // 0=up,1=right,2=down,3=left;

    private static Map<String, Integer> tilesPainted;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);


        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        ic.setInputs(new ArrayList<Long>(Arrays.asList(1L)));
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        while (!output.isEmpty() && !output.get(0).getOpCode().equals("99")) {
            long color = output.get(0).getOutputValue();
            long direction = output.get(1).getOutputValue();
            long nextTileColor = move(color, direction);
            long inputValue = nextTileColor;
            ic.setInputs(new ArrayList<Long>(Arrays.asList(inputValue)));
            output = ic.executeInstructions();
        }

        // get number of tiles painted at least once
        System.out.println(tilesPainted.size());
        drawGrid();
    }

    @Override
    public void part2() throws IOException {

    }

    private void prepare() throws IOException {
        tilesPainted = new HashMap<>();
        grid = new String[HEIGHT][WIDTH][1];
        pos = new Position(WIDTH/2, HEIGHT/2);
        inputString = input.readLine();
        instructions = inputString.split(",");
        instructionMap = new HashMap<>();
        for (long i = 0; i < instructions.length; i++) {
            instructionMap.put(i, instructions[Math.toIntExact(i)]);
        }
        initGrid();
        drawGrid();
    }

    private long move(long color, long direction) {
        // paint
        grid[pos.getY()][pos.getX()][0] = String.valueOf(color);
        String position = pos.getX() + "," + pos.getY();
        if (tilesPainted.containsKey(position)) {
            tilesPainted.put(position, tilesPainted.get(position) + 1);
        } else {
            tilesPainted.put(position, 1);
        }

        // turn
        if (direction == 0) {
            dir = dir - 1 >= 0 ? dir - 1 : 3;
        } else {
            dir = dir + 1 <= 3 ? dir + 1 : 0;
        }

        // move
        if (dir == 0) {
            pos.setY(pos.getY() - 1);
        } else if (dir == 1) {
            pos.setX(pos.getX() + 1);
        } else if (dir == 2) {
            pos.setY(pos.getY() + 1);
        } else {
            pos.setX(pos.getX() - 1);
        }
        drawGrid();
        return Long.parseLong(grid[pos.getY()][pos.getX()][0]);
    }

    private void initGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < HEIGHT; x++) {
                grid[y][x][0] = "0";
            }
        }
    }

    private void drawGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < HEIGHT; x++) {
                if (pos.getX() == x && pos.getY() == y) {
                    if (dir == 0) {
                        sb.append("^");
                    } else if (dir == 1) {
                        sb.append(">");
                    } else if (dir == 2) {
                        sb.append("v");
                    } else {
                        sb.append("<");
                    }
                } else if (grid[y][x][0].equals("0")) {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }

            }
            System.out.println(sb);
        }
    }

    public class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
