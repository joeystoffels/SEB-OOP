package nl.han.ica.airspaceinvaders;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.config.GameProperties;
import nl.han.ica.airspaceinvaders.logger.ConsoleLogHandler;
import nl.han.ica.airspaceinvaders.logger.FileLogHandler;
import nl.han.ica.airspaceinvaders.state.*;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class AirspaceInvadersGame extends GameEngine {

    protected int worldWidth;
    protected int worldHeight;
    private AirspaceInvadersStateMachine stateMachine;
    private Logger logger = LogFactory.getLogger();

    private static AirspaceInvadersGame ourInstance = new AirspaceInvadersGame();

    public static AirspaceInvadersGame getInstance() {
        return ourInstance;
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.airspaceinvaders.AirspaceInvadersGame"});
    }

    @Override
    public void setupGame() {

        this.worldWidth = GameProperties.getValue("worldWidth", true);
        this.worldHeight = GameProperties.getValue("worldHeight", true);

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        this.stateMachine = new AirspaceInvadersStateMachine(this, this.worldWidth, this.worldHeight);
        size(worldWidth, worldHeight);

//        this.changeView(new GameView(this, this.worldWidth, this.worldHeight));

    }

    @Override
    public void update() {
        this.stateMachine.getState().update();
    }

    public void changeView(View view){
        this.stateMachine.changeView(view);
    }
}
