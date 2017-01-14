/*
 * This application is designed for calculating number of "lucky" tickets
 * according to algorithm name read from file
 */
package com.softserve.edu.task06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * App class provides read algorithms string from file
 * specified as program argument and select proper algorithm
 * string representations
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {

    private static String parseAlgorithmFromFile(final String fileName)
            throws IOException, NoMatchingAlgorithmFound {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            String currentLine = null;
            while ((currentLine = bufferedReader.readLine()) != null) {
                for (int i = 0; i < Algorithms.values().length; i++) {
                    if (currentLine.contains(Algorithms.values()[i].getAlgorithm())) {
                        return Algorithms.values()[i].getAlgorithm();
                    }
                }
            }
            throw new NoMatchingAlgorithmFound("No known algorithms are found in the file");
        }
    }

    /**
     * Algorithm factory gets string algorithm representation and
     * returns corresponding CountAlgorithmCalculation instance.
     *
     * @param algorithm t- String algorithm representation.
     * @return - CountAlgorithmCalculation instance
     * @throws IllegalArgumentException - if no known algorithm is passed in the method
     */
    public static CountAlgorithmCalculation algorithmFactory(final String algorithm) {
        CountAlgorithmCalculation algorithmCalculation = null;
        switch (algorithm) {
            case "Moskow":
                algorithmCalculation = new LuckyTicketsMoscow();
                break;
            case "Piter":
                algorithmCalculation = new LuckyTicketsPiter();
                break;
            default:
                throw new IllegalArgumentException("Passed algorithm is not supported");
        }
        return algorithmCalculation;
    }

    /**
     * The entry point of application. It parses algorithm from file argument,
     * get Algorithm instance from factory and perform calculation of "lucky" tickets.
     *
     * @param args - first program argument (arg[0]) is file name
     */
    public static void main(final String[] args) {
        String fileName = null;
        String algorithm = null;
        CountAlgorithmCalculation countAlgorithmCalculation = null;

        try {
            System.out.println("Enter filename to operate");
            fileName = ConsoleHelper.readStringParam();
            algorithm = parseAlgorithmFromFile(fileName);
            countAlgorithmCalculation = algorithmFactory(algorithm);
            System.out.println(countAlgorithmCalculation.calculateCount());

        } catch (NoMatchingAlgorithmFound noMatchFoundExc) {
            System.out.println("No matching algorithms found in the specified file");
        } catch (IOException IOExc) {
            System.out.println("IO error has been occurred");
        }

    }
}
