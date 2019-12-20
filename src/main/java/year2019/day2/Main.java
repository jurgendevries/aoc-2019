package year2019.day2;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2019\\day2-input.txt";
    //private IntcodeComputer intcodeComputer = new IntcodeComputer(inputString);

    private static String inputString = "";
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        //main.part1();
        main.part2();
    }

    public void prepare() throws IOException {
        inputString = input.readLine();
    }

    @Override
    public void part1() throws IOException {
        String[] instructions = inputString.split(",");
        List<Integer> queue = new ArrayList<>();
        //intcodeComputer.executeInstructions(instructions, 12, 2, queue, 1);
    }

    @Override
    public void part2() throws IOException {
        boolean outputFound = false;
        for (int noun = 1; noun < 99; noun++) {
            for (int verb = 1; verb < 99; verb++) {
                String[] instructions = inputString.split(",");
                instructions[1] = String.valueOf(noun);
                instructions[2] = String.valueOf(verb);
                List<Integer> queue = new ArrayList<>();
                //intcodeComputer.executeInstructions(instructions, noun, verb, queue, 1);

                if (Integer.parseInt(instructions[0]) == 19690720) {
                    System.out.println(100 * noun + verb);
                    outputFound = true;
                    break;
                }
            }

            if (outputFound) {
                break;
            }
        }
    }
}
