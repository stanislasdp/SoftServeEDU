/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;

/**
 * thrown if triangle arguments to create triangle instance could not be converted to double
 * or converted double less or equal than zero.
 * @author Stas kiryan
 * @version 1.0
 */
public class TriangleInvalidArgException extends Exception {

    /**
     * Constructs a <code>TriangleInvalidArgException</code> with detail message.
     *
     * @param message detail message
     */
    public TriangleInvalidArgException(final String message) {
        super(message);
    }
}
