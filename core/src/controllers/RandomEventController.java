package controllers;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.pl.firstclicker.FirstClickerGame;

import ui.BasicDialog;

public class RandomEventController {

    public static final int RANDOM_TICK_INTERVAL = 15; //TODO change after initial implementation to higher value

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
    private void triggerDialog(String text){
        BasicDialog basicDialog = new BasicDialog();
        basicDialog.showDialog(stage,text);
    }

    private void addPassiveIncome() {
        game.getScoreService().addPassiveIncome();

        triggerDialog("Your grandma is feeling better today,\n she is making pierogies faster! \n (Passive income +1)");
    }

    private void addMoneyEvent() {
        game.getScoreService().addPoints(200);
        triggerDialog( "You just found some pierogies on the floor! \n(Pierogies +200)");
    }
    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-150);
        triggerDialog( "You fucked up some pierogies! \n(Pierogies -150)");
    }

}
