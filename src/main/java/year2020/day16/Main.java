package year2020.day16;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2020/day16-input.txt";

    private static int total;
    private static List<String> instructions;
    private static List<Rule> rules = new ArrayList<>();
    private static Map<Integer, String[]> validTickets = new LinkedHashMap<>();
    private static String[] myTicket;

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
        // read and store rules
        int currentInstruction = 0;
        for (int r = 0; r < instructions.size(); r ++) {
            if ("".equals(instructions.get(r))) {
                currentInstruction = ++r;
                break;
            }

            String ruleName = instructions.get(r).split(":")[0];
            String rulesString = instructions.get(r).split(":")[1].trim();
            String first = rulesString.split("or")[0].trim();
            String second = rulesString.split("or")[1].trim();

            int low1 = Integer.parseInt(first.split("-")[0].trim());
            int high1 = Integer.parseInt(first.split("-")[1].trim());
            int low2 = Integer.parseInt(second.split("-")[0].trim());
            int high2 = Integer.parseInt(second.split("-")[1].trim());

            rules.add(new Rule(ruleName, low1, high1, low2, high2));

        }

        // ignore your own ticket for now
        for (int o = currentInstruction; o < instructions.size(); o++) {
            if ("".equals(instructions.get(o))) {
                currentInstruction = ++o;
                break;
            }
            if (!"your ticket:".equals(instructions.get(o))) {
                myTicket = instructions.get(o).split(",");
            }
        }

        // validate nearby tickets
        for (int n = currentInstruction; n < instructions.size(); n++) {
            boolean allValid = true;
            if ("nearby tickets:".equals(instructions.get(n))) {
                continue;
            }

            // split values
            String[] vals = instructions.get(n).split(",");

            for (int i = 0; i < vals.length; i++) {
                boolean validValueForOneRule = false;
                for (Rule rule : rules) {
                    if (validateValue(Integer.parseInt(vals[i]), rule)) {
                        validValueForOneRule = true;
                        break;
                    }
                }

                if (!validValueForOneRule) {
                    allValid = false;
                    System.out.println(Integer.parseInt(vals[i]));
                    total += Integer.parseInt(vals[i]);
                }
            }

            if (allValid) {
                validTickets.put(n, vals);
            }
        }

        System.out.println(total);
    }

    @Override
    public void part2() throws IOException {
        int numberOfKeysInTicket = validTickets.entrySet().iterator().next().getValue().length;
        Map<Rule, List<Integer>> ruleMap = new LinkedHashMap<>();

        while (ruleMap.size() < rules.size()) {
            for (Rule rule : rules) {
                for (int i = 0; i < numberOfKeysInTicket; i++) {
                    boolean allValid = true;
                    for (String[] ticketValues : validTickets.values()) {

                        int val = Integer.parseInt(ticketValues[i]);
                        if (!validateValue(val, rule)) {
                            allValid = false;
                            break;
                        }
                    }

                    if (allValid) {
                        // all tickets valid for this key
                        if (ruleMap.containsKey(rule)) {
                            List<Integer> currentRules = ruleMap.get(rule);
                            currentRules.add(i);
                            ruleMap.put(rule, currentRules);
                        } else {
                            List<Integer> newRules = new ArrayList<>();
                            newRules.add(i);
                            ruleMap.put(rule, newRules);
                        }
                    }
                }
            }
        }


        Map<Integer, Rule> finalMap = new HashMap<>();

        while (!ruleMap.isEmpty()) {
            // find all rules with 1 index
            Iterator it = ruleMap.entrySet().iterator();
            List<Rule> rulesToRemove = new ArrayList<>();
            while (it.hasNext()) {
                Map.Entry<Rule, List<Integer>> entry = (Map.Entry<Rule, List<Integer>>) it.next();
                if (entry.getValue().size() == 1) {
                    int idx = entry.getValue().get(0);
                    finalMap.put(idx, entry.getKey());
                    rulesToRemove.add(entry.getKey());

                    // deze index uit de andere lijsten weghalen nu
                    for (Map.Entry<Rule, List<Integer>> entryInner : ruleMap.entrySet()) {
                        if (entryInner.getValue().contains(idx)) {
                            entryInner.getValue().remove((Integer) idx);
                        }
                    }
                }
            }

            for (Rule rule : rulesToRemove) {
                ruleMap.remove(rule);
            }
        }

        long totalTicket = 1;
        for (int i = 0; i < myTicket.length; i++) {
            Rule rule = finalMap.get(i);
            if (rule.getName().startsWith("departure")) {
                totalTicket *= Long.parseLong(myTicket[i]);
            }
        }

        System.out.println(totalTicket);
    }

    private boolean validateValue(int value, Rule rule) {
        return (value >= rule.getLow1() && value <= rule.getHigh1()) || (value >= rule.getLow2() && value <= rule.getHigh2());
    }

    class Rule {
        private String name;
        private int low1;
        private int high1;
        private int low2;
        private int high2;

        public Rule(String name, int low1, int high1, int low2, int high2) {
            this.name = name;
            this.low1 = low1;
            this.high1 = high1;
            this.low2 = low2;
            this.high2 = high2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLow1() {
            return low1;
        }

        public void setLow1(int low1) {
            this.low1 = low1;
        }

        public int getHigh1() {
            return high1;
        }

        public void setHigh1(int high1) {
            this.high1 = high1;
        }

        public int getLow2() {
            return low2;
        }

        public void setLow2(int low2) {
            this.low2 = low2;
        }

        public int getHigh2() {
            return high2;
        }

        public void setHigh2(int high2) {
            this.high2 = high2;
        }
    }

}
