package controllers;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.pl.firstclicker.FirstClickerGame;

public class RandomEventController {

    public static final int RANDOM_TICK_INTERVAL = 5; //TODO change after initial implementation to higher value

    private FirstClickerGame game;
    private Stage stage;

    public RandomEventController(FirstClickerGame game, Stage stage){
        this.game = game;
        this.stage = stage;
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
        int randomNumber = MathUtils.random(1,3);
        switch(randomNumber){
            case 1:
                addMoneyEvent();
                break;
            case 2:
                loseMoneyEvent();
                break;
            case 3:
                addPassiveIncome();
                break;
            default:
                break;
        }
    }

    private void addPassiveIncome() {
        game.getScoreService().addPassiveIncome();
    }

    private void addMoneyEvent() {
        game.getScoreService().addPoints(150);
    }
    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-150);
    }

}
