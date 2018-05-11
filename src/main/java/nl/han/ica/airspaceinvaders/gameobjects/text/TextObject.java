package nl.han.ica.airspaceinvaders.gameobjects.text;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import processing.core.PFont;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;
    private PFont font;
    private AirspaceInvadersGame game;

    /**
     * Constructor for TextObject. This class will represent
     * the text on the various game screens.
     *
     * @param text String
     * @param game AirspaceInvadersGame
     */
    public TextObject(String text, AirspaceInvadersGame game) {
        this.text = text;
        this.game = game;
        this.font = this.game.createFont(AssetLoader.getFont("sprayme.ttf"), 30, true);
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method to update this class. It is automatically called
     * by the Game Engine.
     */
    @Override
    public void update() {
        //no action
    }

    /**
     * Method to draw this TextObject.
     *
     * @param g PGraphics object will be given by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {
        g.fill(0);
        g.textFont(this.font);
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(50);
        g.text(text, getX(), getY());
    }
}
