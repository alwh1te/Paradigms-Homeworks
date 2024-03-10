package queue;

import java.util.function.Predicate;

// Model: arr[1]...arr[n]
// Inv: forall i=1..n: a'[i] = a[i] != null
public interface Queue {

    // Pred: element != null
    // Post: n' == n + 1 && a'[n'] = element
    void enqueue(Object elem);

    // Pred: size > 0
    // Post: n' = n - 1 && Result = arr[0]
    Object dequeue();

    // Pred: size > 0
    // Post: queue : for i in 0..(n'-1) : queue[i]' != queue[i + 1]'
    //       arr'[i]
    void dedup();

    // Pred: size > 0
    // Post: n' = n && Result = arr[0]
    Object element();

    // Pred: True
    // Post: Result = n == 0 ? True : False
    boolean isEmpty();

    // Pred: True
    // Post: Result = n
    int size();

    // Pred: predicate != null && queue.size > 0
    // Post: Result = arr.length = i = 0..n : predicate.test(arr[i]) == True && forall a[i] = a[i']
    int countIf(Predicate<Object> predicate);

    // Pred: True
    // Post: this.isEmpty() == True
    void clear();
}
