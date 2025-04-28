package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int capacity; /** Array capacity. Effective entries is calculated in capacity(). */
    private int nextFirst; /** equals -1 index of effective data queue. */
    private int nextLast;  /** equals (nextFirst + 1) % cap + size() index of effective data queue. */
    private final int defaultCapacity = 8;
    private final int resizeFactor = 2;
    private final int shrinkDenominator = 2;
    private final int maximumSpaceToUseRatio = 4;

    /** initialization: [nextLast ... nextFirst] */
    public ArrayDeque() {
        items = (T[]) new Object[defaultCapacity];
        capacity = defaultCapacity;
        nextLast = 0;
        nextFirst = defaultCapacity - 1;
    }

    /** initialization: [nextLast ... nextFirst] */
    public ArrayDeque(int capacity) {
        capacity = Math.max(capacity, defaultCapacity);
        items = (T[]) new Object[capacity];
        this.capacity = capacity;
        nextLast = 0;
        nextFirst = capacity - 1;
    }

    /** Resize policy: [1st 2nd ... nextLast ... nextFirst].
     *  Updates nextLast, nextFirst, items, and capacity.
     * @param newSize New capacity of the array
     */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        int j = 0;
        for (int i = oneAfter(nextFirst); i != nextLast; i = oneAfter(i)) {
            newArray[j++] = items[i];
        }

        nextLast = j;
        nextFirst = newSize - 1;
        items = newArray;
        capacity = newSize;
    }

    /** Grows backward. [nextLast ... nextFirst F2 F1]*/
    public void addFirst(T elem) {
        if (nextFirst == nextLast) {
            resize(capacity * resizeFactor);
        }
        items[nextFirst] = elem;
        nextFirst = oneBefore(nextFirst);
    }

    /** Grows forward. [L1 L2 nextLast ... nextFirst]*/
    public void addLast(T elem) {
        if (nextLast == nextFirst) {
            resize(capacity * resizeFactor);
        }
        items[nextLast] = elem;
        nextLast = oneAfter(nextLast);
    }

//    public boolean isEmpty() {
//        return oneAfter(nextFirst) == nextLast;
//    }

    /** nextLast = (nextFirst + 1) % cap + size() */
    public int size() {
        return (nextLast - nextFirst - 1 + capacity) % capacity;
    }

    /** Encapsulated index arithmetic, handles wrap around. */
    private int oneBefore(int index) { return (index - 1 + capacity) % capacity; }
    private int oneAfter(int index) { return (index + 1) % capacity; }

    public void printDeque() {
        for (int i = oneAfter(nextFirst); i != nextLast; i = oneAfter(i)) {
            System.out.print(items[i].toString() + " ");
        }
        System.out.print("\n");
    }

    /** nextFirst goes forward. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (size() > defaultCapacity && size() * maximumSpaceToUseRatio <= capacity) {
            resize(capacity / shrinkDenominator);
        }

        T returnVal = items[oneAfter(nextFirst)];
        items[oneAfter(nextFirst)] = null;
        nextFirst = oneAfter(nextFirst);
        return returnVal;
    }

    /** nextLast goes backward. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if (size() > defaultCapacity && size() * maximumSpaceToUseRatio <= capacity) {
            resize(capacity / shrinkDenominator);
        }

        T returnVal = items[oneBefore(nextLast)];
        items[oneBefore(nextLast)] = null;
        nextLast = oneBefore(nextLast);
        return returnVal;
    }

    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        int realIndex = oneAfter(nextFirst) + index;
        return items[realIndex];
    }

    public T getLast() {
        return items[oneBefore(nextLast)];
    }

    public T getFirst() {
        return items[oneAfter(nextFirst)];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int it;

        public ArrayDequeIterator() {
            it = oneAfter(nextFirst);
        }

        public boolean hasNext() {
            return oneAfter(it) == nextLast;
        }

        public T next() {
            T val = items[it];
            it = oneAfter(it);
            return val;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        if (((ArrayDeque<?>) o).size() != size()) {
            return false;
        }

        int index = oneAfter(nextFirst);
        int indexO = oneAfter(((ArrayDeque<?>) o).nextFirst);
        while (index != nextLast) {
            if (((ArrayDeque<T>) o).items[indexO] != items[index]) {
                return false;
            }
            index = oneAfter(index);
            indexO = oneAfter(indexO);
        }
        return true;
    }
}
