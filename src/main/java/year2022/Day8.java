package year2022;

import base.Base;

import java.io.IOException;
import java.util.Arrays;

public class Day8 extends Base {
    private static final String INPUT = "2022/day8-input.txt";
    private static int width;
    private static int height;
    private static int[][] grid;
    private static int[][] directions;
    private static Tree[][] scores;

    public static void main(String[] args) throws IOException {
        Day8 main = new Day8();
        main.mainMethod(INPUT);
        main.prepareInput();
        
        long start = System.currentTimeMillis();
        height = main.instructions.size();
        width = main.instructions.get(0).length();
        grid = new int[height][width];
        for (int i = 0; i < main.instructions.size(); i++) {
            grid[i] = Arrays.stream(main.instructions.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }
        directions = new int[][]{{-1, 0},{0, -1}, {1, 0}, {0, 1}};
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    @Override
    public void part1() throws IOException {
        long score = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (mapToTree(y, x, grid[y][x]).visible) {
                    score++;
                }
            }
        }
        System.out.println(score);
    }

    @Override
    public void part2() throws IOException {
        scores = new Tree[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                scores[y][x] = mapToTree(y, x, grid[y][x]);
            }
        }

        System.out.println(Arrays.stream(scores).flatMapToInt(x -> Arrays.stream(x).mapToInt(t -> t.getScore())).max().getAsInt());
    }

    private boolean isTreeOnEdge(int y, int x) {
        return y == 0 || x == 0 || y == height - 1 || x == width - 1;
    }

    private Tree mapToTree(int y, int x, int treeHeight) {
        Tree tree = new Tree();
        if (isTreeOnEdge(y, x)) {
            tree.visible = true;
            return tree;
        }
        int[] scores = new int[4];
        for (int i = 0; i < directions.length; i++) {
            int[] dir = directions[i];
            int directionScore = 0;
            int nextY = y + dir[0];
            int nextX = x + dir[1];
            boolean treeVisibleFromDirection = true;
            while (nextY >= 0 && nextY < height && nextX >= 0 && nextX < width && treeVisibleFromDirection) {
                directionScore++;
                if (grid[nextY][nextX] >= treeHeight) {
                    treeVisibleFromDirection = false;
                    break;
                }
                nextY = nextY + dir[0];
                nextX = nextX + dir[1];
            }
            if (treeVisibleFromDirection) {
                tree.visible = true;
            }
            scores[i] = directionScore;
        }
        tree.score = scores[0] * scores[1] * scores[2] * scores[3];
        return tree;
    }

    class Tree {
        private boolean visible;
        private int score;
        public int getScore() {
            return score;
        }
    }
}
