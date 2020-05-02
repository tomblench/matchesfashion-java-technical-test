package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// check that the tie break rules are applied correctly: words sorting lower in the dictionary have precedence
public class TieBreakTests {

    // test tie-breaks
    @Test
    public void testTieBreaks() {
        String str = "a b c d a b c d a b c d";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        // abcd all score 3 in the forward table
        Assertions.assertEquals(3, tw.forwardTable.get("a"));
        Assertions.assertEquals(3, tw.forwardTable.get("b"));
        Assertions.assertEquals(3, tw.forwardTable.get("c"));
        Assertions.assertEquals(3, tw.forwardTable.get("d"));
        // but only abc in output as they sort earliest
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

    // test tie-breaks
    @Test
    public void testTieBreaks2() {
        String str = "a a a b b c c d d ";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        // abcd score 3,2,2,2 respectively in the forward table
        Assertions.assertEquals(3, tw.forwardTable.get("a"));
        Assertions.assertEquals(2, tw.forwardTable.get("b"));
        Assertions.assertEquals(2, tw.forwardTable.get("c"));
        Assertions.assertEquals(2, tw.forwardTable.get("d"));
        // but only abc in output as they sort earliest
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

    // test tie-breaks
    @Test
    public void testTieBreaks3() {
        String str = "e e a a a b b c c xx zz xx zz w w";
        TopWords tw = new TopWords(str);
        String[] words = tw.top3Words();
        // as previous tests, but more complex
        Assertions.assertEquals(3, tw.forwardTable.get("a"));
        Assertions.assertEquals(2, tw.forwardTable.get("b"));
        Assertions.assertEquals(2, tw.forwardTable.get("c"));
        Assertions.assertEquals(2, tw.forwardTable.get("e"));
        Assertions.assertEquals(2, tw.forwardTable.get("w"));
        Assertions.assertEquals(2, tw.forwardTable.get("xx"));
        Assertions.assertEquals(2, tw.forwardTable.get("zz"));
        // but only abc in output as they sort earliest
        Assertions.assertEquals("a", words[0]);
        Assertions.assertEquals("b", words[1]);
        Assertions.assertEquals("c", words[2]);
    }

}
