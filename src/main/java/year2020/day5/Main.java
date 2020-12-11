package year2020.day5;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day5-input.txt";

    private static final int ROWS = 128;
    private static final int COLUMNS = 8;

    private static List<String> instructions;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        int maxSeat = 0;

        for (String instruction : instructions) {
            int seat = calculateSeat(instruction);

            maxSeat = seat > maxSeat ? seat : maxSeat;
        }

        System.out.println("Number of max seat: " + maxSeat);
    }

    private int calculate(String instruction, int count, char frontPart) {
        int result = count - 1;

        for (char c : instruction.toCharArray()) {
            count /= 2;

            if (c == frontPart) {
                result -= count;
            }
        }

        return result;
    }

    private int calculateSeat(String instruction) {
        String rowString = instruction.substring(0, 7);
        String colString = instruction.substring(7);

        int row = calculate(rowString, ROWS, 'F');
        int column = calculate(colString, COLUMNS, 'L');
        int seat = row * 8 + column;

        //System.out.println("row: " + row + ", column: " + column + ", seat: " + seat);

        return seat;
    }

    @Override
    public void part2() throws IOException {
        int maxSeat = 0;
        int minSeat = 832;

        List<Integer> listOfSeats = new ArrayList<>();

        for (String instruction : instructions) {
            int seat = calculateSeat(instruction);
            listOfSeats.add(seat);

            maxSeat = seat > maxSeat ? seat : maxSeat;
            minSeat = seat < minSeat ? seat : minSeat;
        }

        int totalCount = 0;
        for (int i = minSeat; i <= maxSeat; i++) {
            totalCount += i;
        }

        int totalListCount = 0;
        for (int j : listOfSeats) {
            totalListCount +=j;
        }

        int yourSeat = totalCount - totalListCount;
        System.out.println("Your seat: " + yourSeat);
    }
}
