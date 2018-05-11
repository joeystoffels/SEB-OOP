package nl.han.ica.airspaceinvaders.gameobjects.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.enums.PowerUpTypes;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.powerups.PowerUp;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Projectile;
import nl.han.ica.airspaceinvaders.gameobjects.weapons.Weapon;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;

import java.util.List;

class EnemyUtil {

    /**
     * Util method of gameObjectCollisionOccurred that prevents double code
     * from the Enemy-typed objects.
     *
     * @param iAirspaceObject     IAirspaceObject
     * @param collidedGameObjects List<GameObject>
     */
    void gameObjectCollisionOccurredUtil(IAirspaceObject iAirspaceObject, List<GameObject> collidedGameObjects) {
        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Projectile) {
                iAirspaceObject.setHealth(iAirspaceObject.getHealth() - ((Projectile) gameObject).getDamage());
                break; // break out of for loop so it only passes once when collided with multiple projectiles at once
            }
            if (gameObject instanceof Player) {
                iAirspaceObject.setHealth(0);
            }
        }

        if (iAirspaceObject.getHealth() <= 0) {
            iAirspaceObject.createPowerUp();
            iAirspaceObject.destroy();

            for (GameObject gameObject : iAirspaceObject.getAirspaceInvadersGame().getGameObjectItems()) {
                if (gameObject instanceof Player) {
                    ((Player) gameObject).setScore(((Player) gameObject).getScore() + iAirspaceObject.getScore());
                }
            }
        }
    }

    /**
     * Util method of createPowerUp that prevents double code from the Enemy-
     * typed objects.
     *
     * @param iAirspaceObject IAirspaceObject
     * @param weapon          Weapon
     */
    void createPowerUpUtil(IAirspaceObject iAirspaceObject, Weapon weapon) {
        if (Math.random() < GameProperties.getValueAsDouble("powerupchance")) {
            int randomNr = (int) Math.floor(Math.random() * PowerUpTypes.values().length);

            Sprite sprite = AssetLoader.getSprite("powerup/PowerUp.png", 10);
            String randomPowerUp = PowerUpTypes.values()[randomNr].toString();

            iAirspaceObject.getAirspaceInvadersGame().addGameObject(
                    new PowerUp(iAirspaceObject.getAirspaceInvadersGame(),
                            sprite, randomPowerUp), iAirspaceObject.getCenterXPos() - (sprite.getWidth() / 2), iAirspaceObject.getCenterYPos() - (sprite.getHeight() / 2)
            );
        }
    }
}
