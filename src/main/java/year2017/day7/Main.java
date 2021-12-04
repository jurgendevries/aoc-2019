package year2017.day7;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2017/day7-input.txt";


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
//        System.out.println("PART1:");
//        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        List<String> copyInstructions = new ArrayList<>(instructions);
        List<String> children = new ArrayList<>();
        for (String instruction : copyInstructions) {
            String[] splitInstruction = instruction.split("->");
            String[] childs = splitInstruction.length > 1 ? splitInstruction[1].split(", ") : null;
            if (childs != null) {
                for (String c : childs) {
                    children.add(c.trim());
                }
            }
        }

        for (String instruction : copyInstructions) {
            String[] splitInstruction = instruction.split("->");
            String[] p = splitInstruction[0].split(" ");
            String name = p[0];
            if (!children.contains(name)) {
                System.out.println(name);
                break;
            }
        }
    }

    private Program addProgram(String name) {
        String programString = instructions.stream().filter(i -> i.split("->")[0].split(" ")[0].equals(name)).findFirst().get();
        String[] splitInstruction = programString.split("->");
        String[] p = splitInstruction[0].split(" ");
        String[] childs = splitInstruction.length > 1 ? splitInstruction[1].split(", ") : null;
        int weight = Integer.parseInt(p[1].substring(1, p[1].length() - 1));

        Program program = new Program(name, weight);

        List<Program> children = new ArrayList<>();
        if (childs != null) {
            for (String c : childs) {
                children.add(addProgram(c.trim()));
            }
        }

        int totalWeight = weight;
        if (children.size() > 0) {
            boolean validTotalWeight = true;

            int firstWeight = children.get(0).getTotalWeight();
            for (int i = 0; i < children.size(); i++) {
                Program child = children.get(i);
                if (child.getTotalWeight() != firstWeight) {
                    validTotalWeight = false;
                }
                totalWeight += child.getTotalWeight();
            }
            if (!validTotalWeight) {
                System.out.println("ERROR!!!");
            }
        }

        program.setTotalWeight(totalWeight);
        program.setChildren(children);
        return program;
    }

    @Override
    public void part2() throws IOException {
        String startProgram = "azqje";
//        String startProgram = "tknk";

        Program mainProgram = addProgram(startProgram);

    }

    class Program {
        private String name;
        private int weight;
        private int totalWeight;
        private List<Program> children;

        public Program() {
        }

        public Program(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public Program(String name, int weight, List<Program> children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(int totalWeight) {
            this.totalWeight = totalWeight;
        }

        public List<Program> getChildren() {
            return children;
        }

        public void setChildren(List<Program> children) {
            this.children = children;
        }
    }
}
