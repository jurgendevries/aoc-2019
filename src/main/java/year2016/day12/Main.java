package year2016.day12;

import org.apache.commons.lang3.StringUtils;
import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "day12-input.txt";
    private static Map<String, Integer> registers;
    private static List<String> instructions;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

        main.prepare();


        main.part1();
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
        registers.put("a", 0);
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
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);

        executeInstructions();
    }

    private void executeInstructions () throws IOException {
        for (int i = 0; i < instructions.size();) {
            String instruction = instructions.get(i);
            String[] splitInst = instruction.split(" ");
            if (splitInst[0].equals("cpy")) {
                if (StringUtils.isNumeric(splitInst[1])) {
                    registers.put(splitInst[2], Integer.parseInt(splitInst[1]));
                } else {
                    registers.put(splitInst[2], registers.get(splitInst[1]));
                }
                i++;
            } else if (splitInst[0].equals("inc")) {
                registers.put(splitInst[1], registers.get(splitInst[1]) + 1);
                i++;
            } else if (splitInst[0].equals("dec")) {
                registers.put(splitInst[1], registers.get(splitInst[1]) - 1);
                i++;
            } else {
                if (StringUtils.isNumeric(splitInst[1])) {
                    if (Integer.parseInt(splitInst[1]) != 0) {
                        i += Integer.parseInt(splitInst[2]);
                    } else {
                        i++;
                    }
                } else {
                    if (registers.get(splitInst[1]) != 0) {
                        i += Integer.parseInt(splitInst[2]);
                    } else {
                        i++;
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            System.out.println("Register " + entry.getKey() + " holds: " + entry.getValue());
        }
    }
}
