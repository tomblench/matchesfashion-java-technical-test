package org.blench.matchesfashion.test.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// check that the forward tables are populated correctly ("clear box" test)
public class ForwardTableTests {

    @Test
    public void testForwardTable() {
        String str = "the the the of the and if and";
        Analyser tw = new Analyser(str);
        tw.topWords(3);
        Assertions.assertEquals(4, tw.forwardTable.get("the"));
        Assertions.assertEquals(2, tw.forwardTable.get("and"));
        Assertions.assertEquals(1, tw.forwardTable.get("of"));
        Assertions.assertEquals(1, tw.forwardTable.get("if"));
    }

}
