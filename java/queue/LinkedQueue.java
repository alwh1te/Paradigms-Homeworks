package queue;

import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    @Override
    protected void enqueueImpl(Object elem) {
        Node temp = tail;
        tail = new Node(elem);
        if (isEmpty()) {
            head = tail;
        } else {
            temp.next = tail;
        }
    }

    @Override
    protected Object dequeueImpl() {
        Object res  = head.value;
        head = head.next;
        return res;
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    public void clear() {
        size = 0;
        this.head = null;
    }

    @Override
    public int countIfImpl(int ans, Predicate<Object> predicate) {
        Node curr = head;
        while (curr.value != null) {
            if (predicate.test(curr.value)) {
                ans++;
            }
            curr = curr.next;
        }
        return ans;
    }
}
class Node {
    protected Object value;
    protected Node next;

    protected Node(Object item) {
        this.value = item;
    }
}