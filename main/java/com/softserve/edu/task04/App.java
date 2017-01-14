/*
 * This application is designed for two purposes:
 * 1) parse file specified in program argument and count how many specified
 * in program agruments string in file;
 * 2) parse file specified in program argument and replace specified in program
 * argument string to another one.
 */
package com.softserve.edu.task04;
import java.io.IOException;

/**
 * App class is used to select file operations mode by number and perform
 * file operation that belongs to selected number.
 *
 * @author Stas Kiryan
 * @version 1.0
 */
public class App {
    /**
     * Main.
     *
     * @param args  - first argument refers to file path
     *               - second argument refers to string to operate
     *               (in case of COUNT mode - string to count, in case of WRITE mode - string to replace)
     *              - third argument is used only in WRITE mode,
     *              it refers to new string that replaces another.
     */
    public static void main(final String[] args) {
        FileOperations fileOperations = new FileOperations();
        String pathToFile = null;
        String stringToOperate = null;
        String stringToReplace = null;
        System.out
                .println("Enter file operation mode: 1 - count specified word in file ");
        System.out.println("2 - replace specified word in file ");

        while (true) {
            try {
                String userAnswer = ConsoleHelper.readStringParam();
                /*get user answer to determine file mode*/
                int fileOperationNumber = Integer.parseInt(userAnswer);
                fileOperations.setOperation(fileOperationNumber);
                break;

            } catch (NumberFormatException IOExc) {
                System.out.
                        println("File operation is not a number. Please try one more time");
            } catch (NotSupportedFileOperationException IOExc) {
                System.out.
                        println("File operation is not supported. Please try one more time");
            } catch (IOException IOExc) {
                System.out.
                        println("Some I/O error happened");
            }
        }

        try {
            pathToFile = args[0];
            stringToOperate = args[1];
        } catch (ArrayIndexOutOfBoundsException indexOfBoundsExc) {
            System.out.println("Path to file or/and string are not defined " +
                    ".Please specify file path as first program argument and string to operate as second");
            return;
        }
        try {
            switch (fileOperations.getSelectedOperationMode()) {
                case COUNT:
                    fileOperations.setOperation(1);
                    fileOperations.setFileName(pathToFile);
                    int countString = fileOperations.countStringInFile(stringToOperate);
                    System.out.printf("File %s contains %s %s",
                            pathToFile.replaceAll("//", "/"), countString, stringToOperate);
                    /*print file name, string to count and count to console*/
                    break;

                case WRITE:
                    try {
                        stringToReplace = args[2];
                    } catch (ArrayIndexOutOfBoundsException IOExc) {
                        System.out.println("New string that replaces old is not defined. "
                               + "Please specify it as program argument");
                        break;
                    }
                    fileOperations.setOperation(2);
                    fileOperations.setFileName(pathToFile);
                    fileOperations.replaceStringInFile(stringToOperate, stringToReplace);
                    System.out.println("Strings in file have been successfully replaced");
                    break;
                    default:
                    throw new NotSupportedFileOperationException("Not supported file operation");
                        /*if selected file mode neither COUNT nor WRITE throw new
                        * NotSupportedFileOperationException exception*/
            }
        } catch (IOException IOExc) {
            IOExc.printStackTrace();
            System.out.println("No file is set");
        } catch (NotSupportedFileOperationException IOExc) {
            System.out.println("Not proper File Operation is set");
        }
    }
}
