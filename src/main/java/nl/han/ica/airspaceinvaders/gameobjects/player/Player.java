package nl.han.ica.airspaceinvaders.gameobjects.player;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Air;
import nl.han.ica.airspaceinvaders.gameobjects.powerups.PowerUp;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Canon;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.airspaceinvaders.state.HighScoreState;
import processing.core.PConstants;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, IFlyingObject {

    private final AirspaceInvadersGame world;
    private Logger logger = LogFactory.getLogger();
    private int health = 250;
    private int shield = 0;
    private int score = 0;
    private final float verticalRecovery = 1.0f;

    private Weapon canon;
    private Weapon missile;


    /**
     *  Class that represents the player in the game
     * @param world AirspaceInvadersGame
     */
    public Player(AirspaceInvadersGame world) {
        super(AssetLoader.getSprite("player/A10-shade.png", 25), 6);
        this.canon = new Canon(world, this);
        this.world = world;
        setFriction(0.04f);
        this.logger.logln(DefaultLogger.LOG_DEBUG, "Player spawned");
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
            float horizontalRecovery = 0.5f;
            if (getxSpeed() < horizontalRecovery && getxSpeed() > (horizontalRecovery * -1)) {
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
            if (gameObject instanceof Air || gameObject instanceof Projectile) {
                handleCollisionDamage(gameObject);
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            } else if (gameObject instanceof PowerUp) {
                handlePowerUp(gameObject);
            }
        }
    }

    private void handleCollisionDamage(GameObject gameObject) {
        if (gameObject instanceof Air) {
            handlePlayerDamage(50);
        } else if (gameObject instanceof Projectile) {
            handlePlayerDamage(((Projectile) gameObject).getDamage());
        }

        if (getHealth() <= 0) {
            this.logger.logln(DefaultLogger.LOG_DEBUG, "ENDGAME");
            this.world.changeView(new HighScoreState(this.world));
        }
    }

    private void handlePlayerDamage(int damage) {
        if (this.getShield() == 0) {
            this.setHealth(this.getHealth() - damage <= 0 ? 0 : this.getHealth() - damage);
        } else
        if (this.getShield() < damage){
            this.setHealth(this.getHealth() + this.getShield() - damage);
            this.setShield(0);
        } else {
            this.setShield(this.getShield() - damage);
        }
    }

    private void handlePowerUp(GameObject gameObject) {
        switch(((PowerUp) gameObject).getPowerUpType()) {
            case "HealthUp": this.setHealth(this.getHealth() + ((PowerUp) gameObject).getAmount()); break;
            case "ShieldUp": this.setShield(this.getShield() + ((PowerUp) gameObject).getAmount()); break;
            case "LivesUp": break; // todo
            case "MissileUp": break; // todo
            default: break;
        }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Weapon getWeapon() {
        return canon;
    }

    public String getName() {
        return "Henk";
    }

    @Override
    public void generatePowerUp() {

    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
}
