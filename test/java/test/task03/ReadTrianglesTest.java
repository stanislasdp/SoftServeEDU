package test.task03;

import com.softserve.edu.task03.ReadTrianglesAndSort;
import com.softserve.edu.task03.TriangleInvalidArgCountException;
import com.softserve.edu.task03.TriangleInvalidArgException;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/9/17.
 */
public class ReadTrianglesTest {
    private static final ReadTrianglesAndSort INSTANCE = new ReadTrianglesAndSort();


    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringIsPassed() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        INSTANCE.getTrianglefromString(null);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void invalidFirstSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name,4gg,5,4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void invalidSecondSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 4, 5fff, 4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void invalidThirdSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 4, 5, 4ggg";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void negativeFirstSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, -4, 5, 4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void negativeSecondSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 4, -5, 4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void negativeThirdSide() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 4, 5, -4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void firstSideGreaterThanOthers() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 10, 5, 4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void secondSideGreaterThanOthers() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 5, 10, 4";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgException.class)
    public void thirdSideGreaterThanOthers() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 5, 4, 10";
        INSTANCE.getTrianglefromString(triangleString);
    }

    @Test(expectedExceptions = TriangleInvalidArgCountException.class)
    public void onlyTwoSidesDefined() throws TriangleInvalidArgException,
            TriangleInvalidArgCountException {
        String triangleString = "Name, 5, 4, ";
        INSTANCE.getTrianglefromString(triangleString);
    }
}
