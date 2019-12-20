package year2019.day14;

import base.Base;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "2019\\day14-test.txt";
    private static List<String> descriptions;
    private static List<Reaction> reactions;
    private static int numberOfOreNeeded;
    private static int numberOfOreLeft;
    private static Map<Reaction, Integer> oreReactionsNeeded;
    private static Map<Reaction, Integer> leftOvers;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

        main.part1();
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();
        numberOfOreNeeded = 0;
        oreReactionsNeeded = new HashMap<>();
        leftOvers = new HashMap<>();
        Reaction fuelReaction = reactions.stream().filter(r -> r.getOutputProduct().equals("FUEL")).findFirst().get();

        findNeededInputs(fuelReaction, 1);

        int totalOre = 0;

        for (Map.Entry<Reaction, Integer> r : oreReactionsNeeded.entrySet()) {
            int oreNeededForReaction = r.getKey().getInputs().get("ORE");
            int outputPerReaction = r.getKey().getNumberOfOutput();
            int outputNeeded = r.getValue();

            totalOre += ((int) Math.ceil((double) outputNeeded / outputPerReaction)) * oreNeededForReaction;
        }

        System.out.println("finished, total ore needed: " + totalOre);
    }

    @Override
    public void part2() throws IOException {
        prepare();
        numberOfOreNeeded = 0;
        Reaction fuelReaction = reactions.stream().filter(r -> r.getOutputProduct().equals("FUEL")).findFirst().get();
    }

    private void findNeededInputs(Reaction reaction, int numberOfProductsNeeded) {
        for (Map.Entry<String, Integer> inputEntry : reaction.getInputs().entrySet()) {
            if (inputEntry.getKey().equals("ORE")) {
                if (oreReactionsNeeded.containsKey(reaction)) {
                    oreReactionsNeeded.put(reaction, oreReactionsNeeded.get(reaction) + numberOfProductsNeeded);
                } else {
                    oreReactionsNeeded.put(reaction, numberOfProductsNeeded);
                }
            } else {
                Reaction subReaction = reactions.stream().filter(r -> r.getOutputProduct().equals(inputEntry.getKey())).findFirst().get();
                int numberOfSubReactionsNeeded = ((int) Math.ceil((double) numberOfProductsNeeded / reaction.getNumberOfOutput()));

                //int subNumberOfProductsNeeded = numberOfProductsNeeded <= reaction.getNumberOfOutput() ? inputEntry.getValue() :
                findNeededInputs(subReaction, numberOfSubReactionsNeeded * inputEntry.getValue());
            }
        }
        if (!reaction.getOutputProduct().equals("FUEL") && !reaction.getInputs().containsKey("ORE")) {
            System.out.println("No Fuel or Ore reaction:");
            if (leftOvers.containsKey(reaction)) {
                System.out.println("There are leftovers for this reation: " + leftOvers.get(reaction));
                System.out.println("Total products needed: " + numberOfProductsNeeded);
                int left = leftOvers.get(reaction);
                if (numberOfProductsNeeded >= left) {
                    leftOvers.put(reaction, 0);
                    numberOfProductsNeeded -= left;
                } else {
                    leftOvers.put(reaction, leftOvers.get(reaction) - numberOfProductsNeeded);
                    numberOfProductsNeeded = 0;
                }
                System.out.println("New left over value: " + leftOvers.get(reaction));
                System.out.println("New products needed value : " + numberOfProductsNeeded);
            } else {
                // no leftover jet
                int reactionsNeeded = (int) Math.ceil((double) numberOfProductsNeeded / reaction.getNumberOfOutput());
                leftOvers.put(reaction, (reactionsNeeded * reaction.getNumberOfOutput()) - numberOfProductsNeeded);

                System.out.println("New left overs: " + leftOvers.get(reaction));
                System.out.println("New products needed value : " + numberOfProductsNeeded);
                System.out.println("");
            }
            System.out.println("");
        }
    }

    private void prepare() throws IOException {
        String line;
        descriptions = new ArrayList<>();
        reactions = new ArrayList<>();

        while ((line = input.readLine()) != null) {
            descriptions.add(line);
        }

        while (!descriptions.isEmpty()) {
            String input = descriptions.get(0).split("=>")[0].trim();
            String output = descriptions.get(0).split("=>")[1].trim();

            Map<String, Integer> inputs = new HashMap<>();

            for (String i : input.split(",")) {
                int numberOfInput = Integer.parseInt(i.trim().split(" ")[0]);
                String product = i.trim().split(" ")[1];

                inputs.put(product, numberOfInput);
            }

            Reaction reaction = new Reaction(inputs, output.split(" ")[1], Integer.parseInt(output.split(" ")[0]));

            reactions.add(reaction);
            descriptions.remove(0);
        }
    }

    public class Reaction {
        private Map<String, Integer> inputs;
        private String outputProduct;
        private Integer numberOfOutput;

        public Reaction(Map<String, Integer> inputs, String outputProduct, Integer numberOfOutput) {
            this.inputs = inputs;
            this.outputProduct = outputProduct;
            this.numberOfOutput = numberOfOutput;
        }

        public Map<String, Integer> getInputs() {
            return inputs;
        }

        public void setInputs(Map<String, Integer> inputs) {
            this.inputs = inputs;
        }

        public String getOutputProduct() {
            return outputProduct;
        }

        public void setOutputProduct(String outputProduct) {
            this.outputProduct = outputProduct;
        }

        public Integer getNumberOfOutput() {
            return numberOfOutput;
        }

        public void setNumberOfOutput(Integer numberOfOutput) {
            this.numberOfOutput = numberOfOutput;
        }
    }
}
