package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;

import java.util.List;

public class Projectile extends SpriteObject implements ICollidableWithGameObjects {

    private int damage;
    private AirspaceInvadersGame world;
    private Logger logger = LogFactory.getLogger();
    private Weapon weapon;

    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite, float xPos, float yPos, float direction) {
        super(sprite);
        this.world = game;
        world.addGameObject(this, weapon.getIFlyingObject() instanceof Player ? xPos - 14 : xPos - 15,
                                                weapon.getIFlyingObject() instanceof Player ? yPos - 125 : yPos + 75);
        this.setDirection(weapon.getIFlyingObject() instanceof Player ? 0 : 180);
        this.setSpeed(weapon.getIFlyingObject() instanceof Player ? 10 : 4);
        this.damage = weapon.getDamage();
        this.weapon = weapon;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        // System.out.println(collidedGameObjects);
        if (collidedGameObjects.get(0) instanceof IFlyingObject) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void update() {
        checkIfOutsideView();
    }

    private void checkIfOutsideView() {
        if (this.getX() > world.getWidth() | this.getY() > world.getHeight() | this.getX() < 0 | this.getY() < 0) {
            //logger.logln(DefaultLogger.LOG_DEBUG,"Projectile removed");
            world.deleteGameObject(this);
        }
    }

    public int getDamage() {
        return damage;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
