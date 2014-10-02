import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testContains() throws Exception {

    }

    /*
    *  Asserts that the linked list grows linearly with nodes added
    * */
    @Test
    public void testLinkedListGrowth() {
        LinkedList linkedList = new LinkedList();
        int llSize = 0;
        for (char letter : alphabet) {
            linkedList.insert(letter);
            assertEquals(linkedList.size, llSize);
            llSize++;
        }
    }

    /*
    *  Asserts that the most recently added node is ALWAYS the head of the Linked List
    * */
    @Test
    public void testHeadAsMostRecentlyAddedNode() {
        LinkedList linkedList = new LinkedList();
        for (char letter : alphabet) {
            Node node = new Node(letter);
            linkedList.insert(node);
            assertEquals(linkedList.head, node);
        }
    }
}


