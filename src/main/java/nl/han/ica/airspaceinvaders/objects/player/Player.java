package nl.han.ica.airspaceinvaders.objects.player;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.dashboard.TextObject;
import processing.core.PConstants;
import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private final AirspaceInvadersGame world;
    private Logger logger = LogFactory.getLogger();
    private int health = 250;
    final int size=25;

    public Player(AirspaceInvadersGame game) {
        super(AssetLoader.getSprite("player/A10.png", 8),1);
        this.world=game;
        world.getDashboardText().setText("Health: " + this.getHealth());
        setCurrentFrameIndex(0);
        setFriction(0.05f);
    }

    /**
     *
     */
    @Override
    public void update() {

        setySpeed(0);
        if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=world.getWidth()-size) {
            setxSpeed(0);
            setX(world.getWidth() - size);
        }
        if (getY()>=world.getHeight()-size) {
            setySpeed(0);
            setY(world.getHeight() - size);
        }
    }

    /**
     * @param keyCode
     * @param key
     */
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 10;
        if (keyCode == PConstants.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == PConstants.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == PConstants.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == PConstants.DOWN) {
            setDirectionSpeed(180, speed);
        }
    }

    /**
     * @param collidedGameObjects
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        setHealth(getHealth() - 50);
        TextObject textObject = new TextObject("test" + getHealth());
        world.getDashboardText().setText("Health:" + getHealth());
        System.out.println(world.getDashboardText().toString());
        if (getHealth() <= 0) {
            System.out.println("ENDGAME");
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
