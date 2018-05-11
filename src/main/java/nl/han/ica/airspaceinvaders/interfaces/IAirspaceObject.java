package nl.han.ica.airspaceinvaders.interfaces;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public interface IAirspaceObject extends ICollidableWithGameObjects {

    // abstract methods to be implemented
    void movement(boolean isDirectionLeft);

    float getCenterXPos();

    float getCenterYPos();

    float getObjectHeight();

    void createPowerUp();

    void destroy();

    AirspaceInvadersGame getAirspaceInvadersGame();

    int getHealth();

    void setHealth(int health);

    int getScore();
}
