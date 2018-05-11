package nl.han.ica.airspaceinvaders.gameobjects.text;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;

    /**
     * Constructor for TextObject. This class will represent
     * the text on the various game screens.
     *
     * @param text String
     */
    public TextObject(String text) {
        this.text = text;
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
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(30);
        g.text(text, getX(), getY());
    }
}
