package nl.han.ica.airspaceinvaders.interfaces;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;

public interface IFlyingObject extends ICollidableWithGameObjects {
    public void movement(boolean isDirectionLeft);
    public float getCenterXPos();
    public float getCenterYPos();
    public float getObjectDirection();
    public AirspaceInvadersGame getWorld();
}
