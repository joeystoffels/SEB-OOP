package nl.han.ica.airspaceinvaders.objects.buttons;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class ButtonChangeView extends ButtonText {

    private View view;
    /**
     * Create a new TextObject.
     *
     * @param game
     * @param text
     * @param fontSize
     * @param width
     * @param height
     */
    public ButtonChangeView(View view, AirspaceInvadersGame game, String text, int fontSize, int width, int height) {
        super(game, text, fontSize, width, height);
        this.view = view;
    }

    @Override
    public void execute() {
        super.execute();
        this.game.changeView(view);
    }
}
