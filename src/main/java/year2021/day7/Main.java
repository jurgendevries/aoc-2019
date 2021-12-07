package year2021.day7;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Base {
    private static final String INPUT = "2021/day7-input.txt";
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
        System.out.println(calculateFuel(false));
    }

    @Override
    public void part2() throws IOException {
        System.out.println(calculateFuel(true));
    }

    private int calculateFuel(boolean part2) {
        List<Integer> positions = Arrays.stream(instructions.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int min = positions.stream().mapToInt(v -> v).min().getAsInt();
        int max = positions.stream().mapToInt(v -> v).max().getAsInt();

        int minFuel = Integer.MAX_VALUE;

        for (int i = min; i <= max; i++) {
            int fuel = 0;

            for (int crab : positions) {
                int diff = Math.abs(crab - i);
                int diffFuel = 0;
                if (part2) {
                    for (int n = 0; n < diff; n++) {
                        diffFuel += n + 1;
                    }
                }
                fuel += part2 ? diffFuel : diff;
            }

            minFuel = fuel < minFuel ? fuel : minFuel;
        }

        return minFuel;
    }
}
