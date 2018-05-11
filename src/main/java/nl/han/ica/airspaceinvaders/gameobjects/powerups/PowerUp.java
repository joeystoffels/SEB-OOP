package nl.han.ica.airspaceinvaders.gameobjects.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;

import java.util.List;

public class PowerUp extends SpriteObject implements ICollidableWithGameObjects {

    private int powerUpAmount;
    private String powerUpType;
    private AirspaceInvadersGame airspaceInvadersGame;

    /**
     * Constructor for PowerUp. This class will represent the PowerUp object
     * that has a chance to drop on the map when the player destroys an enemy.
     * @param game Game
     * @param sprite Sprite
     * @param powerUpType String
     */
    public PowerUp(AirspaceInvadersGame game, Sprite sprite, String powerUpType) {
        super(sprite);
        this.airspaceInvadersGame = game;
        this.powerUpType = powerUpType;
        this.powerUpAmount = 25;
        this.setDirection(180);
        this.setSpeed(1);
    }

    /**
     * Method to update this class. It is automatically called
     * by the Game Engine.
     */
    @Override
    public void update() {}

    /**
     * Implemented method from ICollidableWithGameObjects which determines the
     * behaviour of this class when an collision occurs with an other GameObject.
     * Gets called by the GameEngine.
     * @param collidedGameObjects List with GameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Player) {
                this.airspaceInvadersGame.deleteGameObject(this);
            }
        }
    }

    /**
     * Method to get powerUpAmount
     * @return powerUpAmount int
     */
    public int getAmount() {
        return powerUpAmount;
    }

    /**
     * Method to get powerUpType
     * @return powerUpType String
     */
    public String getPowerUpType() {
        return powerUpType;
    }
}
