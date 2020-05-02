package org.blench.matchesfashion.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Reader;

// "negative" tests - check failures are handled gracefully
public class NegativeTests {

    // null reader - IllegalArgumentException thrown
    @Test
    public void testNullReader() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Reader s = null;
            TopWords tw = new TopWords(s);
        });
    }

    // null string - IllegalArgumentException thrown
    @Test
    public void testNullString() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String s = null;
            TopWords tw = new TopWords(s);
        });
    }

}
