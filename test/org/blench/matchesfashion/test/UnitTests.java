package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class UnitTests {

    @Test
    public void testTokeniser() {
        String str = "blah Blah haven't, and then bus-stop  umm";
        TopWords tw = new TopWords(new StringReader(str));
        Assertions.assertEquals("blah", tw.getWord());
        Assertions.assertEquals("blah", tw.getWord());
        Assertions.assertEquals("haven't", tw.getWord());
        Assertions.assertEquals("and", tw.getWord());
        Assertions.assertEquals("then", tw.getWord());
        Assertions.assertEquals("bus", tw.getWord());
        Assertions.assertEquals("stop", tw.getWord());
        Assertions.assertEquals("umm", tw.getWord());
    }

    @Test
    public void testForwardTable() {
        String str = "the the the of the and if and";
        TopWords tw = new TopWords(new StringReader(str));
        tw.topWords(3);
        Assertions.assertEquals(4, tw.forwardTable.get("the"));
        Assertions.assertEquals(2, tw.forwardTable.get("and"));
        Assertions.assertEquals(1, tw.forwardTable.get("of"));
        Assertions.assertEquals(1, tw.forwardTable.get("if"));
    }


    @Test
    public void testReverseTable() {
        String str = "the the the of of of the and of";
        TopWords tw = new TopWords(new StringReader(str));
        tw.topWords(3);
        Assertions.assertTrue(tw.reverseTable.get(1).contains("and"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("of"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("the"));
    }

    @Test
    public void testDonQuixote() {
        String str = "In a village of La Mancha, the name of which I have no desire to call\n" +
                "to mind, there lived not long since one of those gentlemen that keep a\n" +
                "lance in the lance-rack, an old buckler, a lean hack, and a greyhound\n" +
                "for coursing. An olla of rather more beef than mutton, a salad on most\n" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so\n" +
                "extra on Sundays, made away with three-quarters of his income.";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("of", words[1]);
        Assertions.assertEquals("on", words[2]);
    }

    // test 0 words
    @Test
    public void test0Words() {
        String str = "";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals(0, words.length);
    }

    // test 1 words
    @Test
    public void test1Words() {
        String str = "a";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals(1, words.length);
    }

    // test 2 words (and tie break)
    @Test
    public void test2Words() {
        String str = "a a b b";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals(2, words.length);
    }

    // test tie-breaks
    @Test
    public void testTieBreaks() {
        String str = "a b c d a b c d a b c d";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

    // test tie-breaks
    @Test
    public void testTieBreaks2() {
        String str = "a a a b b c c d d ";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

    // test tie-breaks
    @Test
    public void testTieBreaks3() {
        String str = "e e a a a b b c c xx zz xx zz w w";
        TopWords tw = new TopWords(new StringReader(str));
        String[] words = tw.topWords(3);
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

}
