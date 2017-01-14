/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation
 */
package com.softserve.edu.task05;

/**
 * App class is used to read digit from program argument parameter print
 * it to the console
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {

    /**
     * Main method is used to read digit, validate it and prints its representation
     * to the console
     *
     * @param args - first args parameter (args[0]) is a digit
     */
    public static void main(final String[] args) {

        try {
        	String digitToConvertInString = args[0];
            int digitToConvert = Integer.parseInt(digitToConvertInString);
            System.out.println(new ConverDigits().convertAnyIntToStringRepres(digitToConvert));
        } catch (NumberFormatException numnForExc) {
            System.out.println("Invalid number is specified as first program argument, specify it");
            System.out.println("should be natural digit between -2147483648 and 2147483647");
        } catch (ArrayIndexOutOfBoundsException arroOutOfBound) {
            System.out.println("No number is specified as program argument");
        }

    }
}
