import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkedListTest {

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    //mock a node with value a
    public void testContainsHead() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('a');
        LinkedList ll = new LinkedList();
        ll.head = mockNode;
        assertTrue(ll.contains('a'));
    }

    public void testContainsInsert() throws Exception {
        Node mockNode = mock(Node.class);
        when(mockNode.getData()).thenReturn('a');
        LinkedList ll = new LinkedList();
        ll.insert(mockNode);
        assertTrue(ll.contains('a'));
    }

}