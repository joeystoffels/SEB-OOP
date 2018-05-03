package nl.han.ica.airspaceinvaders.objects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.objects.weapons.Canon;
import nl.han.ica.airspaceinvaders.objects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.objects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.state.GameView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Air extends SpriteObject implements IFlyingObject {

    private int health;
    private int shield;

    private Weapon weapon;
    private GameView gameState;
    private AirspaceInvadersGame airspaceInvadersGame;
    private boolean isDirectionLeft;
    Timer timer = new Timer();

    public Air(GameView game, Sprite sprite) {
        super(sprite);
        this.gameState = game;
        this.airspaceInvadersGame = AirspaceInvadersGame.getInstance();
        this.health = 250;
        this.shield = 0;
        this.weapon = new Canon(this);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                movement(isDirectionLeft);
            }
        }, 1000, 2000);
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Projectile) {
                this.health = health - ((Projectile) gameObject).getDamage();
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            }
        }

        if (this.health <= 0) {
            this.weapon.destroy();
            this.airspaceInvadersGame.deleteGameObject(this);
            this.gameState.enemies.remove(this);
        }
    }

    @Override
    public void update() {
        // TODO ??
    }

    @Override
    public void movement(boolean isDirectionLeft) {
        this.setSpeed(2);
        this.setDirection(isDirectionLeft ? 90 : 270);
        this.move();
        this.isDirectionLeft = !isDirectionLeft;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

//    @Override
//    public AirspaceInvadersGame getWorld() {
//        return world;
//    }

    @Override
    public float getCenterXPos() {
        return this.getCenterX();
    }

    @Override
    public float getCenterYPos() {
        return this.getCenterY();
    }

    @Override
    public float getObjectDirection() {
        return 0;
    }
}
