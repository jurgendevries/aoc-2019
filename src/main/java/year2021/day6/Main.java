package year2021.day6;

import base.Base;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Base {
    private static final String INPUT = "2021/day6-input.txt";
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

    public void part1() throws IOException {
        System.out.println(simulateFish(80));

    }

    public void part2() throws IOException {
        System.out.println(simulateFish(256));
    }

    private long simulateFish(long totalDays) {
        Map<Long, Long> fishPerDayMap = new HashMap<>();
        for (long i = 0; i <= 8; i++) {
            final long day = i;
            fishPerDayMap.put(day, Arrays.asList(instructions.get(0).split(",")).stream().map(Long::parseLong).filter(x -> x == day).count());
        }

        List<Long> days = fishPerDayMap.keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        long newFish = 0;

        for (long i = 0; i < totalDays; i++) {
            for (long day = 0; day < fishPerDayMap.size(); day++) {
                if (day == 0) {
                    newFish = fishPerDayMap.get(day);
                } else if (day == 7) {
                    // add 0 day fishes to day 6 again
                    fishPerDayMap.put(day - 1, (fishPerDayMap.get(day) + newFish));
                } else {
                    fishPerDayMap.put(day - 1, fishPerDayMap.get(day));
                }
            }
            // add new fish
            fishPerDayMap.put(8L, newFish);
        }

        return fishPerDayMap.values().stream().mapToLong(Long::longValue).sum();
    }
}
