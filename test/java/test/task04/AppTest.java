package test.task04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.task04.App;
import com.softserve.edu.task04.ConsoleHelper;


public class AppTest {
    
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static Field IN_CONTENT;
    private static PrintStream OLD_OUT_CONTENT = System.out;
    private static InputStream OLD_IN_CONTENT = System.in;
    private File tempFile; 


    @BeforeClass
    private static void replaceStreams() throws NoSuchFieldException, SecurityException {

        IN_CONTENT = ConsoleHelper.class.getDeclaredField("reader");
        IN_CONTENT.setAccessible(true); 
        System.setOut(new PrintStream(OUT_CONTENT));
    }
    
    
    @AfterClass
    private static void restoreReaders() {
        System.setOut(OLD_OUT_CONTENT);
        System.setIn(OLD_IN_CONTENT);  
    }
    
    @BeforeMethod
    public void createTmpFile() throws IOException {
        try
        {
            tempFile = File.createTempFile("prefix-", ".tmp");
            System.out.println(tempFile.getAbsolutePath());
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }
   
    @AfterMethod
    public void deleteTempFile() throws IOException {
        Files.delete(Paths.get(tempFile.getAbsolutePath()));
        //*After each test method is executed delete temp file*//*
        
    }


    @Test
    public void countModeIspassed() throws NoSuchFieldException, IllegalAccessException, IOException {
        
        writeToTempFile("tag tag");
        
        String[] arguments = {tempFile.getAbsolutePath(), "tag"};
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append("\n");
        setStringAsInputStream(sb.toString());
        App.main(arguments);
        Assert.assertTrue(OUT_CONTENT.toString().contains("Enter file operation mode:"
                + " 1 - count specified word in file"));
        Assert.assertTrue(OUT_CONTENT.toString().contains("2 - replace specified word in file")); 
       Assert.assertTrue(OUT_CONTENT.toString().contains("File "+ tempFile.getAbsolutePath() 
                + " contains 2 tag"));
    }
    
    @Test
    public void replaceModeIspassed() throws NoSuchFieldException, IllegalAccessException, IOException {
        
        writeToTempFile("tag tag");
        String[] arguments = {tempFile.getAbsolutePath(), "tag", "gap"};
        StringBuilder sb = new StringBuilder();
        sb.append(2);
        sb.append("\n");
        setStringAsInputStream(sb.toString());
        App.main(arguments);
       Assert.assertTrue(OUT_CONTENT.toString().contains("Strings in file have been successfully replaced"));
    }
    
    
    @Test
    public void fileOperationIsNotNumber() throws NoSuchFieldException, IllegalAccessException, IOException {
        
        String[] arguments = {tempFile.getAbsolutePath(), "tag"};
        StringBuilder sb = new StringBuilder();
        sb.append("gg");
        sb.append("\n");
        sb.append("1");
        sb.append("\n");
        setStringAsInputStream(sb.toString());
        App.main(arguments);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("File operation is not a number. Please try one more time")); 
    }
    
    
    @Test
    public void fileOperationIsNotSupported() throws NoSuchFieldException, IllegalAccessException, IOException {
        
        String[] arguments = {tempFile.getAbsolutePath(), "tag"};
        StringBuilder sb = new StringBuilder();
        sb.append("3");
        sb.append("\n");
        sb.append("1");
        sb.append("\n");
        setStringAsInputStream(sb.toString());
        App.main(arguments);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("File operation is not supported. Please try one more time")); 
    }
    
    @Test
    public void pathToFileAndStringnotDef() throws NoSuchFieldException, IllegalAccessException, IOException {
        
        
        String[] arguments = {"1","2"};
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append("\n");
        setStringAsInputStream(sb.toString());
        App.main(arguments);
        Assert.assertTrue(OUT_CONTENT.toString().
                contains("Path to file or/and string are not defined" +
                        "Please specify file path as first program argument "
                        + "and string to operate as second")); 
    }
    
    
    
    
   
    private void setStringAsInputStream(String string) throws NoSuchFieldException,
    IllegalAccessException, IOException {
        InputStream arrayInputStream = new ByteArrayInputStream(string.getBytes());
        IN_CONTENT.set(null , new BufferedReader(new InputStreamReader(arrayInputStream)));
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
