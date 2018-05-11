package nl.han.ica.airspaceinvaders.assets.level;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;

import java.io.File;

public class Level {

    private Logger logger = LogFactory.getLogger();
    private TileType[] tileTypes;

    /**
     * Initialize the level, all sprites in de background folder are preloaded
     */
    public void initialize() {
        StringBuilder url = new StringBuilder();
        url.append(Thread.currentThread().getContextClassLoader().getResource("images/").getPath());
        url.append("terrain/60/");

        logger.logln(DefaultLogger.LOG_DEBUG, url.toString());

        File folder = new File(url.toString());
        File[] listOfFiles = folder.listFiles();

        Sprite[] sprites = new Sprite[listOfFiles.length];
        this.tileTypes = new TileType[listOfFiles.length];

        for (int index = 0; index < listOfFiles.length; index++) {
            if (listOfFiles[index].isFile()) {
                logger.logln(DefaultLogger.LOG_DEBUG, "File " + index + ": " + listOfFiles[index].getName());

                StringBuilder url2 = new StringBuilder();
                url2.append("terrain/60/");
                url2.append(listOfFiles[index].getName());

                logger.logln(DefaultLogger.LOG_DEBUG, url2.toString());

                // Add the sprite
                Sprite test = AssetLoader.getSprite(url2.toString());
                sprites[index] = test;
                this.tileTypes[index] = new TileType<>(Tile.class, sprites[index]);
            }
        }
    }

    /**
     * Load a specific level from a csv file
     *
     * @param game        AirspaceInvadersGame
     * @param levelNumber int
     * @return Tilemap
     */
    public TileMap loadLevel(AirspaceInvadersGame game, int levelNumber) {
        String fileName = "level" + Integer.toString(levelNumber) + ".csv";
        return new LevelMap(game, GameProperties.getValueAsInt("tileSize"), this.tileTypes, AssetLoader.getLevel(fileName), levelNumber);
    }
}
