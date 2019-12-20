package year2019.day3;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day3-input.txt";

    /**
     * 0: UP
     * 1: RIGHT
     * 2: DOWN
     * 3: LEFT
     */
    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static List<List<Coordinate>> wires = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
//        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        loopWireSteps();

        int closestMatch = findClosestMatch();

        System.out.println(closestMatch);
    }

    @Override
    public void part2() throws IOException {
        loopWireSteps();

        int shortestMatch = findShortesMatch();

        System.out.println(shortestMatch);
    }

    private void loopWireSteps() throws IOException {
        String line;
        int wire = 0;
        while((line=input.readLine()) != null) {
            String[] instructions = line.split(",");
            List<Coordinate> coordinates = new ArrayList<>();
            wires.add(coordinates);
            Coordinate current = new Coordinate(0,0, 0);
            coordinates.add(current);

            for (String instruction : instructions) {
                String dir = instruction.split("")[0];
                int distance = Integer.parseInt(instruction.replaceAll("\\D+",""));
                int[] direction;

                switch (dir) {
                    case "U":
                        direction = DIRECTIONS[0];
                        break;
                    case "R":
                        direction = DIRECTIONS[1];
                        break;
                    case "D":
                        direction = DIRECTIONS[2];
                        break;
                    default:
                        direction = DIRECTIONS[3];
                }

                for (int i = 0; i < distance; i++) {
                    current = new Coordinate(current.getX() + direction[0], current.getY() + direction[1], current.getDistanceTraveled() + 1);
                    coordinates.add(current);
                }
            }

            wire++;
        }
    }

    private int findClosestMatch() {
        Map<Coordinate, Integer> hits = findHits();

        int closestHit = Integer.MAX_VALUE;
        for (Map.Entry<Coordinate, Integer> hit : hits.entrySet()) {
            int hitDistance = Math.abs(hit.getKey().getX()) + Math.abs(hit.getKey().getY());
            if (hitDistance < closestHit) {
                closestHit = hitDistance;
            }
        }

        return closestHit;
    }

    private int findShortesMatch() {
        Map<Coordinate, Integer> hits = findHits();

        int shortestHit = Integer.MAX_VALUE;
        for (Map.Entry<Coordinate, Integer> hit : hits.entrySet()) {
            int hitRoute = hit.getValue();
            if (hitRoute < shortestHit) {
                shortestHit = hitRoute;
            }
        }
        return shortestHit;
    }

    private Map<Coordinate, Integer> findHits() {
        Map<Coordinate, Integer> hits = new HashMap<>();
        // start at 1 (0,0) is not interesting
        for (int i = 1; i < wires.get(0).size(); i++) {
            Coordinate wireCo1 = wires.get(0).get(i);
            for (int j = 1; j < wires.get(1).size(); j++) {
                Coordinate wireCo2 = wires.get(1).get(j);

                if (wireCo1.getX() == wireCo2.getX() && wireCo1.getY() == wireCo2.getY()) {
                    Optional<Map.Entry<Coordinate, Integer>> existingHit = hits.entrySet().stream().filter(c -> c.getKey().getX() == wireCo1.getX() && c.getKey().getY() == wireCo1.getY()).findFirst();

                    if (!existingHit.isPresent()) {
                        hits.put(wireCo1, wireCo1.getDistanceTraveled() + wireCo2.getDistanceTraveled());
                    }
                }
            }
        }

        return hits;
    }

    public class Coordinate {
        private int x;
        private int y;
        private int distanceTraveled;

        public Coordinate(int x, int y, int distanceTraveled) {
            this.x = x;
            this.y = y;
            this.distanceTraveled = distanceTraveled;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDistanceTraveled() {
            return distanceTraveled;
        }

        public void setDistanceTraveled(int distanceTraveled) {
            this.distanceTraveled = distanceTraveled;
        }
    }
}
