package nl.han.ica.airspaceinvaders.enums;

public enum PowerUpTypes {
    HEALTH("HealthUp"),
    SHIELD("ShieldUp"),
    MISSILE("MissileUp")
    ;

    private final String text;

    /**
     * @param text
     */
    PowerUpTypes(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}