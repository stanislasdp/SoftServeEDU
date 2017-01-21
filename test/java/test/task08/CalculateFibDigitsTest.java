package test.task08;

import com.softserve.edu.task08.CalculateFibDigits;
import com.softserve.edu.task08.WrongArgumentsExeption;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class CalculateFibDigitsTest {
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullFirstIntervBoundIsPassedThrowsNPE() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval(null, "100");
    }
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullsecondIntervBoundIsPassedThrowsWronArgExc() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", null);
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void negFirstIntBoundIsPassedThrowsWronArgExc() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("-100", "200");
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void negSecondIntBoundIsPassedThrowsWronArgExc() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", "-200");
    }
    
    @Test (expectedExceptions = WrongArgumentsExeption.class)
    public void firstGreatThanSecondIsPassedThrowsWronArgExc() throws WrongArgumentsExeption {
      CalculateFibDigits.calculateFibonacciInInterval("100", "50");
    }
    
    @Test
    public void bothZeroesArePassedListWithOneIsReturned() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("0");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("0", "0");
        Assert.assertEquals(actList, expectedList);
    }
    
    
    @Test
    public void bothZeroandOneArePasedListWithZeroAndOneIsReturned() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("0");
        expectedList.add("1");
        expectedList.add("1");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("0", "1");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void bothOneandOneArePassedListWithOneIsReturned() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("1");
        expectedList.add("1");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("1", "1");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void both10and10ArePassedEmptyListIsReturned() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("10", "10");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void both21and21ArePassedListWith21IsReturned() throws WrongArgumentsExeption {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("21");
        List<String> actList = CalculateFibDigits.calculateFibonacciInInterval("21", "21");
        Assert.assertEquals(actList, expectedList);
    }
    
    @Test
    public void zeroAnd100ArePasedListfrom0To89IsReturned() throws WrongArgumentsExeption {
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
