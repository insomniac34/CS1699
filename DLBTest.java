import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.*;

public class DLBTest {

    //DLB output values for easier referencing
    private final int IS_WORD_ONLY = 2;
    private final int IS_PREFIX_AND_WORD = 3;
    private final int IS_PREFIX_ONLY = 1;
    private final int NOT_WORD_OR_PREFIX = 0;

    private final int WORD_COUNT = 10;
    private final int WORD_SIZE = 10;
    private final String[] testWords = {
                                   "test",
                                   "word",
                                   "blah",
                                   "stuff",
                                   "things"
                                   };

    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    @Test
    //DLB.searchPrefix(StringBuilder s) should return int 1 if a searched word is a prefix
    public void testPrefix() throws Exception {
        DLB dict = new DLB();
        dict.add("hello");
        assertEquals(dict.searchPrefix(new StringBuilder("hell")),1);
    }

    //DLB.searchPrefix(StringBuilder s) should return int 3 if a word is both a prefix and a word
    @Test
    public void testPrefixandWord() throws Exception {
        DLB dict = new DLB();
        dict.add("hello");
        dict.add("hell");
        assertEquals(dict.searchPrefix(new StringBuilder("hell")),3);
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

    /*
    * Inserts a series of unique, randomly generated Strings into the DLB; then asserts their presence
    * */
    @Test
    public void testRandomizedWordInsertion() {
        DLB theDLB = new DLB();
        Random rnd = new Random();
        rnd.setSeed(0);
        ArrayList<String> randomStrings = new ArrayList<String>(this.WORD_COUNT);
        for (int i = 0; i < this.WORD_COUNT; i++) {
            char[] chars = new char[this.WORD_SIZE];
            for (int j = 0; j < this.WORD_SIZE; j++) {
                int randomLetterIndex = rnd.nextInt(this.alphabet.length);
                chars[j] = this.alphabet[randomLetterIndex];
            }
            String newString = (randomStrings.contains(new String(chars))) ? null : new String(chars);
            if (newString == null) {
               String altString = randomStrings.get(0);
               char[] altChars = new char[this.WORD_COUNT];
               while (randomStrings.contains(altString)) {
                   for (int j = 0; j < this.WORD_SIZE; j++) {
                       int altRandom = this.alphabet[rnd.nextInt(this.alphabet.length)];
                       altChars[j] = this.alphabet[altRandom];
                   }
                   altString = new String(altChars);
               }
               newString = altString;
            }
            randomStrings.add(newString);
        }

        for (String randomString : randomStrings) {
            theDLB.add(randomString);
            assertEquals(theDLB.searchPrefix(randomString), IS_WORD_ONLY);
        }
    }

    /*
    * Inserts a series of unique, randomly generated Strings into the DLB, then verifies the DLB contains the correct # of words
    * */
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

    /*
    * Inserts words from the global test list, verifies their presence in the DLB
    * */
    @Test
    public void testSearchPrefixPredefinedWords() {
        DLB theDLB = new DLB();
        for (String word : testWords) {
            theDLB.add(word);
        }

        for (String word : testWords) {
            assertEquals(theDLB.searchPrefix(word), IS_WORD_ONLY);
        }
    }

    @Test
    public void testSearchPrefixWithBounds() {

    }

    @Test
    public void testDictionaryPerformance() {

    }

    @Test
    public void testMultipleWords() {

    }

    @Test
    //add a word and try to remove it
    //check that neither searchPrefix or contains report it as there
    public void testWordRemoval() {
        DLB dict = new DLB();
        dict.add("hello");
        assertEquals(dict.searchPrefix(new StringBuilder("hello")),2);
        assertTrue(dict.contains("hello"));
        assertTrue(dict.remove("hello"));
        assertFalse(dict.contains("hello"));
        assertEquals(dict.searchPrefix(new StringBuilder("hello")),0);
    }

    @Test
    //add a prefix and a word that stems from it
    //remove prefix
    //verify that the word is still contained (by both contained and searchPrefix)
    public void testPrefixRemoval() {
        DLB dict = new DLB();
        dict.add("pre");
        dict.add("prefix");
        dict.remove("pre");
        assertEquals(dict.searchPrefix(new StringBuilder("prefix")), 2);
        assertTrue(dict.contains("prefix"));
    }

    //performance test
    //attempt to load every word in war and peace into dictionary
    //then check that every word is contained in the dictionary
    //uses bufferedreader and stringtokenizer
    //WARNING: expect long runtime
    @Test
    public void warandPeaceTest() throws Exception{
        DLB dict = new DLB();
        File warPeace = new File("warandpeace.txt");
        StringTokenizer tk;
        String str;
        String nxt;
        boolean allContained = true;
        if(warPeace.isFile()){
            BufferedReader wpReader = new BufferedReader(new FileReader(warPeace));
            while((str = wpReader.readLine())!=null){
                tk = new StringTokenizer(str , ",.!?*-' ");
                while(tk.hasMoreTokens()){
                    nxt = tk.nextToken();
                    if (nxt != null){
                        dict.add(nxt);
                    }
                }
            }
            wpReader.close();
            wpReader = new BufferedReader(new FileReader(warPeace));
            while((str = wpReader.readLine())!=null){
                tk = new StringTokenizer(str , ",.!?*-' ");
                while(tk.hasMoreTokens()){
                    nxt = tk.nextToken();
                    if (nxt != null){
                        if(dict.searchPrefix(new StringBuilder(nxt)) == 0){
                            allContained = false;
                        }
                    }
                }
            }
        }
        assertTrue(allContained);
    }

    @Test
    //try to add a null string to the dictionary
    public void testNullAdd() {
        DLB dict = new DLB();
        assertNotNull(dict.add(null));
    }

    @Test
    //try to search for a null StringBuilder
    public void testNullSearch(){
        DLB dict = new DLB();
        dict.add("hello");
        assertNotNull(dict.searchPrefix(new StringBuilder(null)));
    }

    @Test
    //call DLB.contains(null)
    public void testNullContains(){
        DLB dict = new DLB();
        dict.add("hello");
        assertNotNull(dict.contains(null));
    }

    @Test
    //no instantiated DLB should equal null
    public void testNotEqualsNull(){
        DLB dict = new DLB();
        assertFalse(dict.equals(null));
    }

}