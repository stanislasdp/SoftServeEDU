
/*
 * This application is designed to check whether one envelope could be inserted to another
 */

package com.softserve.edu.task03;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console helper class provides the standard console input reader
 * @author Stas Kiryan
 * @version 1.0
 */
public final class ConsoleHelper {
    private static  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ConsoleHelper() { }
 
    /**
     * Read String from reader
     *
     * @return String read
     */
    public static String readStringParam() throws IOException {
        return reader.readLine();
    }
}
