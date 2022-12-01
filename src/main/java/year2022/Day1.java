package year2022;

import base.Base;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends Base {
    private static final String INPUT = "2022/day1-input.txt";

    public static void main(String[] args) throws IOException {
        Day1 main = new Day1();
        main.mainMethod(INPUT);
        main.prepareInput();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        long start = System.currentTimeMillis();

        int max = getTotalValues(instructions, 1);

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("Max: " + max + ", duration: " + duration);
    }

    @Override
    public void part2() throws IOException {
        long start = System.currentTimeMillis();

        int sum = getTotalValues(instructions, 3);

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("Sum 3 max: " + sum + ", duration: " + duration);

    }

    private int getTotalValues(List<String> instructions, int limit) {
        int total = 0;
        List<Integer> values = new ArrayList<>();
        for (String instruction : instructions) {
            if (StringUtils.isEmpty(instruction)) {
                values.add(total);
                total = 0;
            } else {
                total += Integer.valueOf(instruction);
            }
        }
        values.add(total);

        Collections.sort(values, Collections.reverseOrder());
        return values.stream().limit(limit).mapToInt(Integer::intValue).sum();
    }
}
