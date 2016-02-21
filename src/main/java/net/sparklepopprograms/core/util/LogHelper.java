package net.sparklepopprograms.core.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    public static void debug(String format, String modid, Object... data) {
    	Logger logger = LogManager.getLogger(modid);
        logger.log(Level.DEBUG, format, data);
    }

    public static void error(String format, String modid, Object... data) {
    	Logger logger = LogManager.getLogger(modid);
        logger.log(Level.ERROR, format, data);
    }

    public static void fatal(String format, String modid, Object... data) {
    	Logger logger = LogManager.getLogger(modid);
        logger.log(Level.FATAL, format, data);
    }

    public static void info(String format, String modid, Object... data) {
    	Logger logger = LogManager.getLogger(modid);
        logger.log(Level.INFO, format, data);
    }

    public static void warn(String format, String modid, Object... data) {
    	Logger logger = LogManager.getLogger(modid);
        logger.log(Level.WARN, format, data);
    }
}