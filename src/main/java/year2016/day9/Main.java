package year2016.day9;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String INPUT = "day9-input.txt";
    private static Matcher matcher;
    private static long counter = 0;

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\projects\\adventofcode\\src\\main\\resources\\"+ INPUT);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            part1(line);
            part2(line);
        }
    }

    private static void part1(String line) {
        StringBuilder sb = new StringBuilder();
        matcher = Pattern.compile("\\(\\d+x\\d+\\)").matcher(line);
        findNextMatch(0, sb, line);
        System.out.println("exp = [" + sb + "]" + ", decompressed = [" + sb.length() + "]");
    }

    private static void part2(String line) {
        counter = 0;
        findMatches(0, line);
        System.out.println("decompressed = [" + counter + "]");
    }

    private static void findNextMatch(int start, StringBuilder sb, String line) {
        if (matcher.find(start)) {
            sb.append(line.substring(start, matcher.start()));
            String exp = line.substring(matcher.start() + 1, matcher.end() - 1);

            int numberOfChars = Integer.parseInt(exp.split("x")[0]);
            int reps = Integer.parseInt(exp.split("x")[1]);

            for (int i = 0; i < reps; i++) {
                sb.append(line.substring(matcher.end(), matcher.end() + numberOfChars));
            }

            findNextMatch((matcher.end() + numberOfChars), sb, line);
        } else {
            sb.append(line.substring(start, line.length()));
        }
    }

    private static void findMatches(int start, String line) {
        Matcher partMatcher = Pattern.compile("\\(\\d+x\\d+\\)").matcher(line);
        if (partMatcher.find(start)) {
            counter += partMatcher.start() - start;
            String exp = line.substring(partMatcher.start() + 1, partMatcher.end() - 1);

            int numberOfChars = Integer.parseInt(exp.split("x")[0]);
            int reps = Integer.parseInt(exp.split("x")[1]);

            for (int i = 0; i < reps; i++) {
                findMatches(0, line.substring(partMatcher.end(), partMatcher.end() + numberOfChars));
            }
            findMatches((partMatcher.end() + numberOfChars), line);
        } else {
            counter += line.length() - start;
        }
    }
}


