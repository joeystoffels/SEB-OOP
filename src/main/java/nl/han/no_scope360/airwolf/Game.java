package nl.han.no_scope360.airwolf;

import nl.han.no_scope360.airwolf.Logger.FileLogHandler;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.*;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
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
        gameProperties.loadProperties("game.properties");

        int worldWidth = parseInt(gameProperties.getValue("worldWidth"));
        int worldHeight = parseInt(gameProperties.getValue("worldHeight"));

        View view = new View(worldWidth,worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
    }
}
