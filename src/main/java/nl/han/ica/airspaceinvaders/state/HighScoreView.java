package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class HighScoreView extends View implements IState {

    private AirspaceInvadersGame game;

    public HighScoreView(AirspaceInvadersGame game, int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
        this.game = game;
    }

    @Override
    public void start() {
        System.out.println("Start HighScoreView");
    }

    @Override
    public void reset() {
        System.out.println("Reset HighScoreView");
    }

    @Override
    public void update() {
        System.out.println("Update HighScoreView");
    }
}
