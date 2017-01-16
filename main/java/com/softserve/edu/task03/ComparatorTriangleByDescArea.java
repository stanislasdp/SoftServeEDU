/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */

package com.softserve.edu.task03;
import java.util.Comparator;

/**
 * This class provides custom comparator that compares two triangle sizes
 *
 * @author Stas kiryan
 * @version 1.0
 */
public class ComparatorTriangleByDescArea implements Comparator<Triangle> {

    @Override
    public int compare(final Triangle triangle1, final Triangle triangle2) {
        return Double.compare(triangle2.getTriangleSize(), triangle1.getTriangleSize());
        /*triangles are compared using their sizes as criterion*/

    }
}
