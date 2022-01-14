package year2021.day19;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2021/day19-input.txt";
    private static List<String> instructions;
    private static final int OVERLAPPING_BEACONS = 12;
    private static List<Scanner> scannersToDo = new ArrayList<>();
    private static List<Scanner> scannersDone = new ArrayList<>();

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
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        scannersToDo = new ArrayList<>();
        Scanner scanner = new Scanner();
        List<Beacon> beacons = new ArrayList<>();
        for (String instruction : instructions) {
            if (instruction.equals("")) {
                scanner.beaconList = beacons;
                scanner.orientedBeaconList = beacons;
                scannersToDo.add(scanner);
            } else if (instruction.startsWith("--")) {
                int scannerId = Integer.parseInt(instruction.split(" ")[2]);

                if (scannerId == 0) {
                    scanner = new Scanner(scannerId, 0 ,0);
                } else {
                    scanner = new Scanner(scannerId);
                }
                beacons = new ArrayList<>();
            } else {
                Beacon beacon = new Beacon();
                beacon.x = Integer.parseInt(instruction.split(",")[0]);
                beacon.y = Integer.parseInt(instruction.split(",")[1]);
                beacon.z = Integer.parseInt(instruction.split(",")[2]);
                beacons.add(beacon);
            }
        }
        scanner.beaconList = beacons;
        scanner.orientedBeaconList = beacons;
        scannersToDo.add(scanner);

        Scanner mainScanner = scannersToDo.remove(0);
        mainScanner = applyOffset("0,0,0", mainScanner);
        scannersDone.add(mainScanner);

        solve();
    }

    private void solve() {
        while (!scannersToDo.isEmpty()) {
            for (int i = 0; i < scannersDone.size(); i++) {
                Scanner done = scannersDone.get(i);
                for (Scanner todo : scannersToDo) {
                    //String offset = getOffset(done, todo);
                    String offset = findOrientation(done, todo);
                    if (offset != null) {
                        todo = applyOffset(offset, todo);
                        scannersToDo.remove(todo);
                        scannersDone.add(todo);
                        break;
                    }
                }
            }
        }
        System.out.println("Scanning done");
        Set<String> uniquePositions = new HashSet<>();
        for (Scanner scanner : scannersDone) {
            scanner.absoluteBeaconList.stream().map(b -> b.x + "," + b.y + "," + b.z).forEach(uniquePositions::add);
        }
        System.out.println("Unique beacons: " + uniquePositions.size());
    }

    private Scanner applyOffset(String offset, Scanner scanner) {
        String[] split = offset.split(",");
        int offsetX = Integer.parseInt(split[0]);
        int offsetY = Integer.parseInt(split[1]);
        int offsetZ = Integer.parseInt(split[2]);
        scanner.x = offsetX;
        scanner.y = offsetY;
        scanner.z = offsetZ;
        List<Beacon> oBeacons = new ArrayList<>();
        for (Beacon b : scanner.orientedBeaconList) {
            Beacon aB = new Beacon();
            aB.x = b.x + offsetX;
            aB.y = b.y + offsetY;
            aB.z = b.z + offsetZ;
            oBeacons.add(aB);
        }
        scanner.absoluteBeaconList = oBeacons;
        return scanner;
    }

    @Override
    public void part2() throws IOException {
        int result = Integer.MIN_VALUE;
        for (Scanner scanner : scannersDone) {
            for(Scanner scannerCompare : scannersDone) {
                int dist = Math.abs(scanner.x - scannerCompare.x) +
                        Math.abs(scanner.y - scannerCompare.y) +
                        Math.abs(scanner.z - scannerCompare.z);
                result = Math.max(dist, result);
            }
        }
        System.out.println(result);
    }

    private String findOrientation(Scanner ref, Scanner scanner) {
        String offset;
        for (int cycle = 0; cycle < 2; cycle++) {
            for (int step = 0; step < 3; step++) {
                List<Beacon> rotations = new ArrayList<>();
                for (Beacon beacon : scanner.orientedBeaconList) {
                    beacon = roll(beacon);
                    rotations.add(new Beacon(beacon));
                }
                scanner.orientedBeaconList = rotations;
                offset = getOffsetForRotation(ref, scanner);
                if (offset != null) {
                    return offset;
                }
                for (int i = 0; i < 3; i++) {
                    List<Beacon> turns = new ArrayList<>();
                    for (Beacon beacon : scanner.orientedBeaconList) {
                        beacon = turn(beacon);
                        turns.add(new Beacon(beacon));
                    }
                    scanner.orientedBeaconList = turns;
                    offset = getOffsetForRotation(ref, scanner);
                    if (offset != null) {
                        return offset;
                    }
                }
            }
            List<Beacon> beacons = new ArrayList<>();
            for (Beacon beacon : scanner.orientedBeaconList) {
                beacon = roll(turn(roll(beacon)));
                beacons.add(new Beacon(beacon));
            }
            scanner.orientedBeaconList = beacons;
        }
        return null;
    }

    private String getOffsetForRotation(Scanner ref, Scanner scanner) {
        Map<String, Integer> offsetCount = new HashMap<>();
        for (Beacon refBeacon : ref.absoluteBeaconList) {
            for (Beacon beacon : scanner.orientedBeaconList) {
                String offsetString = (refBeacon.x - beacon.x) + "," +
                        (refBeacon.y - beacon.y) + "," +
                        (refBeacon.z - beacon.z);
                offsetCount.compute(offsetString, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        String offset = offsetCount.entrySet().stream()
                .filter(e -> e.getValue() >= OVERLAPPING_BEACONS)
                .map(Map.Entry::getKey)
                .findAny().orElse(null);
        if (offset != null) {
            return offset;
        }
        return null;
    }

    private Beacon roll(Beacon b) {
        Beacon bNew = new Beacon();
        bNew.x = b.x;
        bNew.y = b.z;
        bNew.z = -b.y;
        return bNew;
    }

    private Beacon turn(Beacon b) {
        Beacon bNew = new Beacon();
        bNew.x = -b.y;
        bNew.y = b.x;
        bNew.z = b.z;
        return bNew;
    }

    class Scanner {
        private int id;
        private int x;
        private int y;
        private int z;

        private List<Beacon> beaconList;
        private List<Beacon> orientedBeaconList;
        private List<Beacon> absoluteBeaconList;

        public Scanner() {
        }

        public Scanner(int id) {
            this.id = id;
        }

        public Scanner(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    class Beacon {
        private int x;
        private int y;
        private int z;

        public Beacon() {
        }

        public Beacon(Beacon b) {
            this.x = b.x;
            this.y = b.y;
            this.z = b.z;
        }
    }
}
