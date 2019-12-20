package year2019.day9;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day9-input.txt";
    private static String inputString = "";
    private String[] instructions;
    private Map<Long, String> instructionMap;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);


        //main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        ic.setInputs(new ArrayList<Long>(Arrays.asList(1L)));
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        for (IntcodeComputer.IntcodeComputerOutput o : output) {
            System.out.println(o.getOutputValue());
        }
    }

    @Override
    public void part2() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        ic.setInputs(new ArrayList<Long>(Arrays.asList(2L)));
        List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

        for (IntcodeComputer.IntcodeComputerOutput o : output) {
            System.out.println(o.getOutputValue());
        }
    }

    private void prepare() throws IOException {
        inputString = input.readLine();
        instructions = inputString.split(",");
        instructionMap = new HashMap<>();
        for (long i = 0; i < instructions.length; i++) {
            instructionMap.put(i, instructions[Math.toIntExact(i)]);
        }
    }
}
