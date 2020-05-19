package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue
        implements Queue {

    protected int mSize = 0;

    protected abstract void enqueueImpl(Object element);
    protected abstract Object dequeueImpl();
    protected abstract Object elementImpl();
    protected abstract void clearImpl();
    protected abstract AbstractQueue createCopyImpl();

    @Override
    public void enqueue(Object element) {
        assert(element != null);
        enqueueImpl(element);
        mSize++;
    }

    @Override
    public Object element() {
        assert(mSize > 0);
        return elementImpl();
    }

    @Override
    public Object dequeue() {
        assert(mSize > 0);
        Object tmp = dequeueImpl();
        mSize--;
        return tmp;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return mSize == 0;
    }

    @Override
    public void clear() {
        clearImpl();
        mSize = 0;
    }

    @Override
    public Queue filter(Predicate<Object> predicate) {
        AbstractQueue tmp = createCopy();
        for (int i = 0; i < mSize; i++) {
            Object temp = tmp.dequeue();
            if (predicate.test(temp)) {
                tmp.enqueue(temp);
            }
        }
        return tmp;
    }

    @Override
    public Queue map(Function<Object, Object> function) {
        AbstractQueue tmp = createCopy();
        for (int i = 0; i < mSize; i++) {
            tmp.enqueue(function.apply(tmp.dequeue()));
        }
        return tmp;
    }

    public AbstractQueue createCopy() {
        return createCopyImpl();
    }
}
