package nl.han.ica.airspaceinvaders.objects.weapons;

import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IEnemy;
import nl.han.ica.airspaceinvaders.objects.enemies.Air;
import processing.core.PGraphics;

import java.util.Timer;
import java.util.TimerTask;

public class Canon extends Weapon {

    Timer timer = new Timer();
    private AirspaceInvadersGame world;
    private IEnemy enemy;

    /**
     *
     */
    public Canon(IEnemy enemy) {
        super.setDamage(15);
        super.setIntervalTime(1);
        this.enemy = enemy;
        this.world = ((Air)enemy).getWorld();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                shoot();
            }
        }, 1000, 400);
    }

    /**
     *
     */
    @Override
    public void update() {
    }

    //@Override
    public void shoot() {
        new Projectile(this, this.world, AssetLoader.getSprite("enemy/A10.png", 3), ((Air)enemy).getCenterX(), ((Air)enemy).getCenterY());
    }

    /**
     * @param g PGraphics object will be given by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {

    }
}
