package nl.han.noScope360.airwolf.Logger;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogHandler;

public class ConsoleLogHandler implements LogHandler {

    /**
     * Writes the message to the console.
     */
    @Override
    public synchronized void logln(int level, String message) {
        System.out.println(LogMessage.get(level,message));
    }
}