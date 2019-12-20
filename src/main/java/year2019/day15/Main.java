package year2019.day15;

import base.Base;
import year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day15-input.txt";
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    private Map<Long, String> instructionMap;
    private static String inputString = "";
    private String[] instructions;

    private static String[][] grid = new String[HEIGHT][WIDTH];

    List<Coordinate> visitedLocations = new ArrayList<>();
    List<Coordinate> wallLocations = new ArrayList<>();
    Coordinate finish = null;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
//        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        solveMaze(WIDTH/2, HEIGHT/2, ic, true);
    }

    @Override
    public void part2() throws IOException {
        prepare();
        IntcodeComputer ic = new IntcodeComputer(instructionMap);
        solveMaze(WIDTH/2, HEIGHT/2, ic, false);
        fillWithOxygen(12, 7);
        grid[7][12] = "O";
        List<SimpleCoordinate> oxyFields = new ArrayList<>();
        List<SimpleCoordinate> noOxyFields = new ArrayList<>();

        oxyFields.add(new SimpleCoordinate(12, 7));

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (isAlreadyVisited(x, y) && !grid[y][x].equals("O")) {
                    noOxyFields.add(new SimpleCoordinate(x, y));
                }
            }
        }

        printGrid();

        int minutes = 0;

        while (!noOxyFields.isEmpty()) {
            List<SimpleCoordinate> removeableFields = new ArrayList<>();
            for (SimpleCoordinate sc : oxyFields) {
                for (int[] dir : DIRECTIONS) {
                    Optional<SimpleCoordinate> posNoOxyField = noOxyFields.stream().filter(c -> c.getX() == sc.getX() + dir[0] && c.getY() == sc.getY() + dir[1]).findFirst();
                    if (posNoOxyField.isPresent()) {
                        if (grid[sc.getY() + dir[1]][sc.getX() + dir[0]] != "O") {
                            grid[sc.getY() + dir[1]][sc.getX() + dir[0]] = "O";
                            removeableFields.add(posNoOxyField.get());

                        }
                    }
                }
            }

            for (SimpleCoordinate removeableField : removeableFields) {
                noOxyFields.remove(removeableField);
                oxyFields.add(removeableField);
            }
            minutes++;
            printGrid();
            System.out.println(minutes);
        }

        printGrid();

        System.out.println(minutes);
    }

    private void fillWithOxygen(int x, int y) {
        grid[y][x] = "O";
    }

    private void prepare() throws IOException {
        inputString = input.readLine();
        instructions = inputString.split(",");
        instructionMap = new HashMap<>();
        for (long i = 0; i < instructions.length; i++) {
            instructionMap.put(i, instructions[Math.toIntExact(i)]);
        }
    }

    private  void solveMaze(Coordinate current, IntcodeComputer ic, boolean stopAtLocation) {
        for (int dir : current.getPossibleDirections()) {
            if (finish != null && stopAtLocation) {
                break;
            }
            int[] step = DIRECTIONS[dir - 1];

            if (!isValidLocation(current.getX() + step[0], current.getY() + step[1]) ||
                    isAlreadyVisited(current.getX() + step[0], current.getY() + step[1]) ||
                    isWallLocationVisited(current.getX() + step[0], current.getY() + step[1])) {
                continue;
            }

            int backDir = dir == 1 ? 2 : dir == 2 ? 1 : dir == 3 ? 4 : 3;
            List<Integer> possibleDirections = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                if (i != backDir) {
                    possibleDirections.add(i);
                }
            }

            ic.setInputs(new ArrayList<>(Arrays.asList(Long.valueOf(dir))));
            List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();

            if (output.get(0).getOutputValue() == 0) {
                Coordinate toVisit = new Coordinate(current.getX() + step[0], current.getY() + step[1], backDir, possibleDirections, current);
                if (!isWallLocationVisited(toVisit.getX(), toVisit.getY())) {
                    wallLocations.add(toVisit);
                }
                continue;
            } else if (output.get(0).getOutputValue() == 1 || (!stopAtLocation && output.get(0).getOutputValue() == 2)) {
                Coordinate toVisit = new Coordinate(current.getX() + step[0], current.getY() + step[1], backDir, possibleDirections,  current);
                if (!isAlreadyVisited(toVisit.getX(), toVisit.getY())) {
                    visitedLocations.add(toVisit);
                }
                solveMaze(toVisit, ic, stopAtLocation);
            } else {
                finish = current;
                break;
            }
        }

        // backtrack
        if (finish == null && current.getBackDir() != null) {
            ic.setInputs(new ArrayList<>(Arrays.asList(Long.valueOf(current.getBackDir()))));
            List<IntcodeComputer.IntcodeComputerOutput> output = ic.executeInstructions();
            assert output.get(0).getOutputValue() == 1;
        }
    }

    private void solveMaze(int x, int y, IntcodeComputer ic, boolean stopAtLocation) {
        Coordinate start = new Coordinate(x, y, null, Arrays.asList(1,2,3,4), null);
        visitedLocations.add(start);

        solveMaze(start, ic, stopAtLocation);

        List<Coordinate> backtrack = backTrackPath(finish);

        fillGrid();
        printGrid();

        System.out.println(backtrack.size());
    }

    private boolean isWallLocationVisited(int x, int y) {
        return wallLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isAlreadyVisited(int x, int y) {
        return visitedLocations.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().isPresent();
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private List<Coordinate> backTrackPath(Coordinate current) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = current;

        while (iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }

        return path;
    }

    private void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                sb.append(grid[y][x]);
            }
            System.out.println(sb);
        }
    }

    private void fillGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean isWall = isWallLocationVisited(x,y);
                boolean isVisited = isAlreadyVisited(x,y);
                grid[y][x] = isVisited ? "." : isWall ? "#" : " ";
            }
        }
    }

    public class SimpleCoordinate {
        private int x;
        private int y;

        public SimpleCoordinate(int x, int y) {
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

    public class Coordinate {
        private int x;
        private int y;
        private Integer backDir;
        private List<Integer> possibleDirections;
        private Coordinate parent;
        private boolean containsOxygen;

        public Coordinate(int x, int y, Integer backDir, List<Integer> possibleDirections, Coordinate parent) {
            this.x = x;
            this.y = y;
            this.backDir = backDir;
            this.possibleDirections = possibleDirections;
            this.parent = parent;
            this.containsOxygen = false;
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

        public Integer getBackDir() {
            return backDir;
        }

        public void setBackDir(Integer backDir) {
            this.backDir = backDir;
        }

        public List<Integer> getPossibleDirections() {
            return possibleDirections;
        }

        public void setPossibleDirections(List<Integer> possibleDirections) {
            this.possibleDirections = possibleDirections;
        }

        public Coordinate getParent() {
            return parent;
        }

        public void setParent(Coordinate parent) {
            this.parent = parent;
        }

        public boolean isContainsOxygen() {
            return containsOxygen;
        }

        public void setContainsOxygen(boolean containsOxygen) {
            this.containsOxygen = containsOxygen;
        }
    }
}
