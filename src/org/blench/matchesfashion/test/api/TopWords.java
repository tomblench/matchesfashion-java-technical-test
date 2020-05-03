package org.blench.matchesfashion.test.api;

import org.blench.matchesfashion.test.internal.Analyser;

/**
 * Public API - static method facades
 */
public class TopWords {

    private static final int N_WORDS = 3;

    /**
     * Return top 3 most-occurring words in order of occurrence.
     * @param s string to analyse
     * @return array of most-occurring words
     */
    public static String[] top3Words(String s) {
        Analyser analyser = new Analyser(s);
        return analyser.topWords(N_WORDS);
    }

    /**
     * Return top 3 most-occurring words in order of occurrence.
     * @param r input to analyse
     * @return array of most-occurring words
     */
    public static String[] top3Words(Readable r) {
        Analyser analyser = new Analyser(r);
        return analyser.topWords(N_WORDS);
    }

}
