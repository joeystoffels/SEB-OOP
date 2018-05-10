package nl.han.ica.airspaceinvaders.gameobjects.weapons;

import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import processing.core.PGraphics;

import java.util.Timer;
import java.util.TimerTask;

public class Canon extends Weapon {

    Timer timer = new Timer();
    private AirspaceInvadersGame world;
    private IFlyingObject iFlyingObject;

    /**
     *
     */
    public Canon(AirspaceInvadersGame world, IFlyingObject iFlyingObject) {
        super.setDamage(10);
        super.setIntervalTime(1);
        this.iFlyingObject = iFlyingObject;
        this.world = world;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                shoot();
            }
        }, 1000, this.getIFlyingObject() instanceof Player ? 250 : 2000);
    }

    /**
     *
     */
    @Override
    public void update() {
    }

    //@Override
    public void shoot() {
        new Projectile(this, this.world, AssetLoader.getSprite("enemy/A10.png", 3), iFlyingObject.getCenterXPos(), iFlyingObject.getCenterYPos(), iFlyingObject.getObjectDirection());
    }

    /**
     * @param g PGraphics object will be given by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {

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
        world.deleteGameObject(this);
    }
}
