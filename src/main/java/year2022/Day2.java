package year2022;

import base.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day2 extends Base {
    private static final String INPUT = "2022/day2-input.txt";

    public static void main(String[] args) throws IOException {
        Day2 main = new Day2();
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
        long score = 0;
        Map<String, Integer> moves = new HashMap<>();
        moves.put("A", 1);
        moves.put("B", 2);
        moves.put("C", 3);
        moves.put("X", 1);
        moves.put("Y", 2);
        moves.put("Z", 3);
        for (String instruction : instructions) {
            int player1Move = moves.get(instruction.split(" ")[0]);
            int player2Move = moves.get(instruction.split(" ")[1]);

            int result = player1Move - player2Move;
            int roundScore = result == 0 ? 3 : result == -1 || result == 2 ? 6 : 0;
            score += roundScore + player2Move;
        }

        System.out.println(score);
    }

    @Override
    public void part2() throws IOException {
        long score = 0;
        Map<String, Integer> moves = new HashMap<>();
        moves.put("A", 1);
        moves.put("B", 2);
        moves.put("C", 3);

        for (String instruction : instructions) {
            int p1 = moves.get(instruction.split(" ")[0]);
            String neededEnd = instruction.split(" ")[1];

            if ("Y".equals(neededEnd)) {
                // draw
                score += 3 + p1;
            } else {
                if ("X".equals(neededEnd)) {
                    // lose (-1)
                    int myMove = p1 - 1 > 0 ? p1 -1 : 3;
                    score += 0 + myMove;
                } else {
                    // win (+1)
                    int myMove = p1 + 1 <= 3 ? p1 + 1 : 1;
                    score += 6 + myMove;
                }
            }
        }

        System.out.println(score);
    }
}
