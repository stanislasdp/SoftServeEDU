
/*
 * This application is used for printing chess desk with arbitrary height
 * and width to standard output console
 */

package com.softserve.edu.task01;

/**
 * This class provides functionality to parse height and width from input parameters .
 *
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {

    /**
     * Main method
     *
     * @param args  array of input parameters, first element must be height, second width
     */
    public static void main(final String[] args) {
        int deskHeight;
        int deskWidth;

        try {
            deskHeight = Integer.parseInt(args[0]);  // parse int height
            deskWidth = Integer.parseInt(args[1]);  // parse int width

        } catch (ArrayIndexOutOfBoundsException indexOfBoundsExc) {
            System.out.println("Either height or width is not specified");
            System.out
                    .println("Please specify height and width in program arguments");
            return;
        } catch (IllegalArgumentException illegalArgExc) {
            System.out.println("Either height or width is not a digit");
            return;
        }

        if ((deskHeight <= 0) || deskWidth <= 0) {
            System.out
                    .println("Either height or width is equals lower than zero");
            return;
        }

        for (int i = 0; i < deskHeight; i++) {
            for (int j = 0; j < deskWidth; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        System.out.print("*");

                    } else {
                        System.out.print(" ");
                    }
                } else {
                    if (j % 2 == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
            }
            System.out.println();   /* put empty line as a delimiter*/
        }

    }
}
