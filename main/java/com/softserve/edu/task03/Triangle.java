/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Triangle entity class has following triangle attributes: name, first side,
 * second side and third side.
 *
 * @author Stas kiryan
 * @version 1, 0
 */
public class Triangle {
    private double aSide;
    private double bSide;
    private double cSide;
    private String name;

    /**
     * Instantiates a new Triangle.
     *
     * @param triangle - triangle parameters in (Name, first side, secod side , third side format
     *                 Spaces and tabulations between parameters are allowed).
     * @throws TriangleInvalidArgCountException if count of triangle arguments is not four.
     * @throws TriangleInvalidArgException      if at least one triangle sides parameter
     * is not a digit or lower than zero (or triangle name is empty).
     */
    public Triangle(String triangle) throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {

            checkIfTriangleIsValid(triangle);
    }


    /**
     * Gets trianglefrom string.
     *
     * @param triangleLine the input line string parameters should be triangle name,
     * first side length, second side length and third side length
     *  delimited by comma spaces and tabs between parameters are ignored.
     * @throws TriangleInvalidArgCountException if count of triangle arguments is not four
     * @throws TriangleInvalidArgException      if at least one triangle sides parameter
     * is not a digit or lower than zero (or triangle name is empty).
     */
    private void checkIfTriangleIsValid(final String triangleLine)
            throws TriangleInvalidArgCountException, TriangleInvalidArgException {

        if (triangleLine == null) {
            throw new NullPointerException("Input line is null");
        }
        String replacedLine = triangleLine.replaceAll("\\s|\\t", "");
        /*regular expression to delete all space and tab character from input String*/

        String[] splittedString = replacedLine.split(",");
        if (splittedString.length != 4) {
            throw new TriangleInvalidArgCountException("Triangle argument count should be four!");
        }

        String name = splittedString[0];
        double triangleASide = 0;
        double triangleBSide = 0;
        double triangleCSide = 0;

        if (name.isEmpty()) {
            throw new TriangleInvalidArgException("Triangle name is empty");
        }

        try {
            triangleASide = Double.parseDouble(splittedString[1]);
            triangleBSide = Double.parseDouble(splittedString[2]);
            triangleCSide = Double.parseDouble(splittedString[3]);
        } catch (NumberFormatException numbFormatExc) {
            throw new TriangleInvalidArgException("One of triangle arguments is not a digit");
        }
        if (triangleASide <= 0 || triangleBSide <= 0 || triangleCSide <= 0) {
            throw new TriangleInvalidArgException("At least one triangle sides in arguments"
                    + " zero or less than zero");
        }

        if (!isTrnCouldExist(triangleASide, triangleBSide, triangleCSide)) {
            throw new TriangleInvalidArgException("Invalid arguments. "
                    + "One triangle side more than sum of others");
        }
        this.name = name;
        this.aSide = triangleASide;
        this.bSide = triangleBSide;
        this.cSide = triangleCSide;
    }


    public double getaSide() {
        return aSide;
    }
    public double getbSide() {
        return bSide;
    }
    public double getcSide() {
        return cSide;
    }
    public String getName() {
        return name;
    }


    /**
     * Calculate triangle size based on triangle sides length  by Geron formula.
     *
     * @return the double
     */
    public double getTriangleSize() {
        double halfPerimeter = (aSide + bSide + cSide) / 2 *1.0;
        return Math.sqrt((halfPerimeter * (halfPerimeter - aSide)
               * (halfPerimeter - bSide) * (halfPerimeter - cSide)));
    }


    /**
     * check all triangle sides, if sum of any two sides greater than another,
     * false is returned.
     *
     * @param sideA  - first triangle side
     * @param sideB - second triangle side
     * @param sideC - third triangle side
     * @return - boolean value represents whether triangle with these parameters could exist.
     */
    private boolean isTrnCouldExist(double sideA, double sideB, double sideC) {
        if (sideC > (sideA + sideB)) {
            return false;
        } else if (sideB > (sideA + sideC)) {
            return false;
        } else if (sideA > (sideB + sideC)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(name);
        stringBuilder.append("]: ");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        stringBuilder.append(decimalFormat.format(getTriangleSize()));
        stringBuilder.append(" cm2");
        return stringBuilder.toString();
    }
}
