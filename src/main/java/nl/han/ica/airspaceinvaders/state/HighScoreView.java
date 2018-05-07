package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.highscores.HighScores;
import nl.han.ica.airspaceinvaders.highscores.Score;
import nl.han.ica.airspaceinvaders.interfaces.IState;
import nl.han.ica.airspaceinvaders.objects.TextObject;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

public class HighScoreView extends View implements IState {

    private AirspaceInvadersGame game;
    private final int margin = 100;

    private String[] highscoreList;

    public HighScoreView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {
        this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10-fade.jpg")));

        ButtonChangeView buttonStart = new ButtonChangeView(new MenuView(this.game), this.game,"Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

        HighScores list = new HighScores("highscores.csv");
        Score[] highScoresList = list.loadScores();

        int verticalStep = (this.game.getWorldHeight() - (2 * margin)) / highScoresList.length;
        int horizontalWidth = this.game.getWorldWidth() / 100 * 40;

        for( int index = 0; index < highScoresList.length; index++){
            if(index < HighScores.amountOfHighScores ) {
                TextObject name = new TextObject(highScoresList[index].getName());
                this.game.addGameObject(name, horizontalWidth, index * verticalStep + this.margin, 1);

                TextObject score = new TextObject(Integer.toString(highScoresList[index].getScore()));
                this.game.addGameObject(score, horizontalWidth + 200, index * verticalStep + this.margin, 1);
            }
        }

//        list.saveScore("Nick", 10);
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
//        System.out.println("Update HighScoreView");
    }
}
