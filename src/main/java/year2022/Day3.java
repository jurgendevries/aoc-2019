package year2022;

import base.Base;

import java.io.IOException;
import java.util.Arrays;

public class Day3 extends Base {
    private static final String INPUT = "2022/day3-input.txt";

    public static void main(String[] args) throws IOException {
        Day3 main = new Day3();
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
        int sum = instructions.stream().reduce(0, (partial, instruction) -> {
            // split instruction in half
            int half = instruction.length() / 2;
            String first = instruction.substring(0, half);
            String second = instruction.substring(half);
            char match = Arrays.stream(first.split("")).filter(x -> second.contains(x)).findFirst().get().charAt(0);
            return partial + ((int) match < 97 ? match - 38 : match - 96); // calculate value
        }, Integer::sum);
        System.out.println(sum);
    }

    @Override
    public void part2() throws IOException {
        int total = 0;
        for (int i = 0; i < instructions.size() - 2; i += 3) {
            final int j = i + 1;
            final int k = i + 2;
            char match = Arrays.stream(instructions.get(i).split(""))
                    .filter(x -> instructions.get(j).contains(x) && instructions.get(k).contains(x))
                    .findFirst()
                    .get()
                    .charAt(0);
            total += ((int) match < 97 ? match - 38 : match - 96);
        }
        System.out.println(total);
    }
}
