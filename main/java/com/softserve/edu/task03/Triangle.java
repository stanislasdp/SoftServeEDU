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
     * @param name  the name
     * @param aSide the first side
     * @param bSide the second side
     * @param cSide the third side
     */
    public Triangle(final String name, double aSide, double bSide, double cSide) {
        this.aSide = aSide;
        this.bSide = bSide;
        this.cSide = cSide;
        this.name = name;
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
    public double calculateTriangleArea() {
        double halfPerimeter = (aSide + bSide + cSide) / 2;
        return Math.sqrt((halfPerimeter * (halfPerimeter - aSide)
               * (halfPerimeter - bSide) * (halfPerimeter - cSide)));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(name);
        stringBuilder.append("]: ");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        stringBuilder.append(decimalFormat.format(calculateTriangleArea()));
        stringBuilder.append(" cm");
        return stringBuilder.toString();
    }
}
