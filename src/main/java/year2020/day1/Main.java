package year2020.day1;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day1-input.txt";

    private static int total = 0;
    private static int value1 = 0;
    private static int value2 = 0;
    private static int value3 = 0;
    private static boolean valueFound = false;
    private static final int TARGET_VALUE = 2020;

    private static List<Integer> instructions;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        //main.part1();
        main.part2();
    }



    public void loopThroughInstructions(int start, int levels, int value) throws IOException {
        for (int i = start; i < instructions.size(); i++) {
            loopThroughInstructions(i + 1, levels - 1, instructions.get(i));
        }
    }

    @Override
    public void part1() throws IOException {
        total = 0;
        System.out.println("PART1:");


        int val1 = 0;
        int val2 = 0;
        boolean found = false;
        for (int i = 0; i < instructions.size(); i++) {
            for (int j = i + 1; j < instructions.size(); j++) {
                if (instructions.get(i) + instructions.get(j) == 2020) {
                    found = true;
                    val1 = instructions.get(i);
                    val2 = instructions.get(j);
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        long total = val1 * val2;

        System.out.println(total);
        System.out.println("");
    }



    @Override
    public void part2() throws IOException {
        total = 0;
        System.out.println("PART2:");

        int val1 = 0;
        int val2 = 0;
        int val3 = 0;
        boolean found = false;
        for (int i = 0; i < instructions.size(); i++) {
            for (int j = i + 1; j < instructions.size(); j++) {
                for (int k = j +1; k < instructions.size(); k++) {
                    if (instructions.get(i) + instructions.get(j) + instructions.get(k) == 2020) {
                        found = true;
                        val1 = instructions.get(i);
                        val2 = instructions.get(j);
                        val3 = instructions.get(k);
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        long total = val1 * val2 * val3;

        System.out.println(total);
        System.out.println("");
    }

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(Integer.valueOf(line));
        }
    }

}

