package nl.han.ica.airspaceinvaders;

import nl.han.ica.airspaceinvaders.interfaces.IEnemy;
import nl.han.ica.airspaceinvaders.objects.enemies.Air;
import nl.han.ica.airspaceinvaders.objects.enemies.Ground;
import nl.han.ica.airspaceinvaders.objects.player.Player;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.logger.FileLogHandler;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.*;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.tiles.BoardsTile;
import nl.han.ica.airspaceinvaders.logger.ConsoleLogHandler;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AirspaceInvadersGame extends GameEngine {

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();

    private GameProperties gameProperties = new GameProperties();

    private Player player;

    private List<IEnemy> enemies = new ArrayList<>();


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.airspaceinvaders.AirspaceInvadersGame"});
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

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        this.initializeTileMap();

        this.player = new Player(this);
        addGameObject(player, worldWidth/2 - (player.getWidth()/2) , 2000);

        enemies.add(new Air(this, AssetLoader.getSprite("enemy/A10.png", 8)));

        for (IEnemy enemy : enemies) {
            if (enemy instanceof Air) {
                addGameObject((Air) enemy, worldWidth / 3, 200);
            }
            if (enemy instanceof Ground) {
                addGameObject((Ground) enemy, worldWidth / 2 - (player.getWidth() / 2), 2000);
            }
        }

        createView(worldWidth, worldHeight, 800, 800, 1.0f);

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
    private void createView(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
        View view = new View(worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }
}
