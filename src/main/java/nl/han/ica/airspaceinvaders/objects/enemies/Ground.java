package nl.han.ica.airspaceinvaders.objects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

public class Ground extends SpriteObject implements IFlyingObject {

    private int health;
    private int shield;

    public Ground(Sprite sprite) {
        super(sprite);
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void update() {

    }

    @Override
    public void movement(boolean isDirectionLeft) {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    @Override
    public float getCenterXPos() {
        return 0;
    }

    @Override
    public float getCenterYPos() {
        return 0;
    }

    @Override
    public float getObjectDirection() {
        return 0;
    }
}
