package year2022;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 extends Base {
    private static final String INPUT = "2022/day9-input.txt";

    public static void main(String[] args) throws IOException {
        Day9 main = new Day9();
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
        Set<String> visitedTail = new HashSet<>();
        List<Knot> knots = new ArrayList<>();
        knots.add(new Knot(0, 0));
        knots.add(new Knot(0, 0));
        for (String instruction : instructions) {
            move(instruction, knots, visitedTail);
        }

        System.out.println(visitedTail.size());
    }

    @Override
    public void part2() throws IOException {
        Set<String> visitedTail = new HashSet<>();
        // 10 knots
        List<Knot> knots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Knot knot = new Knot(0, 0);
            knots.add(knot);
        }

        for (String instruction : instructions) {
            move(instruction, knots, visitedTail);
        }

        System.out.println(visitedTail.size());
    }

    private void move(String instruction, List<Knot> knots, Set<String> visitedTail) {
        String dir = instruction.split(" ")[0];
        int dist = Integer.parseInt(instruction.split(" ")[1]);

        Knot head = knots.get(0);
        for (int i = 0; i < dist; i++) {
            switch (dir) {
                case "R":
                    head.x += 1;
                    break;
                case "L":
                    head.x -= 1;
                    break;
                case "D":
                    head.y += 1;
                    break;
                default:
                    head.y -= 1;
            }
            // move tails:
            for (int k = 0; k < knots.size() - 1; k++) {
                Knot newHead = knots.get(k);
                Knot newTail = knots.get(k+1);
                if (!isTouching(newHead, newTail)) {
                    int xDif = newHead.x - newTail.x;
                    int yDif = newHead.y - newTail.y;

                    if (xDif > 1 || xDif < -1) {
                        if (xDif > 1) {
                            newTail.x += 1;
                        }
                        if (xDif < -1) {
                            newTail.x -= 1;
                        }
                        if (yDif > 0) {
                            newTail.y += 1;
                        }
                        if (yDif < 0) {
                            newTail.y -= 1;
                        }
                    } else if (yDif > 1 || yDif < -1) {
                        if (yDif > 1) {
                            newTail.y += 1;
                        }
                        if (yDif < -1) {
                            newTail.y -= 1;
                        }
                        if (xDif > 0) {
                            newTail.x += 1;
                        }
                        if (xDif < 0) {
                            newTail.x -= 1;
                        }
                    }
                }
            }
            String loc = "[x,y] = [" + knots.get(knots.size() - 1).x + "," + knots.get(knots.size() - 1).y + "]";
//            System.out.println(instruction);
//            System.out.println(loc);
            visitedTail.add(loc);
        }
    }

    private boolean isTouching(Knot head, Knot tail) {
        return Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y -tail.y) <= 1;
    }

    class Knot {
        private int x;
        private int y;

        public Knot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

