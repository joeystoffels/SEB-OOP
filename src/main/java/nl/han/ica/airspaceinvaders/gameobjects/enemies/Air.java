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

    /**
     * Constructor for Air. This class will represent an air enemy in the game.
     *
     * @param game   AirspaceInvadersGame
     * @param view   GameState
     * @param sprite Sprite
     */
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
     * Implemented method from ICollidableWithGameObjects which determines the
     * behaviour of this class when an collision occurs with an other GameObject.
     * Gets called by the GameEngine.
     *
     * @param collidedGameObjects List with GameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        enemyUtil.gameObjectCollisionOccurredUtil(this, collidedGameObjects);
    }

    /**
     * Method to start the timer which determines the
     * movement period/delay of this object.
     */
    private void startTimer() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                movement(isDirectionLeft);
            }
        }, 1000, 2000);
    }

    /**
     * Method that described the movement logic of the object.
     *
     * @param isDirectionLeft boolean
     */
    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(3);
        this.setDirection(isDirectionLeft ? 110 : 250);
        this.move();
        this.isDirectionLeft = !isDirectionLeft;
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine.
     */
    @Override
    public void destroy() {
        this.timer.cancel();
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
     * Method to get height
     *
     * @return height float
     */
    @Override
    public float getObjectHeight() {
        return this.getHeight();
    }

    /**
     * Method to get airspaceInvadersGame
     *
     * @return airspaceInvadersGame AirspaceInvadersGame
     */
    @Override
    public AirspaceInvadersGame getAirspaceInvadersGame() {
        return this.airspaceInvadersGame;
    }

    /**
     * Method to return health
     *
     * @return int health
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to set health
     *
     * @param health int
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to set score
     *
     * @return score int
     */
    @Override
    public int getScore() {
        return score;
    }
}
