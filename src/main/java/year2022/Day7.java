package year2022;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends Base {
    private static final String INPUT = "2022/day7-input.txt";
    private Drive currentDrive;
    private List<Long> tValues = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Day7 main = new Day7();
        main.mainMethod(INPUT);
        main.prepareInput();
        System.out.println("PART1:");
        long start = System.currentTimeMillis();
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    @Override
    public void part1() throws IOException {
        for (String instruction : instructions) {
            evaluateInstruction(instruction);
        }

        while (currentDrive.getParent() != null) {
            currentDrive = currentDrive.getParent();
        }

        calculateTotalSizes(currentDrive, "/");

        long total = tValues.stream().filter(s -> s <= 100000).reduce(0L, Long::sum);
        System.out.println(total);
    }

    private long calculateTotalSizes(Drive root, String driveName) {
        long total = root.getSize();

        for (Drive child : root.getChildren()) {
            total += calculateTotalSizes(child, driveName + "/" + child.getName());
        }
        tValues.add(total);
        return total;

    }

    private void evaluateInstruction(String instruction) {
        if (instruction.startsWith("$ cd")) {
            String driveName = instruction.split(" ")[2];
            if ("..".equals(driveName)) {
                currentDrive = currentDrive.getParent();
            } else if ("/".equals(driveName)) {
                currentDrive = new Drive("/");
            } else {
                currentDrive = currentDrive.getChildren().stream().filter(x -> driveName.equals(x.getName())).findFirst().get();
            }
        } else if (instruction.startsWith("$ ls")) {
            // do nothing
        } else {
            // listing files, count sizes, ignore dirs
            if (instruction.startsWith("dir")) {
                String driveName = instruction.split(" ")[1];
                if (!currentDrive.getChildren().stream().filter(x -> driveName.equals(x.getName())).findFirst().isPresent()) {
                    Drive newDrive = new Drive(driveName);
                    newDrive.setParent(currentDrive);
                    currentDrive.getChildren().add(newDrive);
                }
            } else {
                currentDrive.setSize(currentDrive.getSize() + Long.valueOf(instruction.split(" ")[0]));
            }
        }
    }

    @Override
    public void part2() throws IOException {
        long neededMem = 30000000 - (70000000 - tValues.stream().mapToLong(x -> x).max().getAsLong());

        long deleteSize = tValues.stream()
                .filter(x -> x >= neededMem)
                .collect(Collectors.toList())
                .stream()
                .mapToLong(v -> v)
                .min().getAsLong();

        System.out.println(deleteSize);
    }

    class Drive {
        private String name;
        private long size;
        private Drive parent;
        private List<Drive> children;

        public Drive(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public Drive getParent() {
            return parent;
        }

        public void setParent(Drive parent) {
            this.parent = parent;
        }

        public List<Drive> getChildren() {
            return children;
        }

        public void setChildren(List<Drive> children) {
            this.children = children;
        }
    }
}
