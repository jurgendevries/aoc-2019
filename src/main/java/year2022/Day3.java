package year2022;

import base.Base;

import java.io.IOException;

public class Day3 extends Base {
    private static final String INPUT = "2022/day3-test.txt";

    public static void main(String[] args) throws IOException {
        Day3 main = new Day3();
//        main.mainMethod(INPUT);
//        main.prepareInput();
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

    }

    @Override
    public void part2() throws IOException {

    }
}
