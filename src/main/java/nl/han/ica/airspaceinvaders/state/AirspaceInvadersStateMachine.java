package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.level.Level;
import nl.han.ica.airspaceinvaders.interfaces.IState;

public class AirspaceInvadersStateMachine {

    private View currentView;
    private AirspaceInvadersGame game;

    /**
     * The AirspaceInvadersStateMachine is used to keep the state of the application.
     * A state design pattern is used for this purpose
     * https://en.wikipedia.org/wiki/State_pattern
     * @param game AirspaceInvadersGame
     */
    public AirspaceInvadersStateMachine(AirspaceInvadersGame game) {
        this.game = game;
        changeView(new MenuState(this.game));
    }

    /**
     * Get the current state
     * @return View
     */
    public View getView() {
        return this.currentView;
    }

    /**
     * Get the current state
     * @return IState
     */
    public IState getState() {
        return (IState) this.currentView;
    }

    /**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     *
     * @param gameView View
     */
    public void changeView(final View gameView) {
        if(this.currentView != null){
            IState state = (IState) this.currentView;
            state.reset();
        }
        this.currentView = gameView;
        IState state = (IState) this.currentView;
        this.game.setView(this.currentView);
        state.start();
    }
}
