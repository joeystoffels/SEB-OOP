package nl.han.ica.airspaceinvaders.interfaces;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;

public interface IEnemy extends ICollidableWithGameObjects {

    public void shoot();

    public void movement(boolean isDirectionLeft);
}
