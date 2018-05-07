package nl.han.ica.airspaceinvaders.objects.player;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.airspaceinvaders.objects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.objects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.state.GameView;
import processing.core.PConstants;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, IFlyingObject {

    private final AirspaceInvadersGame world;
    private Logger logger = LogFactory.getLogger();
    private int health = 250;
    private final float horizontalRecovery = 0.5f;
    private final float verticalRecovery = 1.0f;

    private Weapon canon;
    private Weapon missile;


    public Player() {

        super(AssetLoader.getSprite("player/A10.png", 20), 6);
        this.world = AirspaceInvadersGame.getInstance();
//        world.getDashboardText().setText("Health: " + this.getHealth());
//        this.canon = new Canon(this);
//        setCurrentFrameIndex(0);
//        setFriction(0.02f);
    }

    /**
     *
     */
    @Override
    public void update() {

        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.getWidth() - super.getWidth()) {
            setxSpeed(0);
            setX(world.getWidth() - super.getWidth());
        }
        if (getY() >= world.getHeight() - super.getWidth()) {
            setySpeed(0);
            setY(world.getHeight() - super.getWidth());
        }


        if (getySpeed() < this.verticalRecovery && getySpeed() > (this.verticalRecovery * -1)) {
            if (getxSpeed() < this.horizontalRecovery && getxSpeed() > (this.horizontalRecovery * -1)) {
                setCurrentFrameIndex(1);
            }
        }
    }

    /**
     * @param keyCode
     * @param key
     */
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 6;
        if (keyCode == PConstants.LEFT) {
            setCurrentFrameIndex(5);
            setDirectionSpeed(270, speed);
        }
        if (keyCode == PConstants.UP) {
            setCurrentFrameIndex(2);
            setDirectionSpeed(0, speed);
        }
        if (keyCode == PConstants.RIGHT) {
            setCurrentFrameIndex(4);
            setDirectionSpeed(90, speed);
        }
        if (keyCode == PConstants.DOWN) {
            setCurrentFrameIndex(3);
            setDirectionSpeed(180, speed);
        }
        if (keyCode == PConstants.CONTROL) {

        }
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Projectile) {
                setHealth(getHealth() - ((Projectile) gameObject).getDamage());
                updateDashboard();
                if (getHealth() <= 0) {
                    System.out.println("ENDGAME");
                }
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            }
        }
    }

    public void updateDashboard() {
//        world.getDashboardText().setText("Health:" + getHealth());
    }

    @Override
    public void movement(boolean isDirectionLeft) {

    }

    @Override
    public float getCenterXPos() {
        return this.getCenterX();
    }

    @Override
    public float getCenterYPos() {
        return this.getCenterY();
    }

    @Override
    public float getObjectDirection() {
        return this.getDirection();
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
