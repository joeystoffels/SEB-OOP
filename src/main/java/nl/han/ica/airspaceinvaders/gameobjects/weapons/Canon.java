package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Ground;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

import java.util.Timer;
import java.util.TimerTask;

public class Canon extends Weapon {

    private Timer timer = new Timer();
    private AirspaceInvadersGame airspaceInvadersGame;
    private IAirspaceObject iAirspaceObject;

    /**
     * Constructor for Canon. This class will represent the Canon weapon
     * for either the player or the enemy.
     * @param game AirspaceInvadersGame
     * @param iAirspaceObject IAirspaceObject
     */
    public Canon(AirspaceInvadersGame game, IAirspaceObject iAirspaceObject) {
        if (iAirspaceObject instanceof Ground) {
            super.setDamage(20);
        } else {
            super.setDamage(10);
        }
        this.iAirspaceObject = iAirspaceObject;
        this.airspaceInvadersGame = game;
        startTimer();
    }

    /**
     * Method to shoot a Projectile from the type of this object.
     */
    @Override
    public void shoot() {
        int bulletSize;
        if (iAirspaceObject instanceof Ground) {
            bulletSize = 75;
        } else {
            bulletSize = 45;
        }
        Sprite sprite = AssetLoader.getSprite("weapons/Bullet.png", bulletSize);
        new Projectile(this, this.airspaceInvadersGame, sprite, 3);
    }

    /**
     * Getter for the iAirspaceObject in this class.
     * @return iAirspaceObject IAirspaceObject
     */
    @Override
    public IAirspaceObject getIAirspaceObject() {
        return iAirspaceObject;
    }


    /**
     * Method to configure and start the timer which
     * determines the shoot frequency of this weapon type.
     */
    private void startTimer() {
        int timerInterval;

        if (this.getIAirspaceObject() instanceof Player) {
            timerInterval = 350;
        } else {
            timerInterval = 2000;
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                shoot();
            }
        }, 1000, timerInterval);
    }

    /**
     * Method to handle the actions needed before
     * this class can be removed from the Game Engine.
     */
    @Override
    public void destroy() {
        this.timer.cancel();
        timer = null;
    }
}
