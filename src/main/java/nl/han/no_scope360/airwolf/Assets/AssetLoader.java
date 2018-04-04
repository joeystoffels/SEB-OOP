package nl.han.no_scope360.airwolf.Assets;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AssetLoader {

    public static String getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("images/" + name).toString();
    }

    public static Sprite getSprite(String name) {
        return AssetLoader.getSprite(name, 100);
    }

    public static Sprite getSprite(String name, int sizePercentage) {
        Sprite newSprite = new Sprite(Thread.currentThread().getContextClassLoader().getResource("images/" + name).toString());
        int newWidth = newSprite.getWidth() / 100 * sizePercentage;
        int newHeight = newSprite.getHeight() / 100 * sizePercentage;
        newSprite.resize(newWidth, newHeight);
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
            System.out.println(e.toString());
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
}
