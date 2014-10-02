import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkedListTest {

    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    @Test
    //mock a node with value 'a', set to the head of ll
    //see if ll contains 'a'
    public void testContainsHead() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('a');
        LinkedList ll = new LinkedList();
        ll.head = mockNode;
        assertTrue(ll.contains('a'));
    }

    @Test
    //mock a node with value 'a', insert to ll
    //see if ll contains 'a'
    public void testContainsInsert() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('a');
        LinkedList ll = new LinkedList();
        ll.insert(mockNode);
        assertTrue(ll.contains('a'));
    }

    @Test
    //mock a node with value 'a', insert to ll
    //see if ll contains 'a'
    public void testContainsInsertFalse() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('b');
        LinkedList ll = new LinkedList();
        ll.insert(mockNode);
        assertFalse(ll.contains('z'));
    }

    @Test
    //mock a node with value 'a', set to the head of ll
    //see if ll contains 'A'
    public void testContainsHeadCaseSensitive() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('a');
        LinkedList ll = new LinkedList();
        ll.head = mockNode;
        assertTrue(ll.contains('A'));
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
            llSize++;
            assertEquals(linkedList.size, llSize);
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

    //Test linked list constructor with char
    //Verify that linked list contains char constructed with
    @Test
    public void testConstructor() {
        for (char letter : alphabet) {
            LinkedList linkedList = new LinkedList(letter);
            assertTrue(linkedList.contains(letter));
        }
    }
}


