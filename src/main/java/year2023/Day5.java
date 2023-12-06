package year2023;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Day5 extends Base {
    private static final String INPUT = "2023/day5-input.txt";
    private Map<Long, Long> knownSeeds = new HashMap<>();
    private Map<String, List<Mapping>> mappings;

    public static void main(String[] args) throws IOException {
        Day5 main = new Day5();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();


        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();

        long end = System.currentTimeMillis();
        long duration = (end - start);
        System.out.println("duration: " + duration);
    }

    @Override
    public void part1() throws IOException {
        String[] seeds = instructions.get(0).split(":")[1].trim().split(" ");
        mappings = getMappings(instructions);
        long minLoc = Long.MAX_VALUE;
        for (String s : seeds) {
            long location = (calculateLocation(Long.valueOf(s)));
            minLoc = location < minLoc ? location : minLoc;
        }
        System.out.println(minLoc);
    }

    @Override
    public void part2() throws IOException {
        // reverse lookup from location 0 untill we hit a seed that is in one of our ranges
        List<SeedRange> seedRanges = new ArrayList<>();
        String[] seedStrings = instructions.get(0).split(":")[1].trim().split(" ");
        long minLoc = Long.MAX_VALUE;
        for (int i = 0; i < seedStrings.length; i += 2) {
            long seedStart = Long.valueOf(seedStrings[i]);
            long seedEnd = seedStart + Long.valueOf(seedStrings[i + 1]);
            seedRanges.add(new SeedRange(seedStart, seedEnd));
        }

        boolean seedFound = false;
        Long result = null;
        for (long i = 0; ; i++) {
            long seed = calculateSeed(i);
            for (SeedRange sRange : seedRanges) {
                if (seed >= sRange.getMin() && seed < sRange.getMax()) {
                    seedFound = true;
                    result = i;
                    break;
                }
            }
            if (seedFound) {
                break;
            }
        }
        System.out.println(result);
    }

    private long calculateSeed(long loc) {
        long hum = getMappedValueReverse("humidity-to-location", loc);
        long temp = getMappedValueReverse("temperature-to-humidity", hum);
        long light = getMappedValueReverse("light-to-temperature", temp);
        long water = getMappedValueReverse("water-to-light", light);
        long fert = getMappedValueReverse("fertilizer-to-water", water);
        long soil = getMappedValueReverse("soil-to-fertilizer", fert);
        long seed = getMappedValueReverse("seed-to-soil", soil);
        return seed;
    }

    private long calculateLocation(long seed) {
        long soil = getMappedValue("seed-to-soil", seed);
        long fert = getMappedValue("soil-to-fertilizer", soil);
        long water = getMappedValue("fertilizer-to-water", fert);
        long light = getMappedValue("water-to-light", water);
        long temp = getMappedValue("light-to-temperature", light);
        long hum = getMappedValue("temperature-to-humidity", temp);
        long loc = getMappedValue("humidity-to-location", hum);
        return loc;
    }

    private long getMappedValueReverse(String type, long sv) {
        List<Mapping> mapValues = mappings.get(type);
        for (Mapping mv : mapValues) {
            if (sv >= mv.getDestinationStart() && sv < mv.getDestinationStart() + mv.getRange()) {
                // value found in mapping
                // convert to mapped value
                long diff = mv.getSourceStart() - mv.getDestinationStart();
                return sv + diff;
            }
        }
        // value not found, return source value
        return sv;
    }

    private long getMappedValue(String type, long sv) {
        List<Mapping> mapValues = mappings.get(type);
        for (Mapping mv : mapValues) {
            if (sv >= mv.getSourceStart() && sv < mv.getSourceStart() + mv.getRange()) {
                // value found in mapping
                // convert to mapped value
                long diff =  mv.getDestinationStart() - mv.getSourceStart();
                return sv + diff;
            }
        }
        // value not found, return source value
        return sv;
    }

    Map<String, List<Mapping>> getMappings(List<String> instructions) {
        Map<String, List<Mapping>> mappings = new HashMap<>();
        mappings.put("seed-to-soil", new ArrayList<>());
        mappings.put("soil-to-fertilizer", new ArrayList<>());
        mappings.put("fertilizer-to-water", new ArrayList<>());
        mappings.put("water-to-light", new ArrayList<>());
        mappings.put("light-to-temperature", new ArrayList<>());
        mappings.put("temperature-to-humidity", new ArrayList<>());
        mappings.put("humidity-to-location", new ArrayList<>());

        String mappingName = "seed-to-soil";
        for (int i = 3; i < instructions.size(); i++) {
            if (instructions.get(i).equals("")) {
                i += 1;
                mappingName = instructions.get(i).split(" ")[0];
                continue;
            }
            Mapping m = new Mapping(
                    Long.valueOf(instructions.get(i).split(" ")[0]),
                    Long.valueOf(instructions.get(i).split(" ")[1]),
                    Long.valueOf(instructions.get(i).split(" ")[2])
            );
            mappings.get(mappingName).add(m);
        }
        return mappings;
    }

    class SeedRange {
        private long min;
        private long max;

        public SeedRange(long min, long max) {
            this.min = min;
            this.max = max;
        }

        public long getMin() {
            return min;
        }

        public void setMin(long min) {
            this.min = min;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }
    }

    class Mapping {
        private long destinationStart;
        private long sourceStart;
        private long range;

        public Mapping(long destinationStart, long sourceStart, long range) {
            this.destinationStart = destinationStart;
            this.sourceStart = sourceStart;
            this.range = range;
        }
        public long getDestinationStart() {
            return destinationStart;
        }

        public void setDestinationStart(long destinationStart) {
            this.destinationStart = destinationStart;
        }

        public long getSourceStart() {
            return sourceStart;
        }

        public void setSourceStart(long sourceStart) {
            this.sourceStart = sourceStart;
        }

        public long getRange() {
            return range;
        }

        public void setRange(long range) {
            this.range = range;
        }
    }
}
