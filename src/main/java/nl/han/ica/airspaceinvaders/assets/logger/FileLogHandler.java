package nl.han.ica.airspaceinvaders.assets.logger;

import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.LoggerRuntimeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileLogHandler extends nl.han.ica.OOPDProcessingEngineHAN.Logger.FileLogHandler {

    private OutputStreamWriter osw;

    /**
     * Create a new logger which outputs to a file.
     *
     * @param fileName String
     */
    public FileLogHandler(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, true);
            this.osw = new OutputStreamWriter(fos);
        } catch (IOException e) {
            throw new LoggerRuntimeException(e);
        }
    }

    /**
     * Logs the message with an log option.
     *
     * @param level   int
     * @param message String
     */
    @Override
    public synchronized void logln(int level, String message) {
        try {
            this.osw.write(LogMessage.get(level, message) + "\n");
            this.osw.flush();
        } catch (IOException e) {
            throw new LoggerRuntimeException(e);
        }
    }
}
