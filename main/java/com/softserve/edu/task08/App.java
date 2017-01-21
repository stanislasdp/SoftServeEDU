/*
 * This application is designed for find Fibonacci number within specified range
 */
package com.softserve.edu.task08;

import java.util.List;


/**
 * App class provides lower and upper boundaries reading
 * and print Fibonacci digits within an Interval
 * to console separated by comma.
 * @author Stas kiryan
 * @version 1.0
 */
public class App {

    private static final String NO_BOUNDS_MESSAGE = "Either lower or upper interval boundary "
    + "is not specified as program argument. Please specify both of them";

    private static final String WRONG_BOUNDS_MESSAGE = "Lower boundary is greater than upper"
           + " boundary or either lower or and) upper boundary is not positive natural digit";
    private static final String NO_FIB_DIGITS = "No Fibonacci digits within given interval.";

    /**
     * The entry point of application. Get lower and upper interval boundaries
     * from program arguments, find Fibonacci digit within this interval
     * and print them to console separated by comma.
     * @param args  - first argument (args[0]) is lower interval bound,
     *              second argument (args[1]) is upper interval bound.  .
     */
    public static void main(final String[] args) {
        String lowerIntervalBound;
        String upperIntervalBound;
        try {
            lowerIntervalBound = args[0];
            upperIntervalBound = args[1];
        } catch (ArrayIndexOutOfBoundsException arrayIndOfBExc) {
            System.out.println(NO_BOUNDS_MESSAGE);
            return;
        }
        List<String> resultFibonnDigits  = null;
        try {
            resultFibonnDigits = CalculateFibDigits.calculateFibonacciInInterval(lowerIntervalBound,
                    upperIntervalBound);
        } catch (WrongArgumentsExeption illeGArgExc) {
        	illeGArgExc.printStackTrace();
            System.out.println(WRONG_BOUNDS_MESSAGE);
            return;
        }
        
        if (resultFibonnDigits.isEmpty()) {
            System.out.println(NO_FIB_DIGITS);
        }

        for (String digit: resultFibonnDigits) {
            System.out.printf("%s,", digit);
        }
    }
}
