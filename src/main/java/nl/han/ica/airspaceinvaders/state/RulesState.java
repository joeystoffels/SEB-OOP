package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.gameobjects.buttons.ButtonChangeView;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class RulesState extends View implements IState {

    private AirspaceInvadersGame game;

    /**
     * State to view the rules
     *
     * @param game AirspaceInvadersGame
     */
    public RulesState(AirspaceInvadersGame game) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
    }

    /**
     * When the state is initialized the start function wil be executed
     */
    @Override
    public void start() {
        this.setBackground(AssetLoader.getBackgroundImage("background/a10-fade.jpg", this.game));

        ButtonChangeView buttonStart = new ButtonChangeView(new MenuState(this.game), this.game, "Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

    }

    /**
     * The reset will be executed when the state will be stopped or changed
     */
    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    /**
     * On every cycle when the state is loaded, the update function is called
     */
    @Override
    public void update() {
    }
}
