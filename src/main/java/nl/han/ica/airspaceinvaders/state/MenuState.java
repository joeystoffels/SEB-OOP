package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.interfaces.IState;

import java.sql.Timestamp;

public class MenuState extends View implements IState {

    private AirspaceInvadersGame game;
    private Timestamp startTimestamp;

    public MenuState(AirspaceInvadersGame game) {
        super(GameProperties.getValue("worldWidth", true), GameProperties.getValue("worldHeight", true));
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
    }

    private void showButtons() {
        this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10.jpg")));

        int horizontalOffset = 450;
        int fontSize = 80;

        ButtonChangeView buttonStart = new ButtonChangeView(new GameState(this.game),this.game,"Start game", fontSize, 200, 100);
        this.game.addGameObject(buttonStart, GameProperties.getValue("worldWidth", true) - horizontalOffset, 100, 2);

        ButtonChangeView buttonRules = new ButtonChangeView(new RulesState(this.game), this.game,"Rules", fontSize, 200, 100);
        this.game.addGameObject(buttonRules, GameProperties.getValue("worldWidth", true) - horizontalOffset, 200, 2);

        ButtonChangeView buttonHighscore = new ButtonChangeView(new HighScoreState(this.game), this.game,"Highscore", fontSize, 200, 100);
        this.game.addGameObject(buttonHighscore, GameProperties.getValue("worldWidth", true) - horizontalOffset, 300, 2);
    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
        System.out.println("deleted");
    }

    @Override
    public void update() {
        if(( new Timestamp(System.currentTimeMillis()).getTime() -  this.startTimestamp.getTime()) > GameProperties.getValue("splashTime", true) && this.game.getStartUp()){
            this.game.setStartUp(false);
            this.showButtons();
        }
    }
}
