package year2020.day19;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2020/day19-input.txt";
    private static List<String> instructions;
    private static Map<Integer, String> ruleMap = new HashMap<>();
    private static Map<Integer, List<String>> writtenOutRules = new HashMap<>();

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
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                break;
            }
            // split rule
            int ruleId = Integer.parseInt(instruction.split(":")[0]);
            String format = instruction.split(":")[1].trim().replace("\"", "");

            ruleMap.put(ruleId, format);
        }

        while (writtenOutRules.size() < ruleMap.size()) {
            for (Map.Entry<Integer, String> entry : ruleMap.entrySet()) {
                if (!writtenOutRules.containsKey(entry.getKey())) {
                    if (entry.getValue().matches("^[a-b]$")) {
                        List<String> possibleValues = new ArrayList<>();
                        possibleValues.add(entry.getValue());
                        writtenOutRules.put(entry.getKey(), possibleValues);
                    } else {
                        // make a list of needed rules
                        String[] val = entry.getValue().split(" ");
                        List<Integer> list = new ArrayList<>();
                        for (String v : val) {
                            try {
                                int intVal = Integer.parseInt(v);
                                list.add(intVal);
                            } catch (NumberFormatException e) {
                                // do nothing here
                            }
                        }

                        boolean allKnown = true;
                        for (int i : list) {
                            if (!writtenOutRules.containsKey(i)) {
                                allKnown = false;
                                break;
                            }
                        }

                        if (allKnown) {
                            List<String> possibleValues = new ArrayList<>();

                            // write out new values and to written out map... write out possible values

                            // split on |
                            String[] possibilities = entry.getValue().split("\\|");

                            for (String possibility : possibilities) {
                                String[] rules = possibility.trim().split(" ");
                                List<String> newRules = new ArrayList<>();
                                StringBuilder sbRule = new StringBuilder();

                                for (int i = 0; i < rules.length; i++) {
                                    if (newRules.isEmpty()) {
                                        for (String parentRule : writtenOutRules.get(Integer.parseInt(rules[i]))) {
                                            newRules.add(parentRule);
                                        }
                                    } else {
                                        List<String> subRules = new ArrayList<>();

                                        for (String nRule : newRules) {
                                            for (String parent : writtenOutRules.get(Integer.parseInt(rules[i]))) {
                                                String combinedRule = nRule + parent;
                                                subRules.add(combinedRule);
                                            }
                                        }

                                        // newRule = subRules
                                        newRules = subRules;
                                    }
                                }

                                for (String r : newRules) {
                                    possibleValues.add(r);
                                }
                            }

                            writtenOutRules.put(entry.getKey(), possibleValues);
                        }
                    }
                }
            }
        }

        // validate values
        boolean startValidating = false;
        int total = 0;
        for (String instruction : instructions) {
            if (startValidating) {
                for (String rule : writtenOutRules.get(0)) {
                    if (instruction.equals(rule)) {
                        total++;
                        break;
                    }
                }
            }

            if ("".equals(instruction)) {
                startValidating = true;
            }
        }

        System.out.println(total);


        // validate values
        startValidating = false;
        int total2 = 0;
        for (String instruction : instructions) {
            if (startValidating) {
                for (String rule : writtenOutRules.get(0)) {
                    if (instruction.equals(rule) || validatePart2(instruction)) {
                        total++;
                        break;
                    }
                }
            }

            if ("".equals(instruction)) {
                startValidating = true;
            }
        }



        System.out.println(total);
    }

    private boolean validatePart2(String instruction) {
        for (String rule : writtenOutRules.get(0)) {

        }
        return false;
    }

    @Override
    public void part2() throws IOException {

    }
}
