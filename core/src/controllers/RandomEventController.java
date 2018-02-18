package controllers;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

public class RandomEventController {

    public static final int RANDOM_TICK_INTERVAL = 5; //TODO change after initial implementation to higher value

    public RandomEventController(){
        init();
    }

    public void init(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(MathUtils.randomBoolean()){
                    triggerRandomEvent();
                }
            }
        },RANDOM_TICK_INTERVAL,RANDOM_TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        System.out.println("Random event triggered!");
    }
}
