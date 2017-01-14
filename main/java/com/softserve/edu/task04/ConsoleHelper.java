/*
 * This application is designed for two purposes:
 * 1) parse file specified in program argument and count how many specified
 * in program agruments string in file;
 * 2) parse file specified in program argument and replace specified in program
 * argument string to another one.
 */
package com.softserve.edu.task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console helper class provides the standard console input reader
 * @author Stas Kiryan
 * @version 1.0
 */
public final class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ConsoleHelper() { }

    /**
     * Read String from console
     *
     * @return read String from Console
     */
    public static String readStringParam() throws IOException {
        return reader.readLine();
    }
  
    }
