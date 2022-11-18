package helpers.circularll;

public class CircularLinkedList {
    private Node head = null;
    private Node tail = null;

    public void addNode(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            // huidige tail moet naar de nieuwe node wijzen, dit is niet overbodig!
            tail.nextNode = newNode;
        }

        // tail vervangen door de nieuwe node
        tail = newNode;
        tail.nextNode = head;
    }

    public boolean containsNode(int searchVal) {
        Node currentNode = head;

        if (head == null) {
            return false;
        }

        do {
            if (currentNode.value == searchVal) {
                return true;
            }
            currentNode = currentNode.nextNode;
        } while (currentNode != head);

        return false;
    }

    public void deleteNode(int deleteVal) {
        Node currentNode = head;
        if (head == null) {
            return;
        }

        do {
            // nextNode gebruiken, anders kunnen we de tail niet meer bepalen
            Node nextNode = currentNode.nextNode;
            if (nextNode.value == deleteVal) {
                if (tail == head) {
                    // er is maar 1 item, de lijst is nu leeg
                    head = null;
                    tail = null;
                } else {
                    // er gaat 1 node tussen uit, de cirkel weer sluiten
                    currentNode.nextNode = nextNode.nextNode;
                    if (head == nextNode) {
                        head = head.nextNode;
                    }
                    if (tail == nextNode) {
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
    }

    public void traverseCll() {
        Node currentNode = head;

        if (head != null) {
            do {
                System.out.print(currentNode.value + (currentNode == tail ? "" : " -> "));
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
        }
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.addNode(1);
        cll.addNode(2);
        cll.addNode(3);
        cll.addNode(4);

        cll.traverseCll();
    }
}
