package year2022;

import base.Base;
import helpers.circularll.CircularLinkedListUtil;
import helpers.circularll.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day20 extends Base {
    private static final String INPUT = "2022/day20-input.txt";
    private static final long DECRYPTION_KEY = 811589153;

    List<Node> cll = new ArrayList<>();
    Node zero;

    public static void main(String[] args) throws IOException {
        Day20 main = new Day20();
        main.mainMethod(INPUT);
        main.prepareInput();
        long start = System.currentTimeMillis();

        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
    }

    private void buildList(boolean part1) {
        cll = new ArrayList<>();
        for (String instruction : instructions) {
            long val = part1 ? Integer.parseInt(instruction) : DECRYPTION_KEY * Integer.parseInt(instruction);
            Node newNode = new Node(val);
            if (!cll.isEmpty()) {
                Node prevNode = cll.get(cll.size() - 1);
                prevNode.setNext(newNode);
                newNode.setPrev(prevNode);
            }
            cll.add(newNode);
            if (newNode.getValue() == 0) {
                zero = newNode;
            }
        }
        Node head = cll.get(0);
        Node tail = cll.get(cll.size() - 1);
        head.setPrev(tail);
        tail.setNext(head);
    }

    private void move(Node currentNode) {
        CircularLinkedListUtil.remove(currentNode);
        long moveCount = currentNode.getValue() % (cll.size() - 1);
        if (moveCount < 0) {
            moveCount += cll.size() - 1;
        }

        for (long i = 0; i < moveCount; i++) {
            currentNode.setNext(currentNode.getNext().getNext());
        }
        currentNode.setPrev(currentNode.getNext().getPrev());
        CircularLinkedListUtil.insert(currentNode);
    }

    private long calcCoordinate() {
        long result = 0;
        Node current;
        for (int i = 1000; i <= 3000; i += 1000) {
            current = zero;
            int move = i % cll.size();
            for (int j = 0; j < move; j++) {
                current = current.getNext();
            }
            result += current.getValue();
        }
        return result;
    }

    @Override
    public void part1() throws IOException {
        buildList(true);
        for (Node n : cll) {
            move(n);
        }
        long result = calcCoordinate();
        System.out.println(result);
    }

    @Override
    public void part2() throws IOException {
        buildList(false);
        for (int k = 0; k < 10; k++) {
            for (Node n : cll) {
                move(n);
            }
        }
        long result = calcCoordinate();
        System.out.println(result);
        // 499036015
    }
}
