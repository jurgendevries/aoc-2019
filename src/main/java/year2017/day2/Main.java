package year2017.day2;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2017/day2-input.txt";


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
        int total = 0;
        for (String instruction : instructions) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            String[] values = instruction.split("\\t");

            for (String value : values) {
                int val = Integer.valueOf(value);

                max = val > max ? val : max;
                min = val < min ? val : min;
            }

            total += (max - min);
        }

        System.out.println("total: " + total);
    }

    @Override
    public void part2() throws IOException {

    }
}
