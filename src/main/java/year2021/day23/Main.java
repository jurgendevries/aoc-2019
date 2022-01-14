package year2021.day23;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2021/day23-2-input.txt";
    private static List<String> instructions;
    private static int width;
    private static int height;
    private static String[][] grid;
    private static List<Amphipod> pods = new ArrayList<>();
    private static Map<String, Integer> types = new HashMap<>();
    private static final int[] hallwayPositions = new int[]{0,1,3,5,7,9,10};
    private static Set<Integer> scores = new TreeSet<>();
    private static State rootState;
    private static Map<String, Integer> knownStates = new HashMap<>();


    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }

        height = instructions.size();
        width = instructions.get(0).length();

        grid = new String[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = j < instructions.get(i).length() && !" ".equals(String.valueOf(instructions.get(i).charAt(j))) ? String.valueOf(instructions.get(i).charAt(j)) : "#";
            }
        }

        types.put("A", 0);
        types.put("B", 1);
        types.put("C", 2);
        types.put("D", 3);

        int counter = 0;
        for (int y = 1; y < 5; y++) {
            for (int x = 2; x <= 8; x += 2) {
                int type = types.get(grid[y][x]);
                pods.add(new Amphipod(counter++, type, -1, -1, x, y));
            }
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
        rootState = new State(pods, 0);
        children(rootState);
        System.out.println("DONE: " + scores.iterator().next());

    }

    @Override
    public void part2() throws IOException {

    }


    /**
     * loc -1 if in room
     * room and depth -1 if in hallway
     * rooms at 2/4/6/8
     *
     * types:
     * A = 0
     * B = 1
     * C = 2
     * D = 3
     *
     * isHome?
     * canMove?
     * canGoToRoom?
     * canGoToHallway?
     */
    private void children(State currentState) {
        if (!isEnd(currentState.pods)) {
            for (int i = 0; i < currentState.pods.size(); i++) {
                Amphipod pod = currentState.pods.get(i);
                //for (Amphipod pod : pods) {
                // is amphipod already home?
                if (isHome(currentState.pods, pod)) {
                    continue;
                }

                int destinationRoom = getDestination(pod.type);

                // is it in a room and can it get out?
                // else is it already in the hallway
                boolean canGetOut = true;
                if (pod.room != -1 && pod.depth > 1) {
                    canGetOut = !currentState.pods.stream().filter(p -> p.room == pod.room && p.depth < pod.depth).findFirst().isPresent();
                }

                // can it get out of it's current room and go to destination? or if it's in the hallway already can it get to it's destination
                if (canGetOut) {
                    // can it go to it's destination room?
                    // no other Amphipod type is the room
                    boolean canGoToDestination = !currentState.pods.stream().filter(p -> p.room == destinationRoom && p.type != pod.type).findFirst().isPresent();
                    if (canGoToDestination) {
                        if (!isHallwayBlocked(pod, currentState.pods, pod.room == -1 ? pod.x : pod.room, destinationRoom)) {
                            // cost to hallway
                            int costToHallway = pod.room == -1 ? 0 : pod.depth;
                            // if the partner is already there, assume it is at depth 2
                            //boolean partnerAtDestination = currentState.pods.stream().filter(p -> p.room == destinationRoom && p.type == pod.type).findFirst().isPresent();
//                            int cost = ((int) Math.pow(10, pod.type)) * (Math.abs((pod.room == -1 ? pod.x : pod.room) - destinationRoom) + (partnerAtDestination ? 1 : 2) + costToHallway);
                            int partnersAtDestination = (int) currentState.pods.stream().filter(p -> p.room == destinationRoom && p.type == pod.type).count();
                            int cost = ((int) Math.pow(10, pod.type)) * (Math.abs((pod.room == -1 ? pod.x : pod.room) - destinationRoom) + (4 - partnersAtDestination) + costToHallway);
                            List<Amphipod> podsCopy = new ArrayList<>();
                            podsCopy.addAll(currentState.pods);
//                            podsCopy.set(i, new Amphipod(pod.id, pod.type, -1, -1, destinationRoom, partnerAtDestination ? 1 : 2));
                            podsCopy.set(i, new Amphipod(pod.id, pod.type, -1, -1, destinationRoom, 4 - partnersAtDestination));

                            String stateString = getStateString(podsCopy);
                            if (isCheapestState(stateString, currentState.cost + cost)) {
                                knownStates.put(stateString, currentState.cost + cost);
                                currentState.children.add(new State(podsCopy, currentState.cost + cost));
                            }
                        }
                    }
                }

                // try to enter the hallway from a room
                if (pod.room == -1) {
                    // already in the hallway, do nothing
                    continue;
                }

                // can it get out of the room
                if (!canGetOut) {
                    continue;
                }

                // go to hallway
                for (int destination : hallwayPositions) {
                    if (isHallwayBlocked(pod, currentState.pods, pod.room, destination)) {
                        continue;
                    }
                    int cost = ((int) Math.pow(10, pod.type)) * (Math.abs(pod.room - destination) + pod.depth);
                    List<Amphipod> podsCopy = new ArrayList<>();
                    podsCopy.addAll(currentState.pods);
                    podsCopy.set(i, new Amphipod(pod.id, pod.type, destination, 0, -1, -1));

                    // TODO: check if state is known and can be achieved cheaper... if so than skip this one
                    String stateString = getStateString(podsCopy);
                    if (isCheapestState(stateString, currentState.cost + cost)) {
                        knownStates.put(stateString, currentState.cost + cost);
                        currentState.children.add(new State(podsCopy, currentState.cost + cost));
                    }
                }
            }
            for (State child : currentState.children) {
                children(child);
            }
        }
        else {
            scores.add(currentState.cost);
        }
    }

    private String getStateString(List<Amphipod> pods) {
        StringBuilder stateString = new StringBuilder();
        for (Amphipod pod : pods) {
            stateString.append(pod.id + ":" + pod.x + "-" + pod.y + "-" + pod.room + "-" + pod.depth);
        }
        return stateString.toString();
    }

    private boolean isEnd(List<Amphipod> pods) {
        return !pods.stream().filter(p -> p.room != getDestination(p.type)).findFirst().isPresent();
    }

    // loc = 4, dest = 0
    // loc = 4, dest = 10
    // loc = 6, dest = 7
    // loc = 3, dest = 4
    private boolean isHallwayBlocked(Amphipod pod, List<Amphipod> pods, int location, int destination) {
        return pods.stream()
                .filter(p -> p.id != pod.id && p.x >= 0 && ((p.x <= location && p.x >= destination) || (p.x >= location && p.x <= destination)))
                .findFirst()
                .isPresent();
    }

    private boolean isCheapestState(String stateString, int cost) {
        return !knownStates.containsKey(stateString) || knownStates.get(stateString) > cost;
    }

    private int getDestination(int type) {
        return (type + 1) * 2;
    }

    private boolean isHome(List<Amphipod> pods, Amphipod pod) {
        int destination = getDestination(pod.type);
        if (pod.room == destination) {
            if (pod.depth == 4) {
                return true;
            }

//            Amphipod partner = pods.stream().filter(p -> p.type == pod.type && p.id != pod.id).findFirst().get();
//            if (partner.room == destination && partner.depth == 2) {
            int partnersHome = (int) pods.stream().filter(p -> p.type == pod.type && p.id != pod.id && p.room == destination).count();
            if (4 - partnersHome == pod.depth) {
                return true;
            }
        }
        return false;
    }

    class Amphipod {
        private int id;
        private int type;
        private int x;
        private int y;
        private int room;
        private int depth;

        public Amphipod(int id, int type, int x, int y, int room, int depth) {
            this.id = id;
            this.type = type;
            this.x = x;
            this.y = y;
            this.room = room;
            this.depth = depth;
        }
    }

    class State {
        private List<Amphipod> pods;
        private int cost;
        private List<State> children;

        public State(List<Amphipod> pods, int cost) {
            this.pods = pods;
            this.cost = cost;
            this.children = new ArrayList<>();
        }
    }
}
