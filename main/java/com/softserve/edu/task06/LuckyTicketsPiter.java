/*
 * This application is designed for calculating number of "lucky" tickets
 * according to algorithm name read from file
 */
package com.softserve.edu.task06;

/**
 * "Piter" algorithm works in following way:
 * - for each digit between 1 (means 000001) and 999999 get each digit
 * separately and put them in array.
 * - calculate sum of even and sum of odd digits in array,
 * If they are the same - ticket is "lucky".
 * @author Stas Kiryan
 * @version 1.0
 */
public class LuckyTicketsPiter implements CountAlgorithmCalculation {

    @Override
    public int calculateCount() {
        int counter = 0;
        for (int i =  1; i <= 999999; i++) {
            int [] numbers = new int[6];
            numbers[0] = i / 100000;
            numbers[1] = (i / 10000) % 10;
            numbers[2] = (i / 1000) % 10;
            numbers[3] = (i / 100) % 10;
            numbers[4] = (i / 10) % 10;
            numbers[5] = i % 10;
            int summEven = 0;
            int summOdd = 0;

            for (int j = 0; j < numbers.length; j++) {
                if (j % 2 == 0) {
                    summEven += numbers[j];
                } else {
                    summOdd += numbers[j];
                } 
            }
            if (summEven == summOdd) {
                counter++;
            }
        }
        return counter;
    }
}
