package queue;

import java.util.function.Predicate;

// Model: arr[1]...arr[n]
// Inv: for i = 1..n : arr[i] != null
public interface Queue {

    // Pred: element != null
    // Post: n' == n + 1 && a'[n'] = element
    void enqueue(Object elem);

    // Pred: size > 0
    // Post: n' = n - 1 && Result = arr[0]
    Object dequeue();

    // Pred: size > 0
    // Post: n' = n && Result = arr[0]
    Object element();

    // Pred: True
    // Post: Result = n == 0 ? True : False
    boolean isEmpty();

    // Pred: True
    // Post: Result = n
    int size();

    // Pred: predicate != null
    // Post: Result = arr.length = i = 0..n : predicate.test(arr[i]) == True
    int countIf(Predicate<Object> predicate);

    // Pred: True
    // Post: n' == 0 && for i=0..n: arr'[i] = null
    void clear();
}
