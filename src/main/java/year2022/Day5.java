package year2022;

import base.Base;

import java.io.IOException;

public class Day5 extends Base {
    private static final String INPUT = "2022/day5-test.txt";

    public static void main(String[] args) throws IOException {
        Day5 main = new Day5();
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
