package year2020.day18;

import base.Base;
import base.utils.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day18-input.txt";
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
        solve(false);

    }

    private void solve(boolean part2) {
        BigInteger total = BigInteger.ZERO;
        // solve exps within brackets first
        // find first closing bracket and first match (opening)?
        // 2 * 3 + (4 * 5)
        for (String instruction : instructions) {
            while (instruction.contains(")")) {
                int indexOfFirstClosingBracket = instruction.indexOf(")");
                int matchingOpeningBracket = -1;
                for (int i = indexOfFirstClosingBracket; i >= 0; i--) {
                    if (instruction.charAt(i) == '(') {
                        matchingOpeningBracket = i;
                        break;
                    }
                }

                // replace instruction part with answer of subExpression
                String subExp = instruction.substring(matchingOpeningBracket, indexOfFirstClosingBracket + 1);
                BigInteger subAnswer = solveExp(subExp, part2);
                instruction = instruction.substring(0, matchingOpeningBracket) + subAnswer + instruction.substring(indexOfFirstClosingBracket + 1);
            }

            BigInteger result = solveExp(instruction, part2);
            //System.out.println(result);
            total = total.add(result);
        }

        System.out.println(total);
    }

    private BigInteger solveExp(String exp, boolean part2) {
        exp = exp.replace("(", "").replace(")", "");
        if (part2) {
            exp = solveOperator(exp, part2, '+');
            exp = solveOperator(exp, part2,'*');
        } else {
            exp = solveOperator(exp, part2, '?');
        }

        return new BigInteger(exp);
    }

    private String solveOperator(String exp, boolean part2, char op) {
        while ((part2 && exp.contains(String.valueOf(op))) || (!part2 && (exp.contains("+") || exp.contains("*")))) {
            BigInteger firstNumber, secondNumber;

            for (char s : exp.toCharArray()) {
                if (s == ' ') {
                    continue;
                }
                if ((part2 & s == op) || (!part2 && (s == '+' || s == '*'))) {
                    char nOp = part2 ? op : s;
                    // get first and second number surrounding operater
                    int endFirst = exp.indexOf(nOp) - 1;
                    int startFirst = endFirst - 1;
                    while(startFirst - 1 >= 0 && exp.charAt(startFirst - 1) != ' ' && exp.charAt(startFirst - 1) != '(') {
                        startFirst--;
                    }

                    int startSecond = exp.indexOf(nOp) + 2;
                    int endSecond = startSecond + 1;
                    while (endSecond < exp.length() && exp.charAt(endSecond) != ' ' && exp.charAt(endSecond) != ')') {
                        endSecond++;
                    }

                    firstNumber = new BigInteger(exp.substring(startFirst, endFirst));
                    secondNumber = new BigInteger(exp.substring(startSecond, endSecond));

                    BigInteger subResult = nOp == '+' ? firstNumber.add(secondNumber) : firstNumber.multiply(secondNumber);

                    exp = exp.substring(0, startFirst) + subResult + exp.substring(endSecond);
                    break;
                }
            }
        }

        return exp;
    }

    @Override
    public void part2() throws IOException {
        solve(true);
    }
}
