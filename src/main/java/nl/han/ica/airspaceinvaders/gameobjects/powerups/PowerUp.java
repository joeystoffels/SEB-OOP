package nl.han.ica.airspaceinvaders.gameobjects.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public abstract class PowerUp extends GameObject {

    final int size = 20;
    private int amount;

    @Override
    public void update() {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
