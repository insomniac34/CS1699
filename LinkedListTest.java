import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkedListTest {

    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private final String[] testWords = {
            "blood",
            "ebola",
            "virus",
            "liberia",
            "texas",
            "doomed"
    };

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

    /*
    * Tests the stability of the linked list by breaking up words of arbitrary lengths into chars and inserting them one at a time into the data structure, then asserting that LL size == word length
    * */
    @Test
    public void testWordInsertion() {
        for (String word : testWords) {
            char[] letters = word.toCharArray();
            LinkedList linkedList = new LinkedList();
            for (char letter : letters) {
                linkedList.insert(letter);
            }
            assertEquals(linkedList.size, word.length());
        }
    }
}


