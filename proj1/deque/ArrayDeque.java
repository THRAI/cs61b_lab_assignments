package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int capacity; /** Array capacity. Effective entries is calculated in capacity(). */
    private int nextFirst; /** equals -1 index of effective data queue. */
    private int nextLast;  /** equals (nextFirst + 1) % cap + size() index of effective data queue. */
    private final int defaultCapacity = 8;
    private final int resizeFactor = 2;
    private final int shrinkDenominator = 2;
    private final int maximumSpaceToUseRate = 4;

    /** initialization: [nextLast ... nextFirst] */
    public ArrayDeque() {
        items = (T[]) new Object[defaultCapacity];
        capacity = defaultCapacity;
        nextFirst = 0;
        nextLast = defaultCapacity - 1;
    }

    /** initialization: [nextLast ... nextFirst] */
    public ArrayDeque(int capacity) {
        capacity = Math.max(capacity, defaultCapacity);
        items = (T[]) new Object[capacity];
        this.capacity = capacity;
        nextFirst = 0;
        nextLast = capacity - 1;
    }

    /** Resize policy: [1st 2nd ... nextLast ... nextFirst].
     *  Updates nextLast, nextFirst, items, and capacity.
     * @param newSize New capacity of the array
     */
    private void resizeArray(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        int j = 0;
        for (int i = (nextFirst + 1) % capacity; i != nextLast; i = (i + 1) % capacity) {
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
            resizeArray(capacity * resizeFactor);
        }
        items[nextFirst] = elem;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
    }

    /** Grows forward. [L1 L2 nextLast ... nextFirst]*/
    public void addLast(T elem) {
        if (nextLast == nextFirst) {
            resizeArray(capacity * resizeFactor);
        }
        items[nextLast] = elem;
        nextLast = (nextLast + 1) % capacity;
    }

    public boolean isEmpty() {
        return (nextFirst + 1) % capacity == nextLast;
    }

    public int size() {
        return (nextLast - nextFirst + capacity - 1) % capacity;
    }

    public int capacity() {
        return capacity;
    }

    /** print insertFirst queue first, then insertLast queue. */
    public void printDeque() {
        for (int i = (nextFirst + 1) % capacity; i != nextLast; i = (i + 1) % capacity) {
            System.out.print(items[i].toString() + " ");
        }
        System.out.print("\n");
    }

    /** nextFirst goes forward. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (size() > defaultCapacity && size() * maximumSpaceToUseRate <= capacity) {
            resizeArray(capacity / shrinkDenominator);
        }

        T returnVal = items[(nextFirst + 1) % capacity];
        items[(nextFirst + 1) % capacity] = null;
        return returnVal;
    }

    /** nextLast goes backward. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if (size() > defaultCapacity && size() * maximumSpaceToUseRate <= capacity) {
            resizeArray(capacity / shrinkDenominator);
        }

        T returnVal = items[(nextLast - 1 + capacity) % capacity];
        items[(nextLast - 1 + capacity) % capacity] = null;
        return returnVal;
    }

    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        int realIndex = (nextFirst + 1 + index) % capacity;
        return items[realIndex];
    }

}
