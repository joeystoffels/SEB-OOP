package nl.han.ica.airspaceinvaders.objects.buttons;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;

public class ButtonImage extends SpriteObject implements IMouseInput {


    public ButtonImage(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if(x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight()) {
            System.out.println("Clicked ButtonImage");
        }
    }
}
