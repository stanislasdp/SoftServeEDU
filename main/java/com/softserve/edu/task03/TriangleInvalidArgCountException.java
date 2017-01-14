/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;

/**
 * thrown if triangle arguments to create triangle instance is not four
 * @author Stas kiryan
 * @version 1.0
 */
public class TriangleInvalidArgCountException extends Exception {

    /**
     * Constructs a <code>TriangleInvalidArgCountException</code> with detail message.
     *
     * @param message detail message
     */
    public TriangleInvalidArgCountException(final String message) {
        super(message);
    }
}
