package com.softserve.edu.task03;

/**
 * Created by stas on 1/19/17.
 */
public class ParseTriangleFromString {

    private String parsedName;
    private double parsedASide;
    private double parsedBSide;
    private double parsedCSide;

    public ParseTriangleFromString(String triangleLine) {
        checkTriangleString(triangleLine);
    }
    public String getParsedName() {
        return parsedName;
    }

    public double getParsedASide() {
        return parsedASide;
    }

    public double getParsedBSide() {
        return parsedBSide;
    }

    public double getParsedCSide() {
        return parsedCSide;
    }

    public void checkTriangleString(final String triangleLine) {
        if (triangleLine == null) {
            throw new NullPointerException("Input line is null");
        }
        String replacedLine = triangleLine.replaceAll("\\s|\\t", "");
        /*regular expression to delete all space and tab character from input String*/
        String[] splittedString = replacedLine.split(",");
        if (splittedString.length != 4) {
            throw new IllegalArgumentException("invalid paramteers count");
        }

        parsedName = splittedString[0];
        parsedASide = Double.parseDouble(splittedString[1]);
        parsedBSide = Double.parseDouble(splittedString[2]);
        parsedCSide = Double.parseDouble(splittedString[3]);
    }




}
