/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Main class provides functionality to read triangles delimeted by comma
 * and print output trianges size to console
 *
 * @author Stas kiryan
 * @version 1, 0
 */
public final class ReadTrianglesAndSort {

    private ArrayList<Triangle> triangelList = new ArrayList<>();

    public ArrayList<Triangle> getTriangelList() {
        return triangelList;
    }

    public void setTriangelList(final ArrayList<Triangle> trianges) {
        this.triangelList = trianges;
    }

    /**
     * Gets trianglefrom string.
     *
     * @param inputLine the input line string parameters should be triangle name,
     * first side length, second side length and third side length
     *  delimited by comma spaces and tabs between parameters are ignored.
     * @return Triangle object
     * @throws TriangleInvalidArgCountException if count of triangle arguments is not four
     * @throws TriangleInvalidArgException      if at least one triangle sides parameter
     * is not a digit or lower than zero.
     */
    public Triangle getTrianglefromString(final String inputLine)
            throws TriangleInvalidArgCountException, TriangleInvalidArgException {

        if (inputLine == null) {
            throw new NullPointerException("Input line is null");
        }
        String replacedLine = inputLine.replaceAll("\\s|\\t", "");
        /*regular expression to delete all space and tab character from input String*/

        String[] splittedString = replacedLine.split(",");
        double triangleASide = 0;
        double triangleBSide = 0;
        double triangleCSide = 0;
        if (splittedString.length != 4) {
            throw new TriangleInvalidArgCountException("Triangle argument count should be four!");
        }
        String triangleName = splittedString[0];
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

        if (!isTriangleNotDenegerate(triangleASide, triangleBSide, triangleCSide)) {
            throw new TriangleInvalidArgException("Invalid arguments. "
                    + "One triangle side more than sum of others");
        }
        return new Triangle(triangleName, triangleASide, triangleBSide, triangleCSide);
    }

    /**
     * Sort triangle list according to passed comparator
     *
     * @param comparator -  the instance of triangles comparator
     */
    public void sortTriangleList(final Comparator<Triangle> comparator) {
        Collections.sort(triangelList, comparator);
    }

    /**
     * check all triange sides, if sum of any two sides greater than another,
     * false is returned.
     *
     * @param sideA  - first triangle side
     * @param sideB - second triangle side
     * @param sideC - third triangle side
     * @return - boolean value represents whether triangle with these parameters could exist.
     */
    private boolean isTriangleNotDenegerate(double sideA, double sideB, double sideC) {
        if (sideC > (sideA + sideB)) {
            return false;
        } else if (sideB > (sideA + sideC)) {
            return false;
        } else if (sideA > (sideB + sideC)) {
            return false;
        }
        return true;
    }
}
