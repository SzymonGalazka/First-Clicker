package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.FirstClickerGame;

public class FlyingObject extends Image {

    public enum FlyingObjectType{
        FLOUR,PASSIVE
    }
    public final static String FLOUR = "flour.png";
    public final static String DENARIUS = "denarius.png";

    public static int WIDTH = 150;
    public static int HEIGHT = 150;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = -100;

    private FirstClickerGame game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, FirstClickerGame game) {
        super(new Texture(getTextureString(type)));

        this.type = type;
        this.game = game;

        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);
        this.setPosition(STARTING_X,STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                reactOnClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void reactOnClick() {
        if(FlyingObjectType.FLOUR.equals(type)) game.addPoints(50);
        else if (FlyingObjectType.PASSIVE.equals(type)) game.addPassiveIncome();
        FlyingObject.this.remove();
    }

    private static String getTextureString(FlyingObjectType type) {
        if(FlyingObjectType.FLOUR.equals(type)) return FLOUR;
        else if(FlyingObjectType.PASSIVE.equals(type)) return DENARIUS;
        return "";
    }

    public void fly(){
        Action a = Actions.parallel(
                Actions.moveBy(300,200,5),
                Actions.rotateBy(360,5)
        );
        Action b = Actions.parallel(
                Actions.moveBy(-500,900,3),
                Actions.rotateBy(360,5)
        );
        Action c = Actions.run(new Runnable() {
            @Override
            public void run() {
                FlyingObject.this.remove();
            }
        });
        this.addAction(Actions.sequence(a,b,c));
    }

}

