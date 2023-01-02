package year2022;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.util.*;

public class Day24 extends Base {
    private static final String INPUT = "2022/day24-input.txt";
    private static final int N = 0;
    private static final int E = 1;
    private static final int S = 2;
    private static final int W = 3;

    private static String[][] grid;
    private static int height;
    private static int width;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[] endPos;
    private Map<Integer, Set<String>> blizzardsPerMinute;

    public static void main(String[] args) throws IOException {
        Day24 main = new Day24();
        main.mainMethod(INPUT);
        main.prepareInput();

        long start = System.currentTimeMillis();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void initialize() {
        blizzardsPerMinute = new HashMap<>();
        height = instructions.size();
        width = instructions.get(0).length();

        grid = new String[height][width];
        for (int y = 0; y < instructions.size(); y++) {
            String instruction = instructions.get(y);
            grid[y] = instruction.split("");
        }

        for (int m = 0; m < (width - 2) * (height - 2); m++) {
            Set<String> blizzards = new HashSet<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if ("^".equals(grid[y][x]) ||
                            ">".equals(grid[y][x]) ||
                            "v".equals(grid[y][x]) ||
                            "<".equals(grid[y][x])) {
                        int dir = "^".equals(grid[y][x]) ? N :
                                ">".equals(grid[y][x]) ? E :
                                        "v".equals(grid[y][x]) ? S :
                                             W;
                        String blizzard;
                        switch (dir) {
                            case N:
                                blizzard = x + "," + (1 + Math.floorMod(y - 1 - m, height - 2));
                                break;
                            case E:
                                blizzard = (1 + (x - 1 + m) % (width - 2)) + "," + y;
                                break;
                            case S:
                                blizzard = x + "," + (1 + (y - 1 + m) % (height - 2));
                                break;
                            default:
                                blizzard = (1 + Math.floorMod(x - 1 - m, width - 2)) + "," + y;
                        }
                        blizzards.add(blizzard);
                    }
                }
            }
            blizzardsPerMinute.put(m, blizzards);
        }
        endPos = new int[]{height - 1, width - 2};
    }

    @Override
    public void part1() throws IOException {
        initialize();
        Utils.printGrid(width, height, grid);
        System.out.println(findRoute(true));
    }

    @Override
    public void part2() throws IOException {
        System.out.println(findRoute(false));
    }

    private int findRoute(boolean part1) {
        LinkedList<State> queue = new LinkedList<>();
        Set<String> cache = new HashSet<>();

        queue.add(new State(new Position(1, 0), 0, false, false));

        while(!queue.isEmpty()) {
            State state = queue.pop();

            // end reached?
            if (state.position.y == endPos[0] && state.position.x == endPos[1]) {
                if (part1 || state.startVisited) {
                    return state.minute;
                } else {
                    state = new State(state.position, state.minute, false, true);
                }
            }

            // start
            if (state.position.y == 0 && state.endVisited) {
                state = new State(state.position, state.minute, true, state.endVisited);
            }

            String stateString = createStateString(state);
            if (cache.contains(stateString)) {
                continue;
            }

            cache.add(stateString);
            Set<String> blizzards = blizzardsPerMinute.get((state.minute + 1) % ((width - 2) * (height - 2)));
            if (!blizzards.contains(state.position.x + "," + state.position.y)) {
                // wait
                queue.addLast(new State(state.position, state.minute + 1, state.startVisited, state.endVisited));
            }
            for (int[] dir : directions) {
                Position newPos = new Position(state.position.x + dir[1], state.position.y + dir[0]);
                if (isInGrid(newPos)) {
                    if (!blizzards.contains(newPos.x + "," + newPos.y)) {
                        queue.addLast(new State(newPos, state.minute + 1, state.startVisited, state.endVisited));
                    }
                }
            }
        }
        return 0;
    }

    private boolean isInGrid(Position p) {
        return p.x > 0 && p.x < width - 1 &&
                (p.y > 0 || (p.y == 0 && p.x == 1)) &&
                (p.y < height - 1 || (p.y == height - 1 && p.x == width - 2));
    }

    private String createStateString(State state) {
        return state.minute + "-" + state.position.x + "-" + state.position.y + "-" + (state.startVisited ? "t" : "f") + "-" + (state.endVisited ? "t" : "f");
    }

    class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class State {
        private Position position;
        private int minute;
        private boolean startVisited;
        private boolean endVisited;

        public State(Position position, int minute, boolean startVisited, boolean endVisited) {
            this.position = position;
            this.minute = minute;
            this.startVisited = startVisited;
            this.endVisited = endVisited;
        }
    }
}
