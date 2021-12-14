package year2021.day14;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2021/day14-input.txt";
    private static List<String> instructions;
    private static String template;
    private static Map<String, String[]> insertionRules;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
        template = instructions.get(0);
        insertionRules = new HashMap<>();
        for (int i = 1; i < instructions.size(); i++) {
            String insertRule = instructions.get(i);
            String insert1 = insertRule.split(" -> ")[0].split("")[0] + insertRule.split(" -> ")[1];
            String insert2 = insertRule.split(" -> ")[1] + insertRule.split(" -> ")[0].split("")[1];
            insertionRules.put(
                    insertRule.split(" -> ")[0],
                    new String[] {insert1, insert2}
            );
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
        run(10);
    }

    @Override
    public void part2() throws IOException {
        run(40);
    }

    private void run(int steps) {
        Map<String, Long> stringMap = new HashMap<>();
        Map<String, Long> pairMap = new HashMap<>();
        for (int i = 0; i < template.length() - 1; i++) {
            String pair = template.substring(i, i + 2);
            pairMap.put(pair, pairMap.get(pair) != null ? pairMap.get(pair) + 1 : 1);
        }
        for (int i = 0; i < steps; i ++) {
            pairMap = processPolymerPais(pairMap);
        }

        for (Map.Entry<String, Long> pair : pairMap.entrySet()) {
            String p1 = pair.getKey().substring(0,1);
            String p2 = pair.getKey().substring(1,2);
            stringMap.put(p1, stringMap.get(p1) != null ? stringMap.get(p1) + pair.getValue() : pair.getValue());
            stringMap.put(p2, stringMap.get(p2) != null ? stringMap.get(p2) + pair.getValue() : pair.getValue());
        }
        Map.Entry<String, Long> maxEntry = stringMap.entrySet().stream().max((e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1).get();
        Map.Entry<String, Long> minEntry = stringMap.entrySet().stream().max((e1, e2) -> e1.getValue() < e2.getValue() ? 1 : -1).get();
        int correction = template.length() % 2 == 0 ? -1 : 1;
        long result = ((maxEntry.getValue() - minEntry.getValue()) / 2) + correction;
        System.out.println("" + result);
    }

    private Map<String, Long> processPolymerPais(Map<String, Long> pairMap) {
        Map<String, Long> newPairs = new HashMap<>();
        for (Map.Entry<String, Long> pair : pairMap.entrySet()) {
            // NN => NC CN
            newPairs.compute(insertionRules.get(pair.getKey())[0], (key, val) -> (val == null) ? pair.getValue() : val + pair.getValue());
            newPairs.compute(insertionRules.get(pair.getKey())[1], (key, val) -> (val == null) ? pair.getValue() : val + pair.getValue());
        }
        return newPairs;
    }
}
