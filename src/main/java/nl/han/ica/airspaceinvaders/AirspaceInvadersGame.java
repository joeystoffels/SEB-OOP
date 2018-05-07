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

    private int worldWidth;
    private int worldHeight;
    private Boolean startUp = true;

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
        this.setWorldWidth(GameProperties.getValue("worldWidth", true));
        this.setWorldHeight(GameProperties.getValue("worldHeight", true));

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        this.stateMachine = new AirspaceInvadersStateMachine(this);
        size(worldWidth, worldHeight);
    }

    @Override
    public void update() {
        this.stateMachine.getState().update();
    }

    public void changeView(View view){
        this.stateMachine.changeView(view);
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public Boolean getStartUp() {
        return startUp;
    }

    public void setStartUp(Boolean startUp) {
        this.startUp = startUp;
    }

}
