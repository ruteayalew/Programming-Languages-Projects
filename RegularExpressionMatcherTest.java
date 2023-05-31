import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegularExpressionMatcherTest {

    ArrayList<String> wordsToTest;
    ArrayList<String> expectedResults;

    @Before
    public void setUp() throws Exception {
        wordsToTest = new ArrayList<String>();
        expectedResults = new ArrayList<String>();
    }

    @Test
    public void test_language1() {
        //expectedResults.clear();
        expectedResults.add("10201");
        expectedResults.add("210201");
        expectedResults.add("102012");
        expectedResults.add("2102012");
        expectedResults.add("1010201");
        expectedResults.add("1020101");
        expectedResults.add("101020101");
        expectedResults.add("21010201012");
        expectedResults.add("10201012");
        expectedResults.add("210101020101");

        wordsToTest.clear();
        wordsToTest.addAll(expectedResults);
        wordsToTest.add("2");
        wordsToTest.add("1001");
        wordsToTest.add("10210");
        wordsToTest.add("01201");
        wordsToTest.add("102201");
        wordsToTest.add("101201");
        wordsToTest.add("102001");
        wordsToTest.add("110201");
        wordsToTest.add("102011");
        wordsToTest.add("10");

        Language1Matcher l1m = new Language1Matcher();
        l1m.setWordsToCompareAgainst(wordsToTest);
        assertEquals("Language 1", expectedResults, l1m.findMatches());
    }

    @Test
    public void test_language2() {
        expectedResults.clear();
        expectedResults.add("2023/02");
        expectedResults.add("2023-02");
        expectedResults.add("1915/08");
        expectedResults.add("1915-12");
        expectedResults.add("9999-01");
        expectedResults.add("0001/10");
        expectedResults.add("1111-11");
        expectedResults.add("5432-10");
        expectedResults.add("7676-07");
        expectedResults.add("2024/01");

        wordsToTest.clear();
        wordsToTest.addAll(expectedResults);
        wordsToTest.add("202302");
        wordsToTest.add("202-02");
        wordsToTest.add("2023-2");
        wordsToTest.add("404/04");
        wordsToTest.add("44/4");
        wordsToTest.add("2023-20");
        wordsToTest.add("2023/20");
        wordsToTest.add("10000-02");
        wordsToTest.add("-2023-05");
        wordsToTest.add("4000-083");

        Language2Matcher l2m = new Language2Matcher();
        l2m.setWordsToCompareAgainst(wordsToTest);
        assertEquals("Language 2", expectedResults, l2m.findMatches());
    }

    @Test
    public void test_language3() {
        expectedResults.clear();
        expectedResults.add("001100");
        expectedResults.add("001111000");
        expectedResults.add("0000011100");
        expectedResults.add("110011");
        expectedResults.add("111000011");
        expectedResults.add("111110000001111");
        expectedResults.add("000111000");
        expectedResults.add("111000111");
        expectedResults.add("00000000001100");
        expectedResults.add("001111111111000");

        wordsToTest.clear();
        wordsToTest.addAll(expectedResults);
        wordsToTest.add("010");
        wordsToTest.add("101");
        wordsToTest.add("00100");
        wordsToTest.add("11011");
        wordsToTest.add("00110");
        wordsToTest.add("11001");
        wordsToTest.add("000");
        wordsToTest.add("1110000");
        wordsToTest.add("1");
        wordsToTest.add("0011001");

        Language3Matcher l3m = new Language3Matcher();
        l3m.setWordsToCompareAgainst(wordsToTest);
        assertEquals("Language 3", expectedResults, l3m.findMatches());
    }
}