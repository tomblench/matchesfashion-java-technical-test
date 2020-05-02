package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PublicApiTests {

    @Test
    public void testDonQuixote() {
        String str = "In a village of La Mancha, the name of which I have no desire to call\n" +
                "to mind, there lived not long since one of those gentlemen that keep a\n" +
                "lance in the lance-rack, an old buckler, a lean hack, and a greyhound\n" +
                "for coursing. An olla of rather more beef than mutton, a salad on most\n" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so\n" +
                "extra on Sundays, made away with three-quarters of his income.";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("of", words[1]);
        Assertions.assertEquals("on", words[2]);
    }

    // we can call multiple times (potentially with different values of n) without re-parsing
    @Test
    public void testMultipleCalls() {
        String str = "the the the of of of the and of";
        TopWords tw = new TopWords(str);
        tw.top3Words();
        String[] words2 = tw.topWords(2);
        String[] words3 = tw.topWords(3);
        Assertions.assertEquals(2, words2.length);
        Assertions.assertEquals(3, words3.length);
    }


    // test 0 words
    @Test
    public void test0Words() {
        String str = "";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        Assertions.assertEquals(0, words.length);
    }

    // test 1 words
    @Test
    public void test1Words() {
        String str = "a";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        Assertions.assertEquals(1, words.length);
    }

    // test 2 words (and tie break)
    @Test
    public void test2Words() {
        String str = "a a b b";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        Assertions.assertEquals(2, words.length);
    }

}
