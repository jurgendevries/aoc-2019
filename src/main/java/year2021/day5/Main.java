package year2021.day5;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day5-input.txt";
    private static int HEIGHT;
    private static int WIDTH;

    private static List<String> instructions;

    private String[][] grid;


    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
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
        List<VentLine> vLines = storeVentLines();

        addVentLines(vLines, false);

        drawGrid();

        int totalTwos = count();
        System.out.println(totalTwos);
    }

    private List<VentLine> storeVentLines() {
        List<VentLine> vLines = new ArrayList<>();
        for(String instruction : instructions) {
            String[] points = instruction.split("->");
            Point start = createPoint(points[0]);
            Point end = createPoint(points[1]);
            boolean isDiagonal = !(start.getX() == end.getX() || start.getY() == end.getY());
            VentLine vLine = new VentLine(start, end, isDiagonal);
            vLines.add(vLine);
        }

        int maxStartX = vLines.stream().max(Comparator.comparing(x -> x.getStart().getX())).get().getStart().getX();
        int maxEndX = vLines.stream().max(Comparator.comparing(x -> x.getEnd().getX())).get().getEnd().getX();
        int maxStartY = vLines.stream().max(Comparator.comparing(x -> x.getStart().getY())).get().getStart().getY();
        int maxEndY = vLines.stream().max(Comparator.comparing(x -> x.getEnd().getY())).get().getEnd().getY();

        WIDTH = 1 + (maxStartX > maxEndX ? maxStartX : maxEndX);
        HEIGHT = 1 + (maxStartY > maxEndY ? maxStartY : maxEndY);

        grid = new String[HEIGHT][WIDTH];
        initGrid();

        return vLines;
    }

    private int count() {
        int total = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                String value = grid[y][x];
                if ((!value.equals(".") && Integer.parseInt(value) >= 2)) {
                    total++;
                }
            }
        }
        return total;
    }

    private void addVentLines(List<VentLine> vLines, boolean useDiagonals) {
        for (VentLine vl : vLines) {
            if (!vl.isDiagonal() || useDiagonals) {
                addVentLine(vl);
            }
        }
    }

    private void addVentLine(VentLine vl) {
        boolean isHorizontal = !vl.isDiagonal() && vl.getStart().getX() != vl.getEnd().getX();

        int startX = vl.getStart().getX() > vl.getEnd().getX() ? vl.getEnd().getX() : vl.getStart().getX();
        int endX = vl.getStart().getX() > vl.getEnd().getX() ? vl.getStart().getX() : vl.getEnd().getX();
        int startY = vl.getStart().getY() > vl.getEnd().getY() ? vl.getEnd().getY() : vl.getStart().getY();
        int endY = vl.getStart().getY() > vl.getEnd().getY() ? vl.getStart().getY() : vl.getEnd().getY();

        if (vl.isDiagonal()) {
            int steps = Math.abs(endX - startX);
            int diagonalX = vl.getStart().getX();
            int diagonalY = vl.getStart().getY();
            for (int s = 0; s <= steps; s++) {
                int x = vl.getStart().getX() > vl.getEnd().getX() ? diagonalX - s : diagonalX + s;
                int y = vl.getStart().getY() > vl.getEnd().getY() ? diagonalY - s : diagonalY + s;
                grid[y][x] = grid[y][x].equals(".") ? "1" : String.valueOf(Integer.parseInt(grid[y][x]) + 1);
            }
        } else if (isHorizontal) {
            int y = vl.getStart().getY();
            for (int x = startX; x <= endX; x++) {
                grid[y][x] = grid[y][x].equals(".") ? "1" : String.valueOf(Integer.parseInt(grid[y][x]) + 1);
            }
        } else  {
            int x = vl.getStart().getX();
            for (int y = startY; y <= endY; y++) {
                grid[y][x] = grid[y][x].equals(".") ? "1" :  String.valueOf(Integer.parseInt(grid[y][x]) + 1);
            }
        }
    }

    private Point createPoint(String pointString) {
        return new Point(Integer.parseInt(pointString.trim().split(",")[0]),
                Integer.parseInt(pointString.trim().split(",")[1]));
    }

    private void initGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = ".";
            }
        }
    }

    private void drawGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < HEIGHT; x++) {
                sb.append(grid[y][x]);

            }
            System.out.println(sb);
        }
    }

    @Override
    public void part2() throws IOException {
        List<VentLine> vLines = storeVentLines();
        addVentLines(vLines, true);

        drawGrid();

        int totalTwoOrMore = count();
        System.out.println(totalTwoOrMore);
    }

    class VentLine {
        private Point start;
        private Point end;
        private boolean isDiagonal;

        public VentLine(Point start, Point end, boolean isDiagonal) {
            this.start = start;
            this.end = end;
            this.isDiagonal = isDiagonal;
        }

        public Point getStart() {
            return start;
        }

        public void setStart(Point start) {
            this.start = start;
        }

        public Point getEnd() {
            return end;
        }

        public void setEnd(Point end) {
            this.end = end;
        }

        public boolean isDiagonal() {
            return isDiagonal;
        }

        public void setDiagonal(boolean diagonal) {
            isDiagonal = diagonal;
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
