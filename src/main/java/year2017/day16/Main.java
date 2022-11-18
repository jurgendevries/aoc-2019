package year2017.day16;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2017/day16-input.txt";
    private static List<String> programLine;
    private static Map<String, Long> situations;


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
    }

    private void initiatePrograms() {
        programLine = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p");
    }

    @Override
    public void part1() throws IOException {
        situations = new HashMap<>();
        initiatePrograms();
        String[] danceMoves = instructions.get(0).split(",");
        boolean seenBefore = false;
        long steps = 0;
        boolean first = true;
        do {
            for (String danceMove : danceMoves) {
                move(danceMove);
            }

            StringBuilder result = new StringBuilder();
            for (String p : programLine) {
                result.append(p);
            }
            if (first) {
                first = false;
                System.out.println("PART1: " + result);
            }

            // check known positions
            if (situations.containsKey(result.toString())) {
                seenBefore = true;
            } else {
                situations.put(result.toString(), steps);
                steps++;
            }

        } while (!seenBefore);
        long turns = 1000_000_000;
        long remainder = turns % steps;

        System.out.println("PART2: " + situations.entrySet().stream().filter(entry -> entry.getValue() == (remainder - 1)).findFirst().get().getKey());


    }

    private void move(String move) {
        String type = String.valueOf(move.charAt(0));
        switch (type) {
            case "s":
                spin(move);
                break;
            case "x":
                exchange(move);
                break;
            case "p":
                partner(move);
                break;
            default:
                throw new IllegalArgumentException("unknown move");
        }
    }

    @Override
    public void part2() throws IOException {

    }

    private void spin(String move) {
        String remainder = move.substring(1);
        int numberOfProgramsToMove = Integer.parseInt(remainder);
        Collections.rotate(programLine, numberOfProgramsToMove);
    }

    private void exchange(String move) {
        String remainder = move.substring(1);
        int a = Integer.parseInt(remainder.split("/")[0]);
        int b = Integer.parseInt(remainder.split("/")[1]);
        exchange(a, b);
    }

    private void exchange(int a, int b) {
        String tempA = programLine.get(a);
        programLine.set(a, programLine.get(b));
        programLine.set(b, tempA);
    }

    private void partner(String move) {
        String remainder = move.substring(1);
        String a = remainder.split("/")[0];
        String b = remainder.split("/")[1];
        int posA = programLine.indexOf(a);
        int posB = programLine.indexOf(b);
        exchange(posA, posB);
    }
}
