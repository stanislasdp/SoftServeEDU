package test.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.task04.FileOperations;
import com.softserve.edu.task04.NotSupportedFileOperationException;


public class FileReplaceinFileTest {
    
    private static final FileOperations FILE_OP_INSTANCE = new FileOperations();
    private File tempFile;
    

    @BeforeClass
    public void setCountOper() throws NotSupportedFileOperationException {
        FILE_OP_INSTANCE.setOperation(2);
    }

   @BeforeMethod
    public void createTmpFile() throws IOException {
        try
        {
            tempFile = File.createTempFile("prefix-", ".tmp");
            System.out.println(tempFile.toPath());
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
       //* After each test method is executed delete temp file*//*
    }
    
    @Test
    public void replSingleString() throws IOException, NotSupportedFileOperationException 
    {
        writeToTempFile("String");
        FILE_OP_INSTANCE.replaceStringInFile("String", "gnirtS");
        long expCheckSum = 710668134L;
        long actChecksum = FileUtils.checksumCRC32(tempFile);
        Assert.assertEquals(actChecksum, expCheckSum); 
    }
    
    @Test
    public void replManyStringsInSinglLine() throws IOException, NotSupportedFileOperationException 
    {
        writeToTempFile("StringString StringStr");
        FILE_OP_INSTANCE.replaceStringInFile("String", "gnirtS");
        long expCheckSum = 3460085436L;
        long actChecksum = FileUtils.checksumCRC32(tempFile);
        Assert.assertEquals(actChecksum, expCheckSum); 
    }



     @Test
    public void replManyStringsInSeplLines() throws IOException, NotSupportedFileOperationException 
    {
        writeToTempFile("String\nStringString");
      FILE_OP_INSTANCE.replaceStringInFile("String", "gnirtS");
        long expCheckSum = 2930617874L;
        long actChecksum = FileUtils.checksumCRC32(tempFile);
        Assert.assertEquals(actChecksum, expCheckSum); 
    }
    
    
     @Test
    public void replManyStringsInSeplLinesWithEmptyLines() throws IOException, 
    NotSupportedFileOperationException 
    {
        writeToTempFile("String\n\n\nString");
      FILE_OP_INSTANCE.replaceStringInFile("String", "gnirtS");
        System.out.println(tempFile.getAbsolutePath());
        long expCheckSum =795002765L;
        long actChecksum = FileUtils.checksumCRC32(tempFile);
        Assert.assertEquals(actChecksum, expCheckSum); 
    }

    
    @Test
    public void noStringToReplaceInFile() throws IOException, 
    NotSupportedFileOperationException 
    {
        writeToTempFile("Stri\n\n\nring");
      FILE_OP_INSTANCE.replaceStringInFile("String", "gnirtS");
        System.out.println(tempFile.getAbsolutePath());
        long expCheckSum = 2236297483L;
        long actChecksum = FileUtils.checksumCRC32(tempFile);
        Assert.assertEquals(actChecksum, expCheckSum); 
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
