package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

import java.util.Timer;
import java.util.TimerTask;

public class Canon extends Weapon {

    Timer timer = new Timer();
    private AirspaceInvadersGame airspaceInvadersGame;
    private IAirspaceObject iFlyingObject;

    /**
     *
     */
    public Canon(AirspaceInvadersGame game, IAirspaceObject iFlyingObject) {
        super.setDamage(10);
        super.setIntervalTime(1);
        this.iFlyingObject = iFlyingObject;
        this.airspaceInvadersGame = game;
        startTimer();
    }

    @Override
    public void shoot() {
        Sprite sprite = AssetLoader.getSprite("weapons/Bullet.png", 75);
        new Projectile(this, this.airspaceInvadersGame, sprite, 3);
    }

    @Override
    public IAirspaceObject getIFlyingObject() {
        return iFlyingObject;
    }

    public void stopTimer() {
        this.timer.cancel();
    }

    private void startTimer() {
        int timerInterval;

        if (this.getIFlyingObject() instanceof Player) {
            timerInterval = 400;
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

    @Override
    public void destroy() {
        stopTimer();
    }
}
