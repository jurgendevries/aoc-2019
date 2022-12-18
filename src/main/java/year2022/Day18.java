package year2022;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day18 extends Base {
    private static final String INPUT = "2022/day18-input.txt";

    private static int width;
    private static int height;
    private static int depth;
    private static int[][][] grid;
    private static int[][] directions;

    private static List<Coord> coords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Day18 main = new Day18();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        main.buildGrid();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void buildGrid() {
        // y,x,z
        directions = new int[][]{
                {-1, 0, 0}, {1, 0, 0},
                {0, -1, 0}, {0, 1, 0},
                {0, 0, -1}, {0, 0, 1}
        };

        int maxY = Integer.MIN_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        for (String instruction : instructions) {
            int x = Integer.parseInt(instruction.split(",")[0]);
            int y = Integer.parseInt(instruction.split(",")[1]);
            int z = Integer.parseInt(instruction.split(",")[2]);

            maxX = x > maxX ? x : maxX;
            maxY = y > maxY ? y : maxY;
            maxZ = z > maxZ ? z : maxZ;

            coords.add(new Coord(x, y, z));
        }

        height = maxY + 2;
        width = maxX + 2;
        depth = maxZ + 2;
        grid = new int[height][width][depth];

        for (Coord c : coords) {
            grid[c.y][c.x][c.z] = 1;
        }
    }

    @Override
    public void part1() throws IOException {
        int totalSurface = 0;
        for (Coord c : coords) {
            int surface = 6;
            for (int[] dir : directions) {
                int testY= c.y + dir[0];
                int testX = c.x + dir[1];
                int testZ = c.z + dir[2];
                if (testY >= 0 && testY < height && testX >= 0 && testX < width && testZ >= 0 && testZ < depth) {
                    if (grid[testY][testX][testZ] == 1) {
                        surface--;
                    }
                }
            }
            totalSurface += surface;
        }
        System.out.println(totalSurface);
    }

    private boolean canFill(int x, int y, int z) {
        return y >= 0 && y < height &&
                x >= 0 && x < width &&
                z >= 0 && z < depth &&
                grid[y][x][z] == 0;
    }

    @Override
    public void part2() throws IOException {
        // values:
        // 0 = not visited and air
        // 1 = surface
        // 2 = water and visited
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int filled = 2;
        int inQueue = 9;

        while (!queue.isEmpty()) {
            int[] current = queue.pop();
            grid[current[0]][current[1]][current[2]] = filled;
            for (int[] dir : directions) {
                int testX = current[1] + dir[1];
                int testY = current[0] + dir[0];
                int testZ = current[2] + dir[2];
                if (canFill(testX, testY, testZ)) {
                    grid[testY][testX][testZ] = inQueue;
                    queue.addLast(new int[] {testY, testX, testZ});
                }
            }
        }

        // test surface touched by water
        int totalSurfaceTouched = 0;
        for (Coord c : coords) {
            int surfaceTouchingWater = 0;
            for (int[] dir : directions) {
                int testX = c.x + dir[1];
                int testY = c.y + dir[0];
                int testZ = c.z + dir[2];

                if (grid[testY][testX][testZ] == 2) {
                    surfaceTouchingWater++;
                }
            }
            totalSurfaceTouched += surfaceTouchingWater;
        }
        System.out.println(totalSurfaceTouched);

    }

    class Coord {
        private int x;
        private int y;
        private int z;

        public Coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
