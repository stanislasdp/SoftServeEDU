package test.task07;
import com.softserve.edu.task07.App;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;


/**
 * Created by stas on 1/11/17.
 */
public class AppTest {
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static PrintStream OLD_OUT_CONTENT = System.out;


    @BeforeClass
    private static void replaceStreams() throws NoSuchFieldException, SecurityException {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterClass
    private static void restoreReaders() {
        System.setOut(OLD_OUT_CONTENT);
    }

    @AfterMethod
    public void clearStreams() {
        OUT_CONTENT.reset();
    }

    @Test
    public void testOneDigit() {
        String [] argument = {"2"};
        App.main(argument);
        String act = OUT_CONTENT.toString();
        String exp = "1,";
        Assert.assertEquals(act, exp);
    }

    @Test
    public void testSeveralDigits() {
        String [] argument = {"30"};
        App.main(argument);
        String act = OUT_CONTENT.toString();
        String exp = "1,2,3,4,5,";
        Assert.assertEquals(act, exp);
    }
    
    @Test
    public void noDigitsLower() {
        String [] argument = {"0"};
        App.main(argument);
        String act = OUT_CONTENT.toString();
        String exp = "No digits which square lower than specified digit";
        Assert.assertTrue(act.contains(exp));
    }

}
