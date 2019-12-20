package year2019.day5;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2019\\day5-test.txt";

    private static String inputString = "";
    //private IntcodeComputer intcodeComputer = new IntcodeComputer();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        //main.part1();
        main.part2();
    }

    private void prepare() throws IOException {
        inputString = input.readLine();
    }

    @Override
    public void part1() throws IOException {
        String[] instructions = inputString.split(",");
        List<Integer> queue = new ArrayList<>();
        //intcodeComputer.executeInstructions(instructions, 225, 1, queue, 1);
    }

    @Override
    public void part2() throws IOException {
        String[] instructions = inputString.split(",");
        List<Integer> queue = new ArrayList<>();
        //intcodeComputer.executeInstructions(instructions, 225, 1, queue, 5);
    }
}
