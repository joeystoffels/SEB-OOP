package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import ddf.minim.AudioPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

public class Missile extends Weapon {

    private AirspaceInvadersGame airspaceInvadersGame;
    private IAirspaceObject iFlyingObject;
    boolean leftMissilePosition;
    private AudioPlayer missileSound;

    /**
     * Constructor for Missile
     * @param game, iFlyingObject
     */
    public Missile(AirspaceInvadersGame game, IAirspaceObject iFlyingObject) {
        super.setDamage(100);
        this.airspaceInvadersGame = game;
        this.iFlyingObject = iFlyingObject;
    }

    /**
     * Getter for the iFlyingObject in this class
     * @return iFlyingObject
     */
    @Override
    public IAirspaceObject getIAirspaceObject() {
        return this.iFlyingObject;
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine
     */
    @Override
    public void destroy() {
        // do nothing
    }

    /**
     * Method to shoot a Projectile from the type of this object
     */
    @Override
    public void shoot() {
        Sprite sprite =  AssetLoader.getSprite("weapons/Missile.png", 25);
        new Projectile(this, this.airspaceInvadersGame, sprite, 3);
        this.missileSound = airspaceInvadersGame.soundLibrary.loadFile("sounds/MissileSound.mp3");
        missileSound.play();
        missileSound.rewind();
        this.missileSound = null;
        this.leftMissilePosition = !leftMissilePosition;
    }
}
