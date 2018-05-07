package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

public class HighScoreView extends View implements IState {

    private AirspaceInvadersGame game;

    public HighScoreView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {
        ButtonChangeView buttonStart = new ButtonChangeView(new MenuView(this.game), this.game,"Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
        System.out.println("Update HighScoreView");
    }
}
