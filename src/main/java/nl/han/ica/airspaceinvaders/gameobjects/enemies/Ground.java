package nl.han.ica.airspaceinvaders.gameobjects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Canon;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;
import nl.han.ica.airspaceinvaders.state.GameState;

import java.util.List;

public class Ground extends SpriteObject implements IAirspaceObject {

    private int health;
    private int score;

    private Weapon weapon;
    private GameState gameState;
    private AirspaceInvadersGame airspaceInvadersGame;
    private EnemyUtil enemyUtil = new EnemyUtil();

    public Ground(AirspaceInvadersGame game, GameState view, Sprite sprite) {
        super(sprite);
        this.gameState = view;
        this.airspaceInvadersGame = game;
        this.health = 100;
        this.score = 50;
        this.weapon = new Canon(game, this);
        this.setZ(-1); // displays a ground unit below an air unit
        movement(false);
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        enemyUtil.gameObjectCollisionOccurredUtil(this, collidedGameObjects);
    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(1);
        this.setDirection(180);
        this.move();
    }

    @Override
    public void destroy() {
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
        return airspaceInvadersGame;
    }

    @Override
    public int getHealth() {
        return health;
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
