package test.task03;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.task03.App;
import com.softserve.edu.task03.ConsoleHelper;


public class AppTest {
    private static final ByteArrayOutputStream outСontent = new ByteArrayOutputStream();
    private static  Field inContent;
    private static PrintStream  oldOutContent = System.out;
    private static InputStream oldInContent = System.in;


    @BeforeClass
    private static void replaceStreams() throws NoSuchFieldException, SecurityException {

        inContent = ConsoleHelper.class.getDeclaredField("reader");
        inContent.setAccessible(true);
        System.setOut(new PrintStream(outСontent));
    }

    @AfterClass
    private static void restoreReaders() {
        System.setOut(oldOutContent);
        System.setIn(oldInContent);
    }

    @AfterMethod
    public void clearStreams() {
        outСontent.reset();
    }

    @Test
    public void addSingleTrianglewithYes() throws NoSuchFieldException, IllegalAccessException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String triangleString = "Name,4, 4 ,2";
        stringBuilder.append(triangleString);
        stringBuilder.append("\n");
        stringBuilder.append("n");
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(outСontent.toString().contains("============= Triangles list:"
                + " ==============="));
        Assert.assertTrue(outСontent.toString().contains("[Name]: 3.87 cm"));
    }
    
    
    @Test
    public void addTwoTriangleswithYes() throws NoSuchFieldException, IllegalAccessException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String triangleString1 = "Name,4, 4 ,2";
        String triangleString2 = "Name2,1, 1 ,1";
        stringBuilder.append(triangleString1);
        stringBuilder.append("\n");
        stringBuilder.append("yes");
        stringBuilder.append("\n");
        stringBuilder.append(triangleString2);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(outСontent.toString().contains("============= Triangles list:"
                + " ==============="));
        Assert.assertTrue(outСontent.toString().contains("[Name]: 3.87 cm"));
        Assert.assertTrue(outСontent.toString().contains("[Name2]: 0.43 cm"));
    }
    
    
    @Test
    public void addTwoTriangleswithY() throws NoSuchFieldException, IllegalAccessException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String triangleString1 = "Name,4, 4 ,2";
        String triangleString2 = "Name2,1, 1 ,1";
        stringBuilder.append(triangleString1);
        stringBuilder.append("\n");
        stringBuilder.append("y");
        stringBuilder.append("\n");
        stringBuilder.append(triangleString2);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(outСontent.toString().contains("============= Triangles list:"
                + " ==============="));
        Assert.assertTrue(outСontent.toString().contains("[Name]: 3.87 cm"));
        Assert.assertTrue(outСontent.toString().contains("[Name2]: 0.43 cm"));
    }
    
    
    
    private void setStringAsInputStream(String string) throws NoSuchFieldException,
    IllegalAccessException, IOException {
        InputStream arrayInputStream = new ByteArrayInputStream(string.getBytes());
        IN_CONTENT.set(null , new BufferedReader(new InputStreamReader(arrayInputStream)));
    }
}
