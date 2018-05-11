package nl.han.ica.airspaceinvaders.interfaces;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;

public interface IAirspaceObject extends ICollidableWithGameObjects {
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
