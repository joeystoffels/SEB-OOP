package nl.han.ica.airspaceinvaders.objects.enemies;

import nl.han.ica.airspaceinvaders.interfaces.IEnemy;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Air extends GameObject implements IEnemy {

    private int health;
    private int shield;

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
    public void draw(PGraphics g) {

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
}
