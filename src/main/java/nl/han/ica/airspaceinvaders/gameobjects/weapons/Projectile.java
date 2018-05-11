package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

import java.util.List;

public class Projectile extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private int damage;
    private AirspaceInvadersGame airspaceInvadersGame;
    private Logger logger = LogFactory.getLogger();
    private Weapon weapon;
    private int counter = 0;

    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.airspaceInvadersGame = game;
        this.weapon = weapon;
        createProjectile(weapon);
        this.setCurrentFrameIndex(0);
    }
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        if (collidedGameObjects.get(0) instanceof IAirspaceObject) {
            airspaceInvadersGame.deleteGameObject(this);
        }
    }

    private void createProjectile(Weapon weapon) {
        this.damage = weapon.getDamage();

        this.setDirection(weapon.getIFlyingObject() instanceof Player ? 0 : 180);
        this.setSpeed(weapon instanceof Canon ? 7 : 3);

        float yPos = weapon.getIFlyingObject().getCenterYPos() - weapon.getIFlyingObject().getObjectHeight();
        float xPos = weapon.getIFlyingObject().getCenterXPos() - this.getWidth() / 2;
        float xPosOffset = 0;

        if (weapon.getIFlyingObject() instanceof Player) {
            Player player = (Player) weapon.getIFlyingObject();
            float playerWidth = player.getWidth();
            if (weapon instanceof Missile) {
                yPos = yPos - player.getHeight() / 3;
                xPosOffset = ((Missile) weapon).leftMissilePosition ? - playerWidth / 3 : playerWidth / 3;
            }
        } else {
            yPos = weapon.getIFlyingObject().getCenterYPos() + ((IAirspaceObject) weapon.getIFlyingObject()).getObjectHeight() + this.getHeight();
        }

        airspaceInvadersGame.addGameObject(this, xPos + xPosOffset, yPos);
    }

    @Override
    public void update() {
        checkIfOutsideView();

        counter++;

        if(counter > 45){
            this.setCurrentFrameIndex(2);
        } else if(counter > 15){
            this.setCurrentFrameIndex(1);
        }
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
