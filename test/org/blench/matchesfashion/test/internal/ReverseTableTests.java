package org.blench.matchesfashion.test.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// check that the reverse tables are populated correctly ("clear box" test)
public class ReverseTableTests {

    @Test
    public void testReverseTable() {
        String str = "the the the of of of the and of";
        Analyser tw = new Analyser(str);
        tw.topWords(3);
        Assertions.assertTrue(tw.reverseTable.get(1).contains("and"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("of"));
        Assertions.assertTrue(tw.reverseTable.get(4).contains("the"));
    }

}
