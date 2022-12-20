package year2022;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Day19 extends Base {
    private static final String INPUT = "2022/day19-input.txt";

    private List<BluePrint> bluePrints;
    private Map<String, Integer> knownFactoryStates = new HashMap<>();
    private Set<Factory> bluePrintSolutions = new HashSet<>();


    public static void main(String[] args) throws IOException {
        Day19 main = new Day19();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        main.readBluePrints();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void readBluePrints() {
        bluePrints = new ArrayList<>();
        // each line one blueprint
        for (String instruction : instructions) {
            int id = Integer.parseInt(instruction.split(" ")[1].replace(":", ""));
            int oreRobotCostOre = Integer.parseInt(instruction.split("Each ")[1].split(" ")[3]);
            int clayRobotCostOre = Integer.parseInt(instruction.split("Each ")[2].split(" ")[3]);
            int obsRobotCostOre = Integer.parseInt(instruction.split("Each ")[3].split(" ")[3]);
            int obsRobotCostClay = Integer.parseInt(instruction.split("Each ")[3].split(" ")[6]);
            int geoRobotCostOre = Integer.parseInt(instruction.split("Each ")[4].split(" ")[3]);
            int geoRobotCostObs = Integer.parseInt(instruction.split("Each ")[4].split(" ")[6]);

            Cost oreRobotCost = new Cost(oreRobotCostOre, 0, 0);
            Cost clayRobotCost = new Cost(clayRobotCostOre, 0, 0);
            Cost obsRobotCost = new Cost(obsRobotCostOre, obsRobotCostClay, 0);
            Cost geoRobotCost = new Cost(geoRobotCostOre, 0, geoRobotCostObs);

            BluePrint bluePrint = new BluePrint(id, oreRobotCost, clayRobotCost, obsRobotCost, geoRobotCost);

            bluePrints.add(bluePrint);
        }
    }

    @Override
    public void part1() throws IOException {
        int total = 0;
        for (BluePrint bluePrint : bluePrints) {
            bluePrintSolutions = new HashSet<>();
            Factory factory = new Factory(bluePrint, 24);
            produce(factory);
            int maxGeoStorage = bluePrintSolutions.stream().max(Comparator.comparing(Factory::getGeoStorage)).get().getGeoStorage();
            total += bluePrint.id * maxGeoStorage;
        }
        System.out.println(total);
    }

    private void produce(Factory factory) {
        if (factory.timeLeft == 0) {
            // time is up, store solution
            bluePrintSolutions.add(factory);
        } else {

            // time still running check all options recursively
            // check storage for building posibilities
            // new copy for every build option
            // substract build costs
            // harvest new materials
            // add new robot
            // new copy for saving materials
            // harvest new materials
            // check if buildOptions are unknown or better than known states
            // recurse with every new / better option
            List<FactoryOption> buildOptions = new ArrayList<>();
            if (factory.oreStorage >= factory.bluePrint.geoRobotCost.ore && factory.obsStorage >= factory.bluePrint.geoRobotCost.obs) {
                Factory copyFactory = copyFactory(factory);
                copyFactory.oreStorage -= copyFactory.bluePrint.geoRobotCost.ore;
                copyFactory.obsStorage -= copyFactory.bluePrint.geoRobotCost.obs;
                harvestMaterials(copyFactory);
                copyFactory.geoRobots += 1;
                buildOptions.add(new FactoryOption(copyFactory, "geo"));
            } else {

                if (factory.oreStorage >= factory.bluePrint.oreRobotCost.ore && factory.oreRobots < factory.maxOreCost) {
                    Factory copyFactory = copyFactory(factory);
                    copyFactory.oreStorage -= copyFactory.bluePrint.oreRobotCost.ore;
                    harvestMaterials(copyFactory);
                    copyFactory.oreRobots += 1;
                    buildOptions.add(new FactoryOption(copyFactory, "ore"));
                }

                if (factory.oreStorage >= factory.bluePrint.clayRobotCost.ore && factory.clayRobots < factory.bluePrint.obsRobotCost.clay) {
                    Factory copyFactory = copyFactory(factory);
                    copyFactory.oreStorage -= copyFactory.bluePrint.clayRobotCost.ore;
                    harvestMaterials(copyFactory);
                    copyFactory.clayRobots += 1;
                    buildOptions.add(new FactoryOption(copyFactory, "clay"));
                }

                if (factory.oreStorage >= factory.bluePrint.obsRobotCost.ore && factory.clayStorage >= factory.bluePrint.obsRobotCost.clay && factory.obsRobots < factory.bluePrint.geoRobotCost.obs) {
                    Factory copyFactory = copyFactory(factory);
                    copyFactory.oreStorage -= copyFactory.bluePrint.obsRobotCost.ore;
                    copyFactory.clayStorage -= copyFactory.bluePrint.obsRobotCost.clay;
                    harvestMaterials(copyFactory);
                    copyFactory.obsRobots += 1;
                    buildOptions.add(new FactoryOption(copyFactory, "obs"));
                }


                // don't build any robot, just harvest new materials
                Factory copyFactory = copyFactory(factory);
                harvestMaterials(copyFactory);
                buildOptions.add(new FactoryOption(copyFactory, null));
            }

            for (FactoryOption f : buildOptions) {
                String factoryState = getFactoryStateString(f);
                if (isBestFactory(factoryState, f.factory.geoStorage)) {
                    knownFactoryStates.put(factoryState, f.factory.geoStorage);
                    factory.children.add(f.factory);
                }
            }

            for (Factory childFactory : factory.children) {
                produce(childFactory);
            }
        }
    }

    private void harvestMaterials(Factory factory) {
        factory.oreStorage += factory.oreRobots;
        factory.clayStorage += factory.clayRobots;
        factory.obsStorage += factory.obsRobots;
        factory.geoStorage += factory.geoRobots;
    }

    private Factory copyFactory(Factory factory) {
        return new Factory(
                factory.bluePrint,
                factory.timeLeft - 1,
                factory.oreStorage,
                factory.clayStorage,
                factory.obsStorage,
                factory.geoStorage,
                factory.oreRobots,
                factory.clayRobots,
                factory.obsRobots,
                factory.geoRobots
        );
    }

    private boolean isBestFactory(String factoryState, int score) {
        return !knownFactoryStates.containsKey(factoryState) || knownFactoryStates.get(factoryState) < score;
    }

    private String getFactoryStateString(FactoryOption factoryOption) {
        StringBuilder stateString = new StringBuilder();
        // bluePrint timeleft number of materials/number of robots
        stateString.append(factoryOption.factory.bluePrint.id).append(":")
                .append(factoryOption.factory.timeLeft).append(":")
                .append(factoryOption.factory.clayStorage).append(":")
                .append(factoryOption.factory.obsStorage).append(":")
                .append(factoryOption.factory.geoStorage).append(":")
                .append(factoryOption.factory.oreRobots).append(":")
                .append(factoryOption.factory.clayRobots).append(":")
                .append(factoryOption.factory.obsRobots).append(":")
                .append(factoryOption.factory.geoRobots).append(":")
                .append(factoryOption.constructedBot);
        ;
        return stateString.toString();
    }

    @Override
    public void part2() throws IOException {
        int total = 1;
        for (int i = 0; i < 3; i++) {
            bluePrintSolutions = new HashSet<>();
            BluePrint bluePrint = bluePrints.get(i);
            Factory factory = new Factory(bluePrint, 32);
            produce(factory);
            int maxGeoStorage = bluePrintSolutions.stream().max(Comparator.comparing(Factory::getGeoStorage)).get().getGeoStorage();
            System.out.println("Blueprint " + bluePrint.id + ", storage = " + maxGeoStorage);
            total *= maxGeoStorage;
        }
        System.out.println(total);
    }

    class FactoryOption {
        private Factory factory;
        private String constructedBot;

        public FactoryOption(Factory factory, String constructedBot) {
            this.factory = factory;
            this.constructedBot = constructedBot;
        }
    }

    class BluePrint {
        private int id;
        private Cost oreRobotCost;
        private Cost clayRobotCost;
        private Cost obsRobotCost;
        private Cost geoRobotCost;

        public BluePrint(int id, Cost oreRobotCost, Cost clayRobotCost, Cost obsRobotCost, Cost geoRobotCost) {
            this.id = id;
            this.oreRobotCost = oreRobotCost;
            this.clayRobotCost = clayRobotCost;
            this.obsRobotCost = obsRobotCost;
            this.geoRobotCost = geoRobotCost;
        }
    }

    class Cost {
        private int ore;
        private int clay;
        private int obs;

        public Cost(int ore, int clay, int obs) {
            this.ore = ore;
            this.clay = clay;
            this.obs = obs;
        }
    }

    class Factory {
        private BluePrint bluePrint;
        private int timeLeft;
        private int oreStorage;
        private int clayStorage;
        private int obsStorage;
        private int geoStorage;

        private int maxOreCost;
        
        private int oreRobots;
        private int clayRobots;
        private int obsRobots;
        private int geoRobots;
        private List<Factory> children;

        public Factory(BluePrint bluePrint, int timeLeft) {
            this(bluePrint, timeLeft, 0, 0, 0, 0, 1, 0, 0, 0);
        }

        public Factory(BluePrint bluePrint, int timeLeft, int oreStorage, int clayStorage, int obsStorage, int geoStorage, int oreRobots, int clayRobots, int obsRobots, int geoRobots) {
            this.bluePrint = bluePrint;
            this.timeLeft = timeLeft;
            this.oreStorage = oreStorage;
            this.clayStorage = clayStorage;
            this.obsStorage = obsStorage;
            this.geoStorage = geoStorage;
            this.oreRobots = oreRobots;
            this.clayRobots = clayRobots;
            this.obsRobots = obsRobots;
            this.geoRobots = geoRobots;
            this.maxOreCost = Math.max(Math.max(Math.max(bluePrint.oreRobotCost.ore, bluePrint.clayRobotCost.ore), bluePrint.obsRobotCost.ore), bluePrint.geoRobotCost.ore);
            this.children = new ArrayList<>();
        }

        public int getGeoStorage() {
            return geoStorage;
        }
    }
    
}
