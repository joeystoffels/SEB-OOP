package nl.han.ica.airspaceinvaders;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.config.GameProperties;
import nl.han.ica.airspaceinvaders.interfaces.IEnemy;
import nl.han.ica.airspaceinvaders.level.Level;
import nl.han.ica.airspaceinvaders.logger.ConsoleLogHandler;
import nl.han.ica.airspaceinvaders.logger.FileLogHandler;
import nl.han.ica.airspaceinvaders.objects.TextObject;
import nl.han.ica.airspaceinvaders.objects.enemies.Air;
import nl.han.ica.airspaceinvaders.objects.enemies.Ground;
import nl.han.ica.airspaceinvaders.objects.player.Player;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AirspaceInvadersGame extends GameEngine {

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();
    private GameProperties gameProperties = new GameProperties();

    private Player player;
    private List<IEnemy> enemies = new ArrayList<>();

    private TextObject dashboardText = new TextObject("Health: ");

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.airspaceinvaders.AirspaceInvadersGame"});
    }

    @Override
    public void update() {
        // TODO: Needs implementation
    }

    @Override
    public void setupGame() {


        int worldWidth = GameProperties.getValue("worldWidth", true);
        int worldHeight = GameProperties.getValue("worldHeight", true);

        // Enable console and file logging
        logger.addLogHandler(new ConsoleLogHandler());
        logger.addLogHandler(new FileLogHandler("Log.txt"));

        this.player = new Player(this);
        addGameObject(player, worldWidth / 2 - (player.getWidth() / 2), 1200);

        // Add enemies
        enemies.add(new Air(this, AssetLoader.getSprite("enemy/A10.png", 15)));

        for (IEnemy enemy : enemies) {
            if (enemy instanceof Air) {
                addGameObject((Air) enemy, worldWidth / 3, 200);
            }
            if (enemy instanceof Ground) {
                addGameObject((Ground) enemy, worldWidth / 2 - (player.getWidth() / 2), 2000);
            }
        }
        createDashboard(worldWidth, 100);

        createView(worldWidth, worldHeight, worldWidth, worldHeight, 1.0f);

        Level test = new Level();
        tileMap = test.loadLevel("level1.csv");
    }

    /**
     * Creeërt de view met viewport
     *
     * @param worldWidth   Totale breedte van de wereld
     * @param worldHeight  Totale hoogte van de wereld
     * @param screenWidth  Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     * @param zoomFactor   Factor waarmee wordt ingezoomd
     */
    private void createView(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
        View view = new View(worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("test");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    public TextObject getDashboardText() {
        return dashboardText;
    }

    public void setDashboardText(TextObject dashboardText) {
        this.dashboardText = dashboardText;
    }

}
