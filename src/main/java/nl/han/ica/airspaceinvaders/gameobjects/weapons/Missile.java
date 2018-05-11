package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import ddf.minim.AudioPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

public class Missile extends Weapon {

    boolean leftMissilePosition;
    private AirspaceInvadersGame airspaceInvadersGame;
    private IAirspaceObject iAirspaceObject;

    /**
     * Constructor for Missile. This class will represent the Missile weapon
     * for the player.
     *
     * @param game            AirspaceInvadersGame
     * @param iAirspaceObject IAirspaceObject
     */
    public Missile(AirspaceInvadersGame game, IAirspaceObject iAirspaceObject) {
        super.setDamage(100);
        this.airspaceInvadersGame = game;
        this.iAirspaceObject = iAirspaceObject;
    }

    /**
     * Getter for the iAirspaceObject in this class.
     *
     * @return iAirspaceObject IAirspaceObject
     */
    @Override
    public IAirspaceObject getIAirspaceObject() {
        return this.iAirspaceObject;
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine.
     */
    @Override
    public void destroy() {
        // do nothing
    }

    /**
     * Method to shoot a Projectile from the type of this object.
     */
    @Override
    public void shoot() {
        Sprite sprite = AssetLoader.getSprite("weapons/Missile.png", 25);
        new Projectile(this, this.airspaceInvadersGame, sprite, 3);
        AudioPlayer missileSound = airspaceInvadersGame.soundLibrary.loadFile("sounds/MissileSound.mp3");
        missileSound.play();
        missileSound.rewind();
        this.leftMissilePosition = !leftMissilePosition;
    }
}
