package test.task07;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.task07.Sequence;
import com.softserve.edu.task07.WrongDigitFormatException;

public class SequenceTest {

    @Test (expectedExceptions = NullPointerException.class)
    public void nullStrIsPassed() {
        Sequence.calculate(null);
    }
    
    @Test(expectedExceptions = WrongDigitFormatException.class)
    public void notDigitIsPassed() {
        Sequence.calculate("1f");
    }
    
    @Test (expectedExceptions = WrongDigitFormatException.class)
    public void negativeDifisPassed() {
        Sequence.calculate("-1");
    }
    
    @Test
    public void simpleDigitPased() {
        List<String> expList = new ArrayList<String>();
        expList.add("1");
        List<String> actList = Sequence.calculate("2");
        Assert.assertEquals(actList, expList);
    }
    
    @Test
    public void notSimpleDigitPased() {
        List<String> expList = new ArrayList<String>();
        expList.add("1");
        expList.add("2");
        expList.add("3");
        expList.add("4");
        expList.add("5");
        expList.add("6");
        expList.add("7");
        expList.add("8");
        expList.add("9");
        List<String> actList = Sequence.calculate("97");
        Assert.assertEquals(actList, expList);
    }


    
}
