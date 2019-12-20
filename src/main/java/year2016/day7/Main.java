package year2016.day7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String INPUT = "day7-input.txt";

    private static int counterTLS = 0;
    private static int counterSSL = 0;
    public static void main(String[] args) throws IOException {

        int lineCounter = 0;

        File file = new File("D:\\projects\\adventofcode\\src\\main\\resources\\"+ INPUT);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            boolean hasValidABBA = false;
            boolean hasSquareABBA = false;
            List<Integer> bracketsLeft = new ArrayList<>();
            List<Integer> bracketsRight = new ArrayList<>();

            Matcher matcherLeft = Pattern.compile("\\[").matcher(line);
            Matcher matcherRight = Pattern.compile("\\]").matcher(line);

            while (matcherLeft.find()) {
                bracketsLeft.add(matcherLeft.start());
            }

            while (matcherRight.find()) {
                bracketsRight.add(matcherRight.start());
            }


            List<String> squareParts = new ArrayList<>();
            List<String> validParts = new ArrayList<>();
            for (int i = 0; i < bracketsLeft.size(); i++) {
                if (i == 0) {
                    validParts.add(line.substring(0, bracketsLeft.get(i)));
                }

                // square part
                squareParts.add(line.substring(bracketsLeft.get(i) + 1, bracketsRight.get(i)));

                // right side
                try {
                    validParts.add(line.substring(bracketsRight.get(i) + 1, bracketsLeft.get(i + 1)));
                } catch (IndexOutOfBoundsException e) {
                    validParts.add(line.substring(bracketsRight.get(i) + 1));
                }
            }

            List<String> BAB = new ArrayList<>();
            List<String> ABA = new ArrayList<>();

            for (String squarePart : squareParts) {
                if (hasABBA(squarePart)) {
                    hasSquareABBA = true;
                }
                findABABs(squarePart, BAB);
            }

            for (String validPart : validParts) {
                if (!hasSquareABBA) {
                    if (hasABBA(validPart)) {
                        hasValidABBA = true;
                    }
                }
                findABABs(validPart, ABA);
            }

            if (!hasSquareABBA && hasValidABBA) {
                counterTLS++;
            }

            boolean ABABMatchFound = false;

            for (String bab : BAB) {
                for (String aba : ABA) {
                    if (aba.charAt(0) == bab.charAt(1) && aba.charAt(1) == bab.charAt(0)) {
                        counterSSL ++;
                        ABABMatchFound = true;

                        System.out.println(lineCounter + ": ABA = " + aba + ", BAB = " + bab + ", line = " + line);
                        break;
                    }
                }

                if (ABABMatchFound) {
                    break;
                }
            }
            lineCounter++;
        }

        System.out.println("counterTLS = [" + counterTLS + "]" + "counterSSL = [" + counterSSL + "]");
    }

    private static void findABABs(String linePart, List<String> ABAB) {
        Character first, second, third;
        for (int i = 0; i < linePart.length() - 2; i++) {
            first = linePart.charAt(i);
            second = linePart.charAt(i + 1);
            if (first.equals(second)) {
                continue;
            } else {
                third = linePart.charAt(i + 2);
                if (first.equals(third)) {
                    ABAB.add("" + first + second + third);
                }
            }
        }
    }

    private static boolean hasABBA(String linePart) {
        Character first, second, third, fourth;

        for (int i = 0; i < linePart.length() - 3; i++) {
            first = linePart.charAt(i);
            second = linePart.charAt(i + 1);
            if (first.equals(second)) {
                continue;
            } else {
                third = linePart.charAt(i + 2);
                if (second.equals(third)) {
                    fourth = linePart.charAt(i + 3);
                    if (first.equals(fourth)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
