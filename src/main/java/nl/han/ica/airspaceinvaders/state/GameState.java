package nl.han.ica.airspaceinvaders.state;

import ddf.minim.AudioPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Air;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Ground;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.gameobjects.text.TextObject;
import nl.han.ica.airspaceinvaders.interfaces.IAirspaceObject;
import nl.han.ica.airspaceinvaders.interfaces.IState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameState extends View implements IState {

    public List<IAirspaceObject> enemies = new ArrayList<>();

    private String[] enemyPlanes = {"enemy/F22.png", "enemy/Grippen.png", "enemy/Mig.png"};
    private String[] enemyGround = {"enemy/Tank1.png", "enemy/Tank2.png"};

    private TextObject dashboardText = new TextObject("Health: " + "\n" + "Score: " + "\n" + "Shield" + "\n" + "Missiles: ");
    private TextObject loadingDashboardText = new TextObject("Loading... ");
    private AudioPlayer soundTrack;

    private Player player;
    private AirspaceInvadersGame game;
    private int level;

    private Logger logger = LogFactory.getLogger();

    public GameState(AirspaceInvadersGame game, int level) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
        this.level = level;
    }

    @Override
    public void update() {
        if (this.player != null && this.enemies.isEmpty()) {
            generateEnemies();
        }

        if (this.player != null) {
            this.getDashboardText().setText("Health:" + player.getHealth() + "\n" +
                    "Score: " + player.getScore() + "\n" +
                    "Shield: " + player.getShield() + "\n" +
                    "Missiles: " + player.getMissileAmmo());
        }

        if (player != null && player.getHealth() <= 0) {
            this.logger.logln(DefaultLogger.LOG_DEBUG, "ENDGAME");
            this.game.changeView(new EndGameState(this.game, player));
        }
    }

    /**
     *
     * @param levelNumber int
     */
    public void loadLevel(int levelNumber){
        game.setTileMap(game.getLevel().loadLevel(this.game, levelNumber));
    }

    @Override
    public void start() {
        this.loadLevel(1);
        this.soundTrack = game.soundLibrary.loadFile("sounds/SoundTrack1.mp3");
        soundTrack.play();

        this.player = new Player(this.game);
        this.game.addGameObject(player, worldWidth / 2 - (player.getWidth() / 2), (float) (worldHeight * 0.9));

        createDashboard(worldWidth, 300);
    }

    @Override
    public void reset() {
        this.game.deleteAllDashboards();
        removeGameObjects();
        this.game.deleteAllGameOBjects();
        game.setTileMap(null); // remove level tilemap
        this.soundTrack.pause();
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboard.addGameObject(this.dashboardText);
        this.game.addDashboard(dashboard);
    }

    private TextObject getDashboardText() {
        return dashboardText;
    }

    private void generateEnemies() {

        int maxNrEnemiesAir = (int) Math.ceil(Math.random() * 4);
        int maxNrEnemiesGround = (int) Math.ceil(Math.random() * 2);
        float xPosAir = ((float) ((Math.random() * (worldWidth * 0.8)) + (worldWidth * 0.1)));
        float xPosGround = ((float) ((Math.random() * (worldWidth * 0.8)) + (worldWidth * 0.1)));

        for (int x = 0; x < maxNrEnemiesAir; x++) {
            IAirspaceObject enemyAir = new Air(this.game, this, AssetLoader.getSprite(this.enemyPlanes[ThreadLocalRandom.current().nextInt(0, this.enemyPlanes.length)], 10));
            enemies.add(enemyAir);
            this.game.addGameObject((Air) enemyAir, (xPosAir + (x * ((Air) enemyAir).getWidth())), 0);
        }

        for (int x = 0; x < maxNrEnemiesGround; x++) {
            IAirspaceObject enemyGround = new Ground(this.game, this, AssetLoader.getSprite(this.enemyGround[ThreadLocalRandom.current().nextInt(0, this.enemyGround.length)], 10));
            enemies.add(enemyGround);
            this.game.addGameObject((Ground) enemyGround, xPosGround, 0);
        }
    }

    private void removeGameObjects() {
        ArrayList<GameObject> gameObjectsToRemove = new ArrayList<>();

        for (GameObject gameObject : game.getGameObjectItems()) {
            if (gameObject instanceof IAirspaceObject) {
                gameObjectsToRemove.add(gameObject);
            }
        }

        for (GameObject gameObject : gameObjectsToRemove) {
            ((IAirspaceObject) gameObject).destroy();
        }
    }
}
