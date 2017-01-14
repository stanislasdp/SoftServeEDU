package test.task04;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.task04.FileNotSpecifiedException;
import com.softserve.edu.task04.FileOperations;
import com.softserve.edu.task04.FileOperations.FileOperationMode;
import com.softserve.edu.task04.NotSupportedFileOperationException;

public class FileOperationsExcTest {
    private static final FileOperations FILE_OP_INSTANCE = new FileOperations();
    
    @Test (expectedExceptions = NotSupportedFileOperationException.class)
    public void setWrongFileOperation() throws NotSupportedFileOperationException {
        FILE_OP_INSTANCE.setOperation(3);
    }
    
    @Test
    public void setCountOperation() throws NotSupportedFileOperationException {
        FILE_OP_INSTANCE.setOperation(1);
        FileOperations.FileOperationMode actualMode = FILE_OP_INSTANCE.getSelectedOperationMode();
        FileOperations.FileOperationMode expMode = FileOperationMode.COUNT;
        Assert.assertEquals(actualMode, expMode);
    }
    
    @Test
    public void setWriteOperation() throws NotSupportedFileOperationException {
        FILE_OP_INSTANCE.setOperation(2);
        FileOperations.FileOperationMode actualMode = FILE_OP_INSTANCE.getSelectedOperationMode();
        FileOperations.FileOperationMode expMode = FileOperationMode.WRITE;
        Assert.assertEquals(actualMode, expMode);
    }
    
    @Test(expectedExceptions = NotSupportedFileOperationException.class)
    public void wrongOpSetForCount() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(2);
        FILE_OP_INSTANCE.countStringInFile("anyString");
    }
    
    @Test(expectedExceptions = NotSupportedFileOperationException.class)
    public void wrongOpSetForWrite() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(1);
        FILE_OP_INSTANCE.replaceStringInFile("anyString", "anynewString");
    }
    
    @Test (expectedExceptions = FileNotSpecifiedException.class)
    public void noFileToCntString() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(1);
        FILE_OP_INSTANCE.countStringInFile("arbitrary string");
    }
    
    
    @Test (expectedExceptions = FileNotSpecifiedException.class)
    public void noFileToReplString() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(2);
        FILE_OP_INSTANCE.replaceStringInFile(" old arbitr string", "new arbitr string");
    }
    
    @Test (expectedExceptions = FileNotFoundException.class)
    public void wrongFileToReplString() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(2);
        FILE_OP_INSTANCE.setFileName("wrong path");
        FILE_OP_INSTANCE.replaceStringInFile(" old arbitr string", "new arbitr string");
    }
    
    
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullStrToCountInFile() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(1);
        FILE_OP_INSTANCE.countStringInFile(null);
    }
    
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringToReplInfile() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(2);
        FILE_OP_INSTANCE.replaceStringInFile(null, "arbitrary string");
    }
    
    @Test (expectedExceptions = NullPointerException.class)
    public void nullStringReplStrInfile() throws NotSupportedFileOperationException, IOException {
        FILE_OP_INSTANCE.setOperation(2);
        FILE_OP_INSTANCE.replaceStringInFile("arbitrary string", null);
    }
    
    
    

}
