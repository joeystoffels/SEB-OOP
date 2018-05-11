package nl.han.ica.airspaceinvaders.gameobjects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

import java.util.List;

public class Ground extends SpriteObject implements IAirspaceObject {

    private int health;
    private int shield;

    private Weapon weapon;

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
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void createPowerUp() {
    }

    @Override
    public float getObjectHeight() {
        return 0;
    }

    @Override
    public void destroy() {

    }
}
