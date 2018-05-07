package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IState;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

import java.sql.Timestamp;

public class MenuView extends View implements IState {

    private AirspaceInvadersGame game;
    private Timestamp startTimestamp;
    private Boolean runSplash = true;
    private static final long SPLASH_TIME = 2000;

    public MenuView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {
        this.startTimestamp =  new Timestamp(System.currentTimeMillis());

        if(this.game.getStartUp()){
            this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10-logo.jpg")));
        } else {
            this.showButtons();
        }

//        ButtonImage button1 = new ButtonImage(AssetLoader.getSprite("enemy/A10.png", 100));
//        this.game.addGameObject(button1, 100, 100, 3);
    }

    private void showButtons() {
        this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10.jpg")));

        int horizontalOffset = 450;
        int fontSize = 80;

        ButtonChangeView buttonStart = new ButtonChangeView(new GameView(this.game),this.game,"Start game", fontSize, 200, 100);
        this.game.addGameObject(buttonStart, this.game.getWorldWidth() - horizontalOffset, 100, 2);

        ButtonChangeView buttonRules = new ButtonChangeView(new RulesView(this.game), this.game,"Rules", fontSize, 200, 100);
        this.game.addGameObject(buttonRules, this.game.getWorldWidth() - horizontalOffset, 200, 2);

        ButtonChangeView buttonHighscore = new ButtonChangeView(new HighScoreView(this.game), this.game,"Highscore", fontSize, 200, 100);
        this.game.addGameObject(buttonHighscore, this.game.getWorldWidth() - horizontalOffset, 300, 2);
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
        System.out.println("deleted");
    }

    @Override
    public void update() {
        if(( new Timestamp(System.currentTimeMillis()).getTime() -  this.startTimestamp.getTime()) > SPLASH_TIME && this.game.getStartUp()){
            this.game.setStartUp(false);
            this.showButtons();
        }
    }
}
