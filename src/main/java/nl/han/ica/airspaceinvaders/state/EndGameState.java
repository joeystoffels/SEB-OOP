package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.assets.highscores.HighScores;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class EndGameState extends View implements IState {

    private AirspaceInvadersGame game;
    private Player player;

    /**
     * This state will be loaded when the game ends, and the player's lifes are ended.
     * @param game AirspaceInvadersGame
     * @param player Player
     */
    public EndGameState(AirspaceInvadersGame game, Player player) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
        this.player = player;
    }

    /**
     * When the state is initialized the start function wil be executed
     */
    @Override
    public void start() {
        this.setBackground(AssetLoader.getBackgroundImage("background/a10-gameover.jpg", this.game));

        HighScores list = new HighScores(GameProperties.getValue("highscoreFile"));
        list.saveScore(player.getName(), player.getScore());

        int postionX = (GameProperties.getValueAsInt("worldWidth") / 2)  - 100;
        int postionY = GameProperties.getValueAsInt("worldHeight") - 100;
        ButtonChangeView buttonStart = new ButtonChangeView(new HighScoreState(this.game), this.game,"To menu", 50, 200, 100);
        this.game.addGameObject(buttonStart, postionX, postionY, 1);
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
    }
}
