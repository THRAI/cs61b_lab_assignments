package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> {
    public ArrayDeque<T> arr;
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
        arr = new ArrayDeque<>();
    }

    public T max() {
        if (arr.isEmpty()) {
            return null;
        }

        T maxElem = arr.getFirst();
        for (int i = 1; i < arr.size(); i++) {
            T cur = arr.get(i);
            if (comparator.compare(cur, maxElem) > 0) {
                maxElem = cur;
            }
        }

        return maxElem;
    }

}
