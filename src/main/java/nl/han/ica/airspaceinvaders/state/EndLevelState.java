package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class EndLevelState extends View implements IState {

    private AirspaceInvadersGame game;
    private int levelNumber;

    public EndLevelState(AirspaceInvadersGame game, int levelNumber) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
        this.levelNumber = levelNumber;
    }

    @Override
    public void start() {
        this.setBackground(AssetLoader.getBackgroundImage("background/a10-leveldone.jpg", this.game));

        int postionX = (GameProperties.getValueAsInt("worldWidth") / 2)  - 100;
        int postionY = GameProperties.getValueAsInt("worldHeight") - 100;
        ButtonChangeView buttonStart = new ButtonChangeView(new GameState(this.game, levelNumber + 1), this.game,"Start level " + (this.levelNumber + 1), 50, 200, 100);
        this.game.addGameObject(buttonStart, postionX, postionY, 1);

    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
    }
}
