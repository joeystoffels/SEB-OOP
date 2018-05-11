package nl.han.ica.airspaceinvaders.gameobjects.buttons;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class ButtonChangeView extends ButtonText {

    private View view;

    /**
     * Creates a button that detects that it has been clicked, and changes to the according view.
     *
     * @param view View
     * @param game AirspaceInvadersGame
     * @param text String
     * @param fontSize int
     * @param width int
     * @param height int
     */
    public ButtonChangeView(View view, AirspaceInvadersGame game, String text, int fontSize, int width, int height) {
        super(game, text, fontSize, width, height);
        this.view = view;
    }

    /**
     * Method to execute the change of view.
     */
    @Override
    public void execute() {
        super.execute();
        this.game.changeView(view);
    }
}
