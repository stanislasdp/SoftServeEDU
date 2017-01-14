/*
 * This application is designed for two purposes:
 * 1) parse file specified in program argument and count how many specified
 * in program agruments string in file;
 * 2) parse file specified in program argument and replace specified in program
 * argument string to another one.
 */
package com.softserve.edu.task04;

import java.io.IOException;

/**
 * Thrown if file to count or to replace String does not exist.
 * @author Stas Kiryan
 * @version 1.0
 */
public class FileNotSpecifiedException extends IOException {
    /**
     * Constructs a <code>FileNotSpecifiedException</code> with detail message.
     *
     * @param message detail message
     */
    public FileNotSpecifiedException(final String message) {
        super(message);
    }
}
