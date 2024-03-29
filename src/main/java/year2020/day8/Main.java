package year2020.day8;

import base.Base;
import base.utils.IntCodeComputer2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day8-input.txt";


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
        IntCodeComputer2020 cmp = new IntCodeComputer2020();
        long result = cmp.process(instructions);

        System.out.println("result: " + result);
    }

    @Override
    public void part2() throws IOException {

    }
}
