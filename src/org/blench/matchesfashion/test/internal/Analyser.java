package org.blench.matchesfashion.test.internal;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Internal implementation - not for public API use
 */
public class Analyser {

    // scanner used to get words from stream
    private Scanner scanner;

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
    public Analyser(Readable r) {
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
     * NB: this is the same as calling {@code new Analyser(new StringReader(str));}
     * </p>
     * @param str a string containing words
     * @see #Analyser(Readable)
     */
    public Analyser(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter \"str\" is required to be non-null");
        }
        setup(new StringReader(str));
    }

    // shared constructor stuff
    private void setup(Readable in) {
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

    // generate reverse table stream, sorted descending
    private Stream<Map.Entry<Integer, Collection<String>>> reverseTableSorted() {
        return reverseTable
                .entrySet()
                .stream()
                // sort by number of occurrences, highest first
                .sorted(Comparator.comparingInt(Map.Entry<Integer, Collection<String>>::getKey).reversed());
    }

    /**
     * Return top n most-occurring words in order of occurrence.
     * @param n number of words to return
     * @return array of most-occurring words
     */
    public String[] topWords(int n) {

        // scan text, populate forward and reverse tables
        populateTables();

        // dump out sorted reverse table for debugging purposes
        if (Boolean.parseBoolean(System.getProperty("dumpTable", "false"))) {
            System.err.println(reverseTableSorted().collect(Collectors.toList()));
        }

        // return results:
        // iterate reverse table in descending order
        return reverseTableSorted()
                // map word list entries into a flat stream of words, meaning those with highest occurrence have highest
                // precedence, and alphabetically lower-sorting words win in case of a tie break on occurrence.
                .flatMap(e -> e.getValue().stream())
                .limit(n)
                .toArray(String[]::new);
    }

}
