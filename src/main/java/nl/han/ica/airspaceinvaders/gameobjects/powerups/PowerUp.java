package nl.han.ica.airspaceinvaders.gameobjects.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class PowerUp extends SpriteObject implements ICollidableWithGameObjects {

    final int size = 20;
    private int powerUpAmount;
    private String powerUpType;
    private AirspaceInvadersGame airspaceInvadersGame;

    public PowerUp(AirspaceInvadersGame game, Sprite sprite, String powerUpType) {
        super(sprite);
        this.airspaceInvadersGame = game;
        this.powerUpType = powerUpType;
        this.powerUpAmount = 25;
        this.setDirection(180);
        this.setSpeed(1);
    }

    @Override
    public void update() {}

    public int getAmount() {
        return powerUpAmount;
    }

    public void setAmount(int amount) {
        this.powerUpAmount = amount;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Player) {
                this.airspaceInvadersGame.deleteGameObject(this);
            }
        }
    }

    public String getPowerUpType() {
        return powerUpType;
    }

    public int getPowerUpAmount() {
        return powerUpAmount;
    }
}
