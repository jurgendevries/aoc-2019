package year2020.day15;

import base.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Base {
    int[] input = {16,11,15,0,1,7};
    Map<Integer, Integer> valuesNamed = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        Integer out = null;

        for (int i = 0; i < 2019; i++) {
            if (i < input.length) {
                out = cycle(i, input[i]);
            } else {
                out = cycle(i, out);
            }
        }

        System.out.println(out);
    }

    private int cycle(int turn, int in) {
        int out;
        if (valuesNamed.containsKey(in)) {
            out = turn - valuesNamed.get(in);
            valuesNamed.put(in, turn);
        } else {
            out = 0;
            valuesNamed.put(in, turn);
        }
        return out;
    }

    @Override
    public void part2() throws IOException {
        valuesNamed = new HashMap<>();
        Integer out = null;

        for (int i = 0; i < 29999999; i++) {
            if (i < input.length) {
                out = cycle(i, input[i]);
            } else {
                out = cycle(i, out);
            }
        }

        System.out.println(out);
    }
}
