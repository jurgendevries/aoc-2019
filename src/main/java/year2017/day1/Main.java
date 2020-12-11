package year2017.day1;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2017/day1-input.txt";


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

    @Override
    public void part1() throws IOException {
        for (String instruction : instructions) {
            System.out.println("total (" + instruction + "): " + calculateTotal(instruction));

        }
    }

    private int calculateTotal(String instruction) {
        int total = 0;
        for (int i = 0; i < instruction.length(); i++) {
            if (i == instruction.length() - 1) {
                if (instruction.charAt(i) == instruction.charAt(0)) {
                    total += Integer.parseInt(String.valueOf(instruction.charAt(i)));
                }
            } else {
                if (instruction.charAt(i) == instruction.charAt(i + 1)) {
                    total += Integer.parseInt(String.valueOf(instruction.charAt(i)));
                }
            }
        }

        return total;
    }

    @Override
    public void part2() throws IOException {
        for (String instruction : instructions) {
            System.out.println("total (" + instruction + "): " + calculateTotalHalf(instruction));

        }
    }

    private int calculateTotalHalf(String instruction) {
        int total = 0;
        int halfWay = instruction.length()/2;
        for (int i = 0; i < instruction.length(); i++) {
            if (i + halfWay >= instruction.length()) {
                if (instruction.charAt(i) == instruction.charAt(i - halfWay)) {
                    total += Integer.parseInt(String.valueOf(instruction.charAt(i)));
                }
            } else {
                if (instruction.charAt(i) == instruction.charAt(i + halfWay)) {
                    total += Integer.parseInt(String.valueOf(instruction.charAt(i)));
                }
            }
        }

        return total;
    }
}
