package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.powerups.PowerUp;

import java.util.List;

public class Projectile extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private int damage;
    private AirspaceInvadersGame airspaceInvadersGame;
    private Logger logger = LogFactory.getLogger();
    private int counter = 0;
    private Weapon weapon;

    /**
     * Constructor for Projectile. This class will represent a projectile from one of
     * the possible weapons in-game. Currently Canon or Missile.
     * @param weapon Weapon
     * @param game AirspaceInvadersGame
     * @param sprite Sprite
     * @param totalFrames int
     */
    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.airspaceInvadersGame = game;
        this.weapon = weapon;
        createProjectile(weapon);
        this.setCurrentFrameIndex(0);
    }

    /**
     * Implemented method from ICollidableWithGameObjects which determines the
     * behaviour of this class when an collision occurs with an other GameObject.
     * Gets called by the GameEngine.
     * @param collidedGameObjects List with GameObject
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        if (weapon instanceof Missile) {
            if (!(collidedGameObjects.get(0) instanceof Player) &&
                !(collidedGameObjects.get(0) instanceof Projectile) &&
                !(collidedGameObjects.get(0) instanceof PowerUp)) {
                airspaceInvadersGame.deleteGameObject(this);
            }
        } else if (!(collidedGameObjects.get(0) instanceof Projectile) &&
                   !(collidedGameObjects.get(0) instanceof PowerUp)){
            airspaceInvadersGame.deleteGameObject(this);
        }
    }

    /**
     * Method to create the Projectile based on the given weapon type
     * and its parent IAirspaceObject.
     * @param weapon Weapon
     */
    private void createProjectile(Weapon weapon) {
        this.damage = weapon.getDamage();

        this.setDirection(weapon.getIAirspaceObject() instanceof Player ? 0 : 180);
        this.setSpeed(weapon instanceof Canon ? 7 : 3);

        float yPos = weapon.getIAirspaceObject().getCenterYPos() - weapon.getIAirspaceObject().getObjectHeight();
        float xPos = weapon.getIAirspaceObject().getCenterXPos() - this.getWidth() / 2;
        float xPosOffset = 0;

        if (weapon.getIAirspaceObject() instanceof Player) {
            Player player = (Player) weapon.getIAirspaceObject();
            float playerWidth = player.getWidth();
            if (weapon instanceof Missile) {
                xPosOffset = ((Missile) weapon).leftMissilePosition ? - playerWidth / 4 : playerWidth / 4;
            }
        } else {
            yPos = weapon.getIAirspaceObject().getCenterYPos() + weapon.getIAirspaceObject().getObjectHeight() / 1.5f;
        }

        airspaceInvadersGame.addGameObject(this, xPos + xPosOffset, yPos);
    }

    /**
     * Method to update this class. It is automatically called
     * by the Game Engine.
     */
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

    /**
     * Method to check if this object is outside the View boundaries.
     * In this case only checked on the Y-axis.
     */
    private void checkIfOutsideView() {
        if (this.getX() > airspaceInvadersGame.getWidth() | this.getY() > airspaceInvadersGame.getHeight() | this.getX() < 0 | this.getY() < 0) {
            //logger.logln(DefaultLogger.LOG_DEBUG,"Projectile removed");
            airspaceInvadersGame.deleteGameObject(this);
        }
    }

    /**
     * Getter of damage
     * @return damage int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Getter of weapon
     * @return weapon Weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }
}
