package year2022;

import base.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day22 extends Base {
    private static final String INPUT = "2022/day22-input.txt";

    public static void main(String[] args) throws IOException {
        Day22 main = new Day22();
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
