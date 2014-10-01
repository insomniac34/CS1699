import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AnagramTest {
    @Test
    public void anotherTest() {
        DLB myDLB = new DLB();
        myDLB.add("blah");
        assertEquals(myDLB, myDLB);
    }
}