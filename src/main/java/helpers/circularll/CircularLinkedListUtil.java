package helpers.circularll;

public class CircularLinkedListUtil {

    public static void insert(Node temp) {
        temp.next.prev = temp;
        temp.prev.next = temp;
    }

    public static void remove(Node temp) {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }

    public static void move(Node temp, int size) {
        remove(temp);
        long moveCount = temp.value % (size - 1);
        if (moveCount < 0) moveCount += size - 1;

        for (long i = 0; i < moveCount; i++) {
            temp.next = temp.next.next;
        }
        temp.prev = temp.next.prev;
        insert(temp);
    }
}
