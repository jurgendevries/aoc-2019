package year2020.day6;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2020/day6-input.txt";

    private static int total = 0;

    private static List<String> instructions;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
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
        Set<Character> answers = new HashSet<>();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                total += answers.size();
                answers = new HashSet<>();
                continue;
            }
            for (char answer : instruction.toCharArray()) {
                answers.add(answer);
            }
        }

        total += answers.size();

        System.out.println("total: " + total);
    }

    @Override
    public void part2() throws IOException {
        total = 0;
        int count = 0;

        Map<Character, Integer> answers = new HashMap<>();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                for (Map.Entry<Character, Integer> entry : answers.entrySet()) {
                    if (entry.getValue() == count) {
                        total++;
                    }
                }

                answers = new HashMap<>();
                count = 0;
                continue;
            }

            count++;
            for (char answer : instruction.toCharArray()) {
                if (answers.containsKey(answer)) {
                    answers.put(answer, answers.get(answer) + 1);
                } else {
                    answers.put(answer, 1);
                }
            }
        }

        for (Map.Entry<Character, Integer> entry : answers.entrySet()) {
            if (entry.getValue() == count) {
                total++;
            }
        }

        System.out.println("total: " + total);
    }
}
