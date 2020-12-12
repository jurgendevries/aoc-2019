package year2020.day12;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day12-input.txt";
    private static int x;
    private static int y;
    private static int wayPointXDiff;
    private static int wayPointYDiff;

    // direction: 0=E,1=S,2=W,3=N
    private static int dir;

    private static List<String> instructions;

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
        //main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {

        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            move(action, value);
        }

        int total = Math.abs(x) + Math.abs(y);
        System.out.println(total);

    }

    private void move(char action, int value) {
        switch (action) {
            case 'E':
                x += value;
                break;
            case 'S':
                y += value;
                break;
            case 'W':
                x -= value;
                break;
            case 'N':
                y -= value;
                break;
            case 'L':
                dir -= value / 90;
                dir = dir < 0 ? dir += 4 : dir;
                break;
            case 'R':
                dir += value / 90;
                dir = dir > 3 ? dir -= 4 : dir;
                break;
            case 'F':
                char direction = dir == 0 ? 'E' : dir == 1 ? 'S' : dir == 2 ? 'W' : 'N';
                move(direction, value);
                break;
        }
    }


    @Override
    public void part2() throws IOException {
        x = 0;
        y = 0;
        dir = 0;
        wayPointXDiff = 10;
        wayPointYDiff = -1;

        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            move2(action, value);
        }

        int total = Math.abs(x) + Math.abs(y);
        System.out.println(total);
    }

    private void move2(char action, int value) {
        switch (action) {
            case 'E':
                wayPointXDiff += value;
                break;
            case 'S':
                wayPointYDiff += value;
                break;
            case 'W':
                wayPointXDiff -= value;
                break;
            case 'N':
                wayPointYDiff -= value;
                break;
            case 'L':
                int turns = value / 90;

                for (int i = 0; i < turns; i++) {
                    int tempX = wayPointXDiff;
                    wayPointXDiff = wayPointYDiff;
                    wayPointYDiff = -tempX;
                }
                break;
            case 'R':
                int turnsR = value / 90;

                for (int i = 0; i < turnsR; i++) {
                    int tempX = wayPointXDiff;
                    wayPointXDiff = -wayPointYDiff;
                    wayPointYDiff = tempX;
                }
                break;
            case 'F':
                x += value * wayPointXDiff;
                y += value * wayPointYDiff;
                break;
        }
    }
}
