package com.softserve.edu.task05;

/**
 * ConvertDigit class is used to read digit as main method argument
 * ad convert it to corresponding Russian string representation
 *
 * @author Stas Kiryan
 * @version 1.1
 */
public final class App {

    private static final String INVALID_NUMBER_MES = "Invalid integer is specified as" +
            " first program argument specify it";
    private static final String NO_NUMBER_MES = "No number is specified as program argument";

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
            System.out.println(new ConvertDigit().convertAnyIntToStringRepres(digitToConvert));
        } catch (NumberFormatException numnForExc) {
            System.out.println(INVALID_NUMBER_MES);
        } catch (ArrayIndexOutOfBoundsException arroOutOfBound) {
            System.out.println(NO_NUMBER_MES);
        } catch (IllegalArgumentException illegArg) {
            System.out.print(illegArg.getMessage());
            /* exception is throwing when digit length is not supported*/
        }

    }
}