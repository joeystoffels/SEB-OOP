package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.airspaceinvaders.enums.MissileType;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import processing.core.PGraphics;

public class Missile extends Weapon {

    MissileType missileType;

    /**
     * @param missileType
     */
    public Missile(MissileType missileType) {
        this.missileType = missileType;
    }

    public MissileType getMissileType() {
        return missileType;
    }

    @Override
    public IFlyingObject getIFlyingObject() {
        return null;
    }

    @Override
    public void stopTimer() {

    }

    @Override
    public void destroy() {

    }
}
