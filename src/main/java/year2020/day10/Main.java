package year2020.day10;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2020/day10-input.txt";
    private static int currentJoltage = 0;
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
        List<String> instructionsCopy = new ArrayList<>(instructions);
        int numberOfOneDiff = 0;
        int numberOfThreeDiff = 0;
        while (!instructionsCopy.isEmpty()) {
            for (int diff = 1; diff <= 3; diff++) {
                if (instructionsCopy.contains(String.valueOf(currentJoltage + diff))) {
                    if (diff == 1) {
                        numberOfOneDiff++;
                    } else if (diff == 3) {
                        numberOfThreeDiff++;
                    }
                    currentJoltage += diff;
                    instructionsCopy.remove(String.valueOf(currentJoltage));
                    break;
                }
            }
        }

        // add device adapter (3)
        currentJoltage += 3;
        numberOfThreeDiff++;

        System.out.println("currentJoltage: " + currentJoltage);
        System.out.println("1 diff: " + numberOfOneDiff);
        System.out.println("3 diff: " + numberOfThreeDiff);
        System.out.println("1 * 3: " + numberOfOneDiff * numberOfThreeDiff);

    }

    @Override
    public void part2() throws IOException {
        // sort into list
        
        int[] values = new int[instructions.size()];

        for (int i = 0; i < instructions.size(); i++) {
            values[i] = Integer.parseInt(instructions.get(i));
        }

        Arrays.sort(values);

        System.out.println(countPossibilities(currentJoltage - 3, values));

    }

    private long countPossibilities(int n, int[] vals) {
        Map<Integer, Long> countMap = new HashMap<>();
        countMap.put(0, 0L);
        countMap.put(1, 1L);
        countMap.put(2, 2L);
        countMap.put(3, 4L);

        for (int i = 0; i <= n; i++) {
            int arrayIdx = Arrays.binarySearch(vals, i);
            if (arrayIdx >= 0) {
                int val = vals[arrayIdx];
                if (countMap.get(val) == null) {
                    long valMin1 = countMap.get(val - 1) != null ? countMap.get(val - 1) : 0L;
                    long valMin2 = countMap.get(val - 2) != null ? countMap.get(val - 2) : 0L;
                    long valMin3 = countMap.get(val - 3) != null ? countMap.get(val - 3) : 0L;
                    countMap.put(i, valMin1 + valMin2 + valMin3);
                }
            }
        }

        return countMap.get(n);
    }
}
