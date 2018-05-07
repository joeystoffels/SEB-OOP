package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonImage;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonText;

public class MenuView extends View implements IState {

    private AirspaceInvadersGame game;

    public MenuView(AirspaceInvadersGame game,int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
        this.game = game;
    }

    @Override
    public void start() {
        System.out.println("Start MenuView");

//        Dashboard dashboard = new Dashboard(0,0, 200, 200);
        ButtonText button = new ButtonText("Hallo", 50, 200, 100);
        this.game.addGameObject(button, 100, 100, 2);

        ButtonImage button1 = new ButtonImage(AssetLoader.getSprite("enemy/A10.png", 100));
        this.game.addGameObject(button1, 100, 100, 3);


//        this.game.addDashboard(dashboard);
    }

    @Override
    public void reset() {
        System.out.println("Reset MenuView");
    }

    @Override
    public void update() {
        //System.out.println("Update MenuView");
    }

}
