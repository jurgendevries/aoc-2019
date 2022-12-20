package helpers.circularll;

public class Node {
    long value;
    Node next, prev;

    public Node(long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
