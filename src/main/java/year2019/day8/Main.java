package year2019.day8;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day8-input.txt.txt";
    private static String inputString = "";
    private Map<Long, String> instructions;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        main.part1();
    }

    @Override
    public void part1() throws IOException {
        //IntcodeComputer.IntcodeComputerOutput output = new IntcodeComputer.IntcodeComputerOutput("4", 1);
        List<Long> inputValues = new ArrayList<>();
        inputValues.add(1L);
        IntcodeComputer ic = new IntcodeComputer(instructions);
        //List<IntcodeComputer.IntcodeComputerOutput> outputs = ic.executeInstructions(inputValues);

//        for (IntcodeComputer.IntcodeComputerOutput output : outputs) {
//            System.out.println(output.getOpCode() + ": " + output.getOutputValue());
//        }

    }

    private Map<Long, String> getInstructions() {
        Map<Long, String> map = new HashMap<>();
        long counter = 0;
        for (String instruction : inputString.split(",")) {
            map.put(counter, instruction);
            counter++;
        }

        return map;
    }

    @Override
    public void part2() throws IOException {

    }

    private void prepare() throws IOException {
        inputString = input.readLine();
        instructions = getInstructions();
    }
}
