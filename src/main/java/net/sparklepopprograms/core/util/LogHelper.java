package net.sparklepopprograms.core.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    
    public static void log(Level logLevel, Object object) {
        FMLLog.log("ModName", logLevel, String.valueOf(object));
    }
    
    public static void debug(Object object) {
        log(Level.DEBUG, object);
    }

     public static void error(Object object) {
        log(Level.ERROR, object);
    }

    public static void fatal(Object object) {
        log(Level.FATAL, object);
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

     public static void warn(Object object) {
        log(Level.WARN, object);
    }
}
