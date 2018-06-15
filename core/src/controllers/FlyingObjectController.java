package controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.pl.firstclicker.PierogiClicker;

import entities.FlyingObject;

public class FlyingObjectController {

    private int spawnTime;


    public FlyingObjectController(PierogiClicker game, Stage stage) {
        init(game, stage);
    }

    private void init(final PierogiClicker game, final Stage stage){
        randomizeSpawnTime();

        Timer.schedule(new Task() {
            @Override
            public void run() {

                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        if(!game.isPaused()) addRandomFlyingObjectToStage(game,stage);
                        randomizeSpawnTime();
                    }
                },spawnTime);
            }
        },0,15);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5,10);
    }
    private void addRandomFlyingObjectToStage(PierogiClicker game, Stage stage){
        FlyingObject flyingObject;

        if(MathUtils.randomBoolean()){
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.FLOUR,game);
        }else{
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.PIEROG,game);
        }

            stage.addActor(flyingObject);
            flyingObject.fly();
    }

}
