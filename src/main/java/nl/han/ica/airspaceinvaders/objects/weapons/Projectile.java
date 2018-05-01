package nl.han.ica.airspaceinvaders.objects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.objects.player.Player;

import java.util.List;

public class Projectile extends SpriteObject implements ICollidableWithGameObjects {

    private AirspaceInvadersGame world;

    private Logger logger = LogFactory.getLogger();

    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite, float xPos, float yPos, float direction) {
        super(sprite);
        this.world = game;
        world.addGameObject(this, weapon.getIFlyingObject() instanceof Player ? xPos - 14 : xPos - 15,
                                                weapon.getIFlyingObject() instanceof Player ? yPos - 125 : yPos + 75);
        this.setDirection(weapon.getIFlyingObject() instanceof Player ? 0 : 180);
        this.setSpeed(weapon.getIFlyingObject() instanceof Player ? 10 : 4);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        world.deleteGameObject(this);
    }

    @Override
    public void update() {
        checkIfOutsideView();
    }

    private void checkIfOutsideView() {
        if (this.getX() > world.getWidth() |
                this.getY() > world.getHeight()) {
            logger.logln(DefaultLogger.LOG_DEBUG,"Projectile removed");
            world.deleteGameObject(this);
        }
    }
}
