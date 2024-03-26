package queue;

import java.util.function.Predicate;

// Model: arr[1]...arr[n]
// Inv: forall i = 1..n: arr[i] != null
public interface Queue {

    // Pred: element != null
    // Post: n' == n + 1 && arr'[n'] = element && for i : 1..n arr'[i] = arr[i]
    void enqueue(Object elem);

    // Pred: size > 0
    // Post: n' = n - 1 && Result = arr[1] && for i : 1..(n - 1) arr[i] = arr[i + 1]
    Object dequeue();

    // Pred: size > 0
    // Post: queue' : for i=1..(n - 1): if queue[i] != queue[i + 1]: queue'.enqueue(queue[i])
    // queue = queue'

    void dedup();

    // Pred: size > 0
    // Post: n' = n && Result = arr[1] && for i : 1..n arr[i] == arr'[i]
    Object element();

    // Pred: True
    // Post: Result = n == 0 ? True : False
    boolean isEmpty();

    // Pred: True
    // Post: Result = n && for i : 1..n arr[i] == arr'[i]
    int size();

    // Pred: predicate != null && queue.size > 0
    // Post: Result = (counter = 0) =
    // for i : 1..n : if (predicate.test(arr[i]) == True) : counter' = counter + 1
    // && forall i=1..n: arr'[i] = arr[i]
    int countIf(Predicate<Object> predicate);

    // Pred: True
    // Post: this.isEmpty() == True
    void clear();
}
