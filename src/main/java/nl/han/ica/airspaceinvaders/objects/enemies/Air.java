package nl.han.ica.airspaceinvaders.objects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.interfaces.IEnemy;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.objects.weapons.Canon;
import nl.han.ica.airspaceinvaders.objects.weapons.Weapon;
import processing.core.PGraphics;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Air extends SpriteObject implements IEnemy {

    private int health;
    private int shield;

    private Weapon weapon;

    private boolean isDirectionLeft;

    Timer timer = new Timer();

    public Air(Sprite sprite) {
        super(sprite);
        this.weapon = new Canon();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                movement(isDirectionLeft);
            }
        }, 1000, 2000);
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void update() {
        //movement();
    }

    @Override
    public void shoot() {

    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(2);
        this.setDirection(isDirectionLeft ? 90 : 270);
        this.move();
        this.isDirectionLeft = !isDirectionLeft;
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
