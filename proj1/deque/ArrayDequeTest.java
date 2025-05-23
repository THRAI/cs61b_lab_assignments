package deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    @Test
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        assertEquals(0, lld1.size());

        assertTrue("A newly initialized ArrayDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    /** Check if equal method works with deviated indexes. */
    @Test
    public void testEqual() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        ArrayDeque<Integer> a2 = new ArrayDeque<>();

        a2.addLast(1);
        a2.addLast(2);
        a2.removeFirst();
        a2.removeFirst();

        for (int i = 0; i < 100; i++) {
            a1.addLast(i);
            a2.addLast(i);
        }

        assertEquals(a1, a2);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterward. */
    @Test
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeque with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void randomizedTest() {
        java.util.Deque<Integer> correctDeque = new java.util.ArrayDeque<>();
        ArrayDeque<Integer> dequeUnderTest = new ArrayDeque<>();

        int N = 500000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            switch (operationNumber) {
                case 0:
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    correctDeque.addLast(randVal);
                    dequeUnderTest.addLast(randVal);
                    assertEquals(correctDeque.getLast(), dequeUnderTest.getLast());
                    System.out.println("addLast(" + randVal + ")");
                    break;
                case 1:
                    // size
                    int sizeC = correctDeque.size();
                    int sizeD = dequeUnderTest.size();
                    assertEquals(sizeC, sizeD);
                    System.out.println("size: " + sizeC);
                    break;
                case 2:
                    // getLast
                    if (!correctDeque.isEmpty()) {
                        int lastC = correctDeque.getLast();
                        int lastD = dequeUnderTest.getLast();
                        assertEquals(lastC, lastD);
                        System.out.println("getLast: " + lastC);
                    }
                    break;
                case 3:
                    // removeLast
                    if (!correctDeque.isEmpty()) {
                        int lastC = correctDeque.removeLast();
                        int lastD = dequeUnderTest.removeLast();
                        assertEquals(lastC, lastD);
                        System.out.println("removeLast: " + lastC);
                    }
                    break;
                case 4:
                    // addFirst
                    int randomVal = StdRandom.uniform(0, 100);
                    correctDeque.addFirst(randomVal);
                    dequeUnderTest.addFirst(randomVal);
                    assertEquals(correctDeque.getFirst(), dequeUnderTest.getFirst());
                    System.out.println("addFirst(" + randomVal + ")");
                    break;
                case 5:
                    // removeFirst
                    if (!correctDeque.isEmpty()) {
                        int firstC = correctDeque.removeFirst();
                        int firstD = dequeUnderTest.removeFirst();
                        assertEquals(firstD, firstC);
                        System.out.println("removeLast: " + firstC);
                    }
                    break;
            }
        }
    }
}
