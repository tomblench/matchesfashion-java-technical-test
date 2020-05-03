package org.blench.matchesfashion.test.internal;

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
            Analyser tw = new Analyser(s);
        });
    }

    // null string - IllegalArgumentException thrown
    @Test
    public void testNullString() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String s = null;
            Analyser tw = new Analyser(s);
        });
    }

}
