package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Shelves extends Image {

    private final static int WIDTH = 721;
    private final static int HEIGHT = 156;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 1280-HEIGHT;

    public Shelves(){
        super(prepareTexture());
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);
        this.setPosition(STARTING_X,STARTING_Y);
    }

    private static Texture prepareTexture() {
        Texture shelvesTexture = new Texture("shelves.png");
        return shelvesTexture;
    }
}
