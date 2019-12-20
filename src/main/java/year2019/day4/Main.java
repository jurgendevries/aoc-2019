package year2019.day4;

import base.Base;

import java.io.IOException;

public class Main extends Base {
    private static final String INPUT = "245318-765747";
    private static int start;
    private static int end;
    private static int possiblePasswords;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        start = Integer.parseInt(INPUT.split("-")[0]);
        end = Integer.parseInt(INPUT.split("-")[1]);

        main.part2();
    }

    @Override
    public void part1() throws IOException {
        possiblePasswords = 0;

        for (int i = start; i < end; i++) {
            boolean validPassword = true;
            String stringPassword = String.valueOf(i);
            if (hashContainsSequence(stringPassword, 2, null) != null) {
                for (int j = 0; j < stringPassword.length() - 1; j++) {
                    int first = Character.getNumericValue(stringPassword.charAt(j));
                    int second = Character.getNumericValue(stringPassword.charAt(j + 1));

                    if (first > second) {
                        validPassword = false;
                        break;
                    }
                }
            } else {
                validPassword = false;
            }

            if (validPassword) {
                possiblePasswords++;
            }
        }

        System.out.println(possiblePasswords);
    }

    @Override
    public void part2() throws IOException {
        possiblePasswords = 0;

        for (int i = start; i < end; i++) {
            boolean validPassword = true;
            String stringPassword = String.valueOf(i);
            if (hashContainsSequenceOf2Unique(stringPassword, 2) != null) {
                for (int j = 0; j < stringPassword.length() - 1; j++) {
                    int first = Character.getNumericValue(stringPassword.charAt(j));
                    int second = Character.getNumericValue(stringPassword.charAt(j + 1));

                    if (first > second) {
                        validPassword = false;
                        break;
                    }
                }
            } else {
                validPassword = false;
            }

            if (validPassword) {
                possiblePasswords++;
            }
        }

        System.out.println(possiblePasswords);

    }
}
