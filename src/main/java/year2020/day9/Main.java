package year2020.day9;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final int PREAMBLE = 25;
    private static final String INPUT = "2020/day9-input.txt";

    private static long resultPart1;

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
        MAINLOOP: for (int i = PREAMBLE; i < instructions.size(); i++) {
            long value = Long.parseLong(instructions.get(i));

            // find addition result of 2 PREAMBLE values
            for (int j = i - PREAMBLE; j < i; j++) {
                for (int k = i - PREAMBLE; k < i; k++) {
                    if (Long.parseLong(instructions.get(j)) + Long.parseLong(instructions.get(k)) == value) {
                        continue MAINLOOP;
                    }
                }
            }

            // if not possible break and return value
            resultPart1 = value;
            break;
        }

        System.out.println("Result: " + resultPart1);
    }

    @Override
    public void part2() throws IOException {
        // find result
        List<Long> result = process(instructions, resultPart1);
        System.out.println(result);

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (long v : result) {
            min = v < min ? v : min;
            max = v > max ? v : max;
        }

        long finalResult = min + max;
        System.out.println("Final result: " + finalResult);
    }

    private List<Long> process(List<String> instructions, long remainingValue) {
        List<Long> values = new ArrayList<>();
        MAINLOOP: for (long i = 0; i < instructions.size(); i++) {
            values = new ArrayList<>();
            values.add(Long.valueOf(instructions.get(Math.toIntExact(i))));
            long remainder = remainingValue - Long.parseLong(instructions.get(Math.toIntExact(i)));

            if (remainder == 0) {
                break;
            } else if (remainder < 0) {
                continue;
            } else {
                for (long j = i + 1; j < instructions.size(); j++) {
                    values.add(Long.valueOf(instructions.get(Math.toIntExact(j))));
                    remainder -= Long.parseLong(instructions.get(Math.toIntExact(j)));

                    if (remainder == 0) {
                        break MAINLOOP;
                    } else if (remainder < 0) {
                        continue MAINLOOP;
                    } else {
                        continue;
                    }
                }
            }
        }

        return values;
    }
}
