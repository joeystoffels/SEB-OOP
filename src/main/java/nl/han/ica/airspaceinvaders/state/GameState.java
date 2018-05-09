package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.assets.level.Level;
import nl.han.ica.airspaceinvaders.gameobjects.text.TextObject;
import nl.han.ica.airspaceinvaders.gameobjects.enemies.Air;
import nl.han.ica.airspaceinvaders.gameobjects.player.Player;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.airspaceinvaders.interfaces.IState;

import java.util.ArrayList;
import java.util.List;

public class GameState extends View implements IState {

    public List<IFlyingObject> enemies = new ArrayList<>();

    private TextObject dashboardText = new TextObject("Health: " + "\n" + "Score: ");
    private TextObject loadingDashboardText = new TextObject("Loading ");

    private Player player;
    private AirspaceInvadersGame game;
    private Level level = new Level();

    public GameState(AirspaceInvadersGame game) {
        super(GameProperties.getValue("worldWidth", true), GameProperties.getValue("worldHeight", true));
        this.game = game;
    }

    @Override
    public void update() {

        if (this.player != null && this.enemies.isEmpty()) {
            generateEnemies();
        }

        if(this.player != null) {
            this.getDashboardText().setText("Health:" + player.getHealth() + "\n" + "Score: " + player.getScore());
        }
    }

    @Override
    public void start() {

        this.level.initialize();
        game.setTileMap(level.loadLevel("level1.csv"));

        this.player = new Player(this.game);
        this.game.addGameObject(player, worldWidth / 2 - (player.getWidth() / 2), (float) (worldHeight * 0.9));

        createDashboard(worldWidth, 200);
    }

    @Override
    public void reset() {

    }

//    private Dashboard createLoadingDashboard() {
//        Dashboard dashboard = new Dashboard(0, worldHeight / 2, 200, 200);
//        dashboard.addGameObject(loadingDashboardText);
//        return dashboard;
//    }

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

    public void generateEnemies() {
        int nrEnemies = (int) Math.ceil(Math.random() * 3);
        float xPos = ((float) ((Math.random() * (worldWidth * 0.8)) + (worldWidth * 0.1)));

        for (int x = 0; x < nrEnemies; x++) {
            Air enemy = new Air(this.game, this, AssetLoader.getSprite("enemy/A10.png", 13));
            enemies.add(enemy);
            this.game.addGameObject((Air) enemy, (float) (xPos + (x * enemy.getWidth())), 0); //todo startpositie enemies fixen
        }
    }
}
