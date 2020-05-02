package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TokeniserTests {

    @Test
    public void testTokeniser() {
        String str = "blah Blah haven't, and then bus-stop  umm";
        TopWords tw = new TopWords(str);
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
