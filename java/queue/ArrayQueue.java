package queue;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head;
    private int tail;

    @Override
    protected void enqueueImpl(Object element) {
        this.ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, head, newArray, 0, elements.length - head);
            System.arraycopy(elements, 0, newArray, elements.length - head, tail);
            elements = newArray;
            head = 0;
            tail = size;
        }
    }

    @Override
    public int countIfImpl(int ans, Predicate<Object> predicate) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(elements[(head + i) % elements.length])) {
                ans++;
            }
        }
        return ans;
    }

    @Override
    protected Object dequeueImpl() {
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return temp;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, Math.min(tail, head), Math.max(tail, head), null);
        size = 0;
        head = 0;
        tail = 0;
    }
}
