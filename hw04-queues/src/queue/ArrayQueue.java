package queue;

public class ArrayQueue
        extends AbstractQueue {

    private Object[] mElements = new Object[8];
    private int mHead = 0, mTail = 0;

    public ArrayQueue() {
        mElements = new Object[8];
        mHead = 0;
        mTail = 0;
        mSize = 0;
    }

    public ArrayQueue(int capacity) {
        mElements = new Object[capacity];
        mHead = 0;
        mTail = 0;
        mSize = 0;
    }

    protected void enqueueImpl(Object element) {
        ensureCapacity(size() + 1);
        mElements[mTail] = element;
        mTail = q(mTail + 1);
    }

    protected Object elementImpl() {
        return mElements[mHead];
    }

    protected Object dequeueImpl() {
        Object tmp = mElements[mHead];
        mElements[mHead] = null;
        mHead = q(mHead + 1);
        return tmp;
    }

    protected void clearImpl() {
        mElements = new Object[length()];
        mHead = 0;
        mTail = 0;
    }

    protected ArrayQueue createCopyImpl() {
        ArrayQueue queue = new ArrayQueue(length());
        System.arraycopy(this.mElements, 0, queue.mElements, 0, length());
        queue.mHead = this.mHead;
        queue.mTail = this.mTail;
        queue.mSize = this.mSize;
        return queue;
    }

    private int length() {
        return mElements.length;
    }

    private void ensureCapacity(int capacity) {
        if (capacity == length()) {
            int tmp = size();
            Object[] newElements = reorganizeElements(2 * capacity);
            mTail = tmp;
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
