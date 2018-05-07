package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.highscores.HighScores;
import nl.han.ica.airspaceinvaders.highscores.Score;
import nl.han.ica.airspaceinvaders.interfaces.IState;
import nl.han.ica.airspaceinvaders.objects.TextObject;
import nl.han.ica.airspaceinvaders.objects.buttons.ButtonChangeView;

public class RulesView extends View implements IState {

    private AirspaceInvadersGame game;

    public RulesView(AirspaceInvadersGame game) {
        super(game.getWorldWidth(), game.getWorldHeight());
        this.game = game;
    }

    @Override
    public void start() {
        this.setBackground(this.game.loadImage(AssetLoader.getImage("background/a10-fade.jpg")));

        ButtonChangeView buttonStart = new ButtonChangeView(new MenuView(this.game), this.game,"Back", 50, 200, 100);
        this.game.addGameObject(buttonStart, 100, 100, 1);

    }

    @Override
    public void reset() {
        this.game.deleteAllGameOBjects();
    }

    @Override
    public void update() {
    }
}
