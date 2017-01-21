/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Triangle {
    private double aSide;
    private double bSide;
    private double cSide;
    private String name;


    public Triangle (String name, double aSide, double bSide, double cSide)
            throws TriangleInvalidArgException {
            triangValidCheck(name, aSide, bSide, cSide);
    }

    public double getASide() {
        return aSide;
    }
    public double getBSide() {
        return bSide;
    }
    public double getCSide() {
        return cSide;
    }
    public String getName() {
        return name;
    }


    private void triangValidCheck(String triangleName, double aSide, double bSide, double cSide) throws
            TriangleInvalidArgException {

        if (triangleName == null) {
            throw new NullPointerException("TriangleNameIsNull");
        }

        if (triangleName.isEmpty()) {
            throw new TriangleInvalidArgException("Triangle name is empty");
        }

        if (aSide <= 0 || bSide <= 0 || cSide <= 0) {
            throw new TriangleInvalidArgException("At least one triangle sides in arguments"
                    + " zero or less than zero");
        }

        if (!isTrnCouldExist(aSide, bSide, cSide)) {
            throw new TriangleInvalidArgException("Invalid arguments. "
                    + "One triangle side more than sum of others");
        }
        this.name = triangleName;
        this.aSide = aSide;
        this.bSide = bSide;
        this.cSide = cSide;
    }

    /**
     * Calculate triangle size based on triangle sides length  by Geron formula.
     *
     * @return the double
     */
    public double getTriangleSize() {
        double halfPerimeter = (aSide + bSide + cSide) / 2 * 1.0;
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
