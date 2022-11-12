package year2021.day22;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2021/day22-input.txt";
    private static List<String> instructions;
    private static List<Cuboid> cuboids = new ArrayList<>();

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        for (String instruction: instructions) {
            String[] parts = instruction.split(",");
            boolean on = parts[0].split("x=")[0].trim().equals("on");
            int xMin = Integer.parseInt(parts[0].split("x=")[1].split("\\.\\.")[0]);
            int xMax = Integer.parseInt(parts[0].split("x=")[1].split("\\.\\.")[1]);
            int yMin = Integer.parseInt(parts[1].split("y=")[1].split("\\.\\.")[0]);
            int yMax = Integer.parseInt(parts[1].split("y=")[1].split("\\.\\.")[1]);
            int zMin = Integer.parseInt(parts[2].split("z=")[1].split("\\.\\.")[0]);
            int zMax = Integer.parseInt(parts[2].split("z=")[1].split("\\.\\.")[1]);
            Cube cube = new Cube(xMin, xMax, yMin, yMax, zMin, zMax);
            Cuboid cuboid = new Cuboid(on, cube);
            cuboids.add(cuboid);
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
        System.out.println(solve());
    }

    @Override
    public void part2() throws IOException {
        List<Cube> core = new ArrayList<>();
        for (Cuboid c : cuboids) {
            List<Cube> newCore = new ArrayList<>();

            for (Cube old : core) {
                if (!c.cube.intersect(old)) {
                    newCore.add(old);
                    continue;
                }

                // x positive
                if (old.xMin <= c.cube.xMax  && c.cube.xMax <= old.xMax) {
                    newCore.add(new Cube(c.cube.xMax + 1, old.xMax, old.yMin, old.yMax, old.zMin, old.zMax));
                    old = new Cube(old.xMin, c.cube.xMax, old.yMin, old.yMax, old.zMin, old.zMax);
                }

                // x neg
                if (old.xMin <= c.cube.xMin  && c.cube.xMin <= old.xMax) {
                    newCore.add(new Cube(old.xMin, c.cube.xMin - 1, old.yMin, old.yMax, old.zMin, old.zMax));
                    old = new Cube(c.cube.xMin, old.xMax, old.yMin, old.yMax, old.zMin, old.zMax);
                }

                // y positive
                if (old.yMin <= c.cube.yMax  && c.cube.yMax <= old.yMax) {
                    newCore.add(new Cube(old.xMin, old.xMax, c.cube.yMax + 1, old.yMax, old.zMin, old.zMax));
                    old = new Cube(old.xMin, old.xMax, old.yMin, c.cube.yMax, old.zMin, old.zMax);
                }

                // y neg
                if (old.yMin <= c.cube.yMin  && c.cube.yMin <= old.yMax) {
                    newCore.add(new Cube(old.xMin, old.xMax, old.yMin, c.cube.yMin - 1, old.zMin, old.zMax));
                    old = new Cube(old.xMin, old.xMax, c.cube.yMin, old.yMax, old.zMin, old.zMax);
                }

                // z positive
                if (old.zMin <= c.cube.zMax  && c.cube.zMax <= old.zMax) {
                    newCore.add(new Cube(old.xMin, old.xMax, old.yMin, old.yMax, c.cube.zMax + 1, old.zMax));
                    old = new Cube(old.xMin, old.xMax, old.yMin, old.yMax, old.zMin, c.cube.zMax);
                }

                // z neg
                if (old.zMin <= c.cube.zMin  && c.cube.zMin <= old.zMax) {
                    newCore.add(new Cube(old.xMin, old.xMax, old.yMin, old.yMax, old.zMin, c.cube.zMin - 1));
                    old = new Cube(old.xMin, old.xMax, old.yMin, old.yMax, c.cube.zMin, old.zMax);
                }
            }

            if (c.on) {
                newCore.add(c.cube);
            }
            core = newCore;
        }

        long volume = 0;
        for (Cube c : core) {
            volume += (c.xMax - c.xMin + 1) * (c.yMax - c.yMin + 1) * (c.zMax - c.zMin + 1);
        }

        System.out.println(volume);

    }

    private long solve() {
        long count = 0;

        for (long y = -50; y <= 50; y++) {
            for (long x = -50; x <= 50; x++) {
                for (long z = -50; z <= 50; z++) {
                    boolean on = false;
                    for (Cuboid c : cuboids) {
                        if (x >= c.cube.xMin && x <= c.cube.xMax &&
                                y >= c.cube.yMin && y <= c.cube.yMax &&
                                z >= c.cube.zMin && z <= c.cube.zMax) {
                            on = c.on;
                        }
                    }

                    if (on) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    class Cube {
        private long xMin;
        private long xMax;
        private long yMin;
        private long yMax;
        private long zMin;
        private long zMax;

        public Cube(long xMin, long xMax, long yMin, long yMax, long zMin, long zMax) {
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
            this.zMin = zMin;
            this.zMax = zMax;
        }

        public boolean intersect(Cube c) {
            return Math.max(xMin, c.xMin) <= Math.min(xMax, c.xMax) &&
                    Math.max(yMin, c.yMin) <= Math.min(yMax, c.yMax) &&
                    Math.max(zMin, c.zMin) <= Math.min(zMax, c.zMax);
        }
    }

    class Cuboid {
        private boolean on;
        private Cube cube;


        public Cuboid(boolean on, Cube cube) {
            this.on = on;
            this.cube = cube;
        }
    }
}
