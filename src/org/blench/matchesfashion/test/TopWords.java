package org.blench.matchesfashion.test;

import java.io.StringReader;
import java.util.*;

public class TopWords {

    // scanner used to get words from stream
    private Scanner scanner;

    // set this flag to true once we've finished scanning
    private boolean doneScanning;

    // package-private for testing
    // "forward" hash, mapping word -> number of occurences
    Map<String, Integer> forwardTable;

    // package-private for testing
    // "reverse" hash, mapping number of occurrences to word(s)
    Map<Integer, Collection<String>> reverseTable;

    /**
     * Construct object for returning top-occurring words
     * @param r a source of characters containing words
     */
    TopWords(Readable r) {
        if (r == null) {
            throw new IllegalArgumentException("Parameter \"r\" is required to be non-null");
        }
        setup(r);
    }

    /**
     * <p>
     * Construct object for returning top-occurring words
     * </p>
     * <p>
     * NB: this is the same as calling {@code new TopWords(new StringReader(str));}
     * </p>
     * @param str a string containing words
     * @see #TopWords(Readable)
     */
    TopWords(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter \"str\" is required to be non-null");
        }
        setup(new StringReader(str));
    }

    // shared constructor stuff
    private void setup(Readable in) {
        doneScanning = false;
        forwardTable = new HashMap<>();
        reverseTable = new HashMap<>();
        // only consider letters and apostrophes for words, and drop all other characters
        scanner = new Scanner(in).useDelimiter("[^a-zA-Z']+");
    }

    // package-private for testing
    String getWord() {
        return scanner.next().toLowerCase();
    }

    private void populateTables() {

        // populate forward table
        while (true) {
            try {
                // get next word from stream
                String word = getWord();
                // set word count to 1 if not present, else increment
                forwardTable.compute(word, (word2, occurences) -> {
                    if (occurences == null) {
                        return 1;
                    } else {
                        return occurences + 1;
                    }
                });
            } catch (NoSuchElementException | IllegalStateException ex) {
                // we've probably got to the end of the stream, so exit
                break;
            }
        }

        // close inputstream underlying scanner
        scanner.close();
        doneScanning = true;

        // populate reverse table
        for (Map.Entry<String, Integer> e : forwardTable.entrySet()) {
            int occurrences = e.getValue();
            String word = e.getKey();
            reverseTable.compute(e.getValue(), (occurences2, words) -> {
                // no entry, create new list containing word
                if (words == null) {
                    // use a TreeSet to ensure tie-breaks favour words which collate earlier in the dictionary
                    // (TreeSets are naturally sorted)
                    TreeSet<String> newSet = new TreeSet<>();
                    newSet.add(word);
                    return newSet;
                } else {
                    words.add(word);
                    return words;
                }
            });
        }
    }

    /**
     * Return top n most-occuring words in order of occurrence.
     * @param n number of words to return
     * @return array of most-occuring words
     */
    public String[] topWords(int n) {

        if (!doneScanning) {
            populateTables();
        }

        // iterate reverse table in descending order
        return reverseTable
                .entrySet()
                .stream()
                // sort by number of occurrences, highest first
                .sorted((e1, e2) -> {return e2.getKey() - e1.getKey();})
                // map word list entries into a flat stream of words, meaning those with highest occurrence have highest
                // precedence, and lower-sorting words win in case of a tie break
                .flatMap(e -> e.getValue().stream())
                .limit(n)
                .toArray(String[]::new);
    }

    /**
     * <p>
     * Convenience method.
     * </p>
     * <p>
     * Return top 3 most-occuring words in order of occurrence.
     * </p>
     * @return array of 3 most-occuring words
     * @see #topWords(int)
     */
    public String[] top3Words() {
        return topWords(3);
    }

}
