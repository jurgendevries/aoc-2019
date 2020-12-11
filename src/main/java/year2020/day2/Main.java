package year2020.day2;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day2-input.txt";

    private static List<String> instructions;
    private static long numberOfValidPasswords;

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
        //main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        for (String instruction : instructions) {
            if (isValidPassword(instruction)) {
                numberOfValidPasswords++;
            }
        }

        System.out.println("Number of valid passwords: " + numberOfValidPasswords);
    }

    private boolean isValidPassword(String instruction) {
        String[] parts = instruction.split(":");
        String policy = parts[0];
        String password = parts[1].trim();

        int count = 0;
        int minRange = Integer.parseInt(policy.split(" ")[0].split("-")[0]);
        int maxRange = Integer.parseInt(policy.split(" ")[0].split("-")[1]);
        char character = (policy.split(" ")[1]).charAt(0);

        for (char c : password.toCharArray()) {
            if (c == character) {
                count++;
            }
        }

        return count >= minRange && count <= maxRange;
    }

    @Override
    public void part2() throws IOException {
        for (String instruction : instructions) {
            if (isValidPassword2(instruction)) {
                numberOfValidPasswords++;
            }
        }

        System.out.println("Number of valid passwords: " + numberOfValidPasswords);
    }

    private boolean isValidPassword2(String instruction) {
        String[] parts = instruction.split(":");
        String policy = parts[0];
        String password = parts[1].trim();

        int minRange = Integer.parseInt(policy.split(" ")[0].split("-")[0]);
        int maxRange = Integer.parseInt(policy.split(" ")[0].split("-")[1]);
        char character = (policy.split(" ")[1]).charAt(0);

        return password.charAt(minRange - 1) == character ^ password.charAt(maxRange - 1) == character;
    }
}
