import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class DLBTest {

    private final int WORD_COUNT = 10;
    private final int WORD_SIZE = 10;
    private final String[] testWords = {
                                   "This is a test",
                                   "word",
                                   "blah",
                                   "stuff",
                                   "things"
                                   };

    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};


    @Test
    public void testAddandRetrieve() throws Exception {
        DLB dict = new DLB();
        dict.add("hello");
        assertEquals(dict.searchPrefix(new StringBuilder("hello")), 2);
    }

    @Test
    public void testBasicDLBInsertion() {
        DLB theDLB = new DLB();
        assertEquals(theDLB.isEmpty, true);
        theDLB.add("Test 1");
        assertEquals(theDLB.contains("Test 1"), true);

    }

    @Test
    public void testDLBWordCount() {


    }

    @Test
    public void testRandomizedWordInsertion() {
        DLB theDLB = new DLB();

        Random rnd = new Random();
        rnd.setSeed(0);
        String[] randomStrings = new String[this.WORD_COUNT];

        for (int i = 0; i < this.WORD_COUNT; i++) {
            char[] chars = new char[this.WORD_SIZE];
            for (int j = 0; j < this.WORD_SIZE; j++) {
                int randomLetterIndex = rnd.nextInt(this.alphabet.length);
                chars[j] = this.alphabet[randomLetterIndex];
            }
            randomStrings[i] = new String(chars);
        }

        for (String randomString : randomStrings) {
            theDLB.add(randomString);
            assertEquals(theDLB.contains(randomString), true);
        }
    }

    @Test
    public void testRandomizedWordInsertionCount() {
        DLB theDLB = new DLB();

        Random rnd = new Random();
        rnd.setSeed(0);
        String[] randomStrings = new String[this.WORD_COUNT];

        for (int i = 0; i < this.WORD_COUNT; i++) {
            char[] chars = new char[this.WORD_SIZE];
            for (int j = 0; j < this.WORD_SIZE; j++) {
                int randomLetterIndex = rnd.nextInt(this.alphabet.length);
                chars[j] = this.alphabet[randomLetterIndex];
            }
            randomStrings[i] = new String(chars);
        }

        for (String randomString : randomStrings) {
            System.out.println(randomString);
            theDLB.add(randomString);
        }
        assertEquals(theDLB.size(), this.WORD_COUNT);
    }



    @Test
    public void testDictionaryPerformance() {

    }

    @Test
    public void testMultipleWords() {

    }

    @Test
    public void testWordRemoval() {

    }

}