package test.task03;

import com.softserve.edu.task03.ParsedTriangleFromString;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/19/17.
 */
public class TriangleParseStringTest {

    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringIsPassedNPEisThrown() {
        new ParsedTriangleFromString(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void oneArgumentIsPassedIllegalArgExcisThrown() {
        new ParsedTriangleFromString("Name");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void twoArgumentaIsPassedIllegalArgumentIsThrown() {
        new ParsedTriangleFromString("Name, 1");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void threeArgumentaIsPassedIllegalArgumentIsThrown() {
        new ParsedTriangleFromString("Name,1 ,3");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void secondArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParsedTriangleFromString("Name,1 ,r ,2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void thirdArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParsedTriangleFromString("Name,2 ,y ,2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void fourthArgumentaIsNotDoubleIllegalArgumentIsThrown() {
        new ParsedTriangleFromString("Name,2,5 ,t");
    }


    public void spacesBetweenArgumentsTriangleIsParsed() {
        ParsedTriangleFromString parsedTriangle = new ParsedTriangleFromString("Name ,3 ,4 ,4");
        String expectedName = "Name";
        double expectedASide = 6.0;
        double expectedBSide = 7.0;
        double expectedCSide = 8.0;
        Assert.assertEquals(parsedTriangle.getParsedName(), expectedName);
        Assert.assertEquals(parsedTriangle.getParsedASide(), expectedASide);
        Assert.assertEquals(parsedTriangle.getParsedBSide(), expectedBSide);
        Assert.assertEquals(parsedTriangle.getParsedCSide(), expectedCSide);
    }

    public void noSpacesBetweenArgumentsTriangleIsParsed() {
        ParsedTriangleFromString parsedTriangle = new ParsedTriangleFromString("Name,3,4,4");
        String expectedName = "Name";
        double expectedASide = 6.0;
        double expectedBSide = 7.0;
        double expectedCSide = 8.0;
        Assert.assertEquals(parsedTriangle.getParsedName(), expectedName);
        Assert.assertEquals(parsedTriangle.getParsedASide(), expectedASide);
        Assert.assertEquals(parsedTriangle.getParsedBSide(), expectedBSide);
        Assert.assertEquals(parsedTriangle.getParsedCSide(), expectedCSide);

    }






}
