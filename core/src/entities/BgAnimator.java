package entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

public class BgAnimator extends Actor {

    private Animation animation;
    private TextureRegion currentRegion;
    private static float timeController = 1.1f;
    private float time = 0f;

    public BgAnimator(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        time += timeController*delta;

        currentRegion = (TextureRegion) animation.getKeyFrame(time,true);
    }

    @Override
    public void draw(final Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currentRegion, getX(), getY());


    }

    public static void changeSpeed(){
        timeController = 2.5f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                timeController = 1.1f;
            }
        },10f);
    }
}
