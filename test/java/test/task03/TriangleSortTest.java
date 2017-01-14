package test.task03;

import com.softserve.edu.task02.ConsoleHelper;
import com.softserve.edu.task03.ComparatorTriangleByDescArea;
import com.softserve.edu.task03.ReadTrianglesAndSort;
import com.softserve.edu.task03.Triangle;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/9/17.
 */

public class TriangleSortTest
{
    public static final ReadTrianglesAndSort INSTANCE = new ReadTrianglesAndSort();

    @Test
    public void singleTriangle() throws NoSuchFieldException,
    IllegalAccessException {
        List<Triangle> triangles = new ArrayList<Triangle>(){
            { add(new Triangle("1", 4, 4,3)); }
        };
        List<Triangle> expectedTrianglesList = new ArrayList<>(triangles);
        putOwnTriangesToList(triangles);
        INSTANCE.sortTriangleList(new ComparatorTriangleByDescArea());
        Assert.assertEquals(triangles.toString(), expectedTrianglesList.toString());
    }

    @Test
    public void twoTriangles() throws NoSuchFieldException,
    IllegalAccessException {
        Triangle triangle1 = new Triangle("1", 4, 4,3);
        Triangle triangle2 = new Triangle("2", 40, 40, 50);
        List<Triangle> triangles = new ArrayList<Triangle>();
        triangles.add(triangle1);
        triangles.add(triangle2);
        putOwnTriangesToList(triangles);
        List<Triangle> expListOfTriangles = new ArrayList<>();
        expListOfTriangles.add(triangle2);
        expListOfTriangles.add(triangle1);
        INSTANCE.sortTriangleList(new ComparatorTriangleByDescArea());
        Assert.assertEquals(triangles.toString(), expListOfTriangles.toString());
    }
    
    
    @Test
    public void threeTriangles() throws NoSuchFieldException,
    IllegalAccessException {
        Triangle triangle1 = new Triangle("1", 1, 1,1);
        Triangle triangle2 = new Triangle("1", 10, 10, 10);
        Triangle triangle3 = new Triangle("1", 100, 100, 100);
        List<Triangle> triangles = new ArrayList<Triangle>();
        triangles.add(triangle2);
        triangles.add(triangle3);
        triangles.add(triangle1);
        putOwnTriangesToList(triangles);
        List<Triangle> expListOfTriangles = new ArrayList<>();
        expListOfTriangles.add(triangle3);
        expListOfTriangles.add(triangle2);
        expListOfTriangles.add(triangle1);
        INSTANCE.sortTriangleList(new ComparatorTriangleByDescArea());
        Assert.assertEquals(triangles.toString(), expListOfTriangles.toString());
    }
    
    
    @Test
    public void sameTrianglesWithDifNames() throws NoSuchFieldException,
    IllegalAccessException {
        Triangle triangle1 = new Triangle("B", 1, 1,1);
        Triangle triangle2 = new Triangle("A", 1, 1, 1);
   
        List<Triangle> triangles = new ArrayList<Triangle>();
        triangles.add(triangle1);
        triangles.add(triangle2);
        putOwnTriangesToList(triangles);
        List<Triangle> expListOfTriangles = new ArrayList<>();
        expListOfTriangles.add(triangle2);
        expListOfTriangles.add(triangle1);
        INSTANCE.sortTriangleList(new ComparatorTriangleByDescArea());
        Assert.assertEquals(triangles.toString(), expListOfTriangles.toString());
    }
    

    public void putOwnTriangesToList(final List<Triangle> triangles) throws NoSuchFieldException,
    IllegalAccessException {
        Field modifiersField = INSTANCE.getClass().getDeclaredField("triangelList");
        modifiersField.setAccessible(true);
        modifiersField.set(INSTANCE, triangles);
    }
}
