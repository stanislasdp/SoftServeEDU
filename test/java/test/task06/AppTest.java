package test.task06;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.task06.App;
import com.softserve.edu.task06.CountAlgorithmCalculation;
import com.softserve.edu.task06.LuckyTicketsMoscow;
import com.softserve.edu.task06.LuckyTicketsPiter;

public class AppTest 
{

    @Test
    public void readMoscowAlgFromFileMoscowAlgIsReturned() throws Exception {
        String fileName = "testData/task06/Moscow.txt";
        String exp = "Moskow";
        String act = accessParseFileMethod(fileName);
        Assert.assertEquals(act, exp);
    }
    
    @Test
    public void readPiterAlgFromFilePiterAlgIsReturned() throws Exception {
        String fileName = "testData/task06/Piter.txt";
        String exp = "Piter";
        String act = accessParseFileMethod(fileName);
        Assert.assertEquals(act, exp);
    }
    
    @Test (expectedExceptions = Exception.class)
    public void noKnownAlgorithmisPassedExceptionISThrown() throws Exception {
        String fileName = "testData/task06/noMoscownoPiter.txt";
        accessParseFileMethod(fileName);
    }
    
    @Test
    public void getMoscowAlgorithmInstanceFromFactoryMoscowInstanceISReturned()
    {
        CountAlgorithmCalculation actInstance = App.algorithmFactory("Moskow");
        CountAlgorithmCalculation expInstance = new LuckyTicketsMoscow();
        Assert.assertEquals(actInstance.getClass(), expInstance.getClass());
    }
    
    @Test
    public void getPiterAlgorithmInstanceFromFactoryPiterInstanceIsReturned()
    {
        CountAlgorithmCalculation actInstance = App.algorithmFactory("Piter");
        CountAlgorithmCalculation expInstance = new LuckyTicketsPiter();
        Assert.assertEquals(actInstance.getClass(), expInstance.getClass());
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void geNotSuppportedAlgorithmInstanceFromFactoryIllegArgExceIsThrown()
    {
        App.algorithmFactory("PitM");
    }
    
    private static String accessParseFileMethod(String string) throws Exception {
        Method method = App.class.getDeclaredMethod("parseAlgorithmFromFile", String.class);
        method.setAccessible(true);
        return (String)method.invoke(null,string);
    }
  
}
