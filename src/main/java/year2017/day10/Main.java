package year2017.day10;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Base {
    private static final String TEST = "3,4,1,5";
    private static final String INPUT = "230,1,2,221,97,252,168,169,57,99,0,254,181,255,235,167";



    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("PART1:");
        main.part1();
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        int listLength = 256;
        List<Integer> steps = Arrays.stream(INPUT.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int[] cord = IntStream.range(0, listLength).toArray();

        int currentPos = 0;
        int skipSize = 0;
        int startIndexShiftedTo = 0;
        for (int step : steps) {
            cord = hashCord(cord, listLength, step);
            currentPos += step + skipSize < cord.length ? step + skipSize : (currentPos + step + skipSize) % listLength;
            skipSize++;

            // shift array
            int[] newArr = new int[listLength];
            System.arraycopy(cord, currentPos, newArr, 0, listLength - currentPos);
            System.arraycopy(cord, 0, newArr, listLength - currentPos, currentPos);
            cord = newArr;
            startIndexShiftedTo = (listLength - currentPos + startIndexShiftedTo) % listLength;
            currentPos = 0;
        }

        System.out.println(cord[startIndexShiftedTo] * cord[startIndexShiftedTo + 1]);
    }

    private int[] hashCord (int[] cord, int listLength, int step) {
        int[] newCord = new int[listLength];
        // get sub array
        int[] subArray = Arrays.copyOfRange(cord, 0, step);

        // reverse sub array
        int[] revArray = new int[step];
        for (int i = step - 1; i >= 0; i--) {
            revArray[step - 1 - i] = subArray[i];
        }

        // replace array part
        System.arraycopy(revArray, 0, newCord, 0, step);
        System.arraycopy(cord, step, newCord, step, listLength - step);

        return newCord;
    }

    @Override
    public void part2() throws IOException {

    }
}
