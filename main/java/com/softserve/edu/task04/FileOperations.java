/*
 * This application is designed for two purposes:
 * 1) parse file specified in program argument and count how many specified
 * in program arguments string in file;
 * 2) parse file specified in program argument and replace specified in program
 * argument string to another one.
 */
package com.softserve.edu.task04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by stas on 1/3/17.
 */
public final class FileOperations  {
    private Path file;
    private FileOperationMode selectedOperationMode;

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public Path getFileName() {
        return file.getFileName();
    }

    /**
     * Sets file name.
     *
     * @param pathName the path name
     */
    public void setFileName(final String pathName) {
        file = Paths.get(pathName);
    }

    /**
     * Gets selected operation mode.
     *
     * @return the selected operation mode
     */
    public FileOperationMode getSelectedOperationMode() {
        return selectedOperationMode;
    }

    /**
     * Sets operation 1 - refers to COUNT, 2 - refers to WRITE.
     *
     * @param choice - file operation mode choice.
     * @throws NotSupportedFileOperationException - if file operation is not supported
     */
    public void setOperation(int choice) throws NotSupportedFileOperationException {
        selectedOperationMode = FileOperationMode.getOperation(choice);
    }

    /**
     * Count string in file int.
     *
     * @param stringToSearch - the string to count in file.
     * @return - String count
     * @throws IOException                        he IO exception
     * @throws NotSupportedFileOperationException if not supported file operation is set..
     */
    public int countStringInFile(final String stringToSearch)
            throws IOException, NotSupportedFileOperationException {

        if (selectedOperationMode != FileOperationMode.COUNT) {
            throw new  NotSupportedFileOperationException("Set proper file Operations mode");
        }
        if (stringToSearch == null) {
        	throw new NullPointerException("String to search is null");
        }
        if (file == null) {
            throw new FileNotSpecifiedException("no File was set to operate");
        }
        
        if (!file.toFile().exists()) {
           
            throw new FileNotFoundException("file path is not found");
        }
        
        int stringAmount = 0;
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            Pattern pattern = Pattern.compile(stringToSearch);
            /*build regexp pattern to match string*/
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    stringAmount++;
                }
            }
        }
        return stringAmount;
    }

    /**
     * Replace specified string in file to another one.
     *
     * @param stringToReplace the string to search
     * @param newString       the replace string
     * @throws IOException                        the IO exception
     * @throws NotSupportedFileOperationException if not supported file operation is set.
     */
    public void replaceStringInFile(final String stringToReplace, final String newString)
            throws IOException, NotSupportedFileOperationException {
        if (selectedOperationMode != FileOperationMode.WRITE) {
            throw new NotSupportedFileOperationException("Set proper file Operations mode");
        }
        
        if (stringToReplace == null) {
        	throw new NullPointerException("String to search is null");
        }
        if (newString == null) {
        	throw new NullPointerException("String to replace is null");
        }
        if (file == null) {
            throw new FileNotSpecifiedException("no File was set to operate");
        }
        
        if (!file.toFile().exists()) {
            throw new FileNotFoundException("file path is not found");
        }
        
        Charset charset = Charset.forName("UTF-8");
        StringBuilder sb = new StringBuilder();
        String newLineSep  = System.lineSeparator();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                sb.append(currentLine.replaceAll(stringToReplace, newString));
                sb.append(newLineSep);
            }
        }
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file.toFile()))) {
            printWriter.write(sb.toString().replaceAll(newLineSep+"$", ""));
           /* remove trailing line separator in String Builder*/
        }
    }

    /**
     * The enum File specifies supported file operation modes
     */
    public enum FileOperationMode {
        /**
         * Count file operation mode.
         */
        COUNT(1),
        /**
         * Write file operation mode.
         */
        WRITE(2);

        /**
         * MAP with Integer as a key and corresponding file operation as a value.
         */
        private static final Map<Integer, FileOperationMode> MAP;
        static {
            MAP = new HashMap<>();
            for (FileOperationMode mode : FileOperationMode.values()) {
                MAP.put(mode.getOperationNumber(), mode);
            }
        }
        private int operationNumber;

        /**
         * Build FileOperation enum instance
         * @param operationNumb  - file operation number
         * @throws NotSupportedFileOperationException if not supported file operation is set.
         */
        FileOperationMode(int operationNumb) {
            this.operationNumber = operationNumb;
        }

        public int getOperationNumber() {
            return operationNumber;
        }

        /**
         * Gets corresponding operation according to passed int.
         * @param operationNumber the operation number
         * @return  - file operation enum value.
         * @throws NotSupportedFileOperationException - if no corresponding file operation is found
         */
        public static FileOperationMode getOperation(int operationNumber)
                throws NotSupportedFileOperationException {

            FileOperationMode modeFromMap =  MAP.get(operationNumber);
            if (modeFromMap  == null) {
                throw new NotSupportedFileOperationException("Chosen file operation is not found");
            }
            return modeFromMap;
        }
    }
}
