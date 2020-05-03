package org.blench.matchesfashion.test;

import org.blench.matchesfashion.test.api.TopWords;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Main class. Program can be invoked from the command line with one argument (file name), or zero arguments (use
 * standard input). Top 3 words are printed to the console separated by commas. Basic error checking provided.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            // read from file
            try {
                FileReader fr = new FileReader(args[0]);
                String[] words = TopWords.top3Words(fr);
                printOutput(words);
            } catch (FileNotFoundException fnfe) {
                System.err.println("Error. Could not open file "+args[0]);
                System.exit(1);
            }
        } else if (args.length == 0) {
            if (System.in == null ) {
                System.err.println("Error. Could not open stdin");
                System.exit(1);
            }
            String[] words = TopWords.top3Words(new InputStreamReader(System.in));
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
