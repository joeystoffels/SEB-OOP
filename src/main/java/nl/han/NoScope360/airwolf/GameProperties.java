package nl.han.NoScope360.airwolf;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {

    private final int keyWidth = 20;
    private final static String PROPERTIES_FILE = "game.properties";

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();

    private Properties properties;

    private static GameProperties instance = null;

    /**
     *
     * @throws IOException
     */
    protected GameProperties() {
        this.properties = new Properties();
    }

    public static GameProperties getInstance() {
        if (instance == null) {
            instance = new GameProperties();
        }
        return instance;
    }

    public void loadProperties(String fileName){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            properties.load(resourceStream);

            if (Boolean.valueOf(this.getValue("debug"))) {

                this.logger.logln(DefaultLogger.LOG_DEBUG, "**********************************************");
                // Display all the values in the form of key value
                for (String key : properties.stringPropertyNames()) {
                    String value = properties.getProperty(key);
                    int amountOfSpaces = keyWidth - key.length();
                    String whitespace = "";
                    for (int index = 0; index < amountOfSpaces; index++) {
                        whitespace = whitespace + " ";
                    }
                    this.logger.logln(DefaultLogger.LOG_DEBUG, "Key: " + key + whitespace + ": " + value);
                }
                this.logger.logln(DefaultLogger.LOG_DEBUG, "**********************************************");
            }
        } catch (IOException e){
            this.logger.logln(DefaultLogger.LOG_FAILURE, "Properties file could't be loaded");
        }
    }

    /**
     * Get a specific value from the properties file
     * @param key
     * @return String
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}