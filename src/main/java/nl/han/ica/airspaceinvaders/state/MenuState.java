package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.interfaces.IState;

import java.sql.Timestamp;

public class MenuState extends View implements IState {

    private AirspaceInvadersGame game;
    private Timestamp startTimestamp;

    /**
     * State to view the menu
     * @param game AirspaceInvadersGame
     */
    public MenuState(AirspaceInvadersGame game) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
    }

    /**
     * When the state is initialized the start function wil be executed
     */
    @Override
    public void start() {
        this.startTimestamp =  new Timestamp(System.currentTimeMillis());
        if(this.game.getStartUp()){
            this.setBackground(AssetLoader.getBackgroundImage("background/a10-logo.jpg", this.game));
        } else {
            this.showButtons();
        }
    }

    private void showButtons() {
        this.setBackground(AssetLoader.getBackgroundImage("background/a10.jpg", this.game));

        int horizontalOffset = 450;
        int fontSize = 80;

        ButtonChangeView buttonStart = new ButtonChangeView(new GameState(this.game, 1),this.game,"Start game", fontSize, 200, 100);
        this.game.addGameObject(buttonStart, GameProperties.getValueAsInt("worldWidth") - horizontalOffset, 100, 2);

        ButtonChangeView buttonRules = new ButtonChangeView(new RulesState(this.game), this.game,"Rules", fontSize, 200, 100);
        this.game.addGameObject(buttonRules, GameProperties.getValueAsInt("worldWidth") - horizontalOffset, 200, 2);

        ButtonChangeView buttonHighscore = new ButtonChangeView(new HighScoreState(this.game), this.game,"Highscore", fontSize, 200, 100);
        this.game.addGameObject(buttonHighscore, GameProperties.getValueAsInt("worldWidth") - horizontalOffset, 300, 2);
    }

    /**
     * The reset will be executed when the state will be stopped or changed
     */
    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    /**
     * On every cycle when the state is loaded, the update function is called
     */
    @Override
    public void update() {
        if(( new Timestamp(System.currentTimeMillis()).getTime() -  this.startTimestamp.getTime()) > GameProperties.getValueAsInt("splashTime") && this.game.getStartUp()){
            this.game.setStartUp(false);
            this.showButtons();
        }
    }
}
