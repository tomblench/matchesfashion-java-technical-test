package org.blench.matchesfashion.test.api;

import org.blench.matchesfashion.test.internal.Analyser;

/**
 * Public API: static methods for analysing text.
 */
public class TopWords {

    private static final int N_WORDS = 3;

    /**
     * Return top 3 most-occurring words in descending order of occurrence.
     * @param str string to analyse
     * @return array of most-occurring words
     * @throws IllegalArgumentException if {@code str} is {@code null}
     */
    public static String[] top3Words(String str) {
        Analyser analyser = new Analyser(str);
        return analyser.topWords(N_WORDS);
    }

    /**
     * Return top 3 most-occurring words in descending order of occurrence.
     * @param r input to analyse
     * @return array of most-occurring words
     * @throws IllegalArgumentException if {@code r} is {@code null}
     */
    public static String[] top3Words(Readable r) {
        Analyser analyser = new Analyser(r);
        return analyser.topWords(N_WORDS);
    }

}
