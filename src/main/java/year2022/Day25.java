package year2022;

import base.Base;

import java.io.IOException;

public class Day25 extends Base {
    private static final String INPUT = "2022/day25-input.txt";

    public static void main(String[] args) throws IOException {
        Day25 main = new Day25();
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
        long result = 0;
        for (String instruction : instructions) {
            result += toDecimal(instruction);
        }
        System.out.println(result);
        System.out.println(toSnafu(result));
    }

    @Override
    public void part2() throws IOException {

    }

    static String toSnafu(long decimal) {
        StringBuilder result = new StringBuilder();

        do {
            long fives = (decimal + 2) / 5;
            int digit = (int) (decimal - (5 * fives));
            result.insert(0, convertDecimalToSnafuDigit(digit));
            decimal = fives;
        } while (decimal != 0);

        return result.toString();
    }

    private static char convertDecimalToSnafuDigit(int decimalDigit) {
        switch (decimalDigit) {
            case 2: return '2';
            case 1: return '1';
            case 0: return '0';
            case -1: return '-';
            case -2: return '=';
            default: throw new IllegalArgumentException();
        }
    }

    static long toDecimal(String snafuString) {
        long decimal = 0;
        for (int i = 0; i < snafuString.length(); i++) {
            char snafuDigit = snafuString.charAt(i);
            decimal = decimal * 5 + convertSnafuToDecimalDigit(snafuDigit);
        }
        return decimal;
    }

    private static int convertSnafuToDecimalDigit(char snafuDigit) {
        switch (snafuDigit) {
            case '2': return 2;
            case '1': return 1;
            case '0': return  0;
            case '-': return -1;
            case '=': return -2;
            default: throw new IllegalArgumentException();
        }
    }
}
