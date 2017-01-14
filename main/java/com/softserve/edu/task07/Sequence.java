/*
 * This application is designed for find and print to console  natural digits
 * which square is lower than digit specified in program argument.
 */

package com.softserve.edu.task07;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/3/17.
 */
public class Sequence {
    /**
     * Calculate list of digits whick squre is lower than specified digit..
     * @param digit  - initial digit
     * @return  - List of String
     * @throws WrongDigitFormatException if digit is less than zero.
     * @throws NullPointerException iff digit argument is null
     * @throws NumberFormatException of digit argument is not natural digit
     */
    public static List<String> calculate(final String digit) throws WrongDigitFormatException {
        if (digit == null) {
            throw new NullPointerException("argument is null");
        }
        List<String> digits = new ArrayList<>();
        BigInteger bigIntDigit = null;
        try {
            bigIntDigit = new BigInteger(digit);
        } catch (NumberFormatException numFExc) {
            throw new WrongDigitFormatException("Illegal digit format");
        }
        if (bigIntDigit.compareTo(new BigInteger("0")) < 0) {
            throw new WrongDigitFormatException("Digit is less than zero");
        }

        int i = 1;
        while (true) {
           /*infinite loop where BigInteger instances are compared*/
            BigInteger currentDigit = new BigInteger(i + "");
            if (currentDigit.multiply(currentDigit).compareTo(bigIntDigit) < 0) {
                digits.add(currentDigit.toString());
                i++;
            } else {
                /*if digit in square greater than initial digit - break the loop */
                break;
            }
        }
        return digits;
    }
}
