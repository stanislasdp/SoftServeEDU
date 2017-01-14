package test.task05;
import com.softserve.edu.task05.ConverDigits;
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
        List<Object[]> tmpList = new ArrayList<>();
        while ((currentLine = listReader.read()) != null)
        {
            Object [] currLineData = {Integer.parseInt(currentLine.get(0)), currentLine.get(1)};
            tmpList.add(currLineData);
        }
        listReader.close();
        return tmpList.iterator();
    }

    @Test (dataProvider = "getDigits")
    public void convertOneDigit(final int digit, final String strDigit) {
        final String actualDigit = new ConverDigits().convertAnyIntToStringRepres(digit);
        Assert.assertEquals(actualDigit, strDigit);
    }





}
