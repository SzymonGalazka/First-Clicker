package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class ClickablePierogi extends Image {

    private final static int WIDTH = 721;
    private final static int HEIGHT = 161;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 310;

    public ClickablePierogi(String name){

        super(prepareTexture(name));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);
        this.setPosition(STARTING_X,STARTING_Y);
    }

    public void reactOnClick(boolean shadow) {
            int yMoveAmmount = MathUtils.random(10, 60);
            float moveActionTime = 0.15f;
            Action moveAction;
        if(!shadow) {
            moveAction = Actions.sequence(
                    Actions.moveBy(0, yMoveAmmount, moveActionTime, Interpolation.circleOut),
                    Actions.moveBy(0, -yMoveAmmount, moveActionTime, Interpolation.circle)
            );
        }else{
            moveAction = Actions.sequence(
                    Actions.scaleBy(0,-0.2f,moveActionTime,Interpolation.circleOut),
                    Actions.scaleBy(0,0.2f,moveActionTime,Interpolation.circle)
            );
        }
        this.addAction(moveAction);
    }

    private static TextureRegion prepareTexture(String name){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ClickablePierogi.atlas"));
        TextureRegion textureRegion = atlas.findRegion(name);
        return textureRegion;
    }
}
