package queue;

// q - queue
// s - size of queue
// e - element
// p - predicate
// f - function
// e[] - elements

import java.util.function.Function;
import java.util.function.Predicate;

// Inv: (s >= 0) && (q[i] != null for i = 0..(s - 1))
public interface Queue {

    // Pre: (e != null)
    // Post: (s' = s + 1) && (q'[i] = q[i] for i = 0..(s - 1)) && (q[s' - 1] = e)
    void enqueue(Object element);

    // Pre: (s > 0)
    // Post: (s' = s) && (q'[i] = q[i] for i = 0..(s - 1))
    Object element();

    // Pre: (s > 0)
    // Post: (s' = s - 1) && (q'[i] = q[i] for i = 0..(s' - 1))
    Object dequeue();

    // Pre: true
    // Post: (s' = s) && (q'[i] = q[i] for i = 0..(s' - 1))
    int size();

    // Pre: true
    // Post: (s' = s) && (q'[i] = q[i] for i = 0..(s' - 1))
    boolean isEmpty();

    // Pre: true
    // Post: s' = 0
    void clear();

    // Pre: p != null
    // Post: (s' == s) && (q'[i] = q[i] for i = 0..(s' - 1)) &&
    // && (i[j] - sequence of indexes e[i] : predicate(e[i]) for i = 0..(s - 1) of size k) &&
    // && (v[j] = e[i[j]] for j = 0..(k - 1)) && (RESULT.e[] == v[]) && (RESULT.s == k)
    Queue filter(Predicate<Object> predicate);

    // Pre: f != null
    // Post: (s' == s) && (q'[i] = q[i] for i = 0..(s' - 1)) &&
    // && (v[j] = e[i[j]] for j = 0..(s - 1)) && (RESULT.e[] == v[]) && (RESULT.s == k)
    Queue map(Function<Object, Object> function);
}
