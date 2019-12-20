package year2019.day7;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day7-input.txt";

    private static String inputString = "";
    private List<String> phaseCombinations = new ArrayList<>();
    private String[] instructions;


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

        main.prepare();
        //main.part1();
        main.part2();
    }

    private void prepare() throws IOException {
        inputString = input.readLine();
        instructions = inputString.split(",");
        combinations(Arrays.asList(5,6,7,8,9), "");
    }

    private void combinations(List<Integer> list, String combination) {
        if (list.isEmpty()) {
            phaseCombinations.add(combination);
            return;
        }

        for (Integer phase : list) {
            List<Integer> newList = new ArrayList<>(list);
            newList.remove(phase);
            combinations(newList, combination + phase);
        }


    }


    @Override
    public void part1() throws IOException {
        Map<String, Integer> combiMap = new HashMap<>();
        for (String combination : phaseCombinations) {
            IntcodeComputer.IntcodeComputerOutput output = new IntcodeComputer.IntcodeComputerOutput("4", 0);
            for (int i = 0; i < 5; i++) {
                List<Integer> inputValues = new ArrayList<>();
                inputValues.add(Character.getNumericValue(combination.charAt(i)));
//                inputValues.add(output.getOutputValue());
//                IntcodeComputer ic = new IntcodeComputer(instructions);
//                output = ic.executeInstructions(inputValues);
            }
//            combiMap.put(combination, output.getOutputValue());
            System.out.println("combination: " + combination + " , output = " + output);
        }

        String maxKey = combiMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        System.out.println("combination: " + maxKey + " , has max output = " + combiMap.get(maxKey));
    }

    @Override
    public void part2() throws IOException {
        Map<String, Integer> combiMap = new HashMap<>();
        for (String combination : phaseCombinations) {
            List<IntcodeComputer> iccList = new ArrayList<>();
//            iccList.add(new IntcodeComputer(inputString.split(",")));
//            iccList.add(new IntcodeComputer(inputString.split(",")));
//            iccList.add(new IntcodeComputer(inputString.split(",")));
//            iccList.add(new IntcodeComputer(inputString.split(",")));
//            iccList.add(new IntcodeComputer(inputString.split(",")));

//            IntcodeComputer.IntcodeComputerOutput output = new IntcodeComputer.IntcodeComputerOutput("4", 0);
//
//            while (!output.getOpCode().equals("99")) {
//                for (int i = 0; i < 5; i++) {
//                    List<Integer> inputValues = new ArrayList<>();
//                    if (iccList.get(i).getIccOutput().getLoop() == 0) {
//                        inputValues.add(Character.getNumericValue(combination.charAt(i)));
//                    }

//                    inputValues.add(output.getOutputValue());
//                    output = iccList.get(i).executeInstructions(inputValues);
//                }
//
//            }
//            combiMap.put(combination, output.getOutputValue());
//            System.out.println("combination: " + combination + " , output = " + output);
        }

        String maxKey = combiMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        System.out.println("combination: " + maxKey + " , has max output = " + combiMap.get(maxKey));
    }
}
