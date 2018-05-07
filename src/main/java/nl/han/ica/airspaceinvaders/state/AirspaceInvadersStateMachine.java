package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public class AirspaceInvadersStateMachine {

    private View myGameView;
    private AirspaceInvadersGame game;

    public AirspaceInvadersStateMachine(AirspaceInvadersGame game) {
        this.game = game;
        changeView(new MenuView(this.game));
    }

    public View getView() {
        return this.myGameView;
    }

    public IState getState() {
        return (IState) this.myGameView;
    }


    /**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     *
     * @param gameView the new state of this context
     */
    public void changeView(final View gameView) {
        if(this.myGameView != null){
            IState state = (IState) this.myGameView;
            state.reset();
        }

        this.myGameView = gameView;
        IState state = (IState) this.myGameView;
        this.game.setView(this.myGameView);
        state.start();
    }
}
