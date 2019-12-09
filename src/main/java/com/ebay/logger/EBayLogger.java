package com.ebay.logger;

import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * This method is used to show the logs on console and save the logs in 
 * EBayLogFile.txt for each run.
 * 
 */
public class EBayLogger {

    private static final Logger LOGGER = Logger.getLogger("logger");
    private static PatternLayout layout = new PatternLayout("%d{dd MMM yyyy HH:mm:ss} %5p %c{1} - %m%n");
    private static FileAppender appender;
    private static ConsoleAppender consoleAppender;

    static {
        try {
            consoleAppender = new ConsoleAppender(layout, "System.out");
            appender = new FileAppender(layout, "EBayLogFile.txt", true);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method used to display the errors in the logger file
     * 
     * @param className
     *            name of class in which error occurred.
     * @param methodName
     *            name of method in which error occurred.
     * @param exception
     *            stack trace of exception
     */
    public static void logError(String className, String methodName, String exception) {
        LOGGER.addAppender(appender);
        LOGGER.setLevel((Level) Level.INFO);
        LOGGER.info("ClassName :" + className);
        LOGGER.info("MethodName :" + methodName);
        LOGGER.info("Exception :" + exception);
        LOGGER.info("-----------------------------------------------------------------------------------");
    }

    /**
     * This method is used to display the information in the logger file
     * 
     * @param message
     *            message to be displayed
     */
    public static void info(String message) {
        consoleAppender.setName("Console");
        LOGGER.addAppender(consoleAppender);
        LOGGER.addAppender(appender);
        LOGGER.setLevel((Level) Level.INFO);
        LOGGER.info(message);
    }

}