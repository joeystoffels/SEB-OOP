package nl.han.ica.airspaceinvaders.gameobjects.player;

import ddf.minim.AudioPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Air;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Ground;
import nl.han.ica.airspaceinvaders.gameobjects.powerups.PowerUp;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Canon;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Missile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;
import processing.core.PConstants;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, IAirspaceObject {

    private final AirspaceInvadersGame airspaceInvadersGame;
    private Logger logger = LogFactory.getLogger();

    private Weapon canon;
    private Weapon missile;

    private int health = 250;
    private int shield = 0;
    private int score = 0;
    private int missileAmmo = 10;

    /**
     * Constructor for Player. This class will represent the player in the game.
     *
     * @param game AirspaceInvadersGame
     */
    public Player(AirspaceInvadersGame game) {
        super(AssetLoader.getSprite("player/A10-shade.png", 20), 6);
        this.canon = new Canon(game, this);
        this.missile = new Missile(game, this);
        this.airspaceInvadersGame = game;
        setFriction(0.04f);
        this.logger.logln(DefaultLogger.LOG_DEBUG, "Player spawned");

    }

    /**
     * Method to update this class. It is automatically called
     * by the Game Engine.
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
        if (getX() >= airspaceInvadersGame.getWidth() - super.getWidth()) {
            setxSpeed(0);
            setX(airspaceInvadersGame.getWidth() - super.getWidth());
        }
        if (getY() >= airspaceInvadersGame.getHeight() - super.getWidth()) {
            setySpeed(0);
            setY(airspaceInvadersGame.getHeight() - super.getWidth());
        }

        final float verticalRecovery = 1.0f;

        if (getySpeed() < verticalRecovery && getySpeed() > (verticalRecovery * -1)) {
            float horizontalRecovery = 0.5f;
            if (getxSpeed() < horizontalRecovery && getxSpeed() > (horizontalRecovery * -1)) {
                setCurrentFrameIndex(1);
            }
        }
    }

    /**
     * Method that handles keypressed actions by the player.
     *
     * @param keyCode int
     * @param key     char
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
            shootMissile();
        }
    }

    /**
     * Method to shoot a Missile and also play its sound.
     */
    private void shootMissile() {
        if (missileAmmo > 0) {
            AudioPlayer missileSound = this.airspaceInvadersGame.soundLibrary.loadFile("sounds/MissileSound.mp3");
            this.logger.logln(DefaultLogger.LOG_DEBUG, "SHOOT MISSILE");
            missileSound.play();
            missileSound.rewind();
            this.missile.shoot();
            missileAmmo--;
        }
    }

    /**
     * Implemented method from ICollidableWithGameObjects which determines the
     * behaviour of this class when an collision occurs with an other GameObject.
     * Gets called by the GameEngine.
     *
     * @param collidedGameObjects List with GameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof IAirspaceObject || gameObject instanceof Projectile) {
                handleCollisionDamage(gameObject);
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            } else if (gameObject instanceof PowerUp) {
                handlePowerUp(gameObject);
            }
        }
    }


    /**
     * Method that handles the damage that occurs when the Player
     * collides with an other Game Object.
     *
     * @param gameObject GameObject
     */
    private void handleCollisionDamage(GameObject gameObject) {
        if (gameObject instanceof Air) {
            handlePlayerDamage(25);
        } else if (gameObject instanceof Ground) {
            handlePlayerDamage(50);
        } else if (gameObject instanceof Projectile) {
            if (!(((Projectile) gameObject).getWeapon() instanceof Missile)) {
                handlePlayerDamage(((Projectile) gameObject).getDamage());
            }

        }
    }

    /**
     * Method that determines the damage done to the Player.
     *
     * @param damage int
     */
    private void handlePlayerDamage(int damage) {
        if (this.getShield() - damage <= 0) {
            subtractFromHealth(damage - this.getShield());
            this.setShield(0);
        } else {
            this.setShield(this.getShield() - damage);
        }
    }

    /**
     * Method that calculates and subtracts the damage done by
     * the collision to the Player.
     *
     * @param damage int
     */
    private void subtractFromHealth(int damage) {
        if (this.getHealth() - damage <= 0) {
            this.setHealth(0);
        } else {
            this.setHealth(this.getHealth() - damage);
        }
    }

    /**
     * Method that handles a picked-up PowerUp.
     *
     * @param gameObject GameObject
     */
    private void handlePowerUp(GameObject gameObject) {
        switch (((PowerUp) gameObject).getPowerUpType()) {
            case "HealthUp":
                this.setHealth(this.getHealth() + ((PowerUp) gameObject).getAmount());
                break;
            case "ShieldUp":
                this.setShield(this.getShield() + ((PowerUp) gameObject).getAmount());
                break;
            case "MissileUp":
                this.missileAmmo += 5;
                break;
            default:
                break;
        }
    }

    /**
     * Method that described the movement logic of the object.
     * This method is empty for the Player because the player
     * moves itself.
     *
     * @param isDirectionLeft boolean
     */
    @Override
    public void movement(boolean isDirectionLeft) {
        // do nothing
    }

    /**
     * Method to get centerX.
     *
     * @return centerX float
     */
    @Override
    public float getCenterXPos() {
        return this.getCenterX();
    }

    /**
     * Method to get centerY
     *
     * @return centerY float
     */
    @Override
    public float getCenterYPos() {
        return this.getCenterY();
    }

    /**
     * Method to get health
     *
     * @return health int
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to set health
     *
     * @param health int
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to get score
     *
     * @return score int
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to set score
     *
     * @param score int
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method to get name
     *
     * @return name String
     */
    public String getName() {
        return "Henk";
    }

    /**
     * Method to create a PowerUp object.
     * Remains empty for the Player object.
     */
    @Override
    public void createPowerUp() {
        // do nothing
    }

    /**
     * Method to get shield
     *
     * @return shield int
     */
    public int getShield() {
        return shield;
    }

    /**
     * Method to set shield
     *
     * @param shield int
     */
    public void setShield(int shield) {
        this.shield = shield;
    }

    /**
     * Method to get object height
     *
     * @return height float
     */
    @Override
    public float getObjectHeight() {
        return this.getHeight();
    }

    /**
     * Method to get missileAmmo
     *
     * @return missileAmmo int
     */
    public int getMissileAmmo() {
        return missileAmmo;
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine.
     */
    @Override
    public void destroy() {
        this.canon.destroy();
        this.missile.destroy();
    }

    /**
     * Method to get airspaceInvadersGame
     *
     * @return AirSpaceInvadersGame airspaceInvadersGame
     */
    @Override
    public AirspaceInvadersGame getAirspaceInvadersGame() {
        return airspaceInvadersGame;
    }
}
