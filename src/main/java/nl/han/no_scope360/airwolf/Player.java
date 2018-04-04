package nl.han.no_scope360.airwolf;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.waterworld.WaterWorld;
import nl.han.ica.waterworld.tiles.BoardsTile;
import nl.han.no_scope360.airwolf.Assets.AssetLoader;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.List;


public class Player extends AnimatedSpriteObject implements ICollidableWithTiles {

    final int size=25;
    private final Game world;

    public Player(Game game) {

        super(AssetLoader.getSprite("player/A10.png", 8),1);
        this.world=game;
        setCurrentFrameIndex(0);
        setFriction(0.05f);
    }

    @Override
    public void update() {
        setySpeed(-1);
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


    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {

    }
}
