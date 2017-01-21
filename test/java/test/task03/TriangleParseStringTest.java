package test.task03;

import com.softserve.edu.task03.ParseTriangleFromString;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/19/17.
 */
public class TriangleParseStringTest {

    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringIsPassedNPEisThrown() {
        new ParseTriangleFromString(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void oneArgumentIsPassedIllegalArgExcisThrown() {
        new ParseTriangleFromString("Name");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void twoArgumentaIsPassedIllegalArgumentIsThrown() {
        new ParseTriangleFromString("Name, 1");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void threeArgumentaIsPassedIllegalArgumentIsThrown() {
        new ParseTriangleFromString("Name,1 ,3");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void secondArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParseTriangleFromString("Name,1 ,r ,2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void thirdArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParseTriangleFromString("Name,2 ,y ,2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void fourthArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParseTriangleFromString("Name,2,5 ,t");
    }


    public void spacesBetweenArgumentsTriangleIsParsed() {
        //Arrange
        String expectedName = "Name";
        double expectedASide = 6.0;
        double expectedBSide = 7.0;
        double expectedCSide = 8.0;
        //Act
        ParseTriangleFromString parsedTriangle = new ParseTriangleFromString("Name ,3 ,4 ,4");
        //Assert
        Assert.assertEquals(parsedTriangle.getParsedName(), expectedName);
        Assert.assertEquals(parsedTriangle.getParsedASide(), expectedASide);
        Assert.assertEquals(parsedTriangle.getParsedBSide(), expectedBSide);
        Assert.assertEquals(parsedTriangle.getParsedCSide(), expectedCSide);
    }

    public void noSpacesBetweenArgumentsTriangleIsParsed() {
        //Arrange
        String expectedName = "Name";
        double expectedASide = 6.0;
        double expectedBSide = 7.0;
        double expectedCSide = 8.0;
        //Act
        ParseTriangleFromString parsedTriangle = new ParseTriangleFromString("Name,3,4,4");
        //Assert
        Assert.assertEquals(parsedTriangle.getParsedName(), expectedName);
        Assert.assertEquals(parsedTriangle.getParsedASide(), expectedASide);
        Assert.assertEquals(parsedTriangle.getParsedBSide(), expectedBSide);
        Assert.assertEquals(parsedTriangle.getParsedCSide(), expectedCSide);

    }






}
