package nl.han.ica.airspaceinvaders.gameobjects.buttons;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;

public class ButtonImage extends SpriteObject implements IMouseInput {

    private Logger logger = LogFactory.getLogger();

    public ButtonImage(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if(x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
            this.logger.logln(DefaultLogger.LOG_DEBUG, "Clicked button image");
        }
    }
}
