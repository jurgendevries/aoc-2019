package year2022;

import base.Base;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day11 extends Base {
    private static final String INPUT = "2022/day11-input.txt";
    private static Map<Integer, Monkey> monkeys = new HashMap<>();
    private static Long worryMod;

    public static void main(String[] args) throws IOException {
        Day11 main = new Day11();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void readInput() {
        monkeys = new HashMap<>();
        Monkey monkey = new Monkey();
        List<Long> tests = new ArrayList<>();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                monkeys.put(monkey.id, monkey);
                monkey = new Monkey();
            } else {
                if (instruction.startsWith("Monkey")) {
                    int id = Integer.parseInt(instruction.split(" ")[1].replace(":", ""));
                    monkey.id = id;
                } else if (instruction.trim().startsWith("Starting")) {
                    List<Long> items = Arrays.stream(instruction.trim().split(":")[1].trim().split(", ")).map(x -> Long.parseLong(x)).collect(Collectors.toList());
                    monkey.items = items;
                } else if (instruction.trim().startsWith("Operation")) {
                    String op = instruction.trim().split("=")[1].trim();
                    String operation = op.split(" ")[1];
                    String val = op.split(" ")[2];
                    Function<Long, Long> o;
                    if ("*".equals(operation)) {
                        o = x -> x * (NumberUtils.isCreatable(val) ? Long.valueOf(val) : x);
                    } else {
                        o = x -> x + (NumberUtils.isCreatable(val) ? Long.valueOf(val) : x);
                    }
                    monkey.operation = o;
                } else if (instruction.trim().startsWith("Test")) {
                    Long testVal = Long.valueOf(instruction.trim().split(" ")[3]);
                    monkey.test = x -> x % testVal == 0;
                    tests.add(testVal);
                } else if (instruction.trim().startsWith("If true")) {
                    monkey.match = Integer.parseInt(instruction.trim().split(" ")[5]);
                } else if (instruction.trim().startsWith("If false")) {
                    monkey.miss = Integer.parseInt(instruction.trim().split(" ")[5]);
                }
            }
        }
        monkeys.put(monkey.id, monkey);
        worryMod = tests.stream().reduce(1L, (x,y) -> x * y);
    }

    @Override
    public void part1() throws IOException {
        readInput();
        for (int i = 0; i < 20; i++) {
            playRound(true);
        }

//        for (Map.Entry<Integer, Monkey> m : monkeys.entrySet()) {
//            System.out.println(m.getValue().inspected);
//        }

        List<Long> mostInspected = getSortedMonkeyInspections();

        System.out.println(mostInspected.get(0) * mostInspected.get(1));
    }

    private List<Long> getSortedMonkeyInspections() {
        List<Long> mostInspected = monkeys.entrySet().stream().map(m -> m.getValue().inspected).collect(Collectors.toList()).stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
        return mostInspected;
    }

    private void playRound(boolean part1) {
        for (int i = 0; i < monkeys.size(); i++) {
            Monkey monkey = monkeys.get(i);
            playTurn(monkey, part1);
        }
    }

    private void playTurn(Monkey monkey, boolean part1) {
        List<Long> matches = new ArrayList<>();
        List<Long> misses = new ArrayList<>();
        for (Long item : monkey.items) {
            monkey.inspected++;
            Long newItem = monkey.operation.apply(item);
            if (part1) {
                newItem = newItem / 3;
            } else {
                newItem = newItem % worryMod;
            }
            if (monkey.test.test(newItem)) {
                matches.add(newItem);
            } else {
                misses.add(newItem);
            }
        }

        Monkey match = monkeys.get(monkey.match);
        match.items.addAll(matches);
        Monkey miss = monkeys.get(monkey.miss);
        miss.items.addAll(misses);
        monkey.items = new ArrayList<>();
    }

    @Override
    public void part2() throws IOException {
        readInput();
        for (int i = 0; i < 10000; i++) {
            playRound(false);
//            if (i == 0 || i == 19 || (i+1) % 1000 == 0) {
//                System.out.println("Round: " + (i + 1));
//                for (Map.Entry<Integer, Monkey> m : monkeys.entrySet()) {
//                    System.out.println(m.getValue().inspected);
//                }
//            }
        }

        List<Long> mostInspected = getSortedMonkeyInspections();

        System.out.println(mostInspected.get(0) * mostInspected.get(1));
    }

    class Monkey {
        private int id;
        private List<Long> items = new ArrayList<>();
        private int match;
        private int miss;
        private Predicate<Long> test;
        private Function<Long, Long> operation;
        private long inspected;
    }
}
