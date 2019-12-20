package year2019.day6;

import base.Base;
import org.apache.commons.lang3.StringUtils;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2019\\day6-input.txt";
    private static List<Orbit> orbits;
    private static List<String> descriptions;
    private static int totalOrbits;
    private static DefaultMutableTreeNode root;
    private static DefaultMutableTreeNode you = null;
    private static DefaultMutableTreeNode san = null;

    private static Map<String, Integer> orbitMapYou = new HashMap<>();
    private static Map<String, Integer> orbitMapSan = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);

//        main.part1();
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        prepare();

        countOrbits(root);

        System.out.println(totalOrbits);
    }

    @Override
    public void part2() throws IOException {
        prepare();

        findDistance();
    }

    private void findDistance() {
//        Orbit you = orbits.stream().filter(o -> o.getOrbitingAround().equals("YOU")).findFirst().get();
//        Orbit santa = orbits.stream().filter(o -> o.getOrbitingAround().equals("SAN")).findFirst().get();

        findTreeNodes(root);

        makeListOfParentsAndDistance(you, orbitMapYou, 0);
        makeListOfParentsAndDistance(san, orbitMapSan, 0);

        int shortestDist = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : orbitMapYou.entrySet()) {
            Integer santaDist = orbitMapSan.get(entry.getKey());

            if (santaDist != null) {
                if (santaDist + entry.getValue() < shortestDist) {
                    shortestDist = santaDist + entry.getValue();
                }
            }
        }

        System.out.println(shortestDist);
    }

    private void makeListOfParentsAndDistance(DefaultMutableTreeNode root, Map<String, Integer> orbitMap, int dist) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) root.getParent();
        if (parent != null) {
            orbitMap.put(parent.getUserObject().toString(), dist);
            dist++;
            makeListOfParentsAndDistance(parent, orbitMap, dist);
        }
    }

    private void findTreeNodes(DefaultMutableTreeNode root) {
        Enumeration children = root.children();
        while (children.hasMoreElements() && (you == null || san == null)) {
            DefaultMutableTreeNode group = (DefaultMutableTreeNode) children.nextElement();
            if (group.getUserObject().toString().equals("YOU")) {
                you = group;
            } else if (group.getUserObject().toString().equals("SAN")) {
                san = group;
            }
            findTreeNodes(group);
        }
    }

    private void countOrbits(DefaultMutableTreeNode root) {
        Enumeration children = root.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode group = (DefaultMutableTreeNode) children.nextElement();
            totalOrbits += group.getLevel();
            countOrbits(group);
        }

    }

    private void findOrbiters(String base, DefaultMutableTreeNode root) {
        int index = 0;
        while (orbits.stream().filter(o -> o.getOrbitingAround().equals(base)).findFirst().isPresent()) {
            Orbit orbit = orbits.stream().filter(o -> o.getOrbitingAround().equals(base)).findFirst().get();
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(orbit.getBaseObject());
            root.insert(group, index);
            orbits.remove(orbit);
            findOrbiters(orbit.getBaseObject(), group);
            index++;
        }
    }

    private void prepare() throws IOException {
        totalOrbits = 0;
        String line;
        descriptions = new ArrayList<>();
        orbits = new ArrayList<>();

        while ((line = input.readLine()) != null) {
            descriptions.add(line);
        }

        while (!descriptions.isEmpty()) {
            Orbit orbit = new Orbit(descriptions.get(0).split("\\)")[1], descriptions.get(0).split("\\)")[0]);
            orbits.add(orbit);
            descriptions.remove(0);
        }

        String baseOrbitObject = "COM";
        root = new DefaultMutableTreeNode("COM");

        while (!orbits.isEmpty()) {
            findOrbiters(baseOrbitObject, root);
        }
    }

    public class Orbit {
        private String baseObject;
        private String orbitingAround;

        public Orbit(String baseObject, String orbitingAround) {
            this.baseObject = baseObject;
            this.orbitingAround = orbitingAround;
        }

        public String getBaseObject() {
            return baseObject;
        }

        public void setBaseObject(String baseObject) {
            this.baseObject = baseObject;
        }

        public String getOrbitingAround() {
            return orbitingAround;
        }

        public void setOrbitingAround(String orbitingAround) {
            this.orbitingAround = orbitingAround;
        }
    }
}
