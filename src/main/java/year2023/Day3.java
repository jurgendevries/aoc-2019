package year2023;

import base.Base;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 extends Base {
    private static final String INPUT = "2023/day3-input.txt";
    private Map<String, List<Integer>> gears = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Day3 main = new Day3();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();

        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("PREPARATION duration: " + duration);
    }
    @Override
    public void part1() throws IOException {
        int total = 0;
        for (int y = 0; y < instructions.size(); y++) {
            int numEnd = -1;
            String[] instruction = instructions.get(y).split("");
            for (int x = 0; x < instructions.get(0).length(); x++) {
                if (x > numEnd) {
                    if (NumberUtils.isCreatable(instruction[x])) {
                        String num = instruction[x];
                        for (int z = x + 1; z < instruction.length; z++) {
                            if (NumberUtils.isCreatable(instruction[z])) {
                                num += instruction[z];
                            } else {
                                numEnd = z - 1;
                                break;
                            }
                        }
                        if (numEnd < x) {
                            numEnd = x;
                        }
                        boolean valid = isValid(x, numEnd, y, Integer.parseInt(num));
                        System.out.println(num + ", " + numEnd + ", " + valid);
                        if (valid) {
                            total += Integer.parseInt(num);
                        }
                    }
                }
            }
        }
        System.out.println(total);
    }

    private boolean isValid(int startX, int endX, int y, int value) {
        boolean valid = false;
        Set<String> gearsFound = new HashSet<>();
        for (int x = startX; x <= endX; x++) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (y + i < 0 || y + i >= instructions.size() || x + j < 0 || x + j >= instructions.get(0).length() || (i == 0 && j == 0)) {
                        continue;
                    }
                    String[] instruction = instructions.get(y + i).split("");
                    if (!NumberUtils.isCreatable(instruction[x + j]) && !instruction[x + j].equals(".")) {
                        valid = true;
                        if (instruction[x+j].equals("*")) {
                            gearsFound.add((x+j) + "-" + (y+i));
                        }
                    }
                }
            }
        }
        for (String gear : gearsFound) {
            gears.computeIfAbsent(gear, v -> new ArrayList<>()).add(value);
        }
        return valid;
    }

    @Override
    public void part2() throws IOException {
        long total = 0;
        List<List<Integer>> validGears = gears.values().stream().filter(v -> v.size() == 2).collect(Collectors.toList());
        for (List<Integer> g : validGears) {
            total += g.get(0) * g.get(1);
        }
        System.out.println(total);
    }
}
