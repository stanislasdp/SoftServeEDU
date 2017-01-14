/*
 * This application is designed for find Fibonacci number within specified range
 */

package com.softserve.edu.task08;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides algorithm to calculate Fibonacci digits within an interval
 * @author Stas Kiryan
 * @version 1.0
 */
public class CalculateFibDigits {
    /**
     * Calculate Fibonacci digit within specified interval
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return the list of String,
     * @throws WrongArgumentsExeption - thrown if lower interval bound is greater than upper
     *                                or either lower or upper interval bound is not positive digit.
     * @throws NullPointerException - thrown if either lower or (and) upper bound is null.
     */
    public static List<String> calculateFibonacciInInterval(
            final String lowerBound, final String upperBound) throws WrongArgumentsExeption {
    	if (lowerBound == null || upperBound == null) {
    		throw new NullPointerException("Either lower or upper interval bound is null");
    	}
    	
        List<String> fibonnDigits = new ArrayList<>();
        BigInteger lowerBoundBigInt = null;
        BigInteger upperBoundBigInt = null;

        try {
        	 lowerBoundBigInt = new BigInteger(lowerBound);
            /*construct BigInteger from lower boundary*/
             upperBoundBigInt = new BigInteger(upperBound);
            /*construct BigInteger from upper boundary*/
        } catch (NumberFormatException numbFormExc) {
        	throw new WrongArgumentsExeption("Either lower or upper interval bound "
                   + "is not natural digit");
        }
        if (lowerBoundBigInt.compareTo(new BigInteger("0")) < 0  
        		|| upperBoundBigInt.compareTo(new BigInteger("0")) < 0) {
        	throw new WrongArgumentsExeption("Either lower or upper interval bound (or both)"
                   + " is not positive");
        }
        if (lowerBoundBigInt.compareTo(upperBoundBigInt) > 0) {
            throw new WrongArgumentsExeption("lower interval bound greater than "
                   + "upper interval bound");
        }

        if (lowerBoundBigInt.subtract(new BigInteger("0")).intValue() == 0
                && upperBoundBigInt.subtract(new BigInteger("0")).intValue() == 0) {
            fibonnDigits.add("0");
           /* both interval bounds is zero, add 0 to the list*/
        
        } else if (lowerBoundBigInt.subtract(new BigInteger("0")).intValue() == 0) {
            fibonnDigits.add("0");
            fibonnDigits.add("1");
        }
            /* only lower interval bounds is zero, add 0 and 1 to the list*/
  
        else if (lowerBoundBigInt.compareTo(new BigInteger("1")) == 0) {
            
            fibonnDigits.add("1");
            /*if lower bound is "1" add 1 to the list */
        }

        BigInteger previousFibDigit = new BigInteger("0");
        BigInteger currentFibDigit = new BigInteger("1");
        BigInteger nextFibDigit = new BigInteger("0");

        while (upperBoundBigInt.compareTo(nextFibDigit) >= 0) {
            nextFibDigit = previousFibDigit.add(currentFibDigit);

            if (nextFibDigit.compareTo(lowerBoundBigInt) >= 0
                    && nextFibDigit.compareTo(upperBoundBigInt) <= 0) {
                fibonnDigits.add(nextFibDigit.toString());
            }
            previousFibDigit = currentFibDigit;
            currentFibDigit = nextFibDigit;
        }
        return fibonnDigits;
    }
}
