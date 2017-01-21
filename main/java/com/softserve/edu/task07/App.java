/*
 * This application is designed for find and print to console  natural digits
 * which square is lower than digit specified in program argument.
 */
package com.softserve.edu.task07;

import java.util.List;

/**
 * App class provides read digit from program argument,
 * calculate digits which squre is lower than this digit
 * and print them to console separated by comma.
 * @author Stas kiryan
 * @version 1.0
 */
public class App {
    /**
     * The entry point of application. Get digit from program argument
     * and validates whether. Print digits which square is lower than specified digit
     * separated by comma.
     * @param args  - first argument (args[0]) is digit to operate.
     */
    public static void main(final String[] args) {
        String  digit = null;
        try {
            digit = args[0];
        } catch (ArrayIndexOutOfBoundsException arrIndexOfBoundsExc) {
            System.out.println("No digit is specified as program argument. Please specify it");
            return;
        }
        List<String> resultDigits;
        try {
            resultDigits = new Sequence().calculate(digit);
        } catch (WrongDigitFormatException wrDifExc) {
            System.out.println("Wrong digit format");
            return;
        }
       
        if (resultDigits.isEmpty()) {
            System.out.println("No digits which square lower than specified digit");
        }

        for (String dig: resultDigits) {
            System.out.printf("%s,", dig);
        }
    }
}
