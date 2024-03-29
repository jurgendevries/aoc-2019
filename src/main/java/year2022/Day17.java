package year2022;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day17 extends Base {
    private static final String TEST = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    private static final String INPUT = ">>><><<>>><<><<>>>><<<<>>><<<<><>>><>>>><<>>>><<<>>>><<>>><>>><<>>><>>><>>>><<>>>><<<>><>>><<<><<><>><<><>><<>>>><<>>><>>>><<<<>><<<>><<>>><<>><<<>>>><>>>><<<<><<<<>>><<<<>>><<<><<<>><<<<>>>><<<<>>><<<<>><>>><<<<>>><><<<>>>><><<<<>>><<<>><<<<>><<<>>>><<>><<<<>>>><<<><<>>><<>>>><>>><<>>><<>>><>>>><<<>>><<>>><<>>><>>>><<<<><<><<<><<><<<>>><<<>>>><>><<<>>><<<>><<<><<<<>>>><<<<>>><<<>><>>><>>>><<<>>>><>>>><<<<>>>><<<<><<<<><<<<>>><>>>><<<>><<<<>>><<<><<<>>>><<>>>><<<<>>><<<>><<<<><<<><<>>>><<<>>><>>><><<<>>><>><<<>><<>><<<>><<<>>>><<<<><<<<>>>><<>>><><<<>><<>><<>>>><<<><>><<<>><<<<>>>><<>><<<><<<<>><<>>><<<>><<><<<<>>>><<<<><<<<><<<><<<><<<<>><<<>><<<<>>>><><>>>><<<<><>><<>>><<>><>>><<>><<>>>><>><<<><>>><<<<>>><<>>><>><>>><<>><<<><<<<>>>><>>>><>>><<<>>><<<<>>><<<<>>><<<>>>><<<>><<<><>>>><<<<>><<<<><<<>>><<>>>><<><><<<><<<>><<<>>>><<<><<<<>>>><<<<>>>><><>>><>>>><<><<><<>><<><<<><<<<>>><>>>><<>>><>>>><<<>>>><<>>>><<<>><<<<><<><<<>>>><<<<>><<<>>>><>>><><<<<>>><<<><<>><<>>>><<>>>><>><<<>>>><>><<<<>>><<<>><>>>><<>><><<>>><<>><<>><<<><>>><<<>>>><>>>><<><<<<><<<<>>>><<<<>><<<>><<<>><<<>><<><<<<>>><<>>>><><>><<<<>>>><<>>>><>>>><<>>><<<<>><<><<<><<<<><<><<<<><<>>>><<<<>>>><>>>><<>>><<><<><>>>><>><<<<>><<>>>><<>>>><<<<>>>><<<<>><<<<>>>><<<>>><<>>><<><<<<>><>><<<>>><<<>>><>>>><>><>>>><<<<>><<<>>>><<>>><>><<<<>>><>><<<>>><<><<><<<<>>><<<<>>><<<<>>><<>><<>>><<>><<>><><<<<><<<>><><<<<>>>><<<><<<<>>>><<<>>><>><<<><<<>>><<<<>>><<<<>>><<>>><<<>>>><<<>>><<><<<>>><<>>><<>>><<>>>><<<>>><>><<>><<<>>><<<<>>>><>><<><<>>><<<>>><<<>><<<<>>>><<<<><<>>><<<>>><<>>>><<<>>>><>><<<<>>>><>>><<<<><<<>>>><>>><<>>><<><<>><<<><<<>>>><>>>><>>>><<<<>>><<<>>><<<>>>><<>>>><>><<<><>><<<>><<<>>>><<<>>><>>>><<<<><<<<>><>>>><<><<<>>><<><<<>>>><<<<>>>><<<<>>><<<<><>>>><<><>>><<>>><<>>><>><<<>><<<<>><<<<>><<>>><<><<>><<<>><<><<><>>><<<<><<<<>>>><>><<>>><<><<<<>>>><<<>><<<>>>><<<<>>><<<<>>><>>><<>><<<<>>>><<<>>><<><<<<>>>><<<<>>>><><<<>>><<<><<<>>><<<<><<<>>><<<>><<><>>>><><<>><<><<><<<>><>>>><>>>><>>>><<<<>>>><<>><<<><<<<><<>><><<<>><<<<>><<<<>>><<<>>>><<<<>><<<<>>>><<<<><>><<>>><<<<><>>>><<>>>><<<<>><>>>><><<<>><>>><<><><><<<>>>><<<>>><<><<<<>>>><>><<>>>><>>>><<<<>>>><<<<>>>><<<>>>><<<>><>><<<<><<<<>>>><>><<<><<>><<>>>><>><<><<<>>><<<><>>>><<>><<<<>>>><<<><<<><<<<>>><<>>>><>><<<<>>><<<>>><<<<><>>><>>><>>><>>><<>><<<>><<<>>><<<>><><<<<>><<<<><<>><>><<<>><<><>>><><<>>><<<<>>>><<<<>>>><>><<>>>><<<<>>><<>>>><<<<>>><<<<>>>><<>>>><><>><><<>><<<<>><>><<<<>>><>>><<<>>><<<<>>>><<>><<<>><<<<>>><<<<>>><<<<>>><<<<><<><><>>>><<<>>>><<<<>><<>>>><>><<><<<<><<<>><<<<><<>>><<<>>><><<<<>><<>>>><<<>><<<>>><<<>>><<<<><>>>><<<><<<><<<<>>><<<<><>>><>>><<>>>><<<>>>><<><<><<>><<>><<><>>><<<>>><<><<><<<<>><<<<>><<<>>>><>>>><<<<>>><>>><<<<>><<><<>>>><<<<>>>><>><>>><<<<>>><<<><<>><<><<>><>>><>><<>>><<<<>>>><<<>><<<>>><<>>>><<>><<<<>>>><<<>>>><<>><<<>>><<<><<<>><<<<>>>><<<>><<<<><<<<>>>><><>>><<><<>>><><<>>>><<<>>><<>>>><<<<><>>><<<<>>>><<<<>>>><>>><<>><<<>>>><<>>><<<><>>><<>>><<>>><<>>>><>>>><<>><>><<>>><<<>>>><<<>>><<<>><<<<><<<>>>><<>>><<<<><<<>>><><<<<><<>>><<<<>>><<>>><><<<<>>><>>>><>>>><<>>><<<>>>><>>>><<<>>><<<><<<><<<<><<>>>><<<<>><<>>>><<<>>><><<><<<<><<<><<<>><<<<>>>><<>><<>><<<>><<>><<<>>><<<<>><<<<>><<<>><<<<>>>><<>>>><><<<<>><>>>><<>>>><<<><<><>><<<>>>><<<>>><<><>>>><<<<><<<><<<<>>>><<>><<<<><<<<>>>><<>>>><<>>><<><>>>><><<<>>><<>><>><<<<>>>><<<>>>><<<>>><<<<>>><<<<>>>><<<>><<<<><<<<>>><>><<<>><<><<>><><<<>>>><>>>><<><<<<>>>><<<><<<<>>><<><>>>><>>><><<<<>>><><<<>>><<>>>><>>>><>>>><<<<><<>><>><<<><<<<><<>><>>>><>><<<>>><>>><<>>><<>><<<>>>><<<<>>>><<<>><<>><<>><>>>><<<<>><<>><<>><<<>><<>><<>>>><>>>><<>><<<<>>><<<<><<<>>><<><>>>><<<>><<<>>>><<<>><><>>><<<>><><>>>><<>><<<>>>><><>>><<>>><>>>><>>><<<>><<>>>><<>>>><<><<<>>><<><<<<>><<>><>>>><><<<>>>><<<<>><<<>><<<<>><<>><><<<<>>>><<><<<>><><<<<>>>><>>>><<<<>><>>><<<<>>>><>>>><>>>><><<<>><<<>>>><<<><<<>>><<<<>>>><<>>><<><<<<>>>><<<>><<<<><<<>>><<<>><<>>><<>><<>><<>>>><<<>><<>>><<<<>><>><<<>><<<<>>>><<<>>><>><<>>>><<<<>>>><<<><<>>><<>>><<<><<<<><<>>><<<<><<>>>><<<>>><<<>>>><<>>><<<>>><<>>><<<>><<<<>>>><><<<>>><>>>><<>><<<<>>>><<<>>>><>><<<>><<<><<><<<<>><<<<>>><<<<><<<<>>><<<<><<>><<>>><<<>><<<<>>><<<>><>>><<<<>><<<<><<><>>><<<<><<<>>>><<<<>><<<<>>>><<>>>><<>>>><<><<>>>><<<>>><<<<>><<>>>><<<<><<>>><<>><<><>>>><<>>>><<<>><<<>>><<><><<<<><<>>>><>>>><<<>>><><<<<>>>><<>>>><<<<><>><<>>>><>>>><<<<>>><>><<>><<<>>><<<><<<<>>>><<<<><<<>>>><<><>>><<<>>>><<><>>>><<<>><<>>>><<<<>><<<><<>>>><<>>><>>><>>>><<>>>><<<><>>>><<>>><<<<>>>><<>>><<<<>><<<<><>>><<>><>>><<><<<>><>>><<<<>><<>>><<><<<>><<<>>><<<<>>>><<>>><>><<><>>>><><><>>>><<>>>><<<>><<<<>>>><<>>>><<>>>><>>>><<>>>><<<<>><<>>>><<>><<<<>>><<<><>>>><<<>>><<<>><><>>>><>><<<<>>>><<><>>><<>>>><<>>>><<<>><<<>><><>><<>>>><<<>><<<>>><<>><<<<>>><<<<>>><>>>><<<><>><<<>><<><>>>><<<<>>><>>>><>>>><>>><>><>>><<<<>>>><>>>><>><<<<>><<<>><<<>>><>>><>>><<<><<<><<<<>>><<<<><<>><><>>><>>>><>>><><>>>><><<<><>>><<<<>>>><<>>>><><>>>><<>>>><>>>><<<>>><<><>><>>><>>><<<<><<<<>>><<<<><<<>>>><<<>><<>>><<<<>>><<><<<<>>>><>><<<<><><><<<><>>><<<>>>><<<>>><<<<>><<>>><<>><<<<>><>>>><<<><<>>><<>>>><>>><<<>>><<<>>>><<<<>>>><>>><<<><>>>><<<>>>><<>><<<>>><>>>><<<>>><<<><<<><<<<>><<<>>><<<>>><<<<>>>><<<<>>>><<<<>>>><<<<>><<<>><<>><<>>>><<><<>>>><><<<>><><<<><>>><<<<>>>><<>>><<>>>><<<<><>>><><<<>><<<><<>>>><<<>>><<<<>>>><<<<>>>><<<>>>><<<>><<<>>>><>><<<>>><<<>>>><<>>>><<<>>><<<<>>>><>><><<<<>>><<<>><<>><<>>>><<>><><<<<>>><<<>>>><><<>>>><<<>><<><<>><>>>><<<<>>><<<<><<>>>><<<>>><<<<>>>><>>><><<<>>><<<<>>>><<<>><<<<>>>><>>>><<<>>>><>>><<<<>>>><<>>>><><>><>><<<>><<>>>><<<>>><<>>>><<<><<<<>><<<<>><<<<>>>><<<><>><<<>>>><<>>><<<<>>><>>>><<<<>><>>><>>><<<>>>><<>>>><<<<><><<<>><><<><><><<<><<><<<<><<<<>>>><<<>>>><<<>>>><<<<><<>>>><<<><<>>>><<<>><<<<><<<>>><>>><>>><<<<>>><><<>><>>><<<<>>>><<>>><<<>>><<<>><<<<>>>><>><<>>>><<<>>>><<<><><<<>><<<>><<>><>>>><<>><<<>>>><<>>><<<>>><<><>><<>><>>><<>>><><<<>>>><<>>>><<>><>><>>><<<<><<<<>>><<<>>>><>>>><<<<>><>><<<>>>><<<<><<<<>><<>>>><<<<><<<<>>><<<><<>>><<><>><>>>><>>><<<<>>>><<>><>>>><<<>>><>>>><>>>><<<>><<>>><<<<>><<<<>>><>>><<<<>>>><>><<<<>>>><<<<>>><<<>><<>>><<<<>>>><>>><>><<>>><<<>>><><>><<<>><><>><<<><<<>><<<>>><<>>><<<>><<><<<<>>>><<<<>>><<<>><<>><<<<>><<<>>>><>>><<<>>><<<<>>><><<>><>><>><<<>>><<<<>><<<><<><<<<>>>><<>>><<<<>>><<<><<<>><<<>>>><<<<>><<><>><<<><<<>>>><<<<>><<<>>>><><<<<>>><<<>><>><>>><<><<<<><>><<>>>><>>>><>><<<<>>>><<<>>><<<<>>>><>><<><<>><<<<>>>><<<><>>>><<<<>>>><><<<><<<<>>>><<<<>>><<<>>><<>><<>>>><><>>>><<<>><<<<>>>><<<>><<<>><<<>><<<>>><><<<<><>>><<<<>>>><<<>><><<<>>><<><<>>>><<><<>>><<<>><<<<>>>><>>><><<<<>>>><<<>>>><<<<>>><>><<>>>><><<<<>>><<<>>><<>>><<<><<<>>>><>>>><<<<><<<<>><<<<>><<<<>>><<><>>><<<>><>><<<>>><><>>><>><<><<><<><<<<><<<><<><>>>><<>>><<<<>><<<>><<>>><<<<>><<<>>><<>>>><<>><<<<><<<<>><<<<>><<>><>>>><<<<>>>><<<<><<<><<<<>>>><<<<>>>><<<>>><<<>>>><<>>>><<>>>><<<>>>><<<>>>><<<>><>><<<<>>><<<><>><<<><<<<>>>><<<>><>>><<<>>>><<<<>><<<>>>><>>><<<>>>><<>>><<<<>><<<<>>>><<<>><<>><>>><>>>><<<<>>><<<<>>><<<><<>>><<<>>>><<<<>>><><<><<<<><>>>><>>><<<<>>>><>>>><<<<><<>><<>>><><>>><<<>>><<<><<<<>>>><<>>>><>>>><<>>><<>>>><<<><><<<><<<>><<<>>><<<<>><<<<><<<>><<<>><<<>><<<>>>><>>><<>><<<<>><<<<><<>>>><<>><<><<<><<<<>>><>>><<<>>>><<<<>>><<<<>>>><><<<>>>><>><<<><<><<<<>>>><><<>>>><<<>><<<<>>>><<>>><<>>>><<<>><<<<><<<<>><<<<>>>><<<>><<<<>>><><<><>>>><><<<<>>>><<<><<>>>><<<<>>><>><<<<>>>><<>><<<><<<>>>><<>>><<>>>><<<<>>>><<<><<><<<<>><<<>>>><><<><<<<>>>><<>>>><<<<>>><>>>><><<<>>>><<>><>>><<>>><<<<><<<>>><>>><<><>><<<<>>>><<<<>><<<>>><<<<><<<>>><<<><>>>><<>>>><<<<>>>><<<<>><<<><<>><>>><<<>>>><<<<>>>><<<<><<>>>><>><<<<>>><<><<>>>><><<<<>>><<<<>><<<><<<<>><<<<>>>><<><<<><<>>>><><<><<<>><<<<>><<<><<<>>><<<<>>><<><>><<<>>>><<>><<>>>><<><<>><<<<>><<<<>><><>>>><<<<>>>><<>>><<<>><<<<><<><>>>><<<>>><<>>>><<<<>>><<<><<>><<<>>>><<>>><>>>><<<<><<<>>>><<<>><>>>><<<<>><><<<><<<>>>><<<>>><<>>><<><>>>><<<><<<<>>><<<><<>><<<>>><><<><<><<>><<<>>><<<<><<>><<<>><>>><<<>><<<<>><><<><><<<<><<<>>>><<>><>>>><<<><<>><<<<>>><<<>>><<>><>>>><<<>><<>><><<<<>>>><<<>>>><>><<<><><>><<<<><<<>>>><<<>>><<<<>>>><><>>>><><<<<>><<<><<>>>><<<>>>><<>>>><>>>><<><>>>><>><<<<>>><<><<<<>>>><<>>>><<>>><<<<>><<<><>>><<>>><>><<><<><<<<><<<<><<><<<<><>>><>>>><<<>><<<<>><<<<>><<<>>><>>>><<<>>>><>>><<<<>>><>>>><<<><<<>>>><>>>><<<<>>><>>><<>>>><>>><<>>>><<<<>>>><<<<>>>><<<>><>>>><<>>>><>><><<>>>><>><<><<<<>>><<<<>>><<<<>>>><<>><<<<>>>><<<<>><<>><>><<<>><>><<>>>><<<<><<<<><<>>><<<<>><<>>><<<>>><<<<>>>><<><<<>>>><<<<>>><<<>><>>>><<<>>><<<<>>>><<<<>><<<<>>>><<<>>>><<>><<<<>>>><>>><<<<>><<<<><>>>><<<>>><>>>><<>><>>><<<>>><>>>><<<>><<><>><<>>>><>><<<<><<><<<<>>>><>><<>>>><<>><<<<>><<<<>>><<<<>>><<>>><>><<><>><<>>>><<<<>><<>>>><<<<>>>><>>><<<>>>><>>><>>><<><<<<>>>><>><<><<>>><>>>><<>>>><<>><>>>><<><<>>>><<>><<>>>><<<>><<><>>><<<<><<<><>><<><><<<>><<<<>>>><<<<>><<<>>>><<<<>>><<<<><<<<>>><<<<><<<><<<>>><<>>>><<>><><<>>><<<<>>>><<<<><<>>>><>><<>>>><<>><>>>><<<>>>><<<<>><<>><>>><<<<>>>><<<<>>><<>><<<>>>><<<><><<>>>><>>><<<>>>><<<>><<<<>>>><<>>><<>>><><<<<>>><<<>>><<<<>><>><<<><<<<>>>><>><<<<>>><<><<<>>><<<<>><>>><><<>>>><<>>>><<>><<><<<>><<<<>><><><>><<<>>><<><<<<>>><<>>>><<<>>>><>>>><>>>><<<<>>><<<<>>>><<<<>><>>><<>><<>><<>><<<>>><>>><><<<>>><<<<><<<><><<><<<>><<<<>>>><<<>>><>>>><<<>>><<<<>>>><<<<>><<<<>>><>>><>>><<>>><<>>>><>>><<<<>>><<><<<>>><<<<><<<>>><<<<>>><<><<<>>>><<<>><<<<>>><<<<>>>><<<><<<<><<<>>><<<>>>><<<<>>><>>>><<<<>>><<>>><<<>>>><<<<>>><<><<>>><<>>><><<><<<>>>><>>>><<<<>>><<<<>>><>>><<>>><<>>>><><<<<>>><<<><<<<>>>><<<><<<><<>>>><<<>>><<<<>>><<<>>>><<<><<<<>>>><<<>><<>>><<<<>><<>>>><<<<>>><>><<<<>>>><<>>>><>><<><<<>><>>>><<>>><<<><<>><>>><<<<><<<>><<<<>>><<>>><<<>>>><<<>><<<><<><<><<>>>><>>><<>>><<<<>><<<<>>><<<>>><<><>><>>><<><<<>>>><<<><>><<<><<<<>><<<>>>><<>>><>>><<<>>>><<>>><<<><<<<>>><>><<>>><><<><<<<>>><<<>>>><<<<>><<<>>>><><>>>><>><<>>><><><<>>>><<<>>>><<<<><<<>>><<<<><>>>><<><><<>>>><><<<<>>>><><<<<>>>><>>>><<<<>>><<<><<<>><<<><<<>>>><<<<><<<>>><<<>>><>>><>>>><<><>>>><<>>><<>>><<>><<<<><<>>>><<>>><<<><<<>><<<<>>>><>>>><<<>>><<>>>><<>>>><<<>>><<><<<>>><<<<>>><<<><>>><<<>>><>>>><<>><<<<>>>><<<<>>><<<>><<<<><";
    private static final int BLOCKS_TOTAL_HEIGHT = 13;
    private static final long NUMBER_OF_ROCKS = 2022;
    private static final int NUMBER_OF_BLOCKS = 5;
    private static final int GRID_WIDTH = 7;

    private static long lowestPoint;
    private static long maxGameHeight;
    private static long actualBottom;
    private static int currentPush = 0;
    private static List<Block> blocks = new ArrayList<>();
    private static String[][] grid;

    public static void main(String[] args) throws IOException {
        Day17 main = new Day17();
        long start = System.currentTimeMillis();
//        maxGameHeight = NUMBER_OF_ROCKS / NUMBER_OF_BLOCKS * BLOCKS_TOTAL_HEIGHT;
        lowestPoint = 999;
                //NUMBER_OF_ROCKS / NUMBER_OF_BLOCKS * BLOCKS_TOTAL_HEIGHT;
        maxGameHeight = 10;
        actualBottom = 10;
        grid = new String[(int) lowestPoint + 1][7];
        for (int y = 0; y < lowestPoint; y++) {
            grid[y] = ".......".split("");
        }
        main.buildBlocks();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void buildBlocks() {
        /*  ####    .#.     ..#     #       ##
                    ###     ..#     #       ##
                    .#.     ###     #
                                    #
        */

        Block block1 = new Block(4, 1, "####");
        Block block2 = new Block(3, 3, ".#.", "###", ".#.");
        Block block3 = new Block(3, 3, "..#", "..#", "###");
        Block block4 = new Block(1, 4, "#", "#", "#", "#");
        Block block5 = new Block(2, 2, "##", "##");
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
    }

    @Override
    public void part1() throws IOException {
        long currentBottom = lowestPoint;
        int i = 0;
        while (i < NUMBER_OF_ROCKS) {
            Block currentBlock = blocks.get(i % NUMBER_OF_BLOCKS);
            currentBottom = dropBlock(currentBottom, currentBlock);
            i++;
        }
        //System.out.println("Done: " + currentBottom + " - " + (lowestPoint - currentBottom));
        System.out.println("Done: " + actualBottom + " - " + currentBottom + " - " + lowestPoint);
    }

    private void shiftCurrentGrid(int offset) {
        String[][] tempGrid = new String[(int)lowestPoint][GRID_WIDTH];
        for (int y = 0; y < lowestPoint; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (y < offset) {
                    tempGrid[y] = ".......".split("");
                } else {
                    tempGrid[y][x] = grid[y - offset][x];
                }
            }
        }
        actualBottom += offset;

    }

    private long dropBlock(long currentBottom, Block block) {
        long currentY = currentBottom - block.height - 3;
        if (currentY < 0) {
            currentY = currentY + 50;
            currentBottom += 50;
            // shift grid 100 y pixels down
            shiftCurrentGrid((int) currentY);
        }
        int currentX = 2;
        boolean stopped = false;

        do {
            // push left/right if possible
            int move = getNextPush();
            // can go down?
            if (canMove(currentX, currentY, block, new int[]{0, move})) {
                currentX += move;
            }
            stopped = !canMove(currentX, currentY, block, new int[]{1,0});
            if (!stopped) {
                currentY++;
            }
        } while (!stopped);

        // add block to main grid
        for (int y = 0; y < block.height; y++) {
            for (int x = 0; x < block.width; x++) {
                String current = grid[(int) currentY + y][currentX + x];
                grid[(int) currentY + y][currentX + x] = current.equals("#") ? grid[(int) currentY + y][currentX + x] : block.grid[y][x];
            }
        }

        // print grid
        Utils.printGrid(GRID_WIDTH, (int) lowestPoint, 0, currentY < currentBottom ? (int) currentY : (int) currentBottom, grid);
        System.out.println("");
        // determine new currentLine
        return currentY < currentBottom ? currentY : currentBottom;
    }

    private boolean canMove(int currentX, long currentY, Block block, int[] move) {
        boolean canMove = true;
        if (currentX + move[1] >= 0 && currentX + block.width - 1 + move[1] < GRID_WIDTH) {
            for (int y = 0; y < block.height; y++) {
                for (int x = 0; x < block.width; x++) {
                    int testX = currentX + x + move[1];
                    long testY = currentY + y + move[0];
                    if (
                            currentY + y + move[0] >= lowestPoint || (
                                    block.grid[y][x].equals("#") &&
                                    grid[(int) testY][testX].equals("#")
                            )
                    ) {
                        canMove = false;
                        break;
                    }
                }
            }
        } else {
            canMove = false;
        }
        return canMove;
    }

    private int getNextPush() {
        int nextPush = INPUT.charAt(currentPush++) == '>' ? 1 : -1;
        if (currentPush >= INPUT.length()) {
            currentPush = 0;
        }
        return nextPush;
    }

    @Override
    public void part2() throws IOException {

    }

    class Block {
        private int width;
        private int height;
        private String[][] grid;

        public Block(int width, int height, String... rows) {
            this.width = width;
            this.height = height;
            this.grid = new String[this.height][this.width];

            for (int y = 0; y < rows.length; y++) {
                grid[y] = rows[y].split("");
            }
        }
    }
}
