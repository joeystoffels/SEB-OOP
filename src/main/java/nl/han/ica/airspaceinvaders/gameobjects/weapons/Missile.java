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
     *
     */
    public Missile(AirspaceInvadersGame game, IAirspaceObject iFlyingObject) {
        super.setDamage(100);
        this.airspaceInvadersGame = game;
        this.iFlyingObject = iFlyingObject;
    }

    @Override
    public IAirspaceObject getIAirspaceObject() {
        return this.iFlyingObject;
    }

    @Override
    public void destroy() {
        // do nothing
    }

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
