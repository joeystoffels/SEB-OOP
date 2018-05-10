package nl.han.ica.airspaceinvaders.assets.config;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GameProperties {


    private static Properties properties = new Properties();

    // Only gets initialized once.
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

    /**
     * Get a value from the game.properties as String
     * @param key String
     * @return String
     */
    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get a value from the game.properties as int
     * @param key String
     * @return int
     */
    public static int getValueAsInt(String key) {
        return parseInt(properties.getProperty(key));
    }

    /**
     * Get a value from the game.properties as Double
     * @param key String
     * @return double
     */
    public static double getValueAsDouble(String key) {
        return parseDouble(properties.getProperty(key));
    }
}