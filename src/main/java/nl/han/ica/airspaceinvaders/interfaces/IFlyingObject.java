package nl.han.ica.airspaceinvaders.interfaces;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;

public interface IFlyingObject extends ICollidableWithGameObjects {
    void movement(boolean isDirectionLeft);
    float getCenterXPos();
    float getCenterYPos();
    float getObjectDirection();
}
