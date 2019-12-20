package year2019.day12;

import base.Base;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day12-input.txt";
    private static final int STEPS = 1000;
    private static List<Moon> moons;
    private static MoonAxis[] moonXs;
    private static MoonAxis[] moonYs;
    private static MoonAxis[] moonZs;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
//        main.prepare();
//        main.part1();
        main.prepare2();
        main.part2();
    }

    private void prepare() throws IOException {
        String line;
        moons = new ArrayList<>();

        while ((line = input.readLine()) != null) {
            String[] lineSplit = line.split(",");

            int x = Integer.parseInt(lineSplit[0].split("=")[1]);
            int y = Integer.parseInt(lineSplit[1].split("=")[1]);
            int z = Integer.parseInt(lineSplit[2].split("=")[1].substring(0, lineSplit[2].split("=")[1].length() - 1));

            Coordinate pos = new Coordinate(x,y,z);
            Coordinate vel = new Coordinate(0,0,0);
            Moon moon = new Moon(pos, vel);
            moons.add(moon);
        }
    }

    private void prepare2() throws IOException {
        String line;
        moonXs = new MoonAxis[4];
        moonYs = new MoonAxis[4];
        moonZs = new MoonAxis[4];

        int counter = 0;
        while ((line = input.readLine()) != null) {
            String[] lineSplit = line.split(",");

            int x = Integer.parseInt(lineSplit[0].split("=")[1]);
            int y = Integer.parseInt(lineSplit[1].split("=")[1]);
            int z = Integer.parseInt(lineSplit[2].split("=")[1].substring(0, lineSplit[2].split("=")[1].length() - 1));

            MoonAxis moonX = new MoonAxis(x, 0);
            MoonAxis moonY = new MoonAxis(y, 0);
            MoonAxis moonZ = new MoonAxis(z, 0);

            moonXs[counter] = moonX;
            moonYs[counter] = moonY;
            moonZs[counter] = moonZ;

            counter++;
        }
    }

    @Override
    public void part1() throws IOException {
        for (int i = 0; i < STEPS; i++) {
            step();
        }

        int totalEnergy = 0;
        for (Moon moon : moons) {
            int pot = Math.abs(moon.getPosition().getX()) + Math.abs(moon.getPosition().getY()) + Math.abs(moon.getPosition().getZ());
            int kin = Math.abs(moon.getVelocity().getX()) + Math.abs(moon.getVelocity().getY()) + Math.abs(moon.getVelocity().getZ());

            totalEnergy += pot * kin;
        }

        System.out.println(totalEnergy);
    }

    @Override
    public void part2() throws IOException {
        long counterX = stepAxis(moonXs, "x");
        long counterY = stepAxis(moonYs, "y");
        long counterZ = stepAxis(moonZs, "z");

        long lcm = lcm(lcm(counterX, counterY), counterZ);

        System.out.println("steps: " + lcm);
    }

    private long lcm(long counterX, long counterY) {
        if (counterX == 0 || counterY == 0) {
            return 0;
        }

        long abs1 = Math.abs(counterX);
        long abs2 = Math.abs(counterY);

        long absHigherNumber = Math.max(abs1, abs2);
        long absLowerNumber = Math.min(abs1, abs2);

        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }

        return  lcm;
    }

    private long stepAxis(MoonAxis[] moons, String axis) {
        long counter = 0;
        boolean duplicateFound = false;
        Set<String> visitedStates = new HashSet<>();
        String sb = "";
        for (int i = 0; i < moons.length; i++) {
            sb += moons[i].getX();
            sb += moons[i].getV();
        }
        visitedStates.add(sb);
        while (!duplicateFound) {
            for (int i = 0; i < moons.length; i++) {
                int xChange = 0;

                for (int j = 0; j < moons.length; j++) {
                    if (i != j) {
                        xChange += moons[i].getX() < moons[j].getX() ? 1 : moons[i].getX() > moons[j].getX() ? -1 : 0;
                    }
                }

                moons[i].setV(moons[i].getV() + xChange);
            }

            sb = "";
            for (int k = 0; k < moons.length; k++) {
                moons[k].setX(moons[k].getX() + moons[k].getV());
                sb += moons[k].getX();
                sb += moons[k].getV();

            }

            if (!visitedStates.add(sb)) {
                duplicateFound = true;
            }
            counter++;
        }
        System.out.println("Axis: [" + axis + "], steps: [" + counter + "]");
        return counter;
    }

    private void step() {
        for (Moon moon : moons) {
            int xVelChange = 0;
            int yVelChange = 0;
            int zVelChange = 0;

            for (Moon moonCompare : moons) {
                if (!moonCompare.equals(moon)) {
                    xVelChange += moon.getPosition().getX() < moonCompare.getPosition().getX() ? 1 : moon.getPosition().getX() > moonCompare.getPosition().getX() ? -1 : 0;
                    yVelChange += moon.getPosition().getY() < moonCompare.getPosition().getY() ? 1 : moon.getPosition().getY() > moonCompare.getPosition().getY() ? -1 : 0;
                    zVelChange += moon.getPosition().getZ() < moonCompare.getPosition().getZ() ? 1 : moon.getPosition().getZ() > moonCompare.getPosition().getZ() ? -1 : 0;
                }
            }

            moon.getVelocity().setX(moon.getVelocity().getX() + xVelChange);
            moon.getVelocity().setY(moon.getVelocity().getY() + yVelChange);
            moon.getVelocity().setZ(moon.getVelocity().getZ() + zVelChange);
        }

        for (Moon moon : moons) {
            moon.getPosition().setX(moon.getPosition().getX() + moon.getVelocity().getX());
            moon.getPosition().setY(moon.getPosition().getY() + moon.getVelocity().getY());
            moon.getPosition().setZ(moon.getPosition().getZ() + moon.getVelocity().getZ());
        }
    }

    public class MoonAxis {
        private Integer x;
        private Integer v;

        public MoonAxis(Integer x, Integer v) {
            this.x = x;
            this.v = v;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }
    }

    public class Moon {
        private Coordinate position;
        private Coordinate velocity;

        public Moon(Coordinate position, Coordinate velocity) {
            this.position = position;
            this.velocity = velocity;
        }

        public Coordinate getPosition() {
            return position;
        }

        public void setPosition(Coordinate position) {
            this.position = position;
        }

        public Coordinate getVelocity() {
            return velocity;
        }

        public void setVelocity(Coordinate velocity) {
            this.velocity = velocity;
        }
    }

    public class Coordinate {
        private int x;
        private int y;
        private int z;

        public Coordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
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

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }
    }
}
