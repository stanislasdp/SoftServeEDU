
/*
 * This application is designed to calculate triangle size based on
 * its parameters passed from standard input and sort them
 * by size in descending order.
 */
package com.softserve.edu.task03;
import java.io.IOException;

/**
 * Main class provides functionality to read triangles one by one
 * until user decides not to continue and print output read trianges size to console
 *
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {

    /**
     * Main method is used to process reading triangles from the Console
     * ans print their sizes in centimeters to console
     */
    public static void main(final String[] args) {
        ReadTrianglesAndSort readTriangles = new ReadTrianglesAndSort();
        Triangle nextTriangle = null;

        while (true) {
            try {
                System.out.println("Enter triangle parameters");
                nextTriangle = readTriangles
                        .getTrianglefromString(ConsoleHelper.readStringParam());
                readTriangles.getTriangelList().add(nextTriangle);
                /*add read triangle to the list of triangles*/
                System.out.println("Do you want to add one more triangle?");
                String userAnswer = ConsoleHelper.readStringParam();

                if ("yes".equalsIgnoreCase(userAnswer)
                        || "y".equals(userAnswer)) {
                    /*if user decides to add one more rectangle
                    just start new loop iteration*/
                    continue;
                }
                    readTriangles.
                            sortTriangleList(new ComparatorTriangleByDescArea());
                    break;
                    /*sort triangle list using comparator*/
            } catch (TriangleInvalidArgException invArgExc) {
                System.out.
                        println("At least one entered triangle side is not a digit,"
                                + "equal or less than zero or more than others");
            } catch (TriangleInvalidArgCountException e) {
                System.out.
                        println("Too many or too few triangle arguments. Re-enter");
            } catch (IOException e1)  {
                System.out.println("Some I/O error has been occured");
            }
        }
        System.out.println("============= Triangles list: ===============");
        /*print all read triangles to console*/
        for (Triangle triangle : readTriangles.getTriangelList()) {
            System.out.println(triangle);
        }
    }
}
