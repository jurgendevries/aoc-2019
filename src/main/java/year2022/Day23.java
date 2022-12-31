package year2022;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day23 extends Base {
    private static final String INPUT = "2022/day23-test.txt";
    private static final int N = 0;
    private static final int S = 1;
    private static final int W = 2;
    private static final int E = 3;
    List<Elf> elfs;
    private static int[][][] directions;
    private static int[][] north;
    private static int[][] east;
    private static int[][] south;
    private static int[][] west;

    public static void main(String[] args) throws IOException {
        Day23 main = new Day23();
        main.mainMethod(INPUT);
        main.prepareInput();
        north = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}};
        east = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
        south = new int[][]{{1, -1}, {1, 0}, {1, 1}};
        west = new int[][]{{-1, -1}, {0, -1}, {1, -1}};
        directions = new int[][][]{north, south, west, east};

        long start = System.currentTimeMillis();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));

    }

    @Override
    public void part1() throws IOException {
        simulate(true);
    }

    private void simulate(boolean part1) {
        readInput();
        boolean elfMoved = true;
        int round = 0;
        while ((part1 && round < 10) || (!part1 && elfMoved)) {
            elfMoved = false;
            Set<String> seen = new HashSet<>();
            // check possible movement
            for (Elf elf : elfs) {
                // look in all directions
                boolean canMoveNorth = canMove(N, elf);
                boolean canMoveSouth = canMove(S, elf);
                boolean canMoveWest = canMove(W, elf);
                boolean canMoveEast = canMove(E, elf);


                boolean[] checks = new boolean[4];
                checks[N] = canMoveNorth;
                checks[S] = canMoveSouth;
                checks[W] = canMoveWest;
                checks[E] = canMoveEast;

                if ((canMoveNorth && canMoveEast && canMoveSouth && canMoveWest) || (!canMoveNorth && !canMoveEast && !canMoveSouth && !canMoveWest)) {
                    //elf.proposedPos = new Coord(elf.currentPos.x, elf.currentPos.y);
                } else {
                    boolean moved = false;
                    for (int add = 0; add < 4; add++) {
                        int check = (round + add) % 4;

                        if (checks[check]) {
                            switch (check) {
                                case N:
                                    elf.proposedPos.x = elf.currentPos.x;
                                    elf.proposedPos.y = elf.currentPos.y - 1;
                                    moved = true;
                                    break;
                                case S:
                                    elf.proposedPos.x = elf.currentPos.x;
                                    elf.proposedPos.y = elf.currentPos.y + 1;
                                    moved = true;
                                    break;
                                case W:
                                    elf.proposedPos.x = elf.currentPos.x - 1;
                                    elf.proposedPos.y = elf.currentPos.y;
                                    moved = true;
                                    break;
                                case E:
                                    elf.proposedPos.x = elf.currentPos.x + 1;
                                    elf.proposedPos.y = elf.currentPos.y;
                                    moved = true;
                                    break;
                            }
                            if (!seen.contains("" + elf.proposedPos.x + "," + elf.proposedPos.y)) {
                                seen.add("" + elf.proposedPos.x + "," + elf.proposedPos.y);
                            } else {
                                elf.proposedPos.x = elf.currentPos.x;
                                elf.proposedPos.y = elf.currentPos.y;
                            }
                        }
                        if (moved) {
                            elfMoved = true;
                            break;
                        }
                    }
                }
            }

            // actually move if no other elf is trying this move
            for (Elf elf : elfs) {
                elf.currentPos.x = elf.proposedPos.x;
                elf.currentPos.y = elf.proposedPos.y;
            }
            if (elfMoved) {
                round++;
            }
        }
        int minX = elfs.stream().mapToInt(e -> e.currentPos.x).min().getAsInt();
        int maxX = elfs.stream().mapToInt(e -> e.currentPos.x).max().getAsInt();
        int minY = elfs.stream().mapToInt(e -> e.currentPos.y).min().getAsInt();
        int maxY = elfs.stream().mapToInt(e -> e.currentPos.y).max().getAsInt();
        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
//            int offsetY = minY < 0 ? 0 - minY: 0 - minY;
//            int offsetX = minX < 0 ? 0 - minX: 0 - minX;
//            String[][] grid = new String[height][width];
//
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    grid[y][x] = ".";
//                }
//            }
//
//            for (Elf elf : elfs) {
//                grid[elf.currentPos.y + offsetY][elf.currentPos.x + offsetX] = "#";
//            }
        //Utils.printGrid(width, height, grid);

        System.out.println("Round [" + (round+1) + "]: " + (width * height - elfs.size()));
    }

    private boolean canMove(int dirNum, Elf elf) {
        boolean canMove = true;
        int[][] windDir = directions[dirNum];
        for (int[] dir : windDir) {
            if (elfs.stream().filter(e -> e.id != elf.id && e.currentPos.x == elf.currentPos.x + dir[1] && e.currentPos.y == elf.currentPos.y + dir[0]).findFirst().isPresent()) {
                canMove = false;
                break;
            }
        }
        return canMove;
    }

    private void readInput() {
        int elfId = 1;
        elfs = new ArrayList<>();
        for (int y = 0; y < instructions.size(); y++) {
            String[] row = instructions.get(y).split("");
            for (int x = 0; x < row.length; x++) {
                if ("#".equals(row[x])) {
                    Elf elf = new Elf(elfId++, new Coord(x, y));
                    elfs.add(elf);
                }
            }
        }
    }

    @Override
    public void part2() throws IOException {
        simulate(false);
    }

    class Elf {
        private int id;
        private Coord currentPos;
        private Coord proposedPos;

        public Elf(int id, Coord currentPos) {
            this.id = id;
            this.currentPos = currentPos;
            this.proposedPos = new Coord();
        }
    }

    class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coord() {

        }
    }

}
