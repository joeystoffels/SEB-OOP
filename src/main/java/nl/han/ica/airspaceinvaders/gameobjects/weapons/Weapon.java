package nl.han.ica.airspaceinvaders.gameobjects.weapons;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;

public abstract class Weapon {

    protected int damage;
    protected int intervalTime;

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract IFlyingObject getIFlyingObject();

    public abstract void stopTimer();

    public abstract void destroy();

    public abstract void shoot();
}
