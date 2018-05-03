package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;

public class MenuView extends View implements IState {

    public MenuView(int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
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
