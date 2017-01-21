package test.task05;
import com.softserve.edu.task05.ConvertDigit;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by stas on 1/11/17.
 */
public class ConvertDigitsTest {

    public static final String FILE_WITH_TEST_DATA = "testData/task05/testData.csv";
    
   
   @DataProvider
    public Iterator<Object[]> getDigits() throws IOException {
        CsvListReader listReader = new CsvListReader(new FileReader(FILE_WITH_TEST_DATA),
                CsvPreference.STANDARD_PREFERENCE);
        List<String> currentLine;
        List<Object[]> digitList = new ArrayList<>();
        while ((currentLine = listReader.read()) != null)
        {
            Object [] currLineData = {Integer.parseInt(currentLine.get(0)), currentLine.get(1)};
            digitList.add(currLineData);
        }
        listReader.close();

        return digitList.iterator();
    }

    @Test (dataProvider = "getDigits")
    public void intFromDataProvIsPassedStringRepresentIsRet(final int digit, final String strDigit) {
        final String actualDigit = new ConvertDigit().convertAnyIntToStringRepres(digit);
        Assert.assertEquals(actualDigit, strDigit);
    }

}
