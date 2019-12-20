package year2019.day10;

import base.Base;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day10-input.txt";
    private static final int HEIGHT = 31;
    private static final int WIDTH= 31;
    private static Position[][] grid = new Position[HEIGHT][WIDTH];

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
    }

    private void prepare() throws IOException {
        List<Position> asteroids = new ArrayList<>();
        String line;
        int lineNumber = 0;
        while ((line = input.readLine()) != null) {
            int posNumber = 0;
            for (String p : line.split("")) {
                Position position = new Position(posNumber, lineNumber, p.equals("#"));
                if (p.equals("#")) {
                    asteroids.add(position);
                }
                grid[lineNumber][posNumber] = position;
                posNumber++;
            }
            lineNumber++;
        }

        Map<Position, List<Fracture>> visibleAsteroidsFromAsteroids = new HashMap<>();

        for (Position asteroid : asteroids) {
            List<Fracture> visibleFractures = new ArrayList<>();
            Fracture asteroidFract = asteroid.getDistanceFracture();
            boolean left = false, right = false, up = false, down = false;
            for (Position asteroidCompare : asteroids) {
                Fracture compareFract = asteroidCompare.getDistanceFracture();
                if (!compareFract.equals(asteroidFract)) {
                    Fracture fractureDiff = new Fracture(compareFract.getA() - asteroidFract.getA(), compareFract.getB() - asteroidFract.getB());
                    Fracture smallestPossibleFractureDiff = calc(fractureDiff.getA(), fractureDiff.getB());

                    if (!visibleFractures.stream().filter(f -> f.getA() == smallestPossibleFractureDiff.getA() && f.getB() == smallestPossibleFractureDiff.getB()).findFirst().isPresent()) {
                        if ((smallestPossibleFractureDiff.getA() != 0 && smallestPossibleFractureDiff.getB() != 0) ||
                                (smallestPossibleFractureDiff.getB() == 0 && asteroidFract.getA() < compareFract.getA() && !right) ||
                                (smallestPossibleFractureDiff.getB() == 0 && asteroidFract.getA() > compareFract.getA() && !left) ||
                                (smallestPossibleFractureDiff.getA() == 0 && asteroidFract.getB() < compareFract.getB() && !down) ||
                                (smallestPossibleFractureDiff.getA() == 0 && asteroidFract.getB() > compareFract.getB() && !up)) {
                            right = right || smallestPossibleFractureDiff.getB() == 0 && asteroidFract.getA() < compareFract.getA();
                            left = left || smallestPossibleFractureDiff.getB() == 0 && asteroidFract.getA() > compareFract.getA();
                            down = down || smallestPossibleFractureDiff.getA() == 0 && asteroidFract.getB() < compareFract.getB();
                            up = up || smallestPossibleFractureDiff.getA() == 0 && asteroidFract.getB() > compareFract.getB();
                            visibleFractures.add(smallestPossibleFractureDiff);
                        }
                    }
                }
            }
            visibleAsteroidsFromAsteroids.put(asteroid, visibleFractures);
        }

        for (Map.Entry<Position, List<Fracture>> asteroidEntry : visibleAsteroidsFromAsteroids.entrySet()) {
            System.out.println(asteroidEntry.getKey().getDistanceFracture().getA() + "/" + asteroidEntry.getKey().getDistanceFracture().getB() + ", has " + asteroidEntry.getValue().size() + " visible asteroids");
        }
        Map.Entry<Position, List<Fracture>> maxPos = visibleAsteroidsFromAsteroids.entrySet().stream().max((e1, e2) -> e1.getValue().size() > e2.getValue().size() ? 1 : -1).get();
        System.out.println(maxPos.getKey().getDistanceFracture().getA() + "/" + maxPos.getKey().getDistanceFracture().getB() + ", visible astroids: " + maxPos.getValue().size());


        part2Test(maxPos.getKey(), asteroids);
        //printGrid();

    }

    private void part2Test(Position maxPos, List<Position> asteroids) {
        List<Position> centeredAsteroids = new ArrayList<>();
        Map<Double, List<Position>> map = new HashMap<>();
        for (Position compareAsteroid : asteroids) {
            if (!maxPos.equals(compareAsteroid)) {
                Fracture fractureDiff = new Fracture(compareAsteroid.getDistanceFracture().getA() - maxPos.getDistanceFracture().getA(), compareAsteroid.getDistanceFracture().getB() - maxPos.getDistanceFracture().getB());
                double radiusFromCenter = Math.sqrt(Math.pow(fractureDiff.getA(), 2) + Math.pow(fractureDiff.getB(), 2));
                double angleFromCenter = Math.round(Math.toDegrees(Math.acos(fractureDiff.getA() / radiusFromCenter)) * 100.0) / 100.0;

                if (fractureDiff.getA() >= 0 && fractureDiff.getB() <= 0) {
                    angleFromCenter = 90 - angleFromCenter;
                }

                if (fractureDiff.getB() > 0) {
                    angleFromCenter = angleFromCenter + 90;
                }

                if (fractureDiff.getA() < 0 && fractureDiff.getB() <= 0) {
                    angleFromCenter = 360 - angleFromCenter + 90;
                }

                compareAsteroid.setRadiusFromCenter(radiusFromCenter);
                compareAsteroid.setAngleFromCenter(angleFromCenter);
                centeredAsteroids.add(compareAsteroid);
                if (map.containsKey(angleFromCenter)) {
                    map.get(angleFromCenter).add(compareAsteroid);
                } else {
                    List<Position> pos = new ArrayList<>();
                    pos.add(compareAsteroid);
                    map.put(angleFromCenter, pos);
                }

            }
        }

        Map<Double, List<Position>> treeMap = new TreeMap<>(map);
        System.out.println("done");

        for (Map.Entry<Double, List<Position>> entry : treeMap.entrySet()) {
            Collections.sort(entry.getValue(), new CustomComparator());
        }

        int laserCounter = 0;
        for (Map.Entry<Double, List<Position>> entry : treeMap.entrySet()) {
            Position shot = entry.getValue().remove(0);
            System.out.println(laserCounter + ":Shot asteroid at [" + shot.getX() + "," + shot.getY() + "], angle: [" + shot.getAngleFromCenter() + "], distance: [" + shot.getRadiusFromCenter() + "]");

            if (!treeMap.entrySet().stream().filter(t -> !t.getValue().isEmpty()).findFirst().isPresent()) {
                break;
            }

            laserCounter++;
        }

    }

    private void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < HEIGHT; x++) {
                sb.append(grid[y][x].isAsteroid() ? "#" : ".");
            }
            System.out.println(sb);
        }
    }

    @Override
    public void part1() throws IOException {

    }

    @Override
    public void part2() throws IOException {

    }

    private Fracture calc(int x, int y) {
        if (Math.abs(x) < 2 || Math.abs(y) < 2) {
            return new Fracture(x, y);
        }

        boolean xNeg = x < 0;
        boolean yNeg = y < 0;

        int divider = calcDivider(Math.abs(x), Math.abs(y));


        return new Fracture(x / divider, y / divider);
    }

    private int calcDivider(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calcDivider(b, a % b);
    }

    public class CustomComparator implements Comparator<Position> {
        @Override
        public int compare(Position o1, Position o2) {

            return o1.getRadiusFromCenter().compareTo(o2.getRadiusFromCenter());
        }
    }

    public class Fracture {
        private int a;
        private int b;

        public Fracture(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    public class Position {
        private int x;
        private int y;
        private boolean asteroid;
        private Fracture distanceFracture;
        private Fracture smallePossibleDistanceFracture;
        private Double radiusFromCenter;
        private double angleFromCenter;

        public Position(int x, int y, boolean asteroid) {
            this.x = x;
            this.y = y;
            this.asteroid = asteroid;
            this.distanceFracture = new Fracture(x, y);
            this.smallePossibleDistanceFracture = calc(x, y);
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

        public boolean isAsteroid() {
            return asteroid;
        }

        public void setAsteroid(boolean asteroid) {
            this.asteroid = asteroid;
        }

        public Fracture getDistanceFracture() {
            return distanceFracture;
        }

        public void setDistanceFracture(Fracture distanceFracture) {
            this.distanceFracture = distanceFracture;
        }

        public Fracture getSmallePossibleDistanceFracture() {
            return smallePossibleDistanceFracture;
        }

        public void setSmallePossibleDistanceFracture(Fracture smallePossibleDistanceFracture) {
            this.smallePossibleDistanceFracture = smallePossibleDistanceFracture;
        }

        public Double getRadiusFromCenter() {
            return radiusFromCenter;
        }

        public void setRadiusFromCenter(double radiusFromCenter) {
            this.radiusFromCenter = radiusFromCenter;
        }

        public double getAngleFromCenter() {
            return angleFromCenter;
        }

        public void setAngleFromCenter(double angleFromCenter) {
            this.angleFromCenter = angleFromCenter;
        }
    }
}
