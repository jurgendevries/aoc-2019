package year2019.day1;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2019\\day1-input.txt";

    private static int total = 0;
    private static List<String> instructions;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        total = 0;
        System.out.println("PART1:");
        for (String instruction : instructions) {
            getRequiredFuelForModuleOnce(Integer.parseInt(instruction));
        }
        System.out.println(total);
        System.out.println("");
    }



    @Override
    public void part2() throws IOException {
        total = 0;
        System.out.println("PART2:");
        for (String instruction : instructions) {
            getRequiredFuelForModule(Integer.parseInt(instruction));
        }
        System.out.println(total);
    }

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    private void getRequiredFuelForModuleOnce(int mass) {
        int fuelRequired = (int) (Math.floor(mass/3) - 2);
        total += fuelRequired;
    }

    private void getRequiredFuelForModule(int mass) {
        int fuelRequired = (int) (Math.floor(mass/3) - 2);
        total += fuelRequired;

        if ((int) (Math.floor(fuelRequired /3) - 2) > 0) {
            getRequiredFuelForModule(fuelRequired);
        }
    }
}
