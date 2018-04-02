package nl.han.no_scope360.airwolf;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();

    private Properties properties;

    private static GameProperties instance = null;

    private final static int KEY_WIDTH = 20;

    /**
     *
     * @throws IOException
     */
    public GameProperties() {
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

                    StringBuilder message = new StringBuilder();
                    message.append("Key: ");
                    message.append(key);

                    int amountOfSpaces = KEY_WIDTH - key.length();
                    for (int index = 0; index < amountOfSpaces; index++) {
                        message.append(" ");
                    }
                    message.append(": ");
                    message.append(properties.getProperty(key));

                    this.logger.logln(DefaultLogger.LOG_DEBUG, message.toString());
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