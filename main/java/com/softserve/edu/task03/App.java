
/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Main class provides functionality to read triangles one by one
 * until user decides not to continue and print output read trianges size to console
 *
 * @author Stas Kiryan
 * @version 1.0
 */
public final class App {

    private static final String ENTER_TRIANGLE_MESSAGE = "Enter triangle parameters";
    private static final String ADD_MORE_TRIANGLE_MESSAGE = "Do you want to add one more triangle?";
    private static final String TRIANG_HEAD_PRINT = "============= Triangles list: ===============";



    public static void main(final String[] args) throws IOException {

        new App().addTrianglesAndPrint();
    }

    private void addTrianglesAndPrint() throws IOException {
        List<Triangle> listOfTriangles = new ArrayList<>();
        while (true) {

            System.out.println(ENTER_TRIANGLE_MESSAGE);
            Triangle triangle;

            try {
                String readString = ConsoleHelper.readStringParam();
                ParseTriangleFromString parsedTriangle = new ParseTriangleFromString(readString);
                triangle = new Triangle(parsedTriangle.getParsedName(),
                        parsedTriangle.getParsedASide(),
                        parsedTriangle.getParsedBSide(),
                        parsedTriangle.getParsedCSide());
            }
            catch (TriangleInvalidArgException invArgExc) {
                System.out.println(invArgExc.getMessage());
                continue;
            }


            listOfTriangles.add(triangle);
                /*add read triangle to the list of triangles*/
            System.out.println(ADD_MORE_TRIANGLE_MESSAGE);

            String userAnswer = ConsoleHelper.readStringParam();

            if ("yes".equalsIgnoreCase(userAnswer)
                    || "y".equals(userAnswer)) {
                    /*if user decides to add one more rectangle
                    just start new loop iteration*/
                continue;
            }
            /*sort triangle list using comparator*/
            sortTriangleList(listOfTriangles, new ComparatorTriangleByDescArea());
            break;
        }

        System.out.println(TRIANG_HEAD_PRINT);
        /*print all read triangles to console*/
        for (Triangle triag : listOfTriangles) {
            System.out.println(triag);
        }
    }

    private static void sortTriangleList(final List<Triangle> triangelList,
                                         final Comparator<Triangle> comparator) {
        Collections.sort(triangelList, comparator);
    }
}
