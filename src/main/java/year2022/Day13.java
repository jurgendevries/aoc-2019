package year2022;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Day13 extends Base {
    private static final String INPUT = "2022/day13-input.txt";
    private static List<Pair> pairs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Day13 main = new Day13();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();
        main.readPairs();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void readPairs() {
        Pair pair = new Pair();
        pairs.add(pair);
        boolean firstPart = true;
        for (String instruction: instructions) {
            if ("".equals(instruction)) {
                pair = new Pair();
                firstPart = true;
                pairs.add(pair);
            } else {
                if (firstPart) {
                    pair.firstPacket = instruction;
                    firstPart = false;
                } else {
                    pair.secondPacket = instruction;
                }
            }
        }
    }

    @Override
    public void part1() throws IOException {
        int total = 0;
        for(int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            if (compare(parse(pair.firstPacket), parse(pair.secondPacket)) > 0) {
                total += i + 1;
            }
        }
        System.out.println(total);
    }

    public Packet parse(String s) {
        Packet packet = new Packet();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                // find end of children by finding closing bracket
                int open = 1;
                int endIndex = i + 1;
                while (open > 0) {
                    if (s.charAt(endIndex) == ']') {
                        open--;
                    }
                    else if (s.charAt(endIndex) == '[') {
                        open++;
                    }
                    endIndex++;
                }
                //break out children and recursively parse, then skip over to end of children
                packet.children.add(parse(s.substring(i, endIndex)));
                i = endIndex;
            } else {
                //break out number and add as sub-packet
                int endIndex = s.indexOf(",",i + 1);
                if (endIndex == -1) {
                    endIndex = s.indexOf("]", i);
                }
                String num = s.substring(i, endIndex);
                Packet number = new Packet();
                try {
                    number.value = Integer.parseInt(num);
                    packet.children.add(number);
                } catch(NumberFormatException e) {
                    //empty packet - just don't add as a children
                }
                i = endIndex;
            }
        }
        return packet;
    }

    //returns 1 if the packets are in the correct order, -1 if they are not, and
    public int compare(Packet one, Packet two) {
        for (int i = 0; i < one.children.size() || i < two.children.size(); i++) {
            //handle if either list runs out of values
            if (one.children.size() <= i) {
                return 1;
            }
            if (two.children.size() <= i) {
                return -1;
            }

            Packet curOne = one.children.get(i);
            Packet curTwo = two.children.get(i);
            int curOneVal = curOne.value;//one.children.get(i).value;
            int curTwoVal = curTwo.value;//two.children.get(i).value;

            // both list
            if (curOneVal == -1 && curTwoVal == -1) {
                //only care about definitive result
                int compare = compare(curOne, curTwo);
                if(compare != 0)
                    return compare;
                else {
                    continue;
                }
            }

            // convert right to list
            if (curOne.children.size() > 0 && curTwoVal != -1) {
                Packet temp = new Packet();
                Packet child = new Packet();
                child.value = curTwoVal;
                temp.children.add(child);
                int compare = compare(curOne, temp);

                if(compare != 0)
                    return compare;
                else {
                    continue;
                }
            }

            // convert left to list
            if (curOneVal != -1 && curTwo.children.size() > 0) {
                Packet temp = new Packet();
                Packet child = new Packet();
                child.value = curOneVal;
                temp.children.add(child);
                int compare = compare(temp, curTwo);

                if (compare != 0)
                    return compare;
                else {
                    continue;
                }
            }

            // both value
            if (curOneVal < curTwoVal) {
                return 1;
            }
            if (curOneVal > curTwoVal) {
                return -1;
            }
        }
        //if nothing returned yet, test was inconclusive
        return 0;
    }

    @Override
    public void part2() throws IOException {
        List<Packet> pairList = new ArrayList<>();
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                continue;
            }
            pairList.add(parse(instruction));
        }
        Packet one = parse("[[2]]");
        Packet two = parse("[[6]]");
        pairList.add(one);
        pairList.add(two);

        // sort with compare function
        Collections.sort(pairList, (o, t) -> this.compare(t, o));
        int indexOne = pairList.indexOf(one) + 1;
        int indexTwo = pairList.indexOf(two) + 1;

        System.out.println(indexOne * indexTwo);
    }

    class Pair {
        private String firstPacket;
        private String secondPacket;
    }

    class Packet {
        private int value = -1;
        private List<Packet> children = new ArrayList<>();
    }
}
