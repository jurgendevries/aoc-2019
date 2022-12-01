package year2022;

import base.Base;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends Base {
    private static final String INPUT = "2022/day1-input.txt";
    private static List<Integer> totalValues;
    public static void main(String[] args) throws IOException {
        Day1 main = new Day1();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        totalValues = main.getTotalValues();
        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("PREPARATION duration: " + duration);
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        long start = System.currentTimeMillis();

        int max = getTotalValues(totalValues, 1);

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("Max: " + max + ", duration: " + duration);
    }

    @Override
    public void part2() throws IOException {
        long start = System.currentTimeMillis();

        int sum = getTotalValues(totalValues, 3);

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("Sum 3 max: " + sum + ", duration: " + duration);

    }


    private List<Integer> getTotalValues() {
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

        return values;
    }

    private int getTotalValues(List<Integer> values, int limit) {
        return values.stream().limit(limit).mapToInt(Integer::intValue).sum();
    }
}
