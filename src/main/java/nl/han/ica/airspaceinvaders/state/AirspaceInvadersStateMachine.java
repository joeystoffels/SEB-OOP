package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class AirspaceInvadersStateMachine {

    private View myGameView;

    public AirspaceInvadersStateMachine(int worldWidth, int worldHeight) {
        // Set a default for menu
        changeView(new MenuView(worldWidth, worldHeight));

    }
    public View getView() {
        return this.myGameView;
    }
    public IState getState() {
        return (IState)this.myGameView;
    }


    /**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     *
     * @param gameView the new state of this context
     */
    public void changeView(final View gameView) {
        this.myGameView = gameView;
        AirspaceInvadersGame game = AirspaceInvadersGame.getInstance();
        IState state = (IState)this.myGameView;
        game.setView(this.myGameView);
        state.reset();
        state.start();

    }
}
