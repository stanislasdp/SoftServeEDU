/*
 * This application is designed for calculating number of "lucky" tickets
 * according to algorithm name read from file
 */

package com.softserve.edu.task06;

/**
 * Algorithms enum is used to store available algorithms and theirs
 * string representations
 * @author Stas kiryan
 * @version 1.0
 */

public enum Algorithms {
    /**
     * Moscow algorithm.
     */
    MOSCOW("Moskow"),
    /**
     * Piter algorithm.
     */
    PITER("Piter");

    private final String algorithm;

    /**
     * build Algorithm enum instance
     * to the console
     * @param algorithm - string algorithm representation.
     */
    Algorithms(final String algorithm) {
        this.algorithm = algorithm;
}
    public String getAlgorithm() {
        return algorithm;
    }

}
