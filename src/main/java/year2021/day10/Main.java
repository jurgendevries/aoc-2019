package year2021.day10;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2021/day10-input.txt";
    private static List<String> instructions;
    private static Map<Character, Character> knownValues = new HashMap<>();

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
        knownValues.put('{', '}');
        knownValues.put('(', ')');
        knownValues.put('[', ']');
        knownValues.put('<', '>');
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
        long score = 0;
        for (String instruction : instructions) {
            CheckedInstruction checkedInstruction = checkInstruction(instruction);

            if (checkedInstruction.getCorruptCharacter() != null) {
                score += getScore(checkedInstruction.getCorruptCharacter());
            }
        }
        System.out.println(score);
    }

    private long getScore(Character corruptChar) {
        long score = 0;
        switch (corruptChar) {
            case ')':
                score = 3;
                break;
            case ']':
                score = 57;
            break;
            case '}':
                score = 1197;
            break;
            case '>':
                score = 25137;
            break;
        }
        return score;
    }

    private long getScore(List<Character> remainingCharacters) {
        long score = 0;
        for (int i = remainingCharacters.size() - 1; i >= 0; i--) {
            char c = remainingCharacters.get(i);
            switch (c) {
                case ')':
                    score = score * 5 + 1;
                    break;
                case ']':
                    score = score * 5 + 2;
                    break;
                case '}':
                    score = score * 5 + 3;
                    break;
                case '>':
                    score = score * 5 + 4;
                    break;
            }
        }
        return score;
    }

    @Override
    public void part2() throws IOException {
        List<Long> scores = new ArrayList<>();
        for (String instruction : instructions) {
            CheckedInstruction checkedInstruction = checkInstruction(instruction);

            if (checkedInstruction.getCorruptCharacter() == null) {
                scores.add(getScore(checkedInstruction.getRemainingCharacters()));
            }
        }
        Collections.sort(scores);
        int middle = (int) Math.floor((double)scores.size() / 2);
        System.out.println(scores.get(middle));
    }

    private CheckedInstruction checkInstruction(String instruction) {
        List<Character> foundChars = new ArrayList<>();
        for (char c : instruction.toCharArray()) {
            if (knownValues.containsKey(c)) {
                foundChars.add(knownValues.get(c));
            } else {
                Character expectedChar = foundChars.remove(foundChars.size() - 1);
                if (expectedChar != c) {
                    return new CheckedInstruction(c, foundChars);
                }
            }
        }
        return new CheckedInstruction(null, foundChars);
    }

    class CheckedInstruction {
        private Character corruptCharacter;
        private List<Character> remainingCharacters;

        public CheckedInstruction(Character corruptCharacter, List<Character> remainingCharacters) {
            this.corruptCharacter = corruptCharacter;
            this.remainingCharacters = remainingCharacters;
        }

        public Character getCorruptCharacter() {
            return corruptCharacter;
        }

        public void setCorruptCharacter(Character corruptCharacter) {
            this.corruptCharacter = corruptCharacter;
        }

        public List<Character> getRemainingCharacters() {
            return remainingCharacters;
        }

        public void setRemainingCharacters(List<Character> remainingCharacters) {
            this.remainingCharacters = remainingCharacters;
        }
    }
}
