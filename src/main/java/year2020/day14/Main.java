package year2020.day14;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2020/day14-input.txt";
    private static Map<Long, Long> values = new HashMap<>();

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
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        String mask = "";
        for (String instruction : instructions) {
            String action = instruction.split("=")[0].trim();
            String val = instruction.split("=")[1].trim();

            if ("mask".equals(action)) {
                mask = val;
            } else {
                long memPos = Long.parseLong(action.substring(4, action.length() - 1));
                //mem[8] = 11
                Long longVal = Long.valueOf(val);
                String longByteString = toBitString36(longVal);
                String maskedLongByteString = applyMask(mask, longByteString);
                long endVal = bitToLongVal(maskedLongByteString);

                values.put(memPos, endVal);
            }
        }

        long total = 0;

        for (Map.Entry<Long, Long> entry : values.entrySet()) {
            total += entry.getValue();
        }

        System.out.println(total);
    }

    @Override
    public void part2() throws IOException {
        String mask = "";
        values = new HashMap<>();
        for (String instruction : instructions) {
            String action = instruction.split("=")[0].trim();
            String val = instruction.split("=")[1].trim();

            if ("mask".equals(action)) {
                mask = val;
            } else {
                long memPos = Long.parseLong(action.substring(4, action.length() - 1));
                //mem[8] = 11
                Long longVal = Long.valueOf(val);

                String longByteStringMem = toBitString36(memPos);
                String[] maskedMemPositions = applyMask2(mask, longByteStringMem);

                for (String memP : maskedMemPositions) {
                    long endPos = bitToLongVal(memP);
                    values.put(endPos, longVal);
                }
            }
        }

        long total = 0;

        for (Map.Entry<Long, Long> entry : values.entrySet()) {
            total += entry.getValue();
        }

        System.out.println(total);
    }

    private String[] applyMask2(String mask, String longByteString) {
        List<String> possibleValues = new ArrayList<>();
        possibleValues.add(longByteString);
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                long initialSize = possibleValues.size();
                for (int j = 0; j < initialSize; j++) {
                    String currentVal = possibleValues.get(j);
                    if (currentVal.charAt(i) == '0') {
                        currentVal = currentVal.substring(0, i) + '1' + currentVal.substring(i + 1);
                        possibleValues.add(currentVal);
                    } else {
                        currentVal = currentVal.substring(0, i) + '0' + currentVal.substring(i + 1);
                        possibleValues.add(currentVal);
                    }
                }
            } else if (mask.charAt(i) == '1') {
                for (int j = 0; j < possibleValues.size(); j++) {
                    String currentVal = possibleValues.get(j);
                    currentVal = currentVal.substring(0, i) + mask.charAt(i) + currentVal.substring(i + 1);
                    possibleValues.set(j, currentVal);
                }
            }



        }
        return possibleValues.toArray(new String[possibleValues.size()]);
    }

    private String applyMask(String mask, String longByteString) {
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                continue;
            }

            longByteString = longByteString.substring(0, i) + mask.charAt(i) + longByteString.substring(i + 1);

        }
        return longByteString;
    }

    private long bitToLongVal(String maskedLongByteString) {
        return Long.parseLong(maskedLongByteString, 2);
    }

    private String toBitString36(Long value) {
        String bits = Long.toString(value, 2);
        StringBuilder sb = new StringBuilder();

        for (int toPrepend= 36 - bits.length(); toPrepend > 0; toPrepend--) {
            sb.append('0');
        }

        sb.append(bits);

        return sb.toString();

    }
}
