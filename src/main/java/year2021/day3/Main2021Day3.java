package year2021.day3;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main2021Day3 extends Base {

    private static final String INPUT = "2021/day3-input.txt";

    private static List<String> instructions;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main2021Day3 main = new Main2021Day3();
        main.mainMethod(INPUT);
        main.prepare();
        //main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();

        int loopSize = instructions.get(0).length();

        for (int i = 0; i < loopSize; i++) {
            int ones = 0;
            int zeros = 0;

            for (String instruction : instructions) {
                if (instruction.charAt(i) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            gammaRate.append(zeros > ones ? 0 : 1);
            epsilonRate.append(zeros < ones ? 0 : 1);
        }

        System.out.println(gammaRate.toString() + " : " + epsilonRate.toString());
        int gamma = Integer.parseInt(gammaRate.toString(), 2);
        int epsilon = Integer.parseInt(epsilonRate.toString(), 2);
        System.out.println("gamma: " + gamma + ", epsilon: " + epsilon + ", total: " + (gamma * epsilon));
    }

    @Override
    public void part2() throws IOException {
        List<String> copy = new ArrayList<>(instructions);

        String oxy = determineValue(copy, 0, "ox");
        String co2V = determineValue(copy, 0, "co2");
        int oxyInt = Integer.parseInt(oxy, 2);
        int co2VInt = Integer.parseInt(co2V, 2);
        System.out.println(oxy + " : " + co2V);
        System.out.println(oxyInt + " : " + co2VInt + ", " + (oxyInt * co2VInt));

    }

    private String determineValue(List<String> list, int currentIndex, String type) {
        int ones = 0;
        int zeros = 0;


        for (String instruction : list) {
            if (instruction.charAt(currentIndex) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }

        final int index = currentIndex;
        if ((type.equals("ox") && zeros > ones) || (!type.equals("ox") && zeros <= ones)) {
            list = list.stream().filter(c -> c.charAt(index) == '0').collect(Collectors.toList());
        } else {
            list = list.stream().filter(c -> c.charAt(index) == '1').collect(Collectors.toList());
        }

        if (list.size() > 1) {
            return determineValue(list, ++currentIndex, type);
        }

        return list.get(0);
    }
}
