package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseTableTests {

    @Test
    public void testReverseTable() {
        String str = "the the the of of of the and of";
        TopWords tw = new TopWords(str);
        tw.top3Words();
        Assertions.assertTrue(tw.reverseTable.get(1).contains("and"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("of"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("the"));
    }

}
