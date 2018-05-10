package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;

import java.util.Timer;
import java.util.TimerTask;

public class Canon extends Weapon {

    Timer timer = new Timer();
    private AirspaceInvadersGame airspaceInvadersGame;
    private IFlyingObject iFlyingObject;

    /**
     *
     */
    public Canon(AirspaceInvadersGame game, IFlyingObject iFlyingObject) {
        super.setDamage(10);
        super.setIntervalTime(1);
        this.iFlyingObject = iFlyingObject;
        this.airspaceInvadersGame = game;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                shoot();
            }
        }, 1000, this.getIFlyingObject() instanceof Player ? 400 : 2000);
    }

    @Override
    public void shoot() {
        new Projectile(this, this.airspaceInvadersGame, AssetLoader.getSprite("enemy/A10.png", 3));
    }

    @Override
    public IFlyingObject getIFlyingObject() {
        return iFlyingObject;
    }

    public void stopTimer() {
        this.timer.cancel();
    }

    @Override
    public void destroy() {
        stopTimer();
    }
}
