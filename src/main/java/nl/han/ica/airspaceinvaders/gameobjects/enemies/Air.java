package nl.han.ica.airspaceinvaders.gameobjects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.enums.PowerUpTypes;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.powerups.PowerUp;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Canon;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.airspaceinvaders.state.GameState;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Air extends SpriteObject implements IFlyingObject {

    private int health;
    private int shield;

    private Weapon weapon;
    private GameState gameState;
    private AirspaceInvadersGame airspaceInvadersGame;
    private boolean isDirectionLeft;
    private Logger logger = LogFactory.getLogger();
    private Timer timer = new Timer();


    public Air(AirspaceInvadersGame game, GameState view, Sprite sprite) {
        super(sprite);
        this.gameState = view;
        this.airspaceInvadersGame = game;
        this.health = 50;
        this.shield = 0;
        this.weapon = new Canon(game, this);
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

        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Projectile) {
                this.health = health - ((Projectile) gameObject).getDamage();
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            }
            if (gameObject instanceof Player) {
                this.setHealth(0);
            }
        }

        if (this.health <= 0) {
            createPowerUp();
            this.destroy();

            for (GameObject gameObject : airspaceInvadersGame.getGameObjectItems()) {
                if (gameObject instanceof Player) {
                    ((Player) gameObject).setScore(((Player) gameObject).getScore() + 25);
                }
            }
        }
    }

    private void destroy() {
        this.weapon.destroy();
        this.airspaceInvadersGame.deleteGameObject(this);
        this.gameState.enemies.remove(this);
    }

    @Override
    public void update() {
        if (this.getY() > airspaceInvadersGame.getHeight() | this.getY() < 0) {
            airspaceInvadersGame.deleteGameObject(this);
            gameState.enemies.remove(this);
        }

    }

    @Override
    public void createPowerUp() {
        if (Math.random() < GameProperties.getValueAsDouble("powerupchance")) { // Dropchance
            int randomNr = (int) Math.floor(Math.random() * PowerUpTypes.values().length);

            this.airspaceInvadersGame.addGameObject(
                    new PowerUp(this.airspaceInvadersGame,
                    AssetLoader.getSprite("enemy/" + PowerUpTypes.values()[randomNr].toString() + ".png", 20),
                    PowerUpTypes.values()[randomNr].toString()), this.getCenterXPos() - (this.getWidth() / 2), this.getCenterYPos() - (this.getHeight() / 2)
            );
        }
    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(4);
        this.setDirection(isDirectionLeft ? 120 : 240);
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

    @Override
    public float getCenterXPos() {
        return this.getCenterX();
    }

    @Override
    public float getCenterYPos() {
        return this.getCenterY();
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public float getObjectHeight() {
        return this.getHeight();
    }
}
