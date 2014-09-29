import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLBTest {


    @Test
    public void testAddandRetrieve() throws Exception {
        DLB dict = new DLB();
        dict.add("hello");
        assertEquals(dict.searchPrefix(new StringBuilder("hello")), 2);
    }

}