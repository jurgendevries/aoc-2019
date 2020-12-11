package year2020.day7;

import base.Base;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Base {
    private static final String INPUT = "2020/day7-input.txt";
    private static final Pattern TYPE_PATTERN = Pattern.compile("(\\d+)(.+)");

    private static int total = 0;

    private static List<String> instructions;
    private static List<String> possibleBagTypes;
    private static Map<String, Integer> bagAmounts;

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
        possibleBagTypes = new ArrayList<>();
        String match = "shiny gold bag";
        process(match);
        System.out.println("total: " + total);
    }

    private void process(String match) {
        for (String instruction : instructions) {
            String bagType = instruction.split("contain")[0].trim();
            bagType = bagType.endsWith("s") ? bagType.substring(0, bagType.length() - 1): bagType;
            String containedTypes = instruction.split("contain")[1].trim();

            // split into amount and type
            String[] containerTypes = containedTypes.split(",");

            List<String> types = new ArrayList<>();

            for (String type : containerTypes) {
                Matcher m = TYPE_PATTERN.matcher(type);
                if (m.find()) {
                    int amount = Integer.parseInt(m.group(1));
                    String typeString = m.group(2).trim().replace(".", "");
                    typeString = typeString.endsWith("s") ? typeString.substring(0, typeString.length() - 1): typeString;
                    types.add(typeString);
                }


            }

            // containedTypes contains match?
            if (types.contains(match)) {
                if (!possibleBagTypes.contains(bagType)) {
                    possibleBagTypes.add(bagType);
                    total++;
                    process(bagType);
                }
            }
        }
    }


    @Override
    public void part2() throws IOException {
        total = 0;
        bagAmounts = new HashMap<>();
        String match = "shiny gold bag";


        System.out.println("total: " + (process2(match, bagAmounts) - 1));

    }

    private int process2(String name, Map<String, Integer> bagAmountsMap) {
        if (bagAmountsMap.containsKey(name)) {
            return bagAmountsMap.get(name);
        }
        String line = getLine(name);

        String containedTypes = line.split("contain")[1].trim();
        String[] containerTypes = containedTypes.split(",");

        int totalAmount = 1;

        for (String type : containerTypes) {
            Matcher m = TYPE_PATTERN.matcher(type);
            if (m.find()) {
                int amount = Integer.parseInt(m.group(1));
                String typeString = m.group(2).trim().replace(".", "");
                typeString = typeString.endsWith("s") ? typeString.substring(0, typeString.length() - 1): typeString;

                totalAmount += amount * process2(typeString, bagAmountsMap);
            } else {
                bagAmountsMap.put(name, 1);
                return 1;
            }
        }

        return totalAmount;
    }

    private String getLine(String name) {
        for (String line : instructions) {
            if (line.startsWith(name)) {
                return line;
            }
        }
        return null;
    }


}
