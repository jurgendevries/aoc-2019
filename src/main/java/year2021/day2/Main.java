package year2021.day2;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day2-input.txt";

    private static List<String> instructions;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
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
        int distance = 0;
        int depth = 0;

        for (String instruction : instructions) {
            String dir = instruction.split(" ")[0];
            int dist = Integer.parseInt(instruction.split(" ")[1]);

            switch (dir) {
                case "forward":
                    distance += dist;
                    break;
                case "up":
                    depth -= dist;
                    break;
                case "down":
                    depth += dist;
                    break;
            }
        }
        System.out.println("distance: " + distance + ", depth: " + depth + ", multiply: " + (distance * depth));
    }

    @Override
    public void part2() throws IOException {
        int distance = 0;
        int depth = 0;
        int aim = 0;

        for (String instruction : instructions) {
            String dir = instruction.split(" ")[0];
            int dist = Integer.parseInt(instruction.split(" ")[1]);

            switch (dir) {
                case "forward":
                    distance += dist;
                    depth += dist * aim;
                    break;
                case "up":
                    aim -= dist;
                    break;
                case "down":
                    aim += dist;
                    break;
            }
        }
        System.out.println("distance: " + distance + ", depth: " + depth + ", multiply: " + (distance * depth));
    }
}
