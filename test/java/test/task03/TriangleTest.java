package test.task03;

import com.softserve.edu.task01.App;
import com.softserve.edu.task03.Triangle;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/9/17.
 */
public class TriangleTest {

    @Test
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
    }

}
