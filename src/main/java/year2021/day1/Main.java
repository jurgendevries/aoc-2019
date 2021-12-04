package year2021.day1;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day1-input.txt";

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
        int currentValue = Integer.parseInt(instructions.get(0));
        int incCounter = 0;

        for (int x = 1; x < instructions.size(); x++) {
            int newValue = Integer.parseInt(instructions.get(x));
            if (newValue > currentValue) {
                incCounter++;
            }
            currentValue = newValue;
        }

        System.out.println(incCounter);
    }

    @Override
    public void part2() throws IOException {
        int incCounter = 0;

        for (int x = 2; x < instructions.size() - 1; x++) {
            //int newValue = Integer.parseInt(instructions.get(x));

            int aValue = Integer.parseInt(instructions.get(x - 2)) +
                    Integer.parseInt(instructions.get(x - 1)) +
                    Integer.parseInt(instructions.get(x));

            int bValue = Integer.parseInt(instructions.get(x -1)) +
                    Integer.parseInt(instructions.get(x)) +
                    Integer.parseInt(instructions.get(x + 1));

            if (bValue > aValue) {
                incCounter++;
            }

        }

        System.out.println(incCounter);
    }
}
