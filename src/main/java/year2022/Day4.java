package year2022;

import base.Base;

import java.io.IOException;

public class Day4 extends Base {
    private static final String INPUT = "2022/day4-input.txt";

    public static void main(String[] args) throws IOException {
        Day4 main = new Day4();
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
        int matchingPairs = determineOverlap(true);
        System.out.println(matchingPairs);
    }

    @Override
    public void part2() throws IOException {
        int matchingPairs = determineOverlap(false);
        System.out.println(matchingPairs);
    }

    private int determineOverlap(boolean fullOverlap) {
        int matchingPairs = 0;
        for (String instruction : instructions) {
            int start1 = Integer.parseInt(instruction.split(",")[0].split("-")[0]);
            int end1 = Integer.parseInt(instruction.split(",")[0].split("-")[1]);
            int start2 = Integer.parseInt(instruction.split(",")[1].split("-")[0]);
            int end2 = Integer.parseInt(instruction.split(",")[1].split("-")[1]);

            if (fullOverlap) {
                if ((start1 <= start2 && end1 >= end2) || (start2 <= start1 && end2 >= end1)) {
                    matchingPairs++;
                }
            }  else {
                if ((end1 >= start2 && end1 <= end2) || (end2 >= start1 && end2 <= end1)) {
                    matchingPairs++;
                }
            }
        }

        return matchingPairs;
    }
}
