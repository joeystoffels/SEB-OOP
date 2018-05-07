package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class MenuView extends View implements IState {

    private AirspaceInvadersGame game;

    public MenuView(AirspaceInvadersGame game,int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
        this.game = game;
    }

    @Override
    public void start() {
        System.out.println("Start MenuView");
    }

    @Override
    public void reset() {
        System.out.println("Reset MenuView");
    }

    @Override
    public void update() {
        System.out.println("Update MenuView");
    }

}
