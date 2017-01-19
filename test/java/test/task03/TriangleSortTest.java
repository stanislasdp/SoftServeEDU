package test.task03;

import com.softserve.edu.task03.ComparatorTriangleByDescArea;
import com.softserve.edu.task03.Triangle;
import com.softserve.edu.task03.TriangleInvalidArgException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stas on 1/9/17.
 */

public class TriangleSortTest {

    @Test
    public void singleTriangleReturnTheSameList() throws TriangleInvalidArgException {
        //Arrange
        Triangle triangle = new Triangle("Name", 3, 4, 5);
        List<Triangle> expectedTrianglesList = new ArrayList<Triangle>();
        expectedTrianglesList.add(triangle);
        //Act
        List<Triangle> actualTrianglesList = new ArrayList<Triangle>();
        actualTrianglesList.add(triangle);

        Collections.sort(actualTrianglesList, new ComparatorTriangleByDescArea());
        //Assert
        Assert.assertEquals(actualTrianglesList, expectedTrianglesList);
    }

    @Test
    public void twoTriangleReturnSortedList() throws TriangleInvalidArgException {
        // Arrange
        Triangle triangle1 = new Triangle("Name", 3, 4, 5);
        Triangle triangle2 = new Triangle("Name", 6, 7, 8);
        List<Triangle> expectedTrianglesList = new ArrayList<Triangle>();
        expectedTrianglesList.add(triangle2);
        expectedTrianglesList.add(triangle1);
        //Act
        List<Triangle> actualTrianglesList = new ArrayList<Triangle>();
        actualTrianglesList.add(triangle1);
        actualTrianglesList.add(triangle2);
        Collections.sort(actualTrianglesList, new ComparatorTriangleByDescArea());
        //Assert
        Assert.assertEquals(actualTrianglesList, expectedTrianglesList);
    }

    @Test
    public void threeTriangleReturnSortedList() throws TriangleInvalidArgException {
        // Arrange
        Triangle triangle1 = new Triangle("Name", 3, 4, 5);
        Triangle triangle2 = new Triangle("Name", 6, 7, 8);
        Triangle triangle3 = new Triangle("Name", 10, 11, 12);
        List<Triangle> expectedTrianglesList = new ArrayList<Triangle>();
        expectedTrianglesList.add(triangle3);
        expectedTrianglesList.add(triangle2);
        expectedTrianglesList.add(triangle1);
        //Act
        List<Triangle> actualTrianglesList = new ArrayList<Triangle>();
        actualTrianglesList.add(triangle1);
        actualTrianglesList.add(triangle2);
        actualTrianglesList.add(triangle3);
        Collections.sort(actualTrianglesList, new ComparatorTriangleByDescArea());
        //Assert
        Assert.assertEquals(actualTrianglesList, expectedTrianglesList);
    }



}


