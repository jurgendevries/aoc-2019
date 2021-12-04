package year2017.day8;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2017/day8-input.txt";


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
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        Map<String, Integer> registers = new HashMap<>();
        int highestValue = 0;

        for (String instruction : instructions) {
            String condition = instruction.split("if")[1].trim();
            String[] splitInstruction = instruction.split("if")[0].split(" ");
            String register = splitInstruction[0];
            String command = splitInstruction[1];
            int value = Integer.parseInt(splitInstruction[2]);

            if (!registers.containsKey(register)) {
                registers.put(register, 0);
            }

            try {
                if (evaluateCondition(registers, condition)) {
                    switch (command) {
                        case "inc":
                            registers.put(register, registers.get(register) + value);
                            break;
                        case "dec":
                            registers.put(register, registers.get(register) - value);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown command!!!");
                    }

                    highestValue = registers.get(register) > highestValue ? registers.get(register) : highestValue;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        Map.Entry<String, Integer> maxValue = registers.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();
        System.out.println("Key: " + maxValue.getKey() + ", value: " + maxValue.getValue() + ", highest: " + highestValue);
    }

    private boolean evaluateCondition(Map<String, Integer> registers, String condition) {
        String conditionRegister = condition.split(" ")[0];
        String con = condition.split(" ")[1];
        int conditionValue = Integer.parseInt(condition.split(" ")[2]);

        if (!registers.containsKey(conditionRegister)) {
            registers.put(conditionRegister, 0);
        }

        boolean valid = false;

        switch (con) {
            case ">":
                valid = registers.get(conditionRegister) > conditionValue;
                break;
            case ">=":
                valid = registers.get(conditionRegister) >= conditionValue;
                break;
            case "<":
                valid = registers.get(conditionRegister) < conditionValue;
                break;
            case "<=":
                valid = registers.get(conditionRegister) <= conditionValue;
                break;
            case "==":
                valid = registers.get(conditionRegister) == conditionValue;
                break;
            case "!=":
                valid = registers.get(conditionRegister) != conditionValue;
                break;
            default:
                throw new IllegalArgumentException("Unknown condition!!!");
        }

        return valid;
    }

    @Override
    public void part2() throws IOException {

    }
}
