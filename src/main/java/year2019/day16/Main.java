package year2019.day16;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019/day16-test.txt";
    private static final int NUMBER_OF_PHASES = 100;

    private static String inputString = "";
    List<Integer> basePattern;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

//        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        inputString = input.readLine();
        int[] inputArr = Arrays.asList(inputString.split("")).stream().mapToInt(Integer::parseInt).toArray();
        basePattern = new ArrayList<>();
        basePattern.add(0);
        basePattern.add(1);
        basePattern.add(0);
        basePattern.add(-1);

        List<Integer> pattern = getPattern(1);
        int counter = 1;
        int[] result = runPhase(inputArr, pattern);

        while (counter < NUMBER_OF_PHASES) {
            result = runPhase(result, pattern);
            counter++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i);
        }

        System.out.println(sb);

    }

    private List<Integer> getPattern(int run) {
        List<Integer> pattern = new ArrayList<>();
        for (int x = 0; x < basePattern.size(); x++) {
            for (int i = 0; i < run; i++) {
                pattern.add(basePattern.get(x));
            }
        }

        return pattern;
    }

    private int[] runPhase(int[] inputArr, List<Integer> pattern) {
        int[] resultArr = new int[inputArr.length];

        for (int i = 0; i < inputArr.length ; i++) {

            int res = 0;
            for (int k = 0; k < i; k++) {
                pattern.add(pattern.remove(0));
            }
            for (int j = i; j < inputArr.length ; j++) {
                pattern.add(pattern.remove(0));
                if (pattern.get(0) != 0) {
                    int p = pattern.get(0);
                    int value = inputArr[j];
                    res += value * p;
                }
            }
            res = Math.abs(res % 10);
            resultArr[i] = res;

            pattern = getPattern(i + 2);
        }

        return resultArr;
    }

    @Override
    public void part2() throws IOException {
        inputString = input.readLine();
        String finalMessage = "";
        for (int i = 0; i < 10000; i++) {
            finalMessage += inputString;
        }

        int[] inputArr = Arrays.asList(finalMessage.split("")).stream().mapToInt(Integer::parseInt).toArray();

        int[] offsetArr = Arrays.copyOfRange(inputArr, 0, 7);
        int offset = 0;
        StringBuilder sboffset = new StringBuilder();
        for (int i : offsetArr) {
            sboffset.append(i);
        }
        offset = Integer.parseInt(sboffset.toString());
        basePattern = new ArrayList<>();
        basePattern.add(0);
        basePattern.add(1);
        basePattern.add(0);
        basePattern.add(-1);

        List<Integer> pattern = getPattern(1);
        int counter = 1;
        int[] result = runPhase(inputArr, pattern);

        while (counter < NUMBER_OF_PHASES) {
            result = runPhase(result, pattern);
            counter++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i);
        }

        System.out.println(sb.substring(offset, offset + 8));
    }
}
