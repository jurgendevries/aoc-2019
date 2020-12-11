package year2020.day4;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main extends Base {

    private static final String INPUT = "2020/day4-input.txt";

    private static List<String> instructions;
    private static List<String> passports = new ArrayList<>();
    private static List<String> requiredFieldStrings = Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid");
    private static long numberOfValidPassports;


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
        String passport = new String();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                passports.add(passport);
                if (isValidPassport(passport)) {
                    numberOfValidPassports++;
                }
                passport = new String();
            } else {
                if ("".equals(passport)) {
                    passport += instruction;
                } else {
                    passport += " " + instruction;
                }
            }

        }

        // check the last password as well:
        if (isValidPassport(passport)) {
            numberOfValidPassports++;
        }

        System.out.println("Number of valid passwords: " + numberOfValidPassports);
    }

    private boolean isValidPassportPart2(String passport) {
        String[] passportFields = passport.split(" ");
        boolean validPassport = true;


        for (int i = 0; i < passportFields.length; i++) {
            for (String field : requiredFieldStrings) {
                if (!passport.contains(field)) {
                    validPassport = false;
                    break;
                }
            }

            if (!validPassport) {
                break;
            }

            String passportFieldHeader = passportFields[i].split(":")[0];
            String passportFieldValue = passportFields[i].split(":")[1];
            if (!validatePassportField(passportFieldHeader, passportFieldValue)) {
                validPassport = false;
                break;
            }
        }

        return validPassport;
    }

    private boolean isValidPassport(String passport) {
        String[] passportFields = passport.split(" ");

        boolean validPassword = true;

        for (String requiredField : requiredFieldStrings) {
            boolean fieldFound = false;

            for (int i = 0; i < passportFields.length; i++) {
                String passportFieldHeader = passportFields[i].split(":")[0];
                if (requiredField.equals(passportFieldHeader)) {
                    fieldFound = true;
                    break;
                }
            }

            if (!fieldFound) {
                validPassword = false;
                break;
            }
        }

        return validPassword;
    }

    @Override
    public void part2() throws IOException {
        String passport = new String();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                passports.add(passport);
                if (isValidPassportPart2(passport)) {
                    numberOfValidPassports++;
                }
                passport = new String();
            } else {
                if ("".equals(passport)) {
                    passport += instruction;
                } else {
                    passport += " " + instruction;
                }
            }

        }

        // check the last password as well:
        if (isValidPassportPart2(passport)) {
            numberOfValidPassports++;
        }

        System.out.println("Number of valid passwords: " + numberOfValidPassports);
    }

    private boolean validatePassportField(String header, String value) {
        boolean valid = false;

        switch(header) {
            case "byr":
                valid = value.length() == 4 && Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
                break;
            case "iyr":
                valid = value.length() == 4 && Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
                break;
            case "eyr":
                valid = value.length() == 4 && Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
                break;
            case "hgt":
                valid = value.contains("cm") ? Integer.parseInt(value.substring(0,value.length() - 2)) >= 150 && Integer.parseInt(value.substring(0,value.length() - 2)) <= 193 :
                        value.contains("in") ? Integer.parseInt(value.substring(0,value.length() - 2)) >= 59 && Integer.parseInt(value.substring(0,value.length() - 2)) <= 76 : false;
                break;
            case "hcl":
                valid = value.matches("#[a-f0-9]{6}");
                break;
            case "ecl":
                List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                valid = eyeColors.contains(value);
                break;
            case "pid":
                valid = value.length() == 9;
                break;
            default:
                valid = header.equals("cid");
        }

        return valid;
    }
}
