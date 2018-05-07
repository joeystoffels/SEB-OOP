package nl.han.ica.airspaceinvaders.state;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import nl.han.ica.airspaceinvaders.interfaces.IFlyingObject;
import nl.han.ica.airspaceinvaders.level.Level;
import nl.han.ica.airspaceinvaders.objects.TextObject;
import nl.han.ica.airspaceinvaders.objects.enemies.Air;
import nl.han.ica.airspaceinvaders.objects.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View implements IState {

    private TextObject dashboardText = new TextObject("Health: ");
    private Player player;
    public List<IFlyingObject> enemies = new ArrayList<>();
    private AirspaceInvadersGame game;


    public GameView(AirspaceInvadersGame game, int worldWidth, int worldHeight) {
        super(worldWidth, worldHeight);
        this.game = game;
    }

    @Override
    public void update() {
        System.out.println("Game view update");
//        if (this.enemies.isEmpty()) {
//            generateEnemies();
//        }
    }


    @Override
    public void start() {
        System.out.println("Game view start");
        this.player = new Player();
        game.addGameObject(player, worldWidth / 2 - (player.getWidth() / 2), 1200);
//
//        createDashboard(worldWidth, 100);

        // Add enemies // TODO move to generateEnemies() ?
//        enemies.add(new Air(this, AssetLoader.getSprite("enemy/A10.png", 15)));
//
//        for (IFlyingObject enemy : enemies) {
//            if (enemy instanceof Air) {
//                addGameObject((Air) enemy, worldWidth / 3, 200);
//            }
//            if (enemy instanceof Ground) {
//                addGameObject((Ground) enemy, worldWidth / 2 - (player.getWidth() / 2), 2000);
//            }
//        }

         Level test = new Level();
         game.setTileMap(test.loadLevel("level1.csv"));


    }

    @Override
    public void reset() {

    }

//    private void createDashboard(int dashboardWidth,int dashboardHeight) {
//        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
//        dashboardText = new TextObject("test");
//        dashboard.addGameObject(dashboardText);
//        addDashboard(dashboard);
//    }
//
//    public TextObject getDashboardText() {
//        return dashboardText;
//    }
//
//    public void setDashboardText(TextObject dashboardText) {
//        this.dashboardText = dashboardText;
//    }
//
//    public void generateEnemies() {
//        int nrEnemies = (int)Math.ceil(Math.random() * 3);
//        float xPos = ((float)((Math.random() * (worldWidth * 0.8)) + (worldWidth * 0.1)));
//
//        for (int x = 0 ; x < nrEnemies ; x++) {
//            Air enemy = new Air(this, AssetLoader.getSprite("enemy/A10.png", 15));
//            enemies.add(enemy);
//            addGameObject((Air) enemy, (float)(xPos + (x * enemy.getWidth())), 200);
//        }
//    }
}
