package year2023;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Day4 extends Base {
    private static final String INPUT = "2023/day4-input.txt";


    private Map<String, Double> scores = new HashMap<>();
    private List<Integer> matchesList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Day4 main = new Day4();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();

        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("PREPARATION duration: " + duration);
    }

    @Override
    public void part1() throws IOException {
        double total = 0;
        for (String instruction : instructions) {
            String card = instruction.split(":")[0].split(" ")[instruction.split(":")[0].split(" ").length - 1];
            String[] winningNumbers = instruction.split(":")[1].trim().split("\\|")[0].trim().split(" ");
            String[] yourNumbers = instruction.split(":")[1].trim().split("\\|")[1].trim().split(" ");

            double score = 0;
            Set<String> matches = new HashSet<>();

            for (String win : winningNumbers) {
                if (!win.equals("") && Arrays.stream(yourNumbers).anyMatch(x -> x.equals(win))) {
                    matches.add(win);
                }
            }

            if (!matches.isEmpty()) {
                if (matches.size() == 1) {
                    score = 1;
                } else {
                    score = Math.pow(2, (matches.size() - 1));
                }
            }
            total += score;
            scores.put(card, score);
            matchesList.add(matches.size());
        }
        System.out.println(total);
    }

    @Override
    public void part2() throws IOException {
        System.out.println("part2");
        System.out.println(matchesList);
        Map<Integer, Integer> copies = new HashMap<>();
        for (int i = 0; i < scores.size(); i++) {
            copies.put(i+1, 1);
        }
        for (int i = 0; i < matchesList.size(); i++) {
            // matches on card 1
            int matches = matchesList.get(i);
            int currentKey = i + 1;
            // 4 matches, so each match adds a copy of the following cards
            for (int j = 1; j <= matches; j++) {
                int key = i + j + 1;
                copies.compute((key), (k,v) -> v == null ? 1 : v + (1 * copies.get(currentKey)));
            }
        }

        int total2 = copies.values().stream().reduce(0, (sub, el) -> sub + el);
        System.out.println(total2);
    }
}
