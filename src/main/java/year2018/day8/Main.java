package year2018.day8;

import base.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2018/day8-input.txt";

    private static List<String> instructions;
    private static List<Node> nodes;
    private static String[] values;
    private static int ofset;
    private static int total;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        main.part1();
        //main.part2();
    }

    @Override
    public void part1() throws IOException {
        values = instructions.get(0).split(" ");
        ofset = 0;
        Node[] nodes = processNodes(1);
        System.out.println(total);
    }

    private Node[] processNodes(int numberOfNodes) {
        Node[] nodes = new Node[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            Node node = new Node();

            // get first two values as header
            int[] header = { Integer.parseInt(values[0 + ofset]), Integer.parseInt(values[1 + ofset]) };
            node.setHeader(header);

            // read number of children
            int numberOfChildren = header[0];

            ofset += 2;

            if (numberOfChildren > 0) {
                Node[] children = processNodes(numberOfChildren);
            }

            // read last ? items as meta data (read from header)
            int numberOfMetaDataItems = header[1];
            int[] metadata = new int[numberOfMetaDataItems];
            for (int m = 0; m < numberOfMetaDataItems; m++) {
                metadata[m] = Integer.parseInt(values[ofset + m]);
                total += Integer.parseInt(values[ofset + m]);
            }
            node.setMetadata(metadata);
            nodes[i] = node;
            ofset += numberOfMetaDataItems;
        }

        return nodes;
    }


    @Override
    public void part2() throws IOException {

    }

    class Node {
        private int[] header;
        private int[] metadata;
        private Node[] children;

        public Node() {
        }

        public Node(int[] header, int[] metadata, Node[] children) {
            this.header = header;
            this.metadata = metadata;
            this.children = children;
        }

        public int[] getHeader() {
            return header;
        }

        public void setHeader(int[] header) {
            this.header = header;
        }

        public int[] getMetadata() {
            return metadata;
        }

        public void setMetadata(int[] metadata) {
            this.metadata = metadata;
        }

        public Node[] getChildren() {
            return children;
        }

        public void setChildren(Node[] children) {
            this.children = children;
        }
    }
}
