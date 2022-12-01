package year2016;

import base.Base;

import java.io.IOException;

public class Day16 extends Base {
    private static final String INPUT = "11101000110010100";
    private static final int LENGTH = 35651584;

    public static void main(String[] args) throws IOException {
        Day16 main = new Day16();


        main.part1();
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        String dragonResult = step(INPUT);
        System.out.println(dragonResult);

        String checksum = calculateChecksum(dragonResult.substring(0, LENGTH));
        System.out.println(checksum);
    }

    private String calculateChecksum(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length() - 1; i+=2) {
            result.append(input.charAt(i) == input.charAt(i + 1) ? "1" : "0");
        }

        String checksum = result.toString();
        if (checksum.length() % 2 == 0) {
            return calculateChecksum(checksum);
        }

        return checksum;
    }

    private String step(String input) {
        StringBuilder c = new StringBuilder();
        // reverse and convert
        for (int i = input.length() - 1; i >= 0; i--) {
            c.append(input.charAt(i) == '1' ? "0" : "1");
        }

        String result = input + "0" + c;
        if (result.length() < LENGTH) {
            return step(result);
        }

        return result;
    }

    @Override
    public void part2() throws IOException {

    }
}
