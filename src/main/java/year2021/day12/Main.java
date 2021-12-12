package year2021.day12;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2021/day12-input.txt";
    private static List<String> instructions;
    private static List<List<String>> routes = new ArrayList<>();
    Map<String, List<String>> connections = new HashMap<>();

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        // set connections
        for (String instruction : instructions) {
            String caveA = instruction.split("-")[0];
            String caveB = instruction.split("-")[1];

            if (!connections.containsKey(caveA)) {
                connections.put(caveA, new ArrayList<>());
            }
            connections.get(caveA).add(caveB);

            if (!connections.containsKey(caveB)) {
                connections.put(caveB, new ArrayList<>());
            }
            connections.get(caveB).add(caveA);
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
        resolveRoutes("start", new ArrayList<>(), false, false);
        System.out.println(routes.size());
    }

    @Override
    public void part2() throws IOException {
        routes = new ArrayList<>();
        resolveRoutes("start", new ArrayList<>(), false, true);
        System.out.println(routes.size());
    }

    private void resolveRoutes(String start, List<String> route, boolean hasDoubleVisit, boolean part2) {
        if (part2 && start.equals("start") && route.contains(start)) {
            return;
        }
        if (route.contains(start) && start.equals(start.toLowerCase())) {
            if (!part2 || hasDoubleVisit) {
                return;
            } else {
                hasDoubleVisit = true;
            }
        }
        if (start.equals("end")) {
            route.add(start);
            routes.add(route);
            return;
        }

        route.add(start);
        for (String option : connections.get(start)) {
            resolveRoutes(option, new ArrayList<>(route), hasDoubleVisit, part2);
        }
    }
}
