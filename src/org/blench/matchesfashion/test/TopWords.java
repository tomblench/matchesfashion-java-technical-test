package org.blench.matchesfashion.test;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class TopWords {

    // scanner used to get words from stream
    private Scanner scanner;

    // set this flag to true once we've finished scanning
    private boolean doneScanning;

    // package-private for testing
    // "forward" hash, mapping word -> number of occurences
    Map<String, Integer> forwardTable;

    // package-private for testing
    // "reverse" hash, mapping number of occurrences -> word(s)
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
                forwardTable.merge(word, 1, Integer::sum);
            } catch (NoSuchElementException | IllegalStateException ex) {
                // we've probably got to the end of the stream, so exit
                break;
            }
        }

        // close inputstream underlying scanner
        scanner.close();

        // populate reverse table
        for (Map.Entry<String, Integer> e : forwardTable.entrySet()) {
            Integer occurrences = e.getValue();
            String word = e.getKey();
            // if this slot in the hashtable hasn't been initialised, create a new TreeSet for the words
            // use a TreeSet to ensure tie-breaks favour words which collate earlier in the dictionary
            // since TreeSets are naturally sorted.
            // add word keyed by this number of occurrences
            reverseTable.computeIfAbsent(occurrences, k -> new TreeSet<>()).add(word);
        }
    }

    /**
     * Return top n most-occurring words in order of occurrence.
     * @param n number of words to return
     * @return array of most-occurring words
     */
    public String[] topWords(int n) {

        if (!doneScanning) {
            populateTables();
            doneScanning = true;
        }

        // dump out sorted reverse table for debugging purposes - there's a bit of repetition between this and the
        // code below, but we can't re-use the stream - once it's been collected, that's it!
        if (Boolean.parseBoolean(System.getProperty("dumpTable", "false"))) {
            System.err.println(reverseTable
                    .entrySet()
                    .stream()
                    // sort by number of occurrences, highest first
                    .sorted(Comparator.comparingInt(Map.Entry<Integer, Collection<String>>::getKey).reversed())
                    .collect(Collectors.toList()));
        }

        // iterate reverse table in descending order
        return reverseTable
                .entrySet()
                .stream()
                // sort by number of occurrences, highest first
                .sorted(Comparator.comparingInt(Map.Entry<Integer, Collection<String>>::getKey).reversed())
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
     * Return top 3 most-occurring words in order of occurrence.
     * </p>
     * @return array of 3 most-occurring words
     * @see #topWords(int)
     */
    public String[] top3Words() {
        return topWords(3);
    }

}
