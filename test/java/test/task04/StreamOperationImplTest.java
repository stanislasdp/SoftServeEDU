package test.task04;

import com.softserve.edu.task04.StreamOperationsImpl;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by stas on 1/21/17.
 */
public class StreamOperationImplTest {


    @Test (expectedExceptions = NullPointerException.class)
    public void nullInputStreamIsPassedtoCountNPEISThrown() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.countStringInInputStream(null, "String");
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringIsPassedtoCountNPEISThrown() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "String";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        streamOperations.countStringInInputStream(stringInputStream, null);
    }

    @Test
    public void stringIsPassedStreamContainsSingleSttringtoCountOneIsRet() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "String";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        int exp = 1;
        int act = streamOperations.countStringInInputStream(stringInputStream, "String");
        Assert.assertEquals(act ,exp);
    }

    @Test
    public void stringIsPassedStreamContainsTwoStringsInSingleLineTwoIsReturned() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "StringString";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        int exp = 2;
        int act = streamOperations.countStringInInputStream(stringInputStream, "String");
        Assert.assertEquals(act ,exp);
    }

    @Test
    public void stringIsPassedStreamContainsTwoStringsWithEmptyLineTwoIsReturned() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "String/nString";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        int exp = 2;
        int act = streamOperations.countStringInInputStream(stringInputStream, "String");
        Assert.assertEquals(act ,exp);
    }

    @Test
    public void stringIsPassedStreamContainsTwoStringsWithEmptyLinesTwoIsReturned() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "String/n/n/nString";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        int exp = 2;
        int act = streamOperations.countStringInInputStream(stringInputStream, "String");
        Assert.assertEquals(act ,exp);
    }


    @Test
    public void stringIsPassedStreamNotContainStringsZeroIsReturned() throws IOException {
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        String testStr = "Strin/ntring";
        InputStream stringInputStream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));
        int exp = 0;
        int act = streamOperations.countStringInInputStream(stringInputStream, "String");
        Assert.assertEquals(act ,exp);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void nullInputStreamIsPassedNPEisThrown() throws IOException {
        String testReplacedString = "String";
        String testNewString = "ABC";
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(null, outputStream, testReplacedString ,testNewString);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void nullOutputStreamIsPassedNPEisThrown() throws IOException {
        String testReplacedString = "String";
        String testNewString = "ABC";
        InputStream inputStream = new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, null, testReplacedString ,testNewString);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringToReplacesPassedNPEisThrown() throws IOException {
        String testReplacedString = null;
        String testNewString = "ABC";
        OutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void nullNewStringToReplacesPassedNPEisThrown() throws IOException {
        String testReplacedString = "String";
        String testNewString = null;
        OutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
    }


    @Test
    public void singleStringIsReplacedOutputContainsNewString() throws IOException {
        String stringForInputStream = "String";
        String testReplacedString = "String";
        String testNewString = "ABC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(stringForInputStream.getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
        String expected = "ABC";
        String actual = outputStream.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void twoStringAreReplacedOutputContainsNewStrings() throws IOException {
        String stringForInputStream = "StringString";
        String testReplacedString = "String";
        String testNewString = "ABC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(stringForInputStream.getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
        String expected = "ABCABC";
        String actual = outputStream.toString();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void twoStringInSeparateAreReplacedOutputContainsNewStrings() throws IOException {
        String stringForInputStream = "String/nString";
        String testReplacedString = "String";
        String testNewString = "ABC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(stringForInputStream.getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
        String expected = "ABC/nABC";
        String actual = outputStream.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void noStringToReplInInputStrameOutPutStreamIsRet() throws IOException {
        String stringForInputStream = "Strin/ntring";
        String testReplacedString = "String";
        String testNewString = "ABC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(stringForInputStream.getBytes(StandardCharsets.UTF_8));
        StreamOperationsImpl streamOperations = new StreamOperationsImpl();
        streamOperations.replaceStringInStreams(inputStream, outputStream, testReplacedString ,testNewString);
        String expected = "Strin/ntring";
        String actual = outputStream.toString();
        Assert.assertEquals(actual, expected);
    }

}
