package year2021.day13;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day13-input.txt";
    private static List<String> instructions;
    private static List<Point> points = new ArrayList<>();
    private static List<Fold> folds = new ArrayList<>();
    private static int width;
    private static int height;
    private static String[][] grid;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        initPoints();
        initFolds();
    }

    private void initFolds() {
        boolean atFolds = false;
        for (String instruction : instructions) {
            if (!atFolds) {
                if (instruction.equals("")) {
                    atFolds = true;
                }
                continue;
            }
            String foldLine = instruction.split(" ")[2];
            boolean horizontal = foldLine.split("=")[0].equals("y");
            int position = Integer.parseInt(foldLine.split("=")[1]);
            Fold fold = new Fold(horizontal, position);
            folds.add(fold);
        }
    }

    private void initPoints() {
        int i = 1;
        String instruction = instructions.get(0);
        while (!instruction.equals("")) {
            Point point = new Point(Integer.parseInt(instruction.split(",")[0]), Integer.parseInt(instruction.split(",")[1]));
            points.add(point);
            instruction = instructions.get(i++);
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
        List<Point> foldedPoints = points;
        for (Fold f : folds) {
            foldedPoints = foldPoints(f, foldedPoints);
            System.out.println(foldedPoints.size());
        }

        height = foldedPoints.stream().max(Comparator.comparing(y -> y.getY())).get().getY() + 1;
        width = foldedPoints.stream().max(Comparator.comparing(x -> x.getX())).get().getX() + 1;

        grid = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = " ";
            }
        }

        for (Point p : foldedPoints) {
            grid[p.getY()][p.getX()] = "#";
        }
        Utils.printGrid(width, height, grid);
    }

    private List<Point> foldPoints(Fold f, List<Point> points) {
        List<Point> foldedPoints = new ArrayList<>();
        for (Point point : points) {
            if (f.isHorizontal()) {
                Point newPoint = new Point(point.getX(), point.getY() < f.getPosition() ? point.getY() : 2 * f.getPosition() - point.getY());
                if (foldedPoints.stream().filter(x -> x.getX() == newPoint.getX() && x.getY() == newPoint.getY()).count() == 0) {
                    foldedPoints.add(newPoint);
                }
            } else {
                Point newPoint = new Point(point.getX() < f.getPosition() ? point.getX() : 2 * f.getPosition() - point.getX(), point.getY());
                if (foldedPoints.stream().filter(x -> x.getX() == newPoint.getX() && x.getY() == newPoint.getY()).count() == 0) {
                    foldedPoints.add(newPoint);
                }
            }

        }
        return foldedPoints;
    }

    @Override
    public void part2() throws IOException {
        return;
    }

    class Fold {
        private boolean horizontal;
        private int position;

        public Fold(boolean horizontal, int value) {
            this.horizontal = horizontal;
            this.position = value;
        }

        public boolean isHorizontal() {
            return horizontal;
        }

        public void setHorizontal(boolean horizontal) {
            this.horizontal = horizontal;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
    }
}
