package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Air;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;

import java.util.List;

public class Projectile extends SpriteObject implements ICollidableWithGameObjects {

    private int damage;
    private AirspaceInvadersGame airspaceInvadersGame;
    private Logger logger = LogFactory.getLogger();
    private Weapon weapon;

    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite) {
        super(sprite);
        this.airspaceInvadersGame = game;
        this.weapon = weapon;
        createProjectile(weapon);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        if (collidedGameObjects.get(0) instanceof IFlyingObject) {
            airspaceInvadersGame.deleteGameObject(this);
        }
    }

    private void createProjectile(Weapon weapon) {
        this.damage = weapon.getDamage();
        this.setDirection(weapon.getIFlyingObject() instanceof Player ? 0 : 180);
        this.setSpeed(weapon instanceof Canon ? 7 : 3);

        float yPos;
        float xPos = weapon.getIFlyingObject().getCenterXPos() - this.getWidth() / 2;

        if (weapon.getIFlyingObject() instanceof Player) {
            yPos = weapon.getIFlyingObject().getCenterYPos() - ((Player) weapon.getIFlyingObject()).getHeight();
        } else {
            yPos = weapon.getIFlyingObject().getCenterYPos() + ((IFlyingObject) weapon.getIFlyingObject()).getObjectHeight() + this.getHeight();
        }

        airspaceInvadersGame.addGameObject(this, xPos, yPos);
    }

    @Override
    public void update() {
        checkIfOutsideView();
    }

    private void checkIfOutsideView() {
        if (this.getX() > airspaceInvadersGame.getWidth() | this.getY() > airspaceInvadersGame.getHeight() | this.getX() < 0 | this.getY() < 0) {
            //logger.logln(DefaultLogger.LOG_DEBUG,"Projectile removed");
            airspaceInvadersGame.deleteGameObject(this);
        }
    }

    public int getDamage() {
        return damage;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
