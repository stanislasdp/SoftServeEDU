package test.task08;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserve.edu.task08.App;

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
    public void noBoundsTest() {
        String [] arguments = {""};
        App.main(arguments);
        String act = OUT_CONTENT.toString();
        String exp = "Either lower or upper interval boundary is not specified as program argument" 
        + "Please specify both of them";
        Assert.assertTrue(act.contains(exp));
    }
    
    @Test
    public void noUpperBoundTest() {
        String [] arguments = {"1"};
        App.main(arguments);
        String act = OUT_CONTENT.toString();
        System.out.println(OUT_CONTENT.toString());
        String exp = "Either lower or upper interval boundary is not specified as program argument" 
        + "Please specify both of them";
        Assert.assertTrue(act.contains(exp));
    }
    
    @Test
    public void noDigitsWithinInterval () {
        String [] arguments = {"10", "10"};
        App.main(arguments);
        String act = OUT_CONTENT.toString();
        String exp = "No Fibonacci digits";
        Assert.assertTrue(act.contains(exp));
    }
    
    @Test
    public void someDigitsWithinInterval () {
        String [] arguments = {"0", "100"};
        App.main(arguments);
        String act = OUT_CONTENT.toString();
        String exp = "0,1,1,2,3,5,8,13,21,34,55,89,";
        Assert.assertTrue(act.contains(exp));
    }
    
}
