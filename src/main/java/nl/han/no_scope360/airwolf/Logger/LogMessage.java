package nl.han.no_scope360.airwolf.Logger;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMessage {

    public static String get(int level, String message) {
        //Add timestamp
        String printLine = "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "] ";

        //Add level prefix
        switch (level) {
            case DefaultLogger.LOG_FAILURE:
                printLine += "FAILURE: ";
                break;
            case DefaultLogger.LOG_ERROR:
                printLine += "  ERROR: ";
                break;
            case DefaultLogger.LOG_WARNING:
                printLine += "WARNING: ";
                break;
            case DefaultLogger.LOG_INFO:
                printLine += "   INFO: ";
                break;
            case DefaultLogger.LOG_DEBUG:
                printLine += "  DEBUG: ";
                break;
            default:
                printLine += "     INFO";
                break;
        }

        //Return message
        return printLine += message;
    }
}
