package year2016.day11;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final int HEIGHT = 4;
    private static final int WIDTH = 10;
    private static Element[][] grid = new Element[HEIGHT][WIDTH];
    private static boolean done = false;
    private static Elevator elevator;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.part1();
    }

    @Override
    public void part1() throws IOException {
        prepareGrid();
        fillGrid();
        printGrid();

        elevator = new Elevator();

        while(!done) {
            makeAMove();
        }
    }

    @Override
    public void part2() throws IOException {

    }

    private void makeAMove() {
        List<Element> elementsCurrentFloor = getElementsOnFloor(elevator.getFloor());
        List<Element> elementsAbove;
        List<Element> elementsBelow;
        // what is on the current floor

        // what is above / below
        if (elevator.getFloor() == 0) {
            elementsAbove = getElementsOnFloor(elevator.getFloor() + 1);
        } else if (elevator.getFloor() == 3) {
            elementsBelow = getElementsOnFloor(elevator.getFloor() - 1);
        } else {
            elementsAbove = getElementsOnFloor(elevator.getFloor() + 1);
            elementsBelow = getElementsOnFloor(elevator.getFloor() - 1);
        }


        // what is in the elevator
        // what can you bring to above/below floor
        done = true;
    }

    private List<Element> getElementsOnFloor(int floor) {
        List<Element> elements = new ArrayList<>();
        for (int x = 0; x < WIDTH; x++) {
            elements.add(grid[floor][x]);
        }
        return elements;
    }


    public void prepareGrid() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = null;
            }
        }
    }

    private void fillGrid() {
        Element thg = new Element("THG", "TH", true);
        Element thm = new Element("THM", "TH", false);
        Element plg = new Element("PLG", "PL", true);
        Element stg = new Element("STG", "ST", true);
        Element plm = new Element("PLM", "PL", false);
        Element stm = new Element("STM", "ST", false);
        Element prg = new Element("PRG", "PR", true);
        Element prm = new Element("PRM", "PR", false);
        Element rug = new Element("RUG", "RU", true);
        Element rum = new Element("RUM", "RU", false);

        grid[0][0] = thg;
        grid[0][1] = thm;
        grid[0][2] = plg;
        grid[0][3] = stg;
        grid[1][4] = plm;
        grid[1][5] = stm;
        grid[2][6] = prg;
        grid[2][7] = prm;
        grid[2][8] = rug;
        grid[2][9] = rum;
    }

    public void printGrid() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            StringBuilder sb = new StringBuilder().append("" + y + ": ");
            for (int x = 0; x < WIDTH; x++) {
                if (grid[y][x] == null) {
                    sb.append(" .  ");
                } else {
                    sb.append(grid[y][x].getId() + " ");
                }
            }
            System.out.println(sb);
        }
        System.out.println("");
    }

    public class Element {
        private String id;
        private String type;
        private boolean generator;

        public Element(String id, String type, boolean generator) {
            this.id = id;
            this.type = type;
            this.generator = generator;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isOfType(String type) {
            return type.equals(this.type);
        }

        public boolean isGenerator() {
            return generator;
        }
    }

    public class Elevator {
        private int floor;
        private List<String> inventory;

        public Elevator() {
            this.floor = 0;
            this.inventory = new ArrayList<>();
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public List<String> getInventory() {
            return inventory;
        }

        public void setInventory(List<String> inventory) {
            this.inventory = inventory;
        }
    }
}
