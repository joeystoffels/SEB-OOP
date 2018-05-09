package nl.han.ica.airspaceinvaders.gameobjects.buttons;


import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.AssetLoader;
import processing.core.PFont;
import processing.core.PGraphics;

public class ButtonText extends TextObject implements IMouseInput {

    protected AirspaceInvadersGame game;
    private PFont font;
    private Logger logger = LogFactory.getLogger();

    /**
     * Create a new TextObject.
     *
     * @param text
     * @param fontSize
     */
    public ButtonText(AirspaceInvadersGame game, String text, int fontSize, int width, int height) {
        super(text, fontSize);
        this.setWidth(width);
        this.setHeight(height);
        this.game = game;
        this.font = this.game.createFont(AssetLoader.getFont("sprayme.ttf"), 30, true);
    }


    @Override
    public void mousePressed(int x, int y, int button) {
        if(x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
            this.logger.logln(DefaultLogger.LOG_DEBUG, "Clicked button text");
            execute();
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.textFont(this.font);
        g.fill(0);
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(getFontSize());
        g.text(getText(),getX(),getY());
    }

    public void execute(){

    }
}
