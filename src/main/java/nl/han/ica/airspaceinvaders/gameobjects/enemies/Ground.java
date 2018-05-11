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

    /**
     * Constructor for Ground. This class will represent a ground enemy in the game.
     * @param game AirspaceInvadersGame
     * @param view GameState
     * @param sprite Sprite
     */
    public Ground(AirspaceInvadersGame game, GameState view, Sprite sprite) {
        super(sprite);
        this.gameState = view;
        this.airspaceInvadersGame = game;
        this.health = 100;
        this.score = 50;
        this.weapon = new Canon(game, this);
        this.setZ(-1); // draws a ground unit below an air unit
        movement(false);
    }

    /**
     * Implemented method from ICollidableWithGameObjects which determines the
     * behaviour of this class when an collision occurs with an other GameObject.
     * Gets called by the GameEngine.
     * @param collidedGameObjects List<GameObject>
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        enemyUtil.gameObjectCollisionOccurredUtil(this, collidedGameObjects);
    }

    /**
     * Method that described the movement logic of the object.
     * @param isDirectionLeft boolean
     */
    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(1);
        this.setDirection(180);
        this.move();
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine.
     */
    @Override
    public void destroy() {
        this.weapon.destroy();
        this.airspaceInvadersGame.deleteGameObject(this);
        this.gameState.enemies.remove(this);
    }

    /**
     * Method to update this class. It is automatically called
     * by the Game Engine.
     */
    @Override
    public void update() {
        if (this.getY() > airspaceInvadersGame.getHeight() | this.getY() < 0) {
            airspaceInvadersGame.deleteGameObject(this);
            gameState.enemies.remove(this);
        }
    }

    /**
     * Method to create a PowerUp object.
     * Remains empty for the Player object.
     */
    @Override
    public void createPowerUp() {
        enemyUtil.createPowerUpUtil(this, this.weapon);
    }

    /**
     * Method to get centerX
     * @return centerX float
     */
    @Override
    public float getCenterXPos() {
        return this.getCenterX();
    }

    /**
     * Method to get centerY
     * @return centerY float
     */
    @Override
    public float getCenterYPos() {
        return this.getCenterY();
    }

    /**
     * Method to get height
     * @return height float
     */
    @Override
    public float getObjectHeight() {
        return this.getHeight();
    }

    /**
     * Method to get airspaceInvadersGame
     * @return airspaceInvadersGame AirspaceInvadersGame
     */
    @Override
    public AirspaceInvadersGame getAirspaceInvadersGame() {
        return airspaceInvadersGame;
    }

    /**
     * Method to return health
     * @return int health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Method to set health
     * @param health int
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to set score
     * @return score int
     */
    @Override
    public int getScore() {
        return score;
    }
}
