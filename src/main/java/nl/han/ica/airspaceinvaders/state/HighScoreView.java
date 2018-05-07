package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.highscores.HighScores;
import nl.han.ica.airspaceinvaders.highscores.Score;
import nl.han.ica.airspaceinvaders.interfaces.IState;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

public class HighScoreView extends View implements IState {

    private AirspaceInvadersGame game;

    private String[] highscoreList;

    public HighScoreView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {
        ButtonChangeView buttonStart = new ButtonChangeView(new MenuView(this.game), this.game,"Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

        HighScores list = new HighScores("highscores.csv");
        Score[] highScoresList = list.loadScores();

        list.saveScore("Nick", 10);
        list.saveScore("Nick", 20);
        list.saveScore("Nick", 30);


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
