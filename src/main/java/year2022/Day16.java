package year2022;

import base.Base;
import year2021.day23.Main;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends Base {
    private static final String INPUT = "2022/day16-input.txt";

    private static Set<Integer> scores = new TreeSet<>();
    private static Map<String, Integer> knownStates = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Day16 main = new Day16();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private List<Valve> readValves() {
        List<Valve> valves = new ArrayList<>();
        // Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        for (String instruction : instructions) {
            String id = instruction.split(" ")[1];
            String[] connections = null;
            if (instruction.contains("to valves")) {
                connections = instruction.split("to valves ")[1].split(", ");
            } else {
                connections = instruction.split("to valve ")[1].split(", ");
            }
            int rate = Integer.parseInt(instruction.split(" ")[4].split("=")[1].replace(";", ""));
            Valve valve = new Valve(id, rate, List.of(connections));
            valves.add(valve);
        }
        return valves;
    }

    @Override
    public void part1() throws IOException {
        List<Valve> valves = readValves();
        Valve currentValve = valves.stream().filter(v -> v.id.equals("AA")).findFirst().get();
        State startState = new State(valves, currentValve, 0, 26);
        move(startState);

        int topScore = scores.stream().mapToInt(x -> x).max().orElse(0);
        System.out.println(topScore);

        System.out.println("Part2:");
        // get state from known states
        String state = knownStates.entrySet().stream().filter(x -> x.getValue() == topScore).findFirst().get().getKey();
        // GS:closed;CB:closedTP:closedFI:closedWV:closedEA:openAT:closedWS:closedMP:closedGE:closedSA:openNI:closedGO:closedIT:closedNG:closedRD:closedLR:openTO:closedWF:closedYD:closedXR:closedKF:closedOO:closedHG:closedCT:closedDA:closedYY:closedVW:closedRC:closedPL:closedTH:openQN:openXE:closedXP:closedAX:closedEM:closedNR:closedYG:openPM:closedAY:closedGJ:closedLC:closedUD:closedAA:closedOM:closedWB:closedGW:openNA:closedXX:closedZO:closedKB:open-AY-0 -> {Integer@1018} 1366

        for (Valve valve : valves) {
            for (String valveState : state.split(";")) {
                if (valve.id.equals(valveState.split(":")[0])) {
                    valve.open = valveState.split(":")[1].equals("open");
                    break;
                }
            }
        }
        System.out.println(state);
        scores = new HashSet<>();
        State elephantStartState = new State(valves, currentValve, 0, 26);
        move(elephantStartState);
        int topElephantScore = scores.stream().mapToInt(x -> x).max().orElse(0);
        System.out.println(topElephantScore);

        System.out.println(topElephantScore+ topScore);
    }

    @Override
    public void part2() throws IOException {

    }

    private void move(State currentState) {
        // recursive method
        if (!allOpen(currentState.valves) && currentState.timer > 0) {
            // keep track of timer

            // if rate == 0 never open valve (no score)
            // 1 path for every connection (can go back and open/don't open that valve)

            // open or don't open current valve (2 paths)
            if (currentState.currentValve.open || currentState.currentValve.rate == 0) {
                // move to connecting valves
                for (String nextValveName : currentState.currentValve.connections) {
                    List<Valve> valvesCopy = new ArrayList<>();
                    valvesCopy.addAll(currentState.valves);
                    int nextValveIndex = valvesCopy.indexOf(valvesCopy.stream().filter(v -> v.id.equals(nextValveName)).findFirst().get());
                    Valve nextValve = valvesCopy.get(nextValveIndex);
                    Valve newValve = new Valve(nextValveName, nextValve.rate, nextValve.open, nextValve.connections);
                    valvesCopy.set(nextValveIndex, newValve);
                    int newTime = currentState.timer - 1;

                    String stateString = getStateString(valvesCopy, newTime, nextValveName);
                    if (isBestScore(stateString, currentState.score)) {
                        State childState = new State(valvesCopy, newValve, currentState.score, newTime);
                        knownStates.put(stateString, currentState.score);
                        currentState.children.add(childState);
                    }
                }
            } else {
                // open valve (only if rate > 0)
                List<Valve> valvesCopy = new ArrayList<>();
                valvesCopy.addAll(currentState.valves);
                int nextValveIndex = valvesCopy.indexOf(valvesCopy.stream().filter(v -> v.id.equals(currentState.currentValve.id)).findFirst().get());
                Valve nextValve = valvesCopy.get(nextValveIndex);
                Valve newValve = new Valve(nextValve.id, nextValve.rate, true, nextValve.connections);
                valvesCopy.set(nextValveIndex, newValve);

                int newTimer = currentState.timer - 1;
                int newScore = currentState.score + nextValve.rate * newTimer;

                String stateString = getStateString(valvesCopy, newTimer, newValve.id);
                if (isBestScore(stateString, newScore)) {
                    State childState = new State(valvesCopy, newValve, newScore, newTimer);
                    knownStates.put(stateString, newScore);
                    currentState.children.add(childState);
                }
            }

            for (State stateChild : currentState.children) {
                move(stateChild);
            }
        } else {
            // if all valves with rates are open, stop moving
            // if time is up stop
            scores.add(currentState.score);
//            if (currentState.timer == 0) {
//                System.out.println("Out of time");
//            } else {
//                System.out.println("Done all valves with rate are open");
//            }

        }
    }

    private boolean allOpen(List<Valve> valves) {
        return !valves.stream().filter(v -> v.rate > 0 && !v.open).findFirst().isPresent();
    }

    private boolean isBestScore(String stateString, int score) {
        return !knownStates.containsKey(stateString) || knownStates.get(stateString) < score;
    }

    private String getStateString(List<Valve> valves, int time, String current) {
        StringBuilder stateString = new StringBuilder();
        for (Valve valve : valves) {
            stateString.append(valve.id + ":" + (valve.open ? "open" : "closed") + ";");
        }
        stateString.append("-" + current + "-" + time);
        return stateString.toString();
    }

    class Valve {
        private String id;
        private List<String> connections;
        private int rate;
        private boolean open;

        public Valve(String id, int rate, List<String> connections) {
            this(id, rate, false, connections);
        }

        public Valve(String id, int rate, boolean open, List<String> connections) {
            this.id = id;
            this.rate = rate;
            this.open = open;
            this.connections = connections;
        }
    }

    class State {
        private List<Valve> valves;
        private Valve currentValve;
        private int score;
        private int timer;
        private List<State> children;

        public State(List<Valve> valves, Valve currentValve, int score, int timer) {
            this.valves = valves;
            this.currentValve = currentValve;
            this.score = score;
            this.timer = timer;
            this.children = new ArrayList<>();
        }
    }
}
