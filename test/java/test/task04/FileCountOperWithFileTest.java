package test.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.task04.FileOperations;
import com.softserve.edu.task04.NotSupportedFileOperationException;

public class FileCountOperWithFileTest 
{
    private static final FileOperations FILE_OP_INSTANCE = new FileOperations();
    private File tempFile; 
    
    @BeforeClass
    public void setCountOper() throws NotSupportedFileOperationException {
        FILE_OP_INSTANCE.setOperation(1);
    }

   @BeforeMethod
    public void createTmpFile() throws IOException {
        try
        {
            tempFile = File.createTempFile("prefix-", ".tmp");
            FILE_OP_INSTANCE.setFileName(tempFile.getAbsolutePath());
            /*create temp file for the each test method*/
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }
   
    @AfterMethod
    public void deleteTempFile() throws IOException {
        Files.delete(Paths.get(tempFile.getAbsolutePath()));
        /*After each test method is executed delete temp file*/
        
    }
    
    @Test
    public void countSingString() throws NotSupportedFileOperationException, IOException {
        String stringToCount = "String";
        String stringInFile = "String";
        writeToTempFile(stringInFile);
        int expCount = 1;
        int actCount = FILE_OP_INSTANCE.countStringInFile(stringToCount);
        Assert.assertEquals(actCount , expCount);   
    }
    
    @Test
    public void stringManyTimes1() throws NotSupportedFileOperationException, IOException {
        String stringToCount = "String";
        String stringInFile = "String/n/String";
        writeToTempFile(stringInFile);
        int expCount = 2;
        int actCount = FILE_OP_INSTANCE.countStringInFile(stringToCount);
        Assert.assertEquals(actCount , expCount);   
    }
    
    @Test
    public void stringManyTimes2() throws NotSupportedFileOperationException, IOException {
        String stringToCount = "String";
        String stringInFile = "StringString";
        writeToTempFile(stringInFile);
        int expCount = 2;
        int actCount = FILE_OP_INSTANCE.countStringInFile(stringToCount);
        Assert.assertEquals(actCount , expCount);   
    }
    
    @Test
    public void stringManyTimes3() throws NotSupportedFileOperationException, IOException {
        String stringToCount = "String";
        String stringInFile = "StringString/n/n/n((((/n/***n/nStrinString";
        writeToTempFile(stringInFile);
        int expCount = 3;
        int actCount = FILE_OP_INSTANCE.countStringInFile(stringToCount);
        Assert.assertEquals(actCount , expCount);   
    }
    
    @Test
    public void noStringFound() throws NotSupportedFileOperationException, IOException {
        String stringToCount = "String";
        String stringInFile = "Stri/ng";
        writeToTempFile(stringInFile);
        int expCount = 0;
        int actCount = FILE_OP_INSTANCE.countStringInFile(stringToCount);
        Assert.assertEquals(actCount , expCount);   
    }
    
   
    
     
    private void writeToTempFile(String string) {
        try (FileWriter fileWriter = new FileWriter(tempFile))
        {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
