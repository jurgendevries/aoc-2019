package year2016;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 extends Base {
    private static final String INPUT = "day15-input.txt";
    Pattern pattern = Pattern.compile("\\d+");
    private static Map<String, Integer> registers;
    private static List<String> instructions;
    private static List<Level> levels = new ArrayList<>();
    /**
     * Disc #1 has 5 positions; at time=0, it is at position 2.
     * Disc #2 has 13 positions; at time=0, it is at position 7.
     * Disc #3 has 17 positions; at time=0, it is at position 10.
     * Disc #4 has 3 positions; at time=0, it is at position 2.
     * Disc #5 has 19 positions; at time=0, it is at position 9.
     * Disc #6 has 7 positions; at time=0, it is at position 0.
     */

    public static void main(String[] args) throws IOException {
        Day15 main = new Day15();
        main.mainMethod(INPUT);

        main.prepare();


//        main.part1();
        main.part2();
    }

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    private void setLevels() {
        levels = new ArrayList<>();
        int offset = 0;
        for (String instruction : instructions) {
            Matcher matcher = pattern.matcher(instruction);
            int[] values = new int[4];
            int c = 0;
            while (matcher.find()) {
                values[c++] = Integer.valueOf(matcher.group());
            }
            levels.add(new Level(values[0], values[1], values[3], values[3], offset));
            offset++;
        }
    }

    private void cycle() {
        for (Level level : levels) {
            level.current = level.current + 1 < level.postitions ? level.current + 1 : 0;
        }
    }

    private boolean canDrop(int currentLevel) {
        return levels.get(currentLevel + 1).current == 0;
    }


    @Override
    public void part1() throws IOException {
        setLevels();
        startCycles(-1, 22768, 0);

        System.out.println("prep done: " + levels.size());
    }

    private void startCycles(int startLevel, int startCounter, int currentCounter) {
        if (startLevel == 6) {
            System.out.println("DONE! startCounter: " + (startCounter - 1));
            return;
        }
        while (currentCounter < startCounter) {
            cycle();
            currentCounter++;
        }
        if (canDrop(startLevel)) {
            cycle();
            startCycles(++startLevel, startCounter, ++currentCounter);
        } else {
            setLevels();
            startCycles(-1, startCounter + 62985, 0);
        }
    }

    @Override
    public void part2() throws IOException {
        // bereken de offset naar 0
        //



        int counter = 0;
        int previousCycle = 1;
        setLevels();
        for (Level level : levels) {
            int offset = 0;
            int currentPos = (counter + level.current) % level.postitions;
            while ((currentPos + level.offset) % level.postitions != 0) {
                currentPos += previousCycle;
                offset += previousCycle;
            }
            if (level.offset > 0) {
                int firstCount = counter + offset;
                currentPos += previousCycle;
                offset += previousCycle;
                while ((currentPos + level.offset) % level.postitions != 0) {
                    currentPos += previousCycle;
                    offset += previousCycle;
                }
                int secondCount = counter + offset;
                counter += offset;
                previousCycle = secondCount - firstCount;
            } else {
                counter += offset;
                previousCycle = level.postitions;
            }
        }

        System.out.println(counter);
    }

    class Level {
        public int disc;
        public int postitions;
        public int start;
        public int current;
        public int offset;

        public Level(int disc, int postitions, int start, int current, int offset) {
            this.disc = disc;
            this.postitions = postitions;
            this.start = start;
            this.current = current;
            this.offset = offset;
        }
    }
}
