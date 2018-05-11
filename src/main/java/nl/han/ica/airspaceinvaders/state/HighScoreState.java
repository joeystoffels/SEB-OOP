package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.assets.highscores.HighScores;
import nl.han.ica.airspaceinvaders.assets.highscores.Score;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.gameobjects.text.TextObject;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class HighScoreState extends View implements IState {

    private AirspaceInvadersGame game;
    private final int margin = 100;

    private String[] highscoreList;

    /**
     * State to view highscores
     * @param game AirspaceInvadersGame
     */
    public HighScoreState(AirspaceInvadersGame game) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
    }

    /**
     * When the state is initialized the start function wil be executed
     */
    @Override
    public void start() {
        this.setBackground(AssetLoader.getBackgroundImage("background/a10-fade.jpg", this.game));

        ButtonChangeView buttonStart = new ButtonChangeView(new MenuState(this.game), this.game,"Menu", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

        HighScores list = new HighScores(GameProperties.getValue("highscoreFile"));
        Score[] highScoresList = list.loadScores();

        int verticalStep = ( GameProperties.getValueAsInt("worldHeight") - (2 * margin)) / (highScoresList.length > 15 ? 15 : highScoresList.length);
        int horizontalWidth =  GameProperties.getValueAsInt("worldHeight") / 100 * 40;

        for( int index = 0; index < highScoresList.length; index++){
            if(index <  GameProperties.getValueAsInt("amountOfHighScores") ) {
                TextObject name = new TextObject(highScoresList[index].getName());
                this.game.addGameObject(name, horizontalWidth, index * verticalStep + this.margin, 1);

                TextObject score = new TextObject(Integer.toString(highScoresList[index].getScore()));
                this.game.addGameObject(score, horizontalWidth + 200, index * verticalStep + this.margin, 1);
            }
        }
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
