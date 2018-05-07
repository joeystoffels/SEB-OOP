package nl.han.ica.airspaceinvaders.objects.buttons;


import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import processing.core.PGraphics;

public class ButtonText extends TextObject implements IMouseInput {



    /**
     * Create a new TextObject.
     *
     * @param text
     * @param fontSize
     */
    public ButtonText(String text, int fontSize, int width, int height) {
        super(text, fontSize);
        this.setWidth(width);
        this.setHeight(height);
    }


    @Override
    public void mousePressed(int x, int y, int button) {
        if(x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
            System.out.println("  Clicked button text");
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(getFontSize());
        g.text(getText(),getX(),getY());
    }


}
