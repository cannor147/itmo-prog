package queue;

// s = size(queue)
// l = length(queue)
// h = queue.mHead
// t = queue.mTail
// e[] = queue.mElements[]
// e = element
// c = capacity

// Inv: (s >= 0) && (e[i] != null for i = (h % l)..((t + l - 1) % l)) && (s = (l + t - h) % l) && (s < l)
public class ArrayQueueADT {

    private Object[] mElements = new Object[8];
    private int mHead = 0, mTail = 0;

    // Pre: (e != null)
    // Post: (t' = (t + l - 1) % l) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l)) && (e[(t' + l - 1) % l] = e)
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert(element != null);
        ensureCapacity(queue,size(queue) + 1);
        queue.mElements[queue.mTail] = element;
        queue.mTail = q(queue, queue.mTail + 1);
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t - 1) % l))
    public static Object element(ArrayQueueADT queue) {
        assert(size(queue) > 0);
        return queue.mElements[queue.mHead];
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = (h + 1) % l) && (e'[i] = e[i] for i = (h' % l)..((t' + l - 1) % l)) && (e[h] = null)
    public static Object dequeue(ArrayQueueADT queue) {
        assert(size(queue) > 0);
        Object tmp = queue.mElements[queue.mHead];
        queue.mElements[queue.mHead] = null;
        queue.mHead = q(queue,queue.mHead + 1);
        return tmp;
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public static int size(ArrayQueueADT queue) {
        return q(queue,queue.mTail - queue.mHead);
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    // Pre: true
    // Post: (t' = 0) && (h' = 0) && (e'[i] = null for i = 0..(l - 1))
    public static void clear(ArrayQueueADT queue) {
        queue.mElements = new Object[length(queue)];
        queue.mHead = 0;
        queue.mTail = 0;
    }

    // Pre: (e != null)
    // Post: (t' = t) && (h' = (h + l - 1) % l) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l)) && (e[h'] = e)
    public static void push(ArrayQueueADT queue, Object element) {
        assert(element != null);
        ensureCapacity(queue,size(queue) + 1);
        queue.mHead = q(queue,queue.mHead - 1);
        queue.mElements[queue.mHead] = element;
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public static Object peek(ArrayQueueADT queue) {
        assert(size(queue) > 0);
        return queue.mElements[q(queue,queue.mTail - 1)];
    }

    // Pre: (s > 0)
    // Post: (t' = (t + l - 1) % l) && (h' = h) && (e'[i] = e[i] for i = (h' % l)..((t' + l - 1) % l)) && (e[(t + l - 1) % l] = null)
    public static Object remove(ArrayQueueADT queue) {
        assert(size(queue) > 0);
        queue.mTail = q(queue,queue.mTail - 1);
        Object tmp = queue.mElements[queue.mTail];
        queue.mElements[queue.mTail] = null;
        return tmp;
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    private static int length(ArrayQueueADT queue) {
        return queue.mElements.length;
    }

    // Pre: (c > 0) && (c = s + 1)
    // Post: ((c < l - 1) ? (l' = l) : (l' = 2 * l)) && (t' = s) && (h' = 0) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity == length(queue)) {
            Object[] newElements = reorganizeElements(queue,2 * capacity);
            queue.mTail = size(queue);
            queue.mHead = 0;
            queue.mElements = newElements;
        }
    }

    private static Object[] reorganizeElements(ArrayQueueADT queue, int capacity) {
        Object[] newElements = new Object[capacity];
        if (queue.mHead < queue.mTail) {
            System.arraycopy(queue.mElements, queue.mHead, newElements, 0, size(queue));
        } else {
            System.arraycopy(queue.mElements, queue.mHead, newElements, 0, length(queue) - queue.mHead);
            System.arraycopy(queue.mElements, 0, newElements, length(queue) - queue.mHead, queue.mTail);
        }
        return newElements;
    }

    private static int q(ArrayQueueADT queue, int number) {
        return (number + length(queue)) % length(queue);
    }

    private static void write(ArrayQueueADT queue) {
        for(int i = 0; i < queue.length(queue); i++) {
            if (queue.mElements[i] != null) {
                System.out.print(queue.mElements[i] + " ");
            } else {
                System.out.print("n ");
            }
        }
        System.out.println("(head:" + (queue.mHead) + ", tail: " + (queue.mTail) + ") [" + queue.length(queue) + "]");
    }
}
