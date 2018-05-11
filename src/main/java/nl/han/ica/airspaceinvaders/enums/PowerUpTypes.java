package nl.han.ica.airspaceinvaders.enums;


/**
 * Enum that describes the PowerUpTypes available in the game.
 */
public enum PowerUpTypes {
    HEALTH("HealthUp"),
    SHIELD("ShieldUp"),
    MISSILE("MissileUp");

    private final String text;

    /**
     * @param text String
     */
    PowerUpTypes(final String text) {
        this.text = text;
    }


    /**
     * Overriden implementation of toString to return the
     * String representation of an enum value.
     *
     * @return String
     */
    @Override
    public String toString() {
        return text;
    }
}