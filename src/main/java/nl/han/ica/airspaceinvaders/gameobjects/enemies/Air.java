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
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;
import nl.han.ica.airspaceinvaders.state.GameState;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Air extends SpriteObject implements IAirspaceObject {

    private int health;
    private int score;

    private Weapon weapon;
    private GameState gameState;
    private AirspaceInvadersGame airspaceInvadersGame;
    private boolean isDirectionLeft;
    private Timer timer;
    private EnemyUtil enemyUtil = new EnemyUtil();


    public Air(AirspaceInvadersGame game, GameState view, Sprite sprite) {
        super(sprite);
        this.gameState = view;
        this.airspaceInvadersGame = game;
        this.health = 50;
        this.score = 25;
        this.weapon = new Canon(game, this);
        startTimer();
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        enemyUtil.gameObjectCollisionOccurredUtil(this, collidedGameObjects);
    }

    private void startTimer() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                movement(isDirectionLeft);
            }
        }, 1000, 2000);
    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(3);
        this.setDirection(isDirectionLeft ? 110 : 250);
        this.move();
        this.isDirectionLeft = !isDirectionLeft;
    }

    @Override
    public void destroy() {
        this.timer.cancel();
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
        enemyUtil.createPowerUpUtil(this, this.weapon);
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
    public float getObjectHeight() {
        return this.getHeight();
    }

    @Override
    public AirspaceInvadersGame getAirspaceInvadersGame() {
        return this.airspaceInvadersGame;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getScore() {
        return score;
    }
}
