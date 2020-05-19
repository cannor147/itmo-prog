package queue;

public class LinkedQueue extends AbstractQueue{
    
    private Node mHead, mTail;

    protected void enqueueImpl(Object element) {
        Node tmp = new Node(null, element);
        if (!(isEmpty())) {
            mTail.setNext(tmp);
        }
        mTail = tmp;
        if (isEmpty()) {
            mHead = mTail;
        }
    }

    protected Object elementImpl() {
        return mHead.getValue();
    }

    protected Object dequeueImpl() {
        Object tmp = elementImpl();
        mHead = mHead.getNext();
        return tmp;
    }

    protected void clearImpl() {
        mHead = null;
        mTail = null;
    }

    protected LinkedQueue createCopyImpl() {
        LinkedQueue queue = new LinkedQueue();
        Node tmp = mHead;
        while(tmp != null) {
            queue.enqueue(tmp.getValue());
            tmp = tmp.getNext();
        }
        return queue;
    }

    private class Node {
        private Node mNext;
        private Object mValue;

        private Node(Node next, Object value) {
            this.mNext = next;
            this.mValue = value;
        }

        public Node getNext() {
            return mNext;
        }

        public void setNext(Node next) {
            this.mNext = next;
        }

        public Object getValue() {
            return mValue;
        }
    }
}