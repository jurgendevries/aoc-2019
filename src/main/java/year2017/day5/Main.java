package year2017.day5;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {

    private static final String INPUT = "2017/day5-input.txt";


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
        long cursor = 0;
        long steps = 0;

        List<String> copyInstructions = new ArrayList<>(instructions);

        while (cursor < copyInstructions.size()) {
            int intInstruction = Integer.parseInt(copyInstructions.get(Math.toIntExact(cursor)));
            long tempCursor = cursor + intInstruction;
            copyInstructions.set(Math.toIntExact(cursor), String.valueOf(++intInstruction));
            cursor = tempCursor;
            steps++;
        }

        System.out.println(steps);
    }

    @Override
    public void part2() throws IOException {
        long cursor = 0;
        long steps = 0;

        List<String> copyInstructions = new ArrayList<>(instructions);

        while (cursor < copyInstructions.size()) {
            int intInstruction = Integer.parseInt(copyInstructions.get(Math.toIntExact(cursor)));
            long tempCursor = cursor + intInstruction;
            copyInstructions.set(Math.toIntExact(cursor), String.valueOf(intInstruction >= 3 ? --intInstruction : ++intInstruction));
            cursor = tempCursor;
            steps++;
        }

        System.out.println(steps);
    }
}
