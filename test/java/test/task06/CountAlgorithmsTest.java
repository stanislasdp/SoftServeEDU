package test.task06;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.task06.CountAlgorithmCalculation;
import com.softserve.edu.task06.LuckyTicketsMoscow;
import com.softserve.edu.task06.LuckyTicketsPiter;

public class CountAlgorithmsTest 
{

    @Test
    public void moscowAlgorithmTest55251IsReturned() {
        CountAlgorithmCalculation algorithm = new LuckyTicketsMoscow();
        int expCount = 55251;
        int actCount = algorithm.calculateCount();
        Assert.assertEquals(actCount, expCount);
    }
    
    @Test
    public void piterAlgorithmTest55251IsReturned() {
        CountAlgorithmCalculation algorithm = new LuckyTicketsPiter();
        int expCount = 55251;
        int actCount = algorithm.calculateCount();
        Assert.assertEquals(actCount, expCount);
    }
}
