package year2021.day19;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day19-test.txt";
    private static List<String> instructions;
    private static final int OVERLAPPING_BEACONS = 3;
    private static List<Scanner> scanners = new ArrayList<>();

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
        /*
        --- scanner 0 ---
        0,2
        4,1
        3,3

        --- scanner 1 ---
        -1,-1
        -5,0
        -2,1
         */
        scanners = new ArrayList<>();
        Scanner scanner = new Scanner();
        List<Beacon> beacons = new ArrayList<>();
        for (String instruction : instructions) {
            if (instruction.equals("")) {
                scanner.beaconList = beacons;
                scanners.add(scanner);
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
                beacons.add(beacon);
            }
        }
        scanner.beaconList = beacons;
        scanners.add(scanner);

        System.out.println("Scanner list complete");

        tryIt(scanners.get(0));

    }

    private void tryIt(Scanner compareScanner) {
        for (int i = 0; i < scanners.size(); i++) {
            Scanner currentScanner = scanners.get(i);
            if (currentScanner.id == compareScanner.id) {
                continue;
            }

            int overlappingBeacons = 0;



            for (int cb = 0; cb < currentScanner.beaconList.size(); cb++) {
                // calculate x/y based on comparingBeacon
                Beacon compareBeacon = compareScanner.beaconList.get(0);
                Beacon currentBeacon = currentScanner.beaconList.get(cb);

                int possibleX = compareBeacon.x - currentBeacon.x;
                int possibleY = compareBeacon.y - currentBeacon.y;

                for (int b = 1; b < compareScanner.beaconList.size(); b++) {
                    int pBeaconX = compareScanner.beaconList.get(b).x - possibleX;
                    int pBeaconY = compareScanner.beaconList.get(b).y - possibleY;
//                    currentScanner.beaconList.contains();
                    System.out.println(pBeaconX + "," + pBeaconY);
                }

            }

            if (overlappingBeacons >= OVERLAPPING_BEACONS) {
                System.out.println("match");
                break;
            }


        }
    }

    @Override
    public void part2() throws IOException {

    }

    class Scanner {
        private int id;
        private int x;
        private int y;
        private int z;

        private List<Beacon> beaconList;

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
    }
}
