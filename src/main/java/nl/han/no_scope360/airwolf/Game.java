package nl.han.no_scope360.airwolf;

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
	
	private GameProperties gameProperties =  new GameProperties();
	

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.no_scope360.airwolf.Game"});
    }

    @Override
    public void update() {
        // TODO: Needs implementation
    }

    @Override
    public void setupGame() {

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        // Load properties
        gameProperties.loadProperties("properties/game.properties");

        int worldWidth = parseInt(gameProperties.getValue("worldWidth"));
        int worldHeight = parseInt(gameProperties.getValue("worldHeight"));
        
        this.initializeTileMap();

        View view = new View(worldWidth,worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
    }
    
    private void initializeTileMap() {
    	
    	String[] spriteNames = {"Grass", "Grass1", "Grass2", "GrassRock", "RockSand", "RockStone", "Sand", "Soil", "SoilRock", "SoilSand", "Stone", "Water"};
    	Sprite[] sprites = new Sprite[spriteNames.length];
    	TileType[] tileTypes = new TileType[spriteNames.length];
    	
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	
    	for(int index = 0; index < spriteNames.length; index++ ) {
    		
    		// Build url to image
    		StringBuilder url = new StringBuilder();
    		url.append("images/terrain/60/");
    		url.append(spriteNames[index]);
    		url.append(".jpg");
    		
    		// Add the sprite
    		sprites[index] = new Sprite(loader.getResource(url.toString()).toString());
    		tileTypes[index] =  new TileType<>(BoardsTile.class, sprites[index]);
    	}

         
    	InputStream inputStream = loader.getResourceAsStream("levels/level1.csv");
    	InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    	BufferedReader reader = new BufferedReader(streamReader);
    	
    
    	ArrayList<String[]> container = new ArrayList<String[]>(); 
    	try {
    		int lines = 0;
			for (String line; (line = reader.readLine()) != null;) {
			    // Process line
				container.add(line.split(","));
				lines++;
			}
		} catch (IOException e) {
			this.logger.logln(DefaultLogger.LOG_FAILURE, e.toString());
		}
    	
    	
    	int[][] tilesMap = new int[container.size()][container.get(0).length];
    	int lineIndex = 0;
    	for(String[] line: container) {
    		for(int rowIndex = 0; rowIndex < line.length; rowIndex++) {
    			tilesMap[lineIndex][rowIndex] = parseInt(line[rowIndex]);
    			
    		}
    		lineIndex++;
    	}
    	
    	
       
        int tileSize= parseInt(gameProperties.getValue("tileSize"));
        
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}
