package year2017.day12;

import base.Base;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Base {
    private static final String INPUT = "2017/day12-input.txt";
    private Map<Integer, List<Integer>> pipes;

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
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {


        /*
            0 <-> 2
            1 <-> 1
            2 <-> 0, 3, 4
            3 <-> 2, 4
            4 <-> 2, 3, 6
            5 <-> 6
            6 <-> 4, 5
         */
        pipes = new HashMap<>();
        for (String i : instructions) {
            int id = Integer.parseInt(i.split(" <-> ")[0]);
            List<Integer> connections = Arrays.stream(i.split(" <-> ")[1].split(", ")).map(Integer::parseInt).collect(Collectors.toList());

            pipes.put(id, connections);
        }

        // check pipe 0's connections and check its connections and find missing link
        List<Integer> connections = new ArrayList<>();
        checkConnections(0, connections);
        System.out.println("done");




/*
        List<Integer> reachablePipes = new ArrayList<>();
        while (reachablePipes.size() < pipes.size() - 1) {
            for (Map.Entry<Integer,List<Integer>> p : pipes.entrySet()) {
                if (!reachablePipes.contains(p.getKey())) {
                    if (p.getKey() == 0) {
                        reachablePipes.add(p.getKey());
                        continue;
                    }
                    List<Integer> connections = p.getValue();
                    for (Integer c : connections) {
                        if (reachablePipes.contains(c)) {
                            reachablePipes.add(p.getKey());
                            break;
                        }
                    }
                }
            }
        }

        Set<Integer> allPipes = pipes.keySet();
        allPipes.removeAll(reachablePipes);

        for (Integer id : allPipes) {
            System.out.println(id);
        }*/
    }

    private void checkConnections(int id, List<Integer> knownConnections) {
        if (knownConnections.size() >= pipes.size() - 1) {
            return;
        }

        if (!knownConnections.contains(id)) {
            knownConnections.add(id);
            for (Integer connectionId : pipes.get(id)) {
                checkConnections(connectionId, knownConnections);
            }
        }
    }


    @Override
    public void part2() throws IOException {

    }

    class Pipe {
        private int id;
        private List<Integer> connections;

        public Pipe(int id) {
            this.id = id;
        }

        public Pipe(int id, List<Integer> connections) {
            this.id = id;
            this.connections = connections;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getConnections() {
            return connections;
        }

        public void setConnections(List<Integer> connections) {
            this.connections = connections;
        }
    }
}
