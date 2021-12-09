package year2021.day9;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day9-input.txt";
    private static List<String> instructions;
    private static int width;
    private static int height;
    private static int[][] grid;
    private static int[][] directions;
    private static List<LowPoint> lowPoints = new ArrayList<>();

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        height = instructions.size();
        width = instructions.get(0).length();

        grid = new int[height][];

        for (int i = 0; i < instructions.size(); i++) {
            grid[i] = Arrays.stream(instructions.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }

        directions = new int[][]{{-1, 0},{0, 1}, {1, 0}, {0, -1}};
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int val = grid[y][x];
                boolean lowPoint = true;
                for (int d = 0; d < directions.length; d++) {
                    // up right down left
                    int checkY = y + directions[d][0];
                    int checkX = x + directions[d][1];
                    if (checkY >= 0 && checkY < height && checkX >= 0 && checkX < width) {
                        if (val >= grid[checkY][checkX]) {
                            lowPoint = false;
                            break;
                        }
                    }
                }
                if (lowPoint) {
                    lowPoints.add(new LowPoint(x, y, val));
                    //System.out.println("Lowpoint found: " + val + " at: (" + x + "," + y + ")");
                }
            }
        }

        System.out.println(lowPoints.stream().mapToInt(v -> v.getScore()).sum());
    }

    @Override
    public void part2() throws IOException {
        List<Integer> sizes = new ArrayList<>();
        for (LowPoint lp : lowPoints) {
            int basinSize = 1;
            List<String> visitedPositions = new ArrayList<>();
            visitedPositions.add("" + lp.getY() + "," + lp.getX());
            sizes.add(checkBasin(basinSize, visitedPositions, lp.getX(), lp.getY()));
        }

        Collections.sort(sizes, Collections.reverseOrder());
        int answer = 1;
        for (int s = 0; s < 3; s++) {
            answer *= sizes.get(s);
        }
        System.out.println(answer);
    }

    private int checkBasin(int basinSize, List<String> visitedPositions, int x, int y) {
        for (int d = 0; d < directions.length; d++) {
            int checkY = y + directions[d][0];
            int checkX = x + directions[d][1];
            if (checkY >= 0 && checkY < height && checkX >= 0 && checkX < width) {
                String pos = "" + checkY + "," + checkX;
                if (grid[checkY][checkX] != 9 &&
                        //grid[checkY][checkX] >= grid[y][x] &&
                        !visitedPositions.contains(pos)
                ) {
                    visitedPositions.add(pos);
                    basinSize = checkBasin(++basinSize, visitedPositions, checkX, checkY);
                }
            }
        }

        return basinSize;

    }

    class LowPoint {
        private int x;
        private int y;
        private int val;
        private int score;

        public LowPoint() {
        }

        public LowPoint(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
            this.score = val + 1;
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

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
