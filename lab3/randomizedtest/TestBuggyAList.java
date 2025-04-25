package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();

        for (int x: new int[]{5, 6, 7}) {
            buggyAList.addLast(x);
            aListNoResizing.addLast(x);
        }

        for (int i = 0; i < 3; i++) {
            int x = buggyAList.removeLast();
            int y = aListNoResizing.removeLast();
            assertEquals(x, y);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch(operationNumber) {
                case 0:
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    B.addLast(randVal);
                    assertEquals(L.getLast(), B.getLast());
                    System.out.println("addLast(" + randVal + ")");
                    break;
                case 1:
                    // size
                    int sizeL = L.size();
                    int sizeB = B.size();
                    assertEquals(sizeB, sizeL);
                    System.out.println("size: " + sizeL + " " + sizeB);
                    break;
                case 2:
                    // getLast
                    if (L.size() != 0) {
                        int lastL = L.getLast();
                        int lastB = B.getLast();
                        assertEquals(lastL, lastB);
                        System.out.println("getLast: " + lastL + " " + lastB);
                    }
                    break;
                case 3:
                    // removeLast
                    if (L.size() != 0) {
                        int lastL = L.removeLast();
                        int lastB = B.removeLast();
                        assertEquals(lastL, lastB);
                        System.out.println("removeLast: " + lastL + " " + lastB);
                    }
                    break;
            }
        }
    }
}

