package year2016.day23;

import base.Base;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main  extends Base {
    private static final String INPUT = "day23-input.txt";
    private static Map<String, Integer> registers;
    private static List<String> instructions;
    private static List<String[]> instructionsCut;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

        main.prepare();


//        main.part1();
        main.part2();
    }

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    @Override
    public void part1() throws IOException {
        System.out.println("Part1:");

        registers = new HashMap<>();
        registers.put("a", 7);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        executeInstructions();
        System.out.println("");
    }

    @Override
    public void part2() throws IOException {
        System.out.println("Part2:");

        registers = new HashMap<>();
        registers.put("a", 12);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        executeInstructions();
    }

    private void executeInstructions () throws IOException {
        for (int i = 0; i < instructions.size();) {
            String instruction = instructions.get(i);
            String[] splitInst = instruction.split(" ");
            if (splitInst[0].equals("cpy")) {
                if (NumberUtils.isParsable(splitInst[1]) && NumberUtils.isParsable(splitInst[2])) {
                    i++;
                    continue;
                }
                if (NumberUtils.isParsable(splitInst[1])) {
                    registers.put(splitInst[2], Integer.parseInt(splitInst[1]));
                } else {
                    registers.put(splitInst[2], registers.get(splitInst[1]));
                }
                i++;
            } else if (splitInst[0].equals("inc")) {
                if (NumberUtils.isParsable(splitInst[1])) {
                    i++;
                    continue;
                }
                registers.put(splitInst[1], registers.get(splitInst[1]) + 1);
                i++;
            } else if (splitInst[0].equals("dec")) {
                if (NumberUtils.isParsable(splitInst[1])) {
                    i++;
                    continue;
                }
                registers.put(splitInst[1], registers.get(splitInst[1]) - 1);
                i++;
            } else if (splitInst[0].equals("jnz")){
                if (NumberUtils.isParsable(splitInst[1])) {
                    if (Integer.parseInt(splitInst[1]) != 0) {
                        if (NumberUtils.isParsable(splitInst[2])) {
                            i += Integer.parseInt(splitInst[2]);
                        } else {
                            i += registers.get(splitInst[2]);
                        }
                    } else {
                        i++;
                    }
                } else {
                    if (registers.get(splitInst[1]) != 0) {
                        if (NumberUtils.isParsable(splitInst[2])) {
                            i += Integer.parseInt(splitInst[2]);
                        } else {
                            i += registers.get(splitInst[2]);
                        }
                    } else {
                        i++;
                    }
                }
            } else {
                int toggleInstructionIndex;
                if (NumberUtils.isParsable(splitInst[1])) {
                    toggleInstructionIndex = i + Integer.parseInt(splitInst[1]);
                } else {
                    toggleInstructionIndex = i + registers.get(splitInst[1]);
                }

                if (toggleInstructionIndex >= instructions.size()) {
                    i++;
                    continue;
                }

                String toggleInstruction = instructions.get(toggleInstructionIndex);

                String[] instructionSplit = toggleInstruction.split(" ");
                if (instructionSplit.length <= 2) {
                    // one argument
                    if (instructionSplit[0].equals("inc")) {
                        instructionSplit[0] = "dec";
                    } else {
                        instructionSplit[0] = "inc";
                    }
                } else {
                    if (instructionSplit[0].equals("jnz")) {
                        instructionSplit[0] = "cpy";
                    } else {
                        instructionSplit[0] = "jnz";
                    }
                }

                instructions.set(toggleInstructionIndex, StringUtils.join(instructionSplit, " "));
                i++;
            }
        }

        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            System.out.println("Register " + entry.getKey() + " holds: " + entry.getValue());
        }
    }

    /**
     * Trying a new approach here!
     * @param instructions
     */
    private void cutInstructions(List<String> instructions) {
        for (String instruction: instructions) {
            String[] instructionCut = instruction.split(" ");
            instructionsCut.add(instructionCut);
        }
    }

    private void executeCutInstructions() {
        for (int i = 0; i < instructionsCut.size(); ) {
            String[] instruction = instructionsCut.get(i);
            if (instruction[0].equals("cpy")) {
                copy(instruction);
            }
        }
    }

    private void inc (String[] instruction) {

    }

    private void dec (String[] instruction) {

    }

    private void jump (String[] instruction) {

    }

    private void copy (String[] instruction) {

    }

    private void toggle (String[] instruction) {

    }
}
