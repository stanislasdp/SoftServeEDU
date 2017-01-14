package test.task01;
import com.softserve.edu.task01.App;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Created by stas on 1/8/17.
 */
public class

Task01Test {
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();

    @BeforeMethod
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterMethod
    public void clearStreams() {
        OUT_CONTENT.reset();
    }


    @Test
    public void widthNotSpecified() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Either height or width is not specified");
        stringBuilder.append("\n");
        stringBuilder.append("Please specify height and width in program arguments");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"0"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }



    @Test()
    public void test1AndNotDigitHeight() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Either height or width is not a digit");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"2e", "1"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }

    @Test()
    public void test1AndNotDigitWidth() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Either height or width is not a digit");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"1", "2e"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }

    @Test()
    public void test1AndNegWidth() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Either height or width is equals lower than zero");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"1", "-2"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }

    @Test()
    public void testNegHeightAnd1() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Either height or width is equals lower than zero");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"-1", "3"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }

    @Test
    public void test1And0() {
        String exp = "Either height or width is equals lower than zero\n";
        String [] inputParameters = {"1" , "0"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }

    @Test
    public void test0And1() {
        String exp = "Either height or width is equals lower than zero\n";
        String [] inputParameters = {"1" , "0"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }


    @Test()
    public void test1And1() {
        String exp = "*\n";
        String [] inputParameters = {"1" , "1"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }


    @Test()
    public void test3And3() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("* *");
        stringBuilder.append("\n");
        stringBuilder.append(" * ");
        stringBuilder.append("\n");
        stringBuilder.append("* *");
        stringBuilder.append("\n");
        String exp = stringBuilder.toString();
        String [] inputParameters = {"3", "3"};
        App.main(inputParameters);
        String act  = OUT_CONTENT.toString();
        Assert.assertEquals(act, exp);
    }



}