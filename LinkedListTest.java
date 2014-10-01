import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkedListTest {

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
}