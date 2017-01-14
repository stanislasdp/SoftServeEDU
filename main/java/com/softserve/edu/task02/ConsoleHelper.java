/*
 * This application is designed to check whether one envelope could be inserted to another
 */
package com.softserve.edu.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console helper class provides the standard console input reader
 * @author Stas Kiryan
 * @version 1.0
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Read double from standard input.
     *
     * @return read double
     * @throws IOException the IO exception
     */
    public static double readDoubleParam() throws IOException {
        return Double.parseDouble(reader.readLine());
    }

    /**
     * Read String from standard input
     *
     * @return read String
     * @throws IOException the IO exception
     */
    public static String readStringParam() throws IOException {
        return reader.readLine();
    }
}
