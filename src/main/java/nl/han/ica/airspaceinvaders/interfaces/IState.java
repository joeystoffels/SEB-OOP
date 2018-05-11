package nl.han.ica.airspaceinvaders.interfaces;

public interface IState {

    /**
     * When the state is initialized the start function wil be executed
     */
    void start();

    /**
     * The reset will be executed when the state will be stopped or changed
     */
    void reset();

    /**
     * On every cycle when the state is loaded, the update function is called
     */
    void update();
}
