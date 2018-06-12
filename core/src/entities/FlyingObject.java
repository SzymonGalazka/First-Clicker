package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.PierogiClicker;

public class FlyingObject extends Image {

    public enum FlyingObjectType{
        PIEROG,FLOUR
    }

    public final static String FLOUR = "flour.png";
    public final static String PIEROG = "pierog.png";

    public static int WIDTH = 80;
    public static int HEIGHT = 80;

    private final static int STARTING_X_1 = 0;
    private final static int STARTING_X_2 = PierogiClicker.WIDTH;
    private final static int STARTING_Y = -100;
    private int startingX;

    private PierogiClicker game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, PierogiClicker game) {
        super(new Texture(getTextureString(type)));

        this.type = type;
        this.game = game;

        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        startingX = MathUtils.randomBoolean() ? STARTING_X_1 : STARTING_X_2;
        this.setPosition(startingX,STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                reactOnClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void reactOnClick() {
        if(FlyingObjectType.PIEROG.equals(type)) game.getScoreService().addPoints(game.getScoreService().getClickvalue()*10);
        else if (FlyingObjectType.FLOUR.equals(type)) game.getScoreService().addPassiveIncome(game.getScoreService().getClickvalue()*5);
        FlyingObject.this.remove();
    }

    private static String getTextureString(FlyingObjectType type) {
        if(FlyingObjectType.FLOUR.equals(type)) return FLOUR;
        else if(FlyingObjectType.PIEROG.equals(type)) return PIEROG;
        return "";
    }

    public void fly(){

        int xSign;
        if(startingX == STARTING_X_1) xSign = 1;
        else xSign = -1;

        int time1 = MathUtils.random(1,6);
        int time2 = MathUtils.random(1,6);

        int randomYDist = MathUtils.random(-200,500);

        Action a = Actions.parallel(
                Actions.moveBy(xSign*600+(MathUtils.random(-300,300)),700+randomYDist,5),
                Actions.rotateBy(360,time1)
        );
        Action b = Actions.parallel(
                Actions.moveBy(xSign*-150+(MathUtils.random(-300,300)),1000+randomYDist,3),
                Actions.rotateBy(360,time2)
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

