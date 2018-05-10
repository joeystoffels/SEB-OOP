package nl.han.ica.airspaceinvaders.assets;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AssetLoader {

    public static String getImageUrl(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("images/" + name).toString();
    }

    public static String getFont(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("font/" + name).toString();
    }

    public static Sprite getSprite(String name) {
        return AssetLoader.getSprite(name, 100);
    }

    public static Sprite getSprite(String name, int sizePercentage) {
        Sprite newSprite = new Sprite(Thread.currentThread().getContextClassLoader().getResource("images/" + name).toString());
        float newWidth = (float) newSprite.getWidth() / 100f * sizePercentage;
        float newHeight = (float) newSprite.getHeight() / 100f * sizePercentage;
        newSprite.resize((int)newWidth, (int)newHeight);
        return newSprite;
    }

    public static int[][] getLevel(String levelName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("levels/" + levelName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        ArrayList<String[]> container = new ArrayList<String[]>();
        try {
            int lines = 0;
            for (String line; (line = reader.readLine()) != null; ) {
                // Process line
                container.add(line.split(","));
                lines++;
            }
        } catch (IOException e) {
            LogFactory.getLogger().logln(DefaultLogger.LOG_DEBUG, e.toString());

        }

        int[][] level = new int[container.size()][container.get(0).length];
        int lineIndex = 0;
        for (String[] line : container) {
            for (int rowIndex = 0; rowIndex < line.length; rowIndex++) {
                level[lineIndex][rowIndex] = parseInt(line[rowIndex]);

            }
            lineIndex++;
        }
        return level;
    }

    public static PImage getBackgroundImage(String url, AirspaceInvadersGame game) {
        PImage backgroundImage = game.loadImage(AssetLoader.getImageUrl(url));
        backgroundImage.resize(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        return backgroundImage;
    }
}
