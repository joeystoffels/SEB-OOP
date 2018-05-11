package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

public class Missile extends Weapon {

    private AirspaceInvadersGame airspaceInvadersGame;
    private IAirspaceObject iFlyingObject;
    public boolean leftMissilePosition;

    /**
     *
     */
    public Missile(AirspaceInvadersGame game, IAirspaceObject iFlyingObject) {
        super.setDamage(100);
        this.airspaceInvadersGame = game;
        this.iFlyingObject = iFlyingObject;
    }

    @Override
    public IAirspaceObject getIFlyingObject() {
        return this.iFlyingObject;
    }

    @Override
    public void stopTimer() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void shoot() {
        new Projectile(this, this.airspaceInvadersGame, AssetLoader.getSprite("weapons/Missile.png", 30), 3);
        this.leftMissilePosition = !leftMissilePosition;
    }
}
