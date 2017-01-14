/*
 * This application is designed for calculating number of "lucky" tickets
 * according to algorithm name read from file
 */
package com.softserve.edu.task06;

/**
 * "Moscow" algorithm works in following way:
 * - for each digit between 1 (means 000001) and 999999 get each digit
 * separately and check sum of first three digits aginist last three digits.
 * If they are the same - this is a lucky ticket.
 * @author Stas Kiryan
 * @version 1.0
 */
public class LuckyTicketsMoscow implements CountAlgorithmCalculation {

    @Override
    public int calculateCount() {
        int counter = 0;
        for (int i =  1; i <= 999999; i++) {
            int number1 = i / 100000;
            int number2  = (i / 10000) % 10;
            int number3 = (i / 1000) % 10;
            int number4 = (i / 100) % 10;
            int number5 = (i / 10) % 10;
            int number6 = i % 10;
            if ((number1 + number2 + number3)  == (number4 + number5 + number6)) {
                counter++;
            }
        }
        return counter;
    }
}
