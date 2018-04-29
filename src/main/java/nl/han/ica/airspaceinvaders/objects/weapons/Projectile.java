package nl.han.ica.airspaceinvaders.objects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.interfaces.IEnemy;

import java.util.List;

public class Projectile extends SpriteObject implements ICollidableWithGameObjects {

    private AirspaceInvadersGame world;

    public Projectile(Weapon weapon, AirspaceInvadersGame game, Sprite sprite, float xPos, float yPos) {
        super(sprite);
        this.world = game;
        world.addGameObject(this, xPos, yPos);
        this.setDirection(180);
        this.setSpeed(5);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void update() {

    }
}
