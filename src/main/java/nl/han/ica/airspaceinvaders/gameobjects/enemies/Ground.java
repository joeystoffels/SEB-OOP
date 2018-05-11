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
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Missile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;
import nl.han.ica.airspaceinvaders.state.GameState;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Ground extends SpriteObject implements IAirspaceObject {

    private int health;

    private Weapon weapon;
    private GameState gameState;
    private AirspaceInvadersGame airspaceInvadersGame;

    public Ground(AirspaceInvadersGame game, GameState view, Sprite sprite) {
        super(sprite);
        this.gameState = view;
        this.airspaceInvadersGame = game;
        this.health = 100;
        this.weapon = new Canon(game, this);
        movement(false);
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Projectile) {
                this.health = this.health - ((Projectile) gameObject).getDamage();
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            }
            if (gameObject instanceof Player) {
                this.health = 0;
            }
        }

        if (this.health <= 0) {
            createPowerUp();
            this.destroy();

            for (GameObject gameObject : airspaceInvadersGame.getGameObjectItems()) {
                if (gameObject instanceof Player) {
                    ((Player) gameObject).setScore(((Player) gameObject).getScore() + 50);
                }
            }
        }
    }

    @Override
    public void update() {
        if (this.getY() > airspaceInvadersGame.getHeight() | this.getY() < 0) {
            airspaceInvadersGame.deleteGameObject(this);
            gameState.enemies.remove(this);
        }
    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(1);
        this.setDirection(180);
        this.move();
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
    public void createPowerUp() {
        if (Math.random() < GameProperties.getValueAsDouble("powerupchance")) {
            int randomNr = (int) Math.floor(Math.random() * PowerUpTypes.values().length);

            this.airspaceInvadersGame.addGameObject(
                    new PowerUp(this.airspaceInvadersGame,
                            AssetLoader.getSprite("powerup/PowerUp.png", 10),
                            PowerUpTypes.values()[randomNr].toString()), this.getCenterXPos() - (this.getWidth() / 2), this.getCenterYPos() - (this.getHeight() / 2)
            );
        }
    }

    @Override
    public float getObjectHeight() {
        return this.getHeight();
    }

    @Override
    public void destroy() {
        this.weapon.destroy();
        this.airspaceInvadersGame.deleteGameObject(this);
        this.gameState.enemies.remove(this);
    }
}
