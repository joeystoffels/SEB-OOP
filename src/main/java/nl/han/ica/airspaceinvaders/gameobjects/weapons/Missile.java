package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.enums.MissileType;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import processing.core.PGraphics;

public class Missile extends Weapon {

    private AirspaceInvadersGame airspaceInvadersGame;
    private IFlyingObject iFlyingObject;

    /**
     *
     */
    public Missile(AirspaceInvadersGame game, IFlyingObject iFlyingObject) {
        super.setDamage(100);
        this.airspaceInvadersGame = game;
        this.iFlyingObject = iFlyingObject;
    }

    @Override
    public IFlyingObject getIFlyingObject() {
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
        new Projectile(this, this.airspaceInvadersGame, AssetLoader.getSprite("weapons/Missile.png", 100), 3);
    }
}
