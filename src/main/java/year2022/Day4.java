package year2022;

import base.Base;

import java.io.IOException;

public class Day4 extends Base {
    private static final String INPUT = "2022/day4-input.txt";

    public static void main(String[] args) throws IOException {
        Day4 main = new Day4();
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
