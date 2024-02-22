package queue;

import java.util.Objects;

public class LinkedQueue {
    private Node item;
    private int size;
    public void enqueue(Object elem) {
        this.item = new Node(Objects.requireNonNull(elem), item);
        size++;
    }

    public Object dequeue() {
        assert size > 0;
        size--;
        Object result = item.value;
        item = item.prev;
        return result;
    }

    public Object element() {
        assert size > 0;
        return item.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        this.item = null;
        size = 0;
    }
}
class Node {
    protected Node prev;
    protected Object value;

    protected Node(Object item, Node prev) {
        this.value = item;
        this.prev = prev;
    }
}