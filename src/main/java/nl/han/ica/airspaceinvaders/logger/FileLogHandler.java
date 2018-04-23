package nl.han.ica.airspaceinvaders.logger;

import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.LoggerRuntimeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileLogHandler extends nl.han.ica.OOPDProcessingEngineHAN.Logger.FileLogHandler {

    private FileOutputStream fos;
    private File file;
    private OutputStreamWriter osw;

    /**
     * Create a new logger which outputs to a file.
     */
    public FileLogHandler(String fileName) {
        try {
            this.file = new File(fileName);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            this.fos = new FileOutputStream(this.file, true);
            this.osw = new OutputStreamWriter(this.fos);
        } catch (IOException e) {
            throw new LoggerRuntimeException(e);
        }
    }

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
