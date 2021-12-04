package year2017.day4;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {

    private static final String INPUT = "2017/day4-input.txt";


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
        int numberOfValidPassPhrases = 0;
        for (String rule : instructions) {
            if(isValidPassPhrase(rule, false)) {
                numberOfValidPassPhrases++;
            }
        }
        System.out.println(numberOfValidPassPhrases);
    }

    private boolean isValidPassPhrase(String rule, boolean sort) {
        Set<String> uniqueSet = new HashSet<>();
        String[] values = rule.split(" ");
        boolean valid = true;
        for (String value : values) {
            if (sort) {
                char tempArray[] = value.toCharArray();
                Arrays.sort(tempArray);
                value = new String(tempArray);
            }
            if (!uniqueSet.add(value)) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    @Override
    public void part2() throws IOException {
        int numberOfValidPassPhrases = 0;
        for (String rule : instructions) {
            if(isValidPassPhrase(rule, true)) {
                numberOfValidPassPhrases++;
            }
        }
        System.out.println(numberOfValidPassPhrases);
    }
}
