package org.blench.matchesfashion.test.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// check that the tokeniser works correctly with punctuation, spaces, etc
public class TokeniserTests {

    @Test
    public void testTokeniser() {
        String str = "blah Blah haven't, and then bus-stop  umm";
        Analyser tw = new Analyser(str);
        Assertions.assertEquals("blah", tw.getWord());
        Assertions.assertEquals("blah", tw.getWord());
        Assertions.assertEquals("haven't", tw.getWord());
        Assertions.assertEquals("and", tw.getWord());
        Assertions.assertEquals("then", tw.getWord());
        Assertions.assertEquals("bus", tw.getWord());
        Assertions.assertEquals("stop", tw.getWord());
        Assertions.assertEquals("umm", tw.getWord());
    }

}
