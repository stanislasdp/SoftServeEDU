package test.task03;

import com.softserve.edu.task01.App;
import com.softserve.edu.task03.Triangle;
import com.softserve.edu.task03.TriangleInvalidArgException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/9/17.
 */
public class TriangleTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void triangleWithNullNameNPEExceptionIsThrown() throws TriangleInvalidArgException {
        new Triangle(null,2, 4 ,4);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void triangleWithEmptyNameTriagArgExceptionIsThrown() throws TriangleInvalidArgException {
        new Triangle("",2, 4 ,4);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void firstSideMoreThanSumOfOthTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",10, 4 ,4);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void secSideMoreThanSumOfOthTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",4, 10 ,4);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void thirdSideMoreThanSumOfOthTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",4, 4 ,10);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void firstSideisZeroTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",0, 2 ,3);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void secondSideisZeroTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",3, 0 ,3);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void thirdSideisZeroTriArgExcIsThrown() throws TriangleInvalidArgException {
        new Triangle("",5, 4 ,0);
    }

    @Test
    public void triangleIsInstaniated() throws TriangleInvalidArgException {
        Triangle triangle = new Triangle("Name",3, 4 ,5);
        String expName = "Name";
        double expASide = 3;
        double expBSide = 4;
        double expCside = 5;
        Assert.assertEquals(triangle.getName(), expName);
        Assert.assertEquals(triangle.getASide(), expASide);
        Assert.assertEquals(triangle.getBSide(), expBSide);
        Assert.assertEquals(triangle.getCSide(), expCside);
    }

    @Test
    public void checkSizeCalculatedSizeReturned() throws TriangleInvalidArgException {
       Triangle triangle = new Triangle("Name",5, 4 ,4);
        double expSize = 7.81d;
        Assert.assertEquals(triangle.getTriangleSize(), expSize,0.01);
    }









  /*  @Test
    public void checkSize1() {
        Triangle triangle = new Triangle("Name", 4, 4, 4);
        String expectedSize = "[Name]: 6.93 cm";
        Assert.assertEquals(expectedSize, triangle.toString());
    }

    @Test
    public void checkSize2() {
        Triangle triangle = new Triangle("Name", 1, 2, 2.99);
        String expectedSize = "[Name]: 0.17 cm";
        Assert.assertEquals(expectedSize, triangle.toString());
    }*/

}
