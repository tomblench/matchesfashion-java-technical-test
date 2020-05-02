package org.blench.matchesfashion.test;

// TODO for all classes
// javadoc
// package-info
// tests
// README
// how to run
// samples
// maven
// gradle

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    // TODO print stats, hashtable size etc
    public static void main(String[] args) {
        if (args.length == 1) {
            // read from file
            try {
                FileReader fr = new FileReader(args[0]);
                TopWords tw = new TopWords(fr);
                String[] words = tw.top3Words();
                printOutput(words);
            } catch (FileNotFoundException fnfe) {
                System.err.println("Error. Could not open file "+args[0]);
                System.exit(1);
            }
        } else if (args.length == 0) {
            TopWords tw = new TopWords(new InputStreamReader(System.in));
            String[] words = tw.top3Words();
            printOutput(words);
        } else {
            System.err.println("Error. Invoke with no args to read for stdin or invoke with one argument to read from file");
            System.exit(1);
        }
    }

    private static void printOutput(String[] words) {
        System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
    }
}
