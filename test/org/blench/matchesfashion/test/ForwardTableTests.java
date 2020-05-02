package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// check that the forward tables are populated correctly ("clear box" test)
public class ForwardTableTests {

    @Test
    public void testForwardTable() {
        String str = "the the the of the and if and";
        TopWords tw = new TopWords(str);
        tw.top3Words();
        Assertions.assertEquals(4, tw.forwardTable.get("the"));
        Assertions.assertEquals(2, tw.forwardTable.get("and"));
        Assertions.assertEquals(1, tw.forwardTable.get("of"));
        Assertions.assertEquals(1, tw.forwardTable.get("if"));
    }

}
