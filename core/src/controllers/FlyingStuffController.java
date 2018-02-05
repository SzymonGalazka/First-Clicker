package controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.pl.firstclicker.FirstClickerGame;

import entities.FlyingObject;

public class FlyingStuffController {

    private int spawnTime;


    public FlyingStuffController(FirstClickerGame game,Stage stage) {
        init(game, stage);
    }

    private void init(final FirstClickerGame game,final Stage stage){
        randomizeSpawnTime();

        Timer.schedule(new Task() {
            @Override
            public void run() {

                FlyingObject flyingObject = null;

                if(MathUtils.randomBoolean()){
                    flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.FLOUR,game);
                }else{
                    flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.PASSIVE,game);
                }

                stage.addActor(flyingObject);
                flyingObject.fly();

                randomizeSpawnTime();
            }
        },spawnTime,spawnTime);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5,10);
    }

}
