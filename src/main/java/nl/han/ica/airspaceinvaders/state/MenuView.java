package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.interfaces.IState;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

public class MenuView extends View implements IState {

    private AirspaceInvadersGame game;

    public MenuView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {

        ButtonChangeView buttonStart = new ButtonChangeView(new GameView(this.game),this.game,"Start game", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 2);

        ButtonChangeView buttonHighscore = new ButtonChangeView(new HighScoreView(this.game), this.game,"Highscore", 50, 200, 100);
        this.game.addGameObject(buttonHighscore, 100, 200, 2);

//        ButtonImage button1 = new ButtonImage(AssetLoader.getSprite("enemy/A10.png", 100));
//        this.game.addGameObject(button1, 100, 100, 3);
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
        //System.out.println("Update MenuView");
    }

}
