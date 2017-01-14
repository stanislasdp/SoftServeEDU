/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */

package com.softserve.edu.task03;
import java.io.Serializable;
import java.util.Comparator;

/**
 * This class provides custom comparator that compares two triangle sizes
 *
 * @author Stas kiryan
 * @version 1.0
 */
public class ComparatorTriangleByDescArea implements Comparator<Triangle>, Serializable {

    @Override
    public int compare(final Triangle o1, final Triangle o2) {
        int flag = 0;
        flag = Double.compare(o2.calculateTriangleArea(), o1.calculateTriangleArea());
        if (flag == 0) {
            flag = o1.getName().compareTo(o2.getName());
        }
        return flag;
        /*triangles are compared using their sizes as first criterion
        * and using their names as second criterion*/
    }
}
