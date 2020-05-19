package queue;

// s = size()
// l = length()
// h = mHead
// t = mTail
// e[] = mElements[]
// e = element
// c = capacity

// Inv: (s >= 0) && (e[i] != null for i = (h % l)..((t + l - 1) % l)) && (s = (l + t - h) % l) && (s < l)
public class ArrayQueueClass {

    private Object[] mElements;
    private int mHead, mTail;

    public ArrayQueueClass() {
        mElements = new Object[8];
        mHead = 0;
        mTail = 0;
    }

    public ArrayQueueClass(int capacity) {
        assert(capacity > 0);
        mElements = new Object[capacity];
        mHead = 0;
        mTail = 0;
    }

    // Pre: (e != null)
    // Post: (t' = (t + l - 1) % l) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l)) && (e[(t' + l - 1) % l] = e)
    public void enqueue(Object element) {
        assert(element != null);
        ensureCapacity(size() + 1);
        mElements[mTail] = element;
        mTail = q(mTail + 1);
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t - 1) % l))
    public Object element() {
        assert(size() > 0);
        return mElements[mHead];
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = (h + 1) % l) && (e'[i] = e[i] for i = (h' % l)..((t' + l - 1) % l)) && (e[h] = null)
    public Object dequeue() {
        assert(size() > 0);
        Object tmp = mElements[mHead];
        mElements[mHead] = null;
        mHead = q(mHead + 1);
        return tmp;
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public int size() {
        return q(mTail - mHead);
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public boolean isEmpty() {
        return size() == 0;
    }

    // Pre: true
    // Post: (t' = 0) && (h' = 0) && (e'[i] = null for i = 0..(l - 1))
    public void clear() {
        mElements = new Object[length()];
        mHead = 0;
        mTail = 0;
    }

    // Pre: (e != null)
    // Post: (t' = t) && (h' = (h + l - 1) % l) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l)) && (e[h'] = e)
    public void push(Object element) {
        assert(element != null);
        ensureCapacity(size() + 1);
        mHead = q(mHead - 1);
        mElements[mHead] = element;
    }

    // Pre: (s > 0)
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    public Object peek() {
        assert(size() > 0);
        return mElements[q(mTail - 1)];
    }

    // Pre: (s > 0)
    // Post: (t' = (t + l - 1) % l) && (h' = h) && (e'[i] = e[i] for i = (h' % l)..((t' + l - 1) % l)) && (e[(t + l - 1) % l] = null)
    public Object remove() {
        assert(size() > 0);
        mTail = q(mTail - 1);
        Object tmp = mElements[mTail];
        mElements[mTail] = null;
        return tmp;
    }

    // Pre: true
    // Post: (t' = t) && (h' = h) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    private int length() {
        return mElements.length;
    }

    // Pre: (c > 0) && (c = s + 1)
    // Post: ((c < l - 1) ? (l' = l) : (l' = 2 * l)) && (t' = s) && (h' = 0) && (e'[i] = e[i] for i = (h % l)..((t + l - 1) % l))
    private void ensureCapacity(int capacity) {
        if (capacity == length()) {
            Object[] newElements = reorganizeElements(2 * capacity);
            mTail = size();
            mHead = 0;
            mElements = newElements;
        }
    }

    private Object[] reorganizeElements(int capacity) {
        Object[] newElements = new Object[capacity];
        if (mHead < mTail) {
            System.arraycopy(mElements, mHead, newElements, 0, size());
        } else {
            System.arraycopy(mElements, mHead, newElements, 0, length() - mHead);
            System.arraycopy(mElements, 0, newElements, length() - mHead, mTail);
        }
        return newElements;
    }

    private int q(int number) {
        return (number + length()) % length();
    }
}
