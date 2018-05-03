package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;

public class HighScoreView extends View implements IState {

    public HighScoreView(int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
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
