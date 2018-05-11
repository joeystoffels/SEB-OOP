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
    private TextObject dashboardText = new TextObject("Health: " + "\n" + "Score: " + "\n" + "Shield" + "\n" + "Missiles: ");
    private TextObject loadingDashboardText = new TextObject("Loading... ");

    private Player player;
    private AirspaceInvadersGame game;

    private Logger logger = LogFactory.getLogger();



    public GameState(AirspaceInvadersGame game) {
        super(GameProperties.getValueAsInt("worldWidth"), GameProperties.getValueAsInt("worldHeight"));
        this.game = game;
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
            this.game.changeView(new HighScoreState(this.game));
        }
    }

    @Override
    public void start() {
        game.setTileMap(game.getLevel().loadLevel("level1.csv"));
        AudioPlayer soundTrack = game.soundLibrary.loadFile("sounds/SoundTrack1.mp3");

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
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboard.addGameObject(this.dashboardText);
        this.game.addDashboard(dashboard);
    }

    public TextObject getDashboardText() {
        return dashboardText;
    }

    public void setDashboardText(TextObject dashboardText) {
        this.dashboardText = dashboardText;
    }

    private void generateEnemies() {

        int nrEnemies = (int) Math.ceil(Math.random() * 3);
        float xPos = ((float) ((Math.random() * (worldWidth * 0.8)) + (worldWidth * 0.1)));

        for (int x = 0; x < nrEnemies; x++) {
            IAirspaceObject enemy = new Air(this.game, this, AssetLoader.getSprite(this.enemyPlanes[ThreadLocalRandom.current().nextInt(0, this.enemyPlanes.length)], 20));
            enemies.add(enemy);
            this.game.addGameObject((Air) enemy, (float) (xPos + (x * ((Air) enemy).getWidth())), 0);
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
