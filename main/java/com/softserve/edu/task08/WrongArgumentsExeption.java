/*
 * This application is designed for find Fibonacci number within specified range
 */

package com.softserve.edu.task08;

/**
 * Created by stas on 1/3/17.
 */
public class WrongArgumentsExeption extends Exception {
    /**
     * Constructs a <code>WrongArgumentsException</code> with detail message.
     *
     * @param message detail message
     */
    public WrongArgumentsExeption(final String message) {
        super(message);
    }
}
