package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.assets.highscores.HighScores;
import nl.han.ica.airspaceinvaders.assets.highscores.Score;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.text.TextObject;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class HighScoreState extends View implements IState {

    private AirspaceInvadersGame game;
    private final int margin = 100;

    private String[] highscoreList;

    public HighScoreState(AirspaceInvadersGame game) {
        super(GameProperties.getValue("worldWidth", true), GameProperties.getValue("worldHeight", true));
        this.game = game;
    }

    @Override
    public void start() {
        this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10-fade.jpg")));

        ButtonChangeView buttonStart = new ButtonChangeView(new MenuState(this.game), this.game,"Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

        HighScores list = new HighScores("highscores.csv");
        Score[] highScoresList = list.loadScores();

        int verticalStep = ( GameProperties.getValue("worldHeight", true) - (2 * margin)) / (highScoresList.length > 15 ? 15 : highScoresList.length);
        int horizontalWidth =  GameProperties.getValue("worldHeight", true) / 100 * 40;

        for( int index = 0; index < highScoresList.length; index++){
            if(index <  GameProperties.getValue("amountOfHighScores", true) ) {
                TextObject name = new TextObject(highScoresList[index].getName());
                this.game.addGameObject(name, horizontalWidth, index * verticalStep + this.margin, 1);

                TextObject score = new TextObject(Integer.toString(highScoresList[index].getScore()));
                this.game.addGameObject(score, horizontalWidth + 200, index * verticalStep + this.margin, 1);
            }
        }
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
    }
}
