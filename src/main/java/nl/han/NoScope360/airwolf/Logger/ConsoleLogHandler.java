package nl.han.NoScope360.airwolf.Logger;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogHandler implements LogHandler {

    /**
     * Writes the message to the console.
     */
    @Override
    public synchronized void logln(int level, String message) {
        System.out.println(LogMessage.get(level,message));
    }
}