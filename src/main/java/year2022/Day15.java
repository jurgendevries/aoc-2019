package year2022;

import base.Base;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

public class Day15 extends Base {
    private static final String INPUT = "2022/day15-input.txt";
    private static final int ROW = 2000000;
    private static final int MAX = 4000000;


    private static List<Sensor> sensors = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Day15 main = new Day15();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        main.readSensors();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void readSensors() {
        for (String instruction : instructions) {
            Sensor s = new Sensor();
            s.c = new Coord(
                    Integer.parseInt(instruction.split(" ")[2].split("=")[1].replace(",", "")),
                    Integer.parseInt(instruction.split(" ")[3].split("=")[1].replace(":", ""))
            );
            Beacon b = new Beacon();
            b.c = new Coord(
                    Integer.parseInt(instruction.split(" ")[8].split("=")[1].replace(",", "")),
                    Integer.parseInt(instruction.split(" ")[9].split("=")[1])
            );
            s.b = b;
            s.reach = Math.abs(s.c.x - b.c.x) + Math.abs(s.c.y - b.c.y);
            sensors.add(s);
        }
    }

    @Override
    public void part1() throws IOException {
        Set<Integer> xTotal = new HashSet<>();
        for (Sensor s : sensors) {
            int rowReach = s.reach - Math.abs(s.c.y - ROW);



            for (int i = -rowReach; i <= rowReach; i++) {
                s.xReach.add(s.c.x + i);
            }
            xTotal.addAll(s.xReach);
        }

        for (Sensor s : sensors) {
            if (s.b.c.y == ROW) {
                xTotal.remove(s.b.c.x);
            }
        }
        System.out.println(xTotal.size());
    }

    @Override
    public void part2() throws IOException {
        // run along the edge of each scanner
        Set<String> borderCoords = new HashSet<>();
        for (Sensor s : sensors) {
            for(int y = -s.reach; y < s.reach; y++) {
                int newY = s.c.y + y;
                if (newY >= 0 && newY <= MAX) {
                    int borderLeft = s.c.x - s.reach + Math.abs(y) - 1;
                    int borderRight = s.c.x + s.reach - Math.abs(y) + 1;
                    if (y == -s.reach) {
                        borderCoords.add(s.c.x + "," + (newY - 1));
                    }
                    if (y == s.reach) {
                        borderCoords.add(s.c.x + "," + (newY + 1));
                    }
                    if (borderLeft >= 0 && borderLeft <= MAX) {
                        borderCoords.add(borderLeft + "," + newY);
                    }
                    if (borderRight >= 0 && borderRight <= MAX) {
                        borderCoords.add(borderRight + "," + newY);
                    }
                }
            }
        }

        for (String c : borderCoords) {
            boolean matchFound = false;
            for (Sensor s : sensors) {
                if (Math.abs(s.c.x - Integer.parseInt(c.split(",")[0])) + Math.abs(s.c.y - Integer.parseInt(c.split(",")[1])) <= s.reach) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                System.out.println("FOUND: " + c);

                int x = Integer.parseInt(c.split(",")[0]);
                int y = Integer.parseInt(c.split(",")[1]);
                // 3172756,2767556
                BigInteger b = BigInteger.valueOf(MAX);
                b = b.multiply(BigInteger.valueOf(x));
                b = b.add(BigInteger.valueOf(y));
                System.out.println(b);

                break;
            }
        }


    }

    class Coord implements Serializable {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Coord) obj).x && this.y == ((Coord) obj).y;
        }
    }

    class Sensor {
        private Coord c;
        private int reach;
        private Beacon b;
        private List<Integer> xReach = new ArrayList<>();
    }

    class Beacon {
        private Coord c;

    }
}
