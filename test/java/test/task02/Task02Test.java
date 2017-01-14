package test.task02;

import com.softserve.edu.task02.App;
import com.softserve.edu.task02.ConsoleHelper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by stas on 1/8/17.
 */
public class

Task02Test {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static Field IN_CONTENT;
    private static PrintStream OLD_OUT_CONTENT = System.out;
    private static InputStream OLD_IN_CONTENT = System.in;
    
    @BeforeClass
    private static void replaceStreams() throws NoSuchFieldException, SecurityException {
        IN_CONTENT = ConsoleHelper.class.getDeclaredField("reader");
        IN_CONTENT.setAccessible(true);  
        System.setOut(new PrintStream(OUT_CONTENT));
    }
    
    @AfterClass
    private static void restoreReaders() {
        System.setOut(OLD_OUT_CONTENT);
        IN_CONTENT.setAccessible(false);
        System.setIn(OLD_IN_CONTENT);  
    }
    
   
    @AfterMethod
    public void clearStreams() {
        OUT_CONTENT.reset();
    }

    @Test
    public void testFirstCanBeInsertedtoSecond()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "1";
        String secondEnvWidth = "2";
        String secondEnvHeight = "2";
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("First envelope can be inserted to second"));
    }

    @Test
    public void testSecondCanBeInsertedtoFirst()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "2";
        String firstEnvHeight = "2";
        String secondEnvWidth = "1";
        String secondEnvHeight = "1";
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Second envelope can be inserted to first"));
    }

    @Test
    public void testEnvelopewiththeSameSizes()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "1";
        String secondEnvWidth = "1";
        String secondEnvHeight = "1";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("No envelops can be inserted"));
    }

    @Test
    public void testEnvelopewiththeSameWidth()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "3";
        String secondEnvWidth = "1";
        String secondEnvHeight = "2";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("No envelops can be inserted"));
    }

    @Test
    public void testEnvelopewiththeSameHeight()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "3";
        String firstEnvHeight = "3";
        String secondEnvWidth = "1";
        String secondEnvHeight = "3";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("No envelops can be inserted"));
    }

    @Test
    public void testFirstEnvWidthIsZero()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "0";
        String firstEnvHeight = "4";
        String secondEnvWidth = "1";
        String secondEnvHeight = "3";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Some of envelope parameter(s) is equal or lower than zero." +
                                "Please re-run program"));
    }

    @Test
    public void testFirstdEnvHeightIsZero()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "0";
        String secondEnvWidth = "1";
        String secondEnvHeight = "3";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Some of envelope parameter(s) is equal or lower than zero." +
                        "Please re-run program"));
    }

    @Test
    public void testSecondEnvWidthIsZero()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "1";
        String secondEnvWidth = "0";
        String secondEnvHeight = "4";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Some of envelope parameter(s) is equal or lower than zero." +
                        "Please re-run program"));
    }

    @Test
    public void testSecondEnvHeightIsZero()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1";
        String firstEnvHeight = "1";
        String secondEnvWidth = "4";
        String secondEnvHeight = "0";
        stringBuilder.append(firstEnvHeight);
        stringBuilder.append("\n");
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append(secondEnvHeight);
        stringBuilder.append("\n");
        setStringAsInputStream(stringBuilder.toString());
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Some of envelope parameter(s) is equal or lower than zero." +
                        "Please re-run program"));
    }

    @Test
    public void testIllegalArg()
            throws IllegalAccessException, NoSuchFieldException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String firstEnvWidth = "1r";
        stringBuilder.append(firstEnvWidth);
        stringBuilder.append("\n");
        stringBuilder.append("12\n1\n23\n33\n");
        setStringAsInputStream(stringBuilder.toString());
     
        App.main(null);
        Assert.assertTrue(OUT_CONTENT.toString().contains("At least one of envelope parameters is incorrect. " +
                "Enter all values one more time"));
    }

    private void setStringAsInputStream(String string) throws NoSuchFieldException,
            IllegalAccessException, IOException {
            InputStream arrayInputStream = new ByteArrayInputStream(string.getBytes());
            IN_CONTENT.set(null , new BufferedReader(new InputStreamReader(arrayInputStream)));
          
    }





}
