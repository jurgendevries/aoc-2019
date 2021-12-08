package year2021.day8;

import base.Base;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Base {
    private static final String INPUT = "2021/day8-input.txt";
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
        int total = 0;
        for(String instruction: instructions) {
            String pattern = instruction.split("\\|")[0].trim();
            String output = instruction.split("\\|")[1].trim();

            for (String digit : output.split(" ")) {
                if (digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7) {
                    total++;
                }
            }
        }

        System.out.println(total);
    }

    @Override
    public void part2() throws IOException {
        int total = 0;
        for(String instruction: instructions) {
            List<String> signalPatterns =  Arrays.stream(instruction.split("\\|")[0].trim().split(" ")).collect(Collectors.toList());
            String output = instruction.split("\\|")[1].trim();

            Map<Integer, String> signalMap = new HashMap<>();

            int index = 0;
            while(signalPatterns.size() > 0) {
                String pattern = signalPatterns.get(index++);
                if (deductPattern(pattern, signalMap)) {
                    signalPatterns.remove(pattern);
                    index = 0;
                }
            }

            StringBuilder code = new StringBuilder();
            for (String o : output.split(" ")) {
                String alphaCode = sortString(o);
                code.append(signalMap.entrySet().stream().filter(entry -> alphaCode.equals(entry.getValue())).map(Map.Entry::getKey).findFirst().get());
            }
            total += Integer.parseInt(code.toString());
        }

        System.out.println(total);
    }

    private boolean deductPattern(String pattern, Map<Integer, String> signalMap) {
        boolean patternFound = false;
        switch (pattern.length()) {
            case 2:
                patternFound = true;
                signalMap.put(1, sortString(pattern));
                break;
            case 3:
                patternFound = true;
                signalMap.put(7, sortString(pattern));
                break;
            case 4:
                patternFound = true;
                signalMap.put(4, sortString(pattern));
                break;
            case 7:
                patternFound = true;
                signalMap.put(8, sortString(pattern));
                break;
            case 5:
                if (signalMap.get(1) != null) {
                    if (!missingAWire(signalMap.get(1), pattern)) {
                        signalMap.put(3, sortString(pattern));
                        patternFound = true;
                    } else {
                        if (signalMap.get(4) != null) {
                            // 2/5
                            StringBuilder sb = new StringBuilder();
                            for(char s : signalMap.get(4).toCharArray()) {
                                if (!signalMap.get(1).contains(Character.toString(s))) {
                                    sb.append(s);
                                }
                            }
                            if(!missingAWire(sb.toString(), pattern)) {
                                signalMap.put(5, sortString(pattern));
                                patternFound = true;
                            } else {
                                signalMap.put(2, sortString(pattern));
                                patternFound = true;
                            }
                        }
                    }
                }
                break;
            case 6:
                if (signalMap.get(1) != null) {
                    if (missingAWire(signalMap.get(1), pattern)) {
                        signalMap.put(6, sortString(pattern));
                        patternFound = true;
                    }

                }
                if (signalMap.get(4) != null ) {
                    if (!missingAWire(signalMap.get(4), pattern)) {
                        signalMap.put(9, sortString(pattern));
                        patternFound = true;
                    }
                }
                if (signalMap.get(6) != null && signalMap.get(9) != null) {
                    signalMap.put(0, sortString(pattern));
                    patternFound = true;
                }
            default:
                break;
        }
        return patternFound;
    }

    private boolean missingAWire(String knownSignal, String pattern) {
        boolean missingAWire = false;
        for (char s : knownSignal.toCharArray()) {
            if (pattern.indexOf(s) == -1) {
                missingAWire = true;
                break;
            }
        }
        return missingAWire;
    }

    private String sortString(String str)
    {
        char characterArray[] = str.toCharArray();
        Arrays.sort(characterArray);
        return new String(characterArray);
    }
}
