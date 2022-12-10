package year2022;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day10 extends Base {
    private static final String INPUT = "2022/day10-input.txt";
    private static final int FIRST_CYCLE = 20;
    private static final int CYCLE_STEP = 40;

    private long x = 1;
    private long currentCycle = 0;
    private List<Long> intermediateScores = new ArrayList<>();
    private String[][] crt = new String[6][40];
    public static void main(String[] args) throws IOException {
        Day10 main = new Day10();
        main.mainMethod(INPUT);
        main.prepareInput();

        System.out.println("PART1:");
        long start = System.currentTimeMillis();
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    @Override
    public void part1() throws IOException {
        for (String instruction : instructions) {
            cycle(instruction, false);
        }
        long result = intermediateScores.stream().mapToLong(x -> x).sum();
        System.out.println(result);
    }

    @Override
    public void part2() throws IOException {
        x = 1;
        currentCycle = 0;
        for (String instruction : instructions) {
            cycle(instruction, true);
        }

        for (int y = 0; y < 6; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 40; x++) {
                sb.append(crt[y][x]);
            }
            System.out.println(sb);
        }
        System.out.println("EZBPBFZF");
    }

    private void draw() {
        int currentPos = (int) (currentCycle - 1);
        int row = currentPos / CYCLE_STEP;
        int col = currentPos % CYCLE_STEP;
        int drawPosInRow = currentPos - row * CYCLE_STEP;
        crt[row][col] = Math.abs(drawPosInRow - x) <= 1 ? "#" : ".";
    }

    private void cycle(String instruction, boolean part2) {
        String cmd = instruction.split(" ")[0];
        currentCycle++;
        //System.out.println("[" + instruction + "], [DURING CYCLE]= " + currentCycle + ", [X]= " + x);
        checkIntermediateScore();
        if (part2) {
            draw();
        }
        switch (cmd) {
            case "addx":
                addX(instruction, part2);
                break;
            case "noop":
                break;
            default:
                throw new IllegalArgumentException("UNKNOWN COMMAND");
        }
    }

    private void checkIntermediateScore() {
        if (currentCycle == FIRST_CYCLE || currentCycle % 40 == 20) {
            //System.out.println("INTERMEDIATE SCORE FOR CYCLE " + currentCycle + ": " + (currentCycle * x) + " !!!");
            intermediateScores.add(currentCycle * x);
        }
    }

    private void addX(String instruction, boolean part2) {
        long val = Long.parseLong(instruction.split(" ")[1]);
        currentCycle ++;
        //System.out.println("[" + instruction + "], [DURING CYCLE]= " + currentCycle + ", [X]= " + x);
        checkIntermediateScore();
        if (part2) {
            draw();
        }
        x += val;
        //System.out.println("[" + instruction + "], [AFTER CYCLE]= " + currentCycle + ", [X]= " + x);
    }
}
