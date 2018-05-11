package nl.han.ica.airspaceinvaders.gameobjects.weapons;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

public abstract class Weapon {

    private int damage;

    /**
     * Method to get damage
     * @return
     */
    int getDamage() {
        return damage;
    }

    /**
     * Method to set damage
     * @param damage
     */
    void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract IAirspaceObject getIAirspaceObject();

    public abstract void destroy();

    public abstract void shoot();
}
