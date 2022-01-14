package year2021.day24;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2021/day24-test.txt";
    private static List<String> instructions;
    private static Map<String, Integer> valueMap = new HashMap<>();


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
        // w,x,y,z = 0

        //String inputtest = "13579246899999";
//        String inputtest = "11111111111111";
        //String inputtest = "99979999999999";
        //String inputtest = "55555555555555";

        for (int i = 11; i <= 99; i++) {
//        for(long i = 99979111111111L; i <= 99979999999999L; i++) {
            valueMap.put("w", 0);
            valueMap.put("x", 0);
            valueMap.put("y", 0);
            valueMap.put("z", 0);
            int inputCounter = 0;
            for (String instruction : instructions) {
                String operator = instruction.split(" ")[0];
                if (operator.equals("inp")) {
                    valueMap.put(instruction.split(" ")[1], Character.getNumericValue(String.valueOf(i).charAt(inputCounter++)));
                } else {
                    String variableA = instruction.split(" ")[1];
                    String variableB = instruction.split(" ")[2];
                    switch (operator) {
                        case "add":
                            add(variableA, variableB);
                            break;
                        case "mul":
                            mul(variableA, variableB);
                            break;
                        case "div":
                            div(variableA, variableB);
                            break;
                        case "mod":
                            mod(variableA, variableB);
                            break;
                        case "eql":
                            eql(variableA, variableB);
                            break;
                    }
                }
            }
            if (valueMap.get("z") == 0) {
                System.out.println(i + ": " + valueMap.get("z"));
            }
        }
    }

    private void add(String a, String b) {
        int valueA = valueMap.get(a);
        int valueB = isNumeric(b) ? Integer.parseInt(b) : valueMap.get(b);
        valueMap.put(a, valueA + valueB);
    }

    private void mul(String a, String b) {
        int valueA = valueMap.get(a);
        int valueB = isNumeric(b) ? Integer.parseInt(b) : valueMap.get(b);
        valueMap.put(a, valueA * valueB);
    }

    private void div(String a, String b) {
        int valueA = valueMap.get(a);
        int valueB = isNumeric(b) ? Integer.parseInt(b) : valueMap.get(b);
//        if (valueB == 0) {
//            return;
//        }
        valueMap.put(a, valueA / valueB);
    }

    private void mod(String a, String b) {
        int valueA = valueMap.get(a);
        int valueB = isNumeric(b) ? Integer.parseInt(b) : valueMap.get(b);
//        if (valueA < 0 || valueB <= 0) {
//            return;
//        }
        valueMap.put(a, valueA % valueB);
    }

    private void eql(String a, String b) {
        int valueA = valueMap.get(a);
        int valueB = isNumeric(b) ? Integer.parseInt(b) : valueMap.get(b);
        valueMap.put(a, valueA == valueB ? 1 : 0);
    }

    @Override
    public void part2() throws IOException {

    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
