package nl.han.ica.airspaceinvaders;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.assets.level.Level;
import nl.han.ica.airspaceinvaders.assets.logger.ConsoleLogHandler;
import nl.han.ica.airspaceinvaders.assets.logger.FileLogHandler;
import nl.han.ica.airspaceinvaders.state.AirspaceInvadersStateMachine;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class AirspaceInvadersGame extends GameEngine {

    private Boolean startUp = true;
    private AirspaceInvadersStateMachine stateMachine;
    private Logger logger = LogFactory.getLogger();
    private Level level = new Level();

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.airspaceinvaders.AirspaceInvadersGame"});
    }

    /**
     * Initial game setup function
     */
    @Override
    public void setupGame() {
        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        // Initialise the state machine
        this.stateMachine = new AirspaceInvadersStateMachine(this);

        // Set the size of the game
        size(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.level.initialize();
    }

    /**
     * This update method wil dispatch the update method in the state that is currently loaded
     */
    @Override
    public void update() {
        this.stateMachine.getState().update();
    }

    /**
     * Change state of the game
     * @param view View
     */
    public void changeView(View view){
        this.stateMachine.changeView(view);
    }

    /**
     * Getter for  startUp
     * @return Boolean
     */
    public Boolean getStartUp() {
        return startUp;
    }

    /**
     * Setter for startup
     * @param startUp Boolean
     */
    public void setStartUp(Boolean startUp) {
        this.startUp = startUp;
    }

    /**
     * Getter for Level
     * @return Level
     */
    public Level getLevel() {
        return level;
    }
}
