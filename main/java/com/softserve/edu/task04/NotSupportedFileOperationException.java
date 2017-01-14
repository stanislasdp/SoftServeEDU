package com.softserve.edu.task04;

/**
 * Thrown if selected file operation is not supported.
 * @author Stas kiryan
 * @version 1,0
 */
public class NotSupportedFileOperationException extends Exception {
    /**
     * Constructs a <code>NotSupportedFileOperationException</code> with detail message.
     *
     * @param message detail message
     */
    public NotSupportedFileOperationException(final String message) {
        super(message);
    }
}
