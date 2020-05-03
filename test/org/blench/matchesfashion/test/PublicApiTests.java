package org.blench.matchesfashion.test;

import org.blench.matchesfashion.test.api.TopWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// test the public API ("black box" tests)
public class PublicApiTests {

    // test using the Don Quixote quote from the instructions
    @Test
    public void testDonQuixote() {
        String str = "In a village of La Mancha, the name of which I have no desire to call\n" +
                "to mind, there lived not long since one of those gentlemen that keep a\n" +
                "lance in the lance-rack, an old buckler, a lean hack, and a greyhound\n" +
                "for coursing. An olla of rather more beef than mutton, a salad on most\n" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so\n" +
                "extra on Sundays, made away with three-quarters of his income.";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(3, words.length);
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("of", words[1]);
        Assertions.assertEquals("on", words[2]);
    }

    // test using sample from the instructions
    @Test
    public void testSample1() {
        String str = "e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(3, words.length);
        Assertions.assertEquals("e", words[0]);
        Assertions.assertEquals("ddd", words[1]);
        Assertions.assertEquals("aa", words[2]);
    }

    // test using sample from the instructions
    @Test
    public void testSample2() {
        String str = " //wont won't won't";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(2, words.length);
        Assertions.assertEquals("won't", words[0]);
        Assertions.assertEquals("wont", words[1]);
    }

    // test 0 words
    @Test
    public void test0Words() {
        String str = "";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(0, words.length);
    }

    // test 1 words
    @Test
    public void test1Words() {
        String str = "a";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(1, words.length);
    }

    // test 2 words (and tie break)
    @Test
    public void test2Words() {
        String str = "a a b b";
        String[] words = TopWords.top3Words(str);
        Assertions.assertEquals(2, words.length);
    }

}
