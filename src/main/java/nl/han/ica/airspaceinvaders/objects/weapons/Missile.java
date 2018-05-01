package nl.han.ica.airspaceinvaders.objects.weapons;

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

    /**
     *
     */
    @Override
    public void update() {

    }

    /**
     * @param g PGraphics object will be given by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {

    }

    public MissileType getMissileType() {
        return missileType;
    }

    @Override
    public IFlyingObject getIFlyingObject() {
        return null;
    }
}
