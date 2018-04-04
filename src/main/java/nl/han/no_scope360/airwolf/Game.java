package nl.han.no_scope360.airwolf;

import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.no_scope360.airwolf.Assets.AssetLoader;
import nl.han.no_scope360.airwolf.Logger.FileLogHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.*;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.tiles.BoardsTile;
import nl.han.no_scope360.airwolf.Logger.ConsoleLogHandler;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Game extends GameEngine {

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();

    private GameProperties gameProperties = new GameProperties();

    private Player player;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.no_scope360.airwolf.Game"});
    }

    @Override
    public void update() {
        // TODO: Needs implementation
    }

    @Override
    public void setupGame() {

        // Load properties
        gameProperties.loadProperties("properties/game.properties");
        int worldWidth = gameProperties.getValue("worldWidth", true);
        int worldHeight = gameProperties.getValue("worldHeight", true);

        player = new Player(this);
        addGameObject(player, 100, worldHeight);

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));


        this.initializeTileMap();

        createViewWithViewport(worldWidth, worldHeight, 800, 800, 1.1f);

    }

    private void initializeTileMap() {

        String[] spriteNames = {"Grass", "Grass1", "Grass2", "GrassRock", "RockSand", "RockStone", "Sand", "Soil", "SoilRock", "SoilSand", "Stone", "Water"};
        Sprite[] sprites = new Sprite[spriteNames.length];
        TileType[] tileTypes = new TileType[spriteNames.length];

        for (int index = 0; index < spriteNames.length; index++) {

            // Build url to image
            StringBuilder url = new StringBuilder();
            url.append("terrain/60/");
            url.append(spriteNames[index]);
            url.append(".jpg");

            // Add the sprite
            sprites[index] = AssetLoader.getSprite(url.toString());
            tileTypes[index] = new TileType<>(BoardsTile.class, sprites[index]);
        }

        int tileSize = gameProperties.getValue("tileSize", true);

        tileMap = new TileMap(tileSize, tileTypes, AssetLoader.getLevel("level1.csv"));
    }


    /**
     * Creeërt de view met viewport
     *
     * @param worldWidth   Totale breedte van de wereld
     * @param worldHeight  Totale hoogte van de wereld
     * @param screenWidth  Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     * @param zoomFactor   Factor waarmee wordt ingezoomd
     */
    private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {

        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), -100, -100);
        viewPort.setTolerance(50, 50, 0, 0);

        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
//        view.setBackground(loadImage("src/main/java/nl/han/ica/waterworld/media/background.jpg"));
    }
}
