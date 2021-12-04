package year2017.day6;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {

    Integer[] testInput = new Integer[] {0, 2, 7, 0};
    Integer[] puzzleInput = new Integer[] {5, 1, 10, 0, 1, 7, 13, 14, 3, 12, 8, 10, 7, 12, 0, 6};

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("PART1:");
        main.part1();
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        Map<List<Integer>, Integer> seen = new HashMap<>();
        List<Integer> banks = Arrays.asList(puzzleInput);
        int cycles = 0;
        seen.put(banks, cycles);

        while(true) {
            int blocks = Collections.max(banks);
            int index = banks.indexOf(blocks);

            // set bank with most blocks to 0
            banks.set(index++, 0);

            // redistribute
            while (blocks-- > 0) {
                index %= banks.size();
                banks.set(index, banks.get(index++) + 1);
            }
            cycles++;

            if (seen.containsKey(banks)) {
                System.out.println("Part 2:" + (cycles - seen.get(banks)));
                break;
            }
            seen.put(banks, cycles);
        }

        System.out.println(cycles);
    }

    private boolean addSituation(List<String> list, List<Integer> banks) {
        StringBuilder sb = new StringBuilder();
        for (Integer bank : banks) {
            sb.append(bank);
        }
        if (list.contains(sb.toString())) {
            return false;
        }
        return list.add(sb.toString());
    }

    @Override
    public void part2() throws IOException {

    }
}
