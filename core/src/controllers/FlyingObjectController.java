package controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.pl.firstclicker.FirstClickerGame;

import entities.FlyingObject;

public class FlyingObjectController {

    private int spawnTime;


    public FlyingObjectController(FirstClickerGame game, Stage stage) {
        init(game, stage);
    }

    private void init(final FirstClickerGame game,final Stage stage){
        randomizeSpawnTime();

        Timer.schedule(new Task() {
            @Override
            public void run() {

                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        addRandomFlyingObjectToStage(game,stage);
                        randomizeSpawnTime();
                    }
                },spawnTime);
            }
        },0,5);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5,10);
    }
    private void addRandomFlyingObjectToStage(FirstClickerGame game,Stage stage){
        FlyingObject flyingObject = null;

        if(MathUtils.randomBoolean()){
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.FLOUR,game);
        }else{
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.PASSIVE,game);
        }

        stage.addActor(flyingObject);
        flyingObject.fly();
    }

}
