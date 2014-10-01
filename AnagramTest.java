import static org.junit.Assert.*;
import org.junit.Test;

public class AnagramTest {
    @Test
    public void anotherTest() {
        DLB myDLB = new DLB();
        myDLB.add("blah");
        assertEquals(myDLB, myDLB);
    }

    @Test
    public void anagramTest() {
        Anagram agram = new Anagram();
        agram.anagramize(new StringBuilder(" "),new char[10],0,10);
    }
}