package year2022;

import base.Base;

import java.io.IOException;

public class Day13 extends Base {
    private static final String INPUT = "2022/day11-input.txt";

    public static void main(String[] args) throws IOException {
        Day13 main = new Day13();
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

    @Override
    public void part1() throws IOException {

    }

    @Override
    public void part2() throws IOException {

    }
}
