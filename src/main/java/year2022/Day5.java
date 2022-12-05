package year2022;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Day5 extends Base {
    private static final String INPUT = "2022/day5-input.txt";
    private int mainCount = 0;

    public static void main(String[] args) throws IOException {
        Day5 main = new Day5();
        main.mainMethod(INPUT);
        main.prepareInput();
        System.out.println("PART1:");
        long start = System.currentTimeMillis();
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    @Override
    public void part1() throws IOException {
        System.out.println(arrangeStacks(true));
    }

    @Override
    public void part2() throws IOException {
        System.out.println(arrangeStacks(false));
    }

    private String arrangeStacks(boolean part1) {
        Map<Integer, Stack<String>> stacks = getStacks();
        int nextCount = mainCount;
        nextCount++;
        for (; nextCount < instructions.size(); nextCount++) {
            int amount = Integer.parseInt(instructions.get(nextCount).split(" ")[1]);
            int from = Integer.parseInt(instructions.get(nextCount).split(" ")[3]);
            int to = Integer.parseInt(instructions.get(nextCount).split(" ")[5]);

            if (part1) {
                for (; amount > 0; amount--) {
                    stacks.get(to).push(stacks.get(from).pop());
                }
            } else {
                List<String> tempStack = new ArrayList<>();
                for (; amount > 0; amount--) {
                    tempStack.add(stacks.get(from).pop());
                }
                for (int i = tempStack.size() - 1; i >= 0; i--) {
                    String pack = tempStack.get(i);
                    stacks.get(to).push(pack);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Stack<String>> entry : stacks.entrySet()) {
            sb.append(entry.getValue().pop());
        }
        return sb.toString();
    }

    private Map<Integer, Stack<String>> getStacks() {
        Map<Integer, Stack<String>> stacks = new HashMap<>();
        int count = 0;
        String line = instructions.get(count);
        while (!"".equals(line)) {
            line = instructions.get(++count);
        }

        mainCount = count;

        for (; count >= 0; count--) {
            line = instructions.get(count);
            String[] stackString = line.split("");
            for (int i = 0; i < stackString.length; i+=4) {
                if ("[".equals(stackString[i])) {
                    int index = i == 0 ? 1 : i / 4 + 1;
                    String pack = stackString[i+1];
                    if (stacks.get(index) == null) {
                        Stack<String> stack = new Stack<>();
                        stack.push(pack);
                        stacks.put(index, stack);
                    } else {
                        stacks.get(index).push(pack);
                        stacks.put(index, stacks.get(index));
                    }
                }
            }
        }

        return stacks;
    }
}
