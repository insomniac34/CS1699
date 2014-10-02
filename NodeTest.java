import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Random;

import static org.junit.Assert.*;

public class NodeTest {

    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    /*
    * Verifies that the node constructor works...
    * */
    @Test
    public void testNodeConstructor() {
        for (char letter : alphabet) {
            Node newNode = new Node(letter);
            assertEquals(newNode.data, letter);
        }
    }

    /*
    * Verifies that the Node object's getCurrentList() successfully returns a ptr to the LinkedList it is part of...
    * */
    @Test
    public void testGetCurrentList() {
        Random rnd = new Random();
        rnd.setSeed(0);
        ArrayDeque<LinkedList> listQueue = new ArrayDeque<LinkedList>();

        for (int i = 0; i < 10; i++) {
            listQueue.add(new LinkedList()); //according to API, add ALWAYS inserts at end of deque...
            for (char letter : alphabet) {
                listQueue.getLast().insert(new Node(letter));
            }
        }

        for (LinkedList linkedList: listQueue) {
            assertEquals(linkedList.head.getCurrentList(), linkedList);
        }
    }
}
