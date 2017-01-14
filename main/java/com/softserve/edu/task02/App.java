
/*
 * This application is designed to check whether one envelope
 * could be inserted to another
 */

package com.softserve.edu.task02;
import java.io.IOException;

/**
 *
 * The class App is used to parse envelopes width and height from program arguments
 * and perform check
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {
    /**
     * Check envelope height and width against another envelope height and width
     *
     * @param firstWidth   the first envelope width
     * @param firstHeight  the first envelope height
     * @param secondWidth  the second envelope width
     * @param secondHeight the second envelope height
     * @return the boolean represents whether envelope could be inserted
     */
    public static boolean checkEnvelopeCanBeInsToOther(double firstWidth,
            double firstHeight,  double secondWidth, double secondHeight) {
        return (Double.compare(secondWidth, firstWidth)) > 0
                && (Double.compare(secondHeight, firstHeight)) > 0;
    }

    public static void main(final String[] args) {
        double firstEnVelopeWidth = 0;
        double firstEnVelopeHeight = 0;
        double secondEnvelopeWidth = 0;
        double secondEnvelopeHeight = 0;

        while (true) {
            boolean firstEnvelopeCanBeInserted = false;
            boolean secondEnvelopeCanBeInserted = false;
            try {
                System.out.println("Enter first envelope width");
                firstEnVelopeWidth = ConsoleHelper.readDoubleParam();

                System.out.println("Enter first envelope height");
                firstEnVelopeHeight = ConsoleHelper.readDoubleParam();

                System.out.println("Enter second envelope width");
                secondEnvelopeWidth = ConsoleHelper.readDoubleParam();

                System.out.println("Enter second envelope height");
                secondEnvelopeHeight = ConsoleHelper.readDoubleParam();

                if (firstEnVelopeWidth <= 0 || firstEnVelopeHeight <= 0
                        || secondEnvelopeWidth <= 0 || secondEnvelopeHeight <= 0) {

                    System.out
                            .println("Some of envelope parameter(s) is equal or lower than zero."
                                    + "Please re-run program");
                    break;
                }
                firstEnvelopeCanBeInserted = checkEnvelopeCanBeInsToOther(
                        firstEnVelopeWidth, firstEnVelopeHeight,
                        secondEnvelopeWidth, secondEnvelopeHeight);
                if (!firstEnvelopeCanBeInserted)
                {   /*check second envelope insertion to the first if first cannot be inserted*/
                    secondEnvelopeCanBeInserted = checkEnvelopeCanBeInsToOther(
                            secondEnvelopeWidth, secondEnvelopeHeight,
                            firstEnVelopeWidth, firstEnVelopeHeight); }

                if (firstEnvelopeCanBeInserted) {
                    System.out
                            .println("First envelope can be inserted to second");
                } else if (secondEnvelopeCanBeInserted) {
                    System.out
                            .println("Second envelope can be inserted to first");
                } else {
                    /*if neither envelopes could be inserted print corresponding line*/
                    System.out.println("No envelops can be inserted");
                }
                System.out
                        .println("Calculation is over. Do you want to continue?");
                String userAnswer = ConsoleHelper.readStringParam();
                if (!"y".equalsIgnoreCase(userAnswer)
                        && !"yes".equalsIgnoreCase(userAnswer)) {
                    break; /*end program if user types any string not equal to y or yes*/
                }
            } catch (IOException interruptedExc) {
                System.out.println("Some I/O error has been occurred");
                break;
            } catch (IllegalArgumentException illegExc) {
                System.out
                        .println("At least one of envelope parameters is incorrect. "
                                + "Enter all values one more time");
            }
        }

    }
}