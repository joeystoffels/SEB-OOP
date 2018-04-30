package nl.han.ica.airspaceinvaders.level;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.config.GameProperties;
import nl.han.ica.waterworld.tiles.BoardsTile;

import java.io.File;

public class Level {

    private Logger logger = LogFactory.getLogger();
    private TileType[] tileTypes;

    public Level() {


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
                logger.logln(DefaultLogger.LOG_DEBUG, "File " + listOfFiles[index].getName());

                StringBuilder url2 = new StringBuilder();
                url2.append("terrain/60/");
                url2.append(listOfFiles[index].getName());

                logger.logln(DefaultLogger.LOG_DEBUG, url2.toString());

                // Add the sprite
                Sprite test = AssetLoader.getSprite(url2.toString());
                sprites[index] = test;
                this.tileTypes[index] = new TileType<>(BoardsTile.class, sprites[index]);
            }
        }
    }

    public TileMap loadLevel(String fileName){
//        this.logger.logln(DefaultLogger.LOG_DEBUG, GameProperties.getValue("tileSize", true).toString());
      return new LevelMap(GameProperties.getValue("tileSize", true), this.tileTypes, AssetLoader.getLevel(fileName));
//        return new TileMap(GameProperties.getValue("tileSize", true), this.tileTypes, AssetLoader.getLevel(fileName));
    }

    //
//
//    Sprite[] sprites = new Sprite[spriteNames.length];
//    TileType[] tileTypes = new TileType[spriteNames.length];
//
//        for (int index = 0; index < spriteNames.length; index++) {
//
//        // Build url to image
//        StringBuilder url = new StringBuilder();
//        url.append("terrain/60/");
//        url.append(spriteNames[index]);
//        url.append(".jpg");
//
//        // Add the sprite
//        sprites[index] = AssetLoader.getSprite(url.toString());
//        tileTypes[index] = new TileType<>(BoardsTile.class, sprites[index]);
//    }
//
//    int tileSize = gameProperties.getValue("tileSize", true);
//
////        tileMap = new TileMap(tileSize, tileTypes, AssetLoader.getLevel("level1.csv"));
//    tileMap = new LevelMap(tileSize, tileTypes, AssetLoader.getLevel("level1.csv"));

}
