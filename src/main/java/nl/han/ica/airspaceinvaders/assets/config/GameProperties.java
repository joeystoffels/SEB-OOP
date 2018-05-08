package nl.han.ica.airspaceinvaders.assets.config;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class GameProperties {

    /**
     * This logger is used to output information to a console or file.
     * //
     */
//    private Logger logger = LogFactory.getLogger();

    private static Properties properties = new Properties();

    static {
        int keyWidth = 20;
        Logger logger = LogFactory.getLogger();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream("properties/game.properties")) {
            properties.load(resourceStream);

            if (Boolean.valueOf(GameProperties.getValue("debug"))) {

                logger.logln(DefaultLogger.LOG_DEBUG, "**********************************************");
                // Display all the values in the form of key value
                for (String key : properties.stringPropertyNames()) {

                    StringBuilder message = new StringBuilder();
                    message.append("Key: ");
                    message.append(key);

                    int amountOfSpaces = keyWidth - key.length();
                    for (int index = 0; index < amountOfSpaces; index++) {
                        message.append(" ");
                    }
                    message.append(": ");
                    message.append(properties.getProperty(key));

                    logger.logln(DefaultLogger.LOG_DEBUG, message.toString());
                }
                logger.logln(DefaultLogger.LOG_DEBUG, "**********************************************");
            }
        } catch (IOException e) {
            logger.logln(DefaultLogger.LOG_FAILURE, "Properties file could't be loaded");
        }

    }


    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static Integer getValue(String key, Boolean isInteger) {
        if (!isInteger) {
            GameProperties.getValue(key);
        }
        return parseInt(properties.getProperty(key));
    }
}