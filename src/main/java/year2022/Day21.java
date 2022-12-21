package year2022;

import base.Base;
import helpers.search.BinarySearchHelper;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day21 extends Base {
    private static final String INPUT = "2022/day21-input.txt";

    private static Map<String, String> monkeys = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Day21 main = new Day21();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        main.readInstructions();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void readInstructions() {
        for (String instruction : instructions) {
           String name = instruction.split(":")[0].trim();
           String job = instruction.split(":")[1].trim();
           monkeys.put(name, job);
        }
    }

    @Override
    public void part1() throws IOException {
        long result = solve("root");
        System.out.println(result);
    }

    private long solve(String root) {
        String job = monkeys.get(root);
        if (NumberUtils.isCreatable(job)) {
            return Long.parseLong(job);
        }
        String sub1 = job.split(" ")[0];
        String op = job.split(" ")[1];
        String sub2 = job.split(" ")[2];

        long result1 = solve(sub1);
        long result2 = solve(sub2);
        long total;
        switch (op) {
            case "+":
                total = result1 + result2;
                break;
            case "-":
                total = result1 - result2;
                break;
            case "/":
                total = result1 / result2;
                break;
            case "*":
                total = result1 * result2;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
        return total;

    }

    @Override
    public void part2() throws IOException {
        boolean match = false;
        long attempt = Long.parseLong(monkeys.get("humn"));
        String rootJob = monkeys.get("root");
        String sub1 = rootJob.split(" ")[0];
        String sub2 = rootJob.split(" ")[2];
        BinarySearchHelper bsh = new BinarySearchHelper(1, 3712643965999L);
        while (!match) {
            monkeys.put("humn", String.valueOf(attempt));
            long result1 = solve(sub1);
            long result2 = solve(sub2);

            long newAttempt = bsh.getNextBinaryResult(attempt, result1, result2);
            match = attempt == newAttempt;
            attempt = newAttempt;
        }
        System.out.println(attempt);

    }

}
