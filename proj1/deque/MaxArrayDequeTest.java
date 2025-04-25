package deque;
import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;
import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

public class MaxArrayDequeTest {
    @Test
    public void testCanonOrder() {
        MaxArrayDeque<Integer> a = new MaxArrayDeque<>(new canonicalOrder());
        a.arr.addLast(3);
        a.arr.addLast(2);
        a.arr.addLast(1);
        assertEquals(3, (int)a.max());
    }

    @Test
    public void testReverseOrder() {
        MaxArrayDeque<Integer> a = new MaxArrayDeque<>(new reverseOrder());
        assertNull(a.max());
        a.arr.addLast(999);
        a.arr.addLast(888);
        a.arr.addLast(777);
        assertEquals(777, (int)a.max());
    }

    @Test
    public void testModulo3Order() {
        MaxArrayDeque<Integer> a = new MaxArrayDeque<>(new modulo3Order());
        assertNull(a.max());
        a.arr.addLast(4);
        a.arr.addLast(9999);
        a.arr.addLast(2);
        assertEquals(2, (int)a.max());
    }
}

class canonicalOrder implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

class reverseOrder implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
        return i2 - i1;
    }
}

class modulo3Order implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
        return (i1 % 3) - (i2 % 3);
    }
}