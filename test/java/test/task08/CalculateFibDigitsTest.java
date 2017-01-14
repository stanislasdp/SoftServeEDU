package test.task08;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.softserve.edu.task08.CalculateFibDigits;
import com.softserve.edu.task08.WrongArgumentsExeption;


public class CalculateFibDigitsTest {
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullFirstIntervBoundIsPassed() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval(null, "100");
    }
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullsecondIntervBoundIsPassed() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", null);
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void negFirstIntBoundIsPassed() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("-100", "200");
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void negSecondIntBoundIsPassed() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", "-200");
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void firstGreatThanSecondIsPassed() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", "50");
    }
    
    @Test
    public void bothZeroesArePas() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("0");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("0", "0");
        Assert.assertEquals(actList, expectedList);
    }
    
    
    @Test
    public void bothZeroandOneArePas() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("0");
        expectedList.add("1");
        expectedList.add("1");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("0", "1");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void bothOneandOneArePas() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("1");
        expectedList.add("1");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("1", "1");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void both10and10ArePassed() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("10", "10");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void both21and21ArePassed() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("21");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("21", "21");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void zeroAnd100ArePased() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("0");
        expectedList.add("1");
        expectedList.add("1");
        expectedList.add("2");
        expectedList.add("3");
        expectedList.add("5");
        expectedList.add("8");
        expectedList.add("13");
        expectedList.add("21");
        expectedList.add("34");
        expectedList.add("55");
        expectedList.add("89");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("0", "100");
        Assert.assertEquals(actList, expectedList);
    }
    
}
