package queue;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {

    protected int size;
    public void enqueue(Object elem) {
        this.enqueueImpl(Objects.requireNonNull(elem));
        size++;
    }

    protected abstract void enqueueImpl(Object elem);

    public Object dequeue() {
        assert size > 0;
        size--;
        return dequeueImpl();
    }

    public void dedup() {
        Queue newQueue = new LinkedQueue();
        while (!isEmpty()) {
            Object temp = this.dequeue();
            while (!isEmpty() && temp.equals(this.element())) {
                this.dequeue();
            }
            newQueue.enqueue(temp);
        }
        while (!newQueue.isEmpty()) {
            this.enqueue(newQueue.dequeue());
        }
    }



    protected abstract Object dequeueImpl();

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }


    public int countIf(Predicate<Object> predicate) {
        int ans = 0;
        return countIfImpl(ans, predicate);
    }

    protected abstract int countIfImpl(int ans, Predicate<Object> predicate);
}
