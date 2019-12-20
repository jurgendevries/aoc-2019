package year2016.day1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
     * R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
     * R5, L5, R5, R3 leaves you 12 blocks away.
     */
    private static final String INPUT = "R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3";
    //private static final String INPUT = "R8, R4, R4, R8";
    public static void main(String[] args) {
        String[] inputSplitted = INPUT.split(",");
        int x = 0;
        int y = 0;
        int direction = 0; // 0 == N, 1 == E, 2 == S, 3 == W

        List<String> visitedPositions= new ArrayList<>();
        visitedPositions.add("0,0");
        boolean visitedTwice = false;
        for (String instruction : inputSplitted) {
            instruction = instruction.trim();
            int distance = Integer.parseInt(instruction.substring(1));
            if (instruction.startsWith("R")) {
                direction = direction + 1 > 3 ? 0 : direction + 1;
            } else {
                direction = direction - 1 < 0 ? 3 : direction - 1;
            }

            for (int i = 1; i <= distance; i++) {
                switch (direction) {
                    case 0:
                        y += 1;
                        break;
                    case 1:
                        x += 1;
                        break;
                    case 2:
                        y -= 1;
                        break;
                    case 3:
                        x -= 1;
                        break;
                }

                if (visitedPositions.contains(x + "," + y)) {
                    visitedTwice = true;
                    break;
                } else {
                    visitedPositions.add(x + "," + y);
                }
            }
            if (visitedTwice) {
                break;
            }
        }


        System.out.println("endpos = [" + x + ", " + y + "]" + " distance = [" + (Math.abs(x) + Math.abs(y)) + "]");
    }
}
